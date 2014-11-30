package de.macbarfuss.collectivestory.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.macbarfuss.collectivestory.model.User;
import de.macbarfuss.collectivestory.model.dto.UserDto;
import de.macbarfuss.collectivestory.model.repository.UserRepository;

/**
 * Service for encapsulating operations on User objects.
 * 
 * Outside this service only instances of UserDto are allowed for security
 * reasons.
 * 
 * @author macbarfuss
 * 
 */
@Service
public final class UserDtoService {

    @Autowired
    private UserRepository userRepsitory;

    public UserDtoService() {
    }

    public List<UserDto> findAll() {
        final List<UserDto> result = new ArrayList<UserDto>();
        for (User user : userRepsitory.findAll()) {
            result.add(user.getDto());
        }
        return result;
    }

    public UserDto findByUsername(final String username) {
        final User user = userRepsitory.findByUsername(username);
        if (user != null) {
            return user.getDto();
        } else {
            return null;
        }
    }

    public boolean existingUser(final UserDto user) {
        return userRepsitory.findByUsername(user.getUsername()) != null;
    }

    public void delete(final UserDto user) {
        if (existingUser(user)) {
            final User trash = userRepsitory.findByUsername(user.getUsername());
            userRepsitory.delete(trash);
        }
    }

    public void addUser(final UserDto user) {
        final User newUser = new User(user.getUsername());
        newUser.setEmail(user.getEmail());
        userRepsitory.save(newUser);
    }
}
