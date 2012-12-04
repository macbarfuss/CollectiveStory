package de.macbarfuss.collectivestory.client;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.macbarfuss.collectivestory.client.welcome.WelcomeMask;
import de.macbarfuss.collectivestory.shared.UserSessionInfo;

public final class Display {

    private static Display singleton;
    private static boolean onLoginPage = true;

    private Widget content = new FlowPanel();
    private MessageBar messageBar = new MessageBar();

    private final VerticalPanel vPanel = new VerticalPanel();

    private UserSessionInfo usi;

    private Display() {
        onLoginPage = false;
        RootPanel.get().clear();
        vPanel.setBorderWidth(1);
        vPanel.setWidth("100%");
        RootPanel.get().add(vPanel);
        vPanel.add(new MenuBar());
        vPanel.add(messageBar);
    }

    public static void init(final UserSessionInfo sessionInfo) {
        singleton = new Display();
        singleton.setSessionInfo(sessionInfo);
        singleton.show(new WelcomeMask());
    }

    public static Display getInstance() {
        if (singleton == null || onLoginPage) {
            singleton = new Display();
        }
        return singleton;
    }

    // G E T T E R S
    public UserSessionInfo getSessionInfo() {
        return usi;
    }

    // S E T T E R S
    public void setSessionInfo(final UserSessionInfo theSession) {
        usi = theSession;
    }

    public static void markOnLoginPage() {
        onLoginPage = true;
    }

    public void setMessage(final String message) {
        messageBar.setMessage(message);
    }

    public void setMessageType(final MessageType type) {
        messageBar.setMessageType(type);
    }

    // H A N D L E R S
    public void show(final Widget newScreen) {
        content.removeFromParent();
        content = newScreen;
        vPanel.add(content);
    }
}
