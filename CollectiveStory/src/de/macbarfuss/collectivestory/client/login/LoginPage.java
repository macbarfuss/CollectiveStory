package de.macbarfuss.collectivestory.client.login;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

import de.macbarfuss.collectivestory.client.Display;
import de.macbarfuss.collectivestory.shared.UserSessionInfo;

public final class LoginPage implements EntryPoint {

    private static final String SID = "sid";

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
                        final UserSessionInfo sessionInfo = new UserSessionInfo(sessionID);
                        Display.init(sessionInfo);
                    } else {
                        buildUI();
                    }
                }
            }
        );
    }

    protected void buildUI() {
        RootPanel.get().add(new LoginComposite());
    }
}
