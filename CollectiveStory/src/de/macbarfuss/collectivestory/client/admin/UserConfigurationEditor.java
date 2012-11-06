package de.macbarfuss.collectivestory.client.admin;

import java.awt.Checkbox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;

import de.macbarfuss.collectivestory.shared.UserConfiguration;

public final class UserConfigurationEditor implements Editor<UserConfiguration> {

    interface MyUiBinder extends UiBinder<Widget, UserConfigurationEditor> {
    }

    private static MyUiBinder binder = GWT.create(MyUiBinder.class);

    @UiField Checkbox isAdmin;

    public UserConfigurationEditor() {
        binder.createAndBindUi(this);
    }
}
