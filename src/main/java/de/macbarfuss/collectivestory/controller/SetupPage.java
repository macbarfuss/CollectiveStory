package de.macbarfuss.collectivestory.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.macbarfuss.collectivestory.service.AuthenticationService;

@Controller
@RequestMapping("/setup")
public class SetupPage extends AbstractPage {

    private static final Logger LOG = LoggerFactory.getLogger(SetupPage.class);
    private static final String BASENAME = "setup";

    @Autowired
    private AuthenticationService authenticationService;

    public SetupPage() {}

    @RequestMapping(method = RequestMethod.GET)
    public String show(final Model model) {
        if (authenticationService.isInitialized()) {
            return "redirect:/home";
        } else {
            LOG.info("the setup-page was displayed.");
            return BASENAME;
        }
    }

    @RequestMapping(method = RequestMethod.POST, params = { "email" })
    public String addDefaultAdmin(final String email) {
        authenticationService.addFirstUser(email);
        return "login";
    }
}
