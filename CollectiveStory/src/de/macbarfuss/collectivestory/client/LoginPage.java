package de.macbarfuss.collectivestory.client;

import java.util.Date;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

import de.macbarfuss.collectivestory.shared.LoginData;
import de.macbarfuss.collectivestory.shared.UserSessionInfo;

public final class LoginPage implements EntryPoint {

    private static final String SID = "sid";
    private static final int LEFT_RULER = 153;
    private static final int LINE_HEIGHT = 40;

    // 2 weeks
    private static final long DURATION = 1000 * 60 * 60 * 24 * 14;

    private Button btnLogin;
    private TextBox usernameBox;
    private PasswordTextBox passphraseBox;
    private String sessionID;

    public LoginPage() { }

    @Override
    public void onModuleLoad() {
        sessionID = Cookies.getCookie(SID);
        if (sessionID != null) {
            checkWithServerIfSessionIdIsStillLegal();
        } else {
            buildUI();
        }
    }

    private void checkWithServerIfSessionIdIsStillLegal() {
        LoginService.Util.getInstance().isValidSessionID(sessionID, new AsyncCallback<Boolean>() {

                @Override
                public void onFailure(final Throwable caught) {
                }

                @Override
                public void onSuccess(final Boolean sidIsValid) {
                    if (sidIsValid) {
                        RootPanel.get().clear();
                        final UserSessionInfo userInfo = new UserSessionInfo(sessionID);
                        final Display display = Display.getInstance();
                        display.setSessionInfo(userInfo);
                        display.prepareButtonBar();
                        display.showHome();
                    } else {
                        buildUI();
                    }
                }
            }
        );
    }

    protected void buildUI() {
        int line = 1;

        final RootPanel rootPanel = RootPanel.get();

        final Label lblUsername = new Label("Username");
        rootPanel.add(lblUsername, LEFT_RULER, LINE_HEIGHT * line++);

        usernameBox = new TextBox();
        rootPanel.add(usernameBox, LEFT_RULER, LINE_HEIGHT * line++);

        final Label lblPassword = new Label("Passphrase");
        rootPanel.add(lblPassword, LEFT_RULER, LINE_HEIGHT * line++);

        passphraseBox = new PasswordTextBox();
        rootPanel.add(passphraseBox, LEFT_RULER, LINE_HEIGHT * line++);

        btnLogin = new Button("Login");
        rootPanel.add(btnLogin, LEFT_RULER, LINE_HEIGHT * line++);
        btnLogin.setSize("85px", "25px");
        btnLogin.addClickHandler(new SendLoginDataHandler());
    }

    private class SendLoginDataHandler implements ClickHandler {

        public SendLoginDataHandler() { }

        @Override
        public void onClick(final ClickEvent event) {
            showProcessing(true);
            sendLoginToServer();
        }

        private void showProcessing(final boolean state) {
            btnLogin.setEnabled(!state);
        }

        private void sendLoginToServer() {
            final LoginData data = new LoginData();
            data.setUsername(usernameBox.getText());
            data.setPassphrase(passphraseBox.getText());
            LoginService.Util.getInstance().startSession(data,
                new AsyncCallback<UserSessionInfo>() {

                    @Override
                    public void onFailure(final Throwable caught) {
                        showProcessing(false);
                        // TODO show general error message on screen.
                    }

                    @Override
                    public void onSuccess(final UserSessionInfo result) {
                        showProcessing(false);
                        if (result.hasSessionID()) {
                            final Date expires = new Date(System.currentTimeMillis() + DURATION);
                            Cookies.setCookie(SID, result.getSessionID(), expires);
                            RootPanel.get().clear();
                            final Display display = Display.getInstance();
                            display.setSessionInfo(result);
                            display.prepareButtonBar();
                            display.showHome();
                        }
                    }
                }
            );
        }
    }
}
