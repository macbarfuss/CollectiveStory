package de.macbarfuss.collectivestory.controller;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public abstract class AbstractPage {

    private Model model;

    public AbstractPage() {
    }

    public void registerModel(final Model newModel) {
        model = newModel;
    }

    public Model getModel() {
        return model;
    }

    public ModelAndView getViewWithModel(final String viewName) {
        final ModelAndView result = new ModelAndView(viewName);
        result.addAllObjects(model.asMap());
        return result;
    }

    public void setError(final String errorMsg) {
        model.addAttribute("errorMessage", errorMsg);
    }

    public void setSuccess(final String successMsg) {
        model.addAttribute("successMessage", successMsg);
    }

    public void setWarning(final String warnMsg) {
        model.addAttribute("warnMessage", warnMsg);
    }

    public void setInfo(final String infoMsg) {
        model.addAttribute("infoMessage", infoMsg);
    }
}
