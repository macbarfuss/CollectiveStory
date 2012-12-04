package de.macbarfuss.collectivestory.client.projects;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProjectServiceAsync {

    void addProject(String sid, String projectname, AsyncCallback<Boolean> callback);
}
