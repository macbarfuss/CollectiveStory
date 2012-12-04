package de.macbarfuss.collectivestory.client.login;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import de.macbarfuss.collectivestory.client.Display;
import de.macbarfuss.collectivestory.shared.LoginData;
import de.macbarfuss.collectivestory.shared.UserSessionInfo;

public final class LoginComposite extends Composite {

    private static final String SID = "sid";

    // 2 weeks
    private static final long DURATION = 1000 * 60 * 60 * 24 * 14;

    interface MyUiBinder extends UiBinder<Widget, LoginComposite> {
    }

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField Button btnLogin;
    @UiField Label message;
    @UiField PasswordTextBox passphraseBox;
    @UiField TextBox usernameBox;

    public LoginComposite() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @UiHandler("btnLogin")
    void handleClick(final ClickEvent e) {
        message.setText("");
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
        LoginService.Util.getInstance().startSession(data, new StartSessionCallback());
    }

    private final class StartSessionCallback implements AsyncCallback<UserSessionInfo> {

        public StartSessionCallback() {
        }

        @Override
        public void onFailure(final Throwable caught) {
            showProcessing(false);
            message.setText(caught.getMessage());
        }

        @Override
        public void onSuccess(final UserSessionInfo result) {
            showProcessing(false);
            if (result != null && result.hasSessionID()) {
                final Date expires = new Date(System.currentTimeMillis() + DURATION);
                Cookies.setCookie(SID, result.getSessionID(), expires);
                Display.init(result);
            } else {
                message.setText("Username and/or password are not valid.");
            }
            // TODO else show error
        }
    }
}
