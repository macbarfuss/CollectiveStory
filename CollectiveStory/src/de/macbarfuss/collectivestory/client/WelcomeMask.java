package de.macbarfuss.collectivestory.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import de.macbarfuss.collectivestory.shared.UserSessionInfo;

public final class WelcomeMask extends Composite {

    interface MyUiBinder extends UiBinder<Widget, WelcomeMask> {
    }

    private static MyUiBinder binder = GWT.create(MyUiBinder.class);

    @UiField
    Label sessionID;

    public WelcomeMask() {
        final Display display = Display.getInstance();
        initWidget(binder.createAndBindUi(this));
        final UserSessionInfo usi = display.getSessionInfo();
        sessionID.setText(usi.getSessionID());
    }
}
