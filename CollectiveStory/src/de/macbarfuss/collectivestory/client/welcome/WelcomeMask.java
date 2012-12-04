package de.macbarfuss.collectivestory.client.welcome;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public final class WelcomeMask extends Composite {

    interface MyUiBinder extends UiBinder<Widget, WelcomeMask> {
    }

    private static MyUiBinder binder = GWT.create(MyUiBinder.class);

    public WelcomeMask() {
        initWidget(binder.createAndBindUi(this));
    }
}
