package de.macbarfuss.collectivestory.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public final class AdminMask extends Composite {

	interface MyUiBinder extends UiBinder<Widget, AdminMask> { }

	private static MyUiBinder binder = GWT.create(MyUiBinder.class);

    public AdminMask() {
		initWidget(binder.createAndBindUi(this));
    }
}
