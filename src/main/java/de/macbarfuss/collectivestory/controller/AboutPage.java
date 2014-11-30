package de.macbarfuss.collectivestory.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * A simple page showing information about the software.
 * 
 * @author geoema
 * 
 */
@Controller
@RequestMapping("/about")
public final class AboutPage {

    private static final Logger LOG = LoggerFactory.getLogger(AboutPage.class);

    public AboutPage() {
    }

    /**
     * Selects the about page and populates.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView show() {
        LOG.debug("GET /about");
        return new ModelAndView("about");
    }

}
