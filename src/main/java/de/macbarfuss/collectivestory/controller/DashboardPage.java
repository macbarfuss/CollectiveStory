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
@RequestMapping("/dashboard")
public final class DashboardPage extends AbstractPage {

    private static final Logger LOG = LoggerFactory.getLogger(DashboardPage.class);
    private static final String BASENAME = "dashboard";

    public DashboardPage() {
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getHome(final Model model) {
        LOG.info("GET /dashboard");
        registerModel(model);
        return new ModelAndView(BASENAME);
    }
}
