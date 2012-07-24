package de.macbarfuss.collectivestory.client;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.macbarfuss.collectivestory.shared.UserSessionInfo;

public final class Display {

    private static final int BUTTON_SIZE = 10;

    private static Display singleton;

    private final Button logoutBtn = new Button("Logout");

    private Panel contentPanel = new FlowPanel();

    private final VerticalPanel vPanel = new VerticalPanel();
    private final DockLayoutPanel buttonBar = new DockLayoutPanel(Unit.EM);

    private final Button homeButton = new Button("Home");

    private UserSessionInfo sessionInfo;

    private Display() {
        vPanel.setBorderWidth(1);
        vPanel.setWidth("100%");
        RootPanel.get().add(vPanel);
    }

    public static Display getInstance() {
        if (singleton == null) {
            singleton = new Display();
        }
        return singleton;
    }

    // U I   P R E P A R A T I O N
    public void prepareButtonBar() {
        buttonBar.setHeight("50px");
        vPanel.add(buttonBar);

        buttonBar.addWest(homeButton, BUTTON_SIZE);
        homeButton.addClickHandler(new SwitchMaskHandler(new WelcomeMask()));

        final Button listProjectsButton = new Button("Projects");
        buttonBar.addWest(listProjectsButton, BUTTON_SIZE);
        listProjectsButton.addClickHandler(new SwitchMaskHandler(new ProjectsMask()));

        final Button adminButton = new Button("Administration");
        buttonBar.addWest(adminButton, BUTTON_SIZE);
        adminButton.addClickHandler(new SwitchMaskHandler(new AdminMask()));

        prepareLogoutButton();

        final Label userName = new Label("eingeloggt als: " + sessionInfo.getUsername());
        buttonBar.addEast(userName, BUTTON_SIZE);
    }

    private void prepareLogoutButton() {
        buttonBar.addEast(logoutBtn, BUTTON_SIZE);
        logoutBtn.addClickHandler(new LogoutHandler());
    }

// G E T T E R S
    public Panel getContentPanel() {
        return contentPanel;
    }

    public UserSessionInfo getSessionInfo() {
        return sessionInfo;
    }

// S E T T E R S
    public void setContentPanel(final Panel newContentPanel) {
        contentPanel.removeFromParent();
        contentPanel = newContentPanel;
        vPanel.add(newContentPanel);
    }

    public void setSessionInfo(final UserSessionInfo theSession) {
        sessionInfo = theSession;
    }

// H A N D L E R S
    public void showHome() {
        homeButton.click();
    }

    public void show(final AbstractMask newScreen) {
        setContentPanel(newScreen.getContentPanel());
    }
}
