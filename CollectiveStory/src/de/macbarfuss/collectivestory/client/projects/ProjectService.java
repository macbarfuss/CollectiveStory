package de.macbarfuss.collectivestory.client.projects;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("ProjectService")
public interface ProjectService extends RemoteService {

    public static class Util {

        private static ProjectServiceAsync instance;

        public Util() { }

        public static ProjectServiceAsync getInstance() {
            if (instance == null) {
                instance = GWT.create(ProjectService.class);
            }
            return instance;
        }
    }

    Boolean addProject(String sid, String projectname);
}
