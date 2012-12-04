package de.macbarfuss.collectivestory.client.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.macbarfuss.collectivestory.shared.LoginData;
import de.macbarfuss.collectivestory.shared.UserSessionInfo;

@RemoteServiceRelativePath("LoginService")
public interface LoginService extends RemoteService {

    public static class Util {

        private static LoginServiceAsync instance;

        public Util() { }

        public static LoginServiceAsync getInstance() {
            if (instance == null) {
                instance = GWT.create(LoginService.class);
            }
            return instance;
        }
    }

    UserSessionInfo startSession(LoginData data);

    Boolean isValidSessionID(String sessionID);

    UserSessionInfo closeSession(String sessionID);

    Iterable<UserSessionInfo> getSessions();
}
