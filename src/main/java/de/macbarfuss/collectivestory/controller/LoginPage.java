package de.macbarfuss.collectivestory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.macbarfuss.collectivestory.service.AuthenticationService;

@Controller
@RequestMapping("/login")
public class LoginPage extends AbstractPage {

// private static final Logger LOG = LoggerFactory.getLogger(LoginPage.class);
    private static final String BASENAME = "login";

    @Autowired
    private AuthenticationService authenticationService;

    public LoginPage() {}

    @RequestMapping(method = RequestMethod.GET)
    public String show(final Model model) {
        if (authenticationService.isInitialized()) {
            return BASENAME;
        } else {
            return "redirect:/setup";
        }
    }
}
