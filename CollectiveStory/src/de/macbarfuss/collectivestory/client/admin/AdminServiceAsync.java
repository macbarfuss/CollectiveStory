package de.macbarfuss.collectivestory.client.admin;

import java.util.Set;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface AdminServiceAsync {

    void addUser(String nickname, String password, AsyncCallback<Void> callback);

    void getAllUser(AsyncCallback<Set<String>> asyncCallback);
}
