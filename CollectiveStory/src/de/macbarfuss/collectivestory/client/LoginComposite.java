package de.macbarfuss.collectivestory.client;

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
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import de.macbarfuss.collectivestory.shared.LoginData;
import de.macbarfuss.collectivestory.shared.UserSessionInfo;

public class LoginComposite extends Composite {

    private static final String SID = "sid";

    // 2 weeks
    private static final long DURATION = 1000 * 60 * 60 * 24 * 14;

	interface MyUiBinder extends UiBinder<Widget, LoginComposite> {
	}

	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    @UiField Button btnLogin;
    @UiField TextBox usernameBox;
    @UiField PasswordTextBox passphraseBox;

	public LoginComposite() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiHandler("btnLogin")
	void handleClick(ClickEvent e) {
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
                        display.show(new WelcomeMask());
                    }
                }
            }
        );
    }
}
