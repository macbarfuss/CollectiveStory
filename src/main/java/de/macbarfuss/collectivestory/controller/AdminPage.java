package de.macbarfuss.collectivestory.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import de.macbarfuss.collectivestory.model.User;
import de.macbarfuss.collectivestory.model.dto.UserDto;
import de.macbarfuss.collectivestory.model.repository.UserRepository;
import de.macbarfuss.collectivestory.service.AuthenticationService;
import de.macbarfuss.collectivestory.service.MailService;
import de.macbarfuss.collectivestory.service.UserDtoService;

@Controller
@RequestMapping("/admin")
// @PreAuthorize("hasRole('ADMIN')")
@Secured("ROLE_ADMIN")
// @RolesAllowed("ADMIN")
public class AdminPage extends AbstractPage {

    private static final Logger LOG = LoggerFactory.getLogger(AdminPage.class);
    private static final String BASENAME = "admin";

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserDtoService userDtoService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailService mailService;

    public AdminPage() {
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView show(final Model model) {
        registerModel(model);
        LOG.info("Welcome admin!");
        model.addAttribute("newUser", new UserDto());
        model.addAttribute("users", userDtoService.findAll());
        return getViewWithModel(BASENAME);
    }

    @RequestMapping(method = RequestMethod.POST, params = { "addUser" })
    public ModelAndView addUser(final UserDto user, final Model model) {
        registerModel(model);
        if (userDtoService.existingUser(user)) {
            final String errorMsg = "A user with username " + user.getUsername() + " already exists.";
            LOG.info(errorMsg);
            setError(errorMsg);
        } else {
            authenticationService.createUser(user);
            setSuccess("The user " + user.getUsername() + " was successfully created.");
        }
        return show(model);
    }

    @RequestMapping(method = RequestMethod.POST, params = { "deleteUser" })
    public ModelAndView deleteUser(@RequestParam("deleteUser") final String username, final Model model) {
        registerModel(model);
        LOG.info("POST /admin - deleteUser: " + username);
        userDtoService.delete(userDtoService.findByUsername(username));
        return show(model);
    }

    @RequestMapping(method = RequestMethod.POST, params = { "resetPassword" })
    public ModelAndView resetPassword(@RequestParam("resetPassword") final String username, final Model model) {
        registerModel(model);
        LOG.info("POST /admin - resetPassword: " + username);
        final User user = userRepository.findByUsername(username);
        authenticationService.createAndSaveAndSendNewPassword(user);
        return show(model);
    }

    @RequestMapping(method = RequestMethod.POST, params = { "testMail" })
    public ModelAndView testMail(final Model model) {
        registerModel(model);
        LOG.info("POST /admin - testMail");
        mailService.sendPing();
        return show(model);
    }

}
