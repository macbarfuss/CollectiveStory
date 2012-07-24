package de.macbarfuss.collectivestory.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.macbarfuss.collectivestory.shared.LoginData;
import de.macbarfuss.collectivestory.shared.UserSessionInfo;

public interface LoginServiceAsync {

    void startSession(
            LoginData data,
            AsyncCallback<UserSessionInfo> callback);

    void isValidSessionID(
            String sessionID,
            AsyncCallback<Boolean> callback);

    void closeSession(
            String sessionID,
            AsyncCallback<UserSessionInfo> asyncCallback);

    void getSessions(
            AsyncCallback<Iterable<UserSessionInfo>> asyncCallback);
}
