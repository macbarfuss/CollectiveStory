package de.macbarfuss.collectivestory.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import de.macbarfuss.collectivestory.model.User;
import de.macbarfuss.collectivestory.model.dto.UserDto;
import de.macbarfuss.collectivestory.service.AuthenticationService;
import de.macbarfuss.collectivestory.service.AuthorizationService;
import de.macbarfuss.collectivestory.service.UserDtoService;

/**
 * Sample controller for going to the home page with a message.
 */
@Controller
@RequestMapping("/user/settings")
public final class UserSettingsPage extends AbstractPage {

    private static final Logger LOG = LoggerFactory.getLogger(UserSettingsPage.class);
    private static final String BASENAME = "usersettings";

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private UserDtoService userDtoService;

// @Autowired
// private UserRepository userRepository;

// @Autowired
// private MailService mailService;

    public UserSettingsPage() {
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView show(@RequestParam("username") final String username, final Model model) {
        registerModel(model);
        final String targetName = username;
        final UserDto target;
        final User currentUser = authenticationService.getCurrentUser();

        if (authorizationService.canEditUser(username)) {
            LOG.debug("Granted edit access on user " + username + " for user " + currentUser.getUsername() + ".");
            target = userDtoService.findByUsername(targetName);
        } else {
            LOG.info("A user without sufficent rights tried to edit a user.");
            target = currentUser.getDto();
        }

        model.addAttribute("user", target);

        return getViewWithModel(BASENAME);
    }

// @RequestMapping(method = RequestMethod.POST, params = { "addUser" })
// public ModelAndView addUser(final UserDto user, final Model model) {
// registerModel(model);
// if (userDtoService.existingUser(user)) {
// final String errorMsg = "A user with username " + user.getUsername() +
// " already exists.";
// LOG.info(errorMsg);
// setError(errorMsg);
// } else {
// userDtoService.addUser(user);
// setSuccess("The user " + user.getUsername() + " was successfully created.");
// }
// return show(model);
// }
//
// @RequestMapping(method = RequestMethod.POST, params = { "deleteUser" })
// public ModelAndView deleteUser(@RequestParam("deleteUser") final String
// username, final Model model) {
// registerModel(model);
// LOG.info("POST /admin - deleteUser: " + username);
// userDtoService.delete(userDtoService.findByUsername(username));
// return show(model);
// }
//
// @RequestMapping(method = RequestMethod.POST, params = { "resetPassword" })
// public ModelAndView resetPassword(@RequestParam("resetPassword") final String
// username, final Model model) {
// registerModel(model);
// LOG.info("POST /admin - resetPassword: " + username);
// final User user = userRepository.findByUsername(username);
// authenticationService.createAndSaveAndSendNewPassword(user);
// return show(model);
// }
//
// @RequestMapping(method = RequestMethod.POST, params = { "testMail" })
// public ModelAndView testMail(final Model model) {
// registerModel(model);
// LOG.info("POST /admin - testMail");
// mailService.sendPing();
// return show(model);
// }

}
