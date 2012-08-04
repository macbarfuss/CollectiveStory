package de.macbarfuss.collectivestory.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;

import de.macbarfuss.collectivestory.shared.UserSessionInfo;

public final class LogoutHandler implements ClickHandler {

    private Button logoutBtn;

    public LogoutHandler() { }

    public void onClick(final ClickEvent event) {
        logoutBtn = (Button) event.getSource();
        showProcessing(true);
        logout();
    }

    private void showProcessing(final boolean state) {
        // TODO add modal dialog with progress bar
        logoutBtn.setEnabled(!state);
    }

    private void logout() {
        LoginService.Util.getInstance().closeSession(
                Display.getInstance().getSessionInfo().getSessionID(),
                new AsyncCallback<UserSessionInfo>() {

                    @Override
                    public void onFailure(final Throwable caught) {
                        showProcessing(false);
                        // TODO show general error message on screen.
                    }

                    @Override
                    public void onSuccess(final UserSessionInfo result) {
                        showProcessing(false);
                        Display.getInstance().setSessionInfo(null);
                        Cookies.removeCookie("sid");
                        RootPanel.get().clear();
                        Display.markOnLoginPage();
                        new LoginPage().onModuleLoad();
                    }
                }
        );
    }
}
