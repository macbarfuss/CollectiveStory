package de.macbarfuss.collectivestory.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public final class MessageBar extends Composite {

    interface MyUiBinder extends UiBinder<Widget, MessageBar> {
    }

    private static MyUiBinder binder = GWT.create(MyUiBinder.class);

    @UiField
    Label messageLabel;

    public MessageBar() {
        initWidget(binder.createAndBindUi(this));
    }

    public void clear() {
        messageLabel.setText("");
    }

    public void setMessage(final String message) {
        messageLabel.setText(message);
    }

    public void setMessageType(final MessageType type) {
        switch (type) {
            case ERROR:
            case WARNING:
            default:
                break;
        }
    }
}
