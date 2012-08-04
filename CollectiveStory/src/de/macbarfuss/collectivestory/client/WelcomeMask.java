package de.macbarfuss.collectivestory.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public final class WelcomeMask extends Composite {

	interface MyUiBinder extends UiBinder<Widget, WelcomeMask> { }

	private static MyUiBinder binder = GWT.create(MyUiBinder.class);

	@UiField Label sessionID;

    public WelcomeMask() {
		initWidget(binder.createAndBindUi(this));
		sessionID.setText(Display.getInstance().getSessionInfo().getSessionID());
    }
}
