package de.macbarfuss.collectivestory.client.admin;

import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("AdminService")
public interface AdminService extends RemoteService {

    public static class Util {

        private static AdminServiceAsync instance;

        public Util() { }

        public static AdminServiceAsync getInstance() {
            if (instance == null) {
                instance = GWT.create(AdminService.class);
            }
            return instance;
        }
    }

    void addUser(String nickname, String password);

    Set<String> getAllUser();
}
