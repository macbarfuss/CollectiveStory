package de.macbarfuss.collectivestory.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Sample controller for going to the home page with a message.
 */
@Controller
@RequestMapping({ "/home", "/" })
public final class HomePage extends AbstractPage {

    private static final Logger LOG = LoggerFactory.getLogger(HomePage.class);
    private static final String BASENAME = "home";

    public HomePage() {
    }

    /**
     * Selects the home page and populates the model with a message.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getHome(final Model model) {
        registerModel(model);
        LOG.info("Welcome home!");
        return new ModelAndView(BASENAME);
    }
}
