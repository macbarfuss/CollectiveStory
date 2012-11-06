package de.macbarfuss.collectivestory.client.admin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;

import de.macbarfuss.collectivestory.shared.UserConfiguration;

public final class EditUserConfigurationWorkflow {

    interface Driver extends SimpleBeanEditorDriver<UserConfiguration, UserConfigurationEditor> { }

    Driver driver = GWT.create(Driver.class);

    public EditUserConfigurationWorkflow() { }

    void edit(final UserConfiguration config) {

    }

    void save() {

    }
}
