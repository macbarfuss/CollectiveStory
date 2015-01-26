package de.macbarfuss.collectivestory.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/story")
public final class StoriesPage extends AbstractPage {

    private static final Logger LOG = LoggerFactory.getLogger(StoriesPage.class);
    private static final String BASENAME = "stories";

    public StoriesPage() {
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView getHome(final Model model) {
        LOG.info("GET /stories");
        registerModel(model);
        return new ModelAndView(BASENAME);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView getNewStoryForm(final Model model) {
        registerModel(model);
        return new ModelAndView("addStory");
    }
}
