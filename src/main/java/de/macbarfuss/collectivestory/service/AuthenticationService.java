package de.macbarfuss.collectivestory.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import de.macbarfuss.collectivestory.model.Authority;
import de.macbarfuss.collectivestory.model.User;
import de.macbarfuss.collectivestory.model.dto.UserDto;
import de.macbarfuss.collectivestory.model.repository.UserRepository;

@Service
public final class AuthenticationService implements UserDetailsManager {

    private static final String EMPTY_STRING = "";

    private static final String DEFAULT_ADMIN_USERNAME = "admin";

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailService mailService;

    public AuthenticationService() {}

    @Override
    public UserDetails loadUserByUsername(final String username) {
        final User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("The user with name \"" + username
                    + "\" could not be found in the database.");
        }
        return user;
    }

    @Override
    public void createUser(final UserDetails userDetails) {
        final User user = (User) userDetails;
        userRepository.save(user);
    }

    public boolean createUser(final User user) {
        String plainPassword = user.getPassword();
        if (plainPassword == null || plainPassword.equals(EMPTY_STRING)) {
            plainPassword = getNewRandomPassword();
        }
        final String encryptedPassword = passwordEncoder.encode(plainPassword);
        user.setPassword(encryptedPassword);

        // TODO: needs prechecks if creating is possible or must do a delete
        // when mailing fails.
        Boolean sendMailSuccess = mailService.notifyNewUser(user.getDto(), plainPassword);
        if (sendMailSuccess) {
            createUser((UserDetails) user);
            LOG.debug("created new user: " + user.getUsername());
            return true;
        } else {
            LOG.error("Could not create new user " + user.getUsername()
                    + " because mailService was not able to send email.");
            return false;
        }
    }

    public void createUser(final UserDto userDto) {
        final User user = new User(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setEnabled(true);
        user.getPassword();
        createUser(user);
    }

    @Override
    public void updateUser(final UserDetails user) {
        userRepository.save((User) user);
    }

    @Override
    public void deleteUser(final String username) {
        final User user = userRepository.findByUsername(username);
        if (user != null) {
            userRepository.delete(user);
        }
    }

    @Override
    public void changePassword(final String oldPassword, final String newPassword) {
        // TODO Auto-generated method stub
    }

    public void createAndSaveAndSendNewPassword(final User user) {
        final String newPlainPassword = getNewRandomPassword();
        final String encryptedPassword = passwordEncoder.encode(newPlainPassword);
        user.setPassword(encryptedPassword);
        updateUser(user);
        mailService.sendNewPassword(user.getDto(), newPlainPassword);
    }

    private String getNewRandomPassword() {
        return RandomStringUtils.randomAlphanumeric(8);
    }

    @Override
    public boolean userExists(final String username) {
        final User user = userRepository.findByUsername(username);
        return user != null;
    }

    public void addFirstUser(final String email) {
        if (noUserExists()) {
            final User adminUser = new User(DEFAULT_ADMIN_USERNAME);
            adminUser.addAuthority(Authority.ROLE_ADMIN);
            adminUser.setPassword(getNewRandomPassword());
            adminUser.setEmail(email);
            boolean success = createUser(adminUser);
            if (success) {
                LOG.info("Default admin user created!");
            }
        }
    }

    private boolean noUserExists() {
        return userRepository.count() == 0;
    }

    public User getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        return (User) authentication.getPrincipal();
    }

    public boolean isInitialized() {
        return userRepository.count() > 0;
    }
}
