package de.macbarfuss.collectivestory.client;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.macbarfuss.collectivestory.shared.UserSessionInfo;

public final class Display {

    private static Display singleton;
    private static boolean onLoginPage = true;

    private Widget content = new FlowPanel();

    private final VerticalPanel vPanel = new VerticalPanel();

    private UserSessionInfo sessionInfo;

    private Display() {
    	onLoginPage = false;
        vPanel.setBorderWidth(1);
        vPanel.setWidth("100%");
        RootPanel.get().add(vPanel);
        vPanel.add(new MenuBar());
    }

    public static Display getInstance() {
        if (singleton == null || onLoginPage) {
            singleton = new Display();
        }
        return singleton;
    }

// G E T T E R S
    public UserSessionInfo getSessionInfo() {
        return sessionInfo;
    }

// S E T T E R S
    public void setSessionInfo(final UserSessionInfo theSession) {
        sessionInfo = theSession;
    }

    public static void markOnLoginPage() {
    	onLoginPage = true;
    }

// H A N D L E R S
    public void show(final Widget newScreen) {
        content.removeFromParent();
        content = newScreen;
        vPanel.add(content);
    }
}
