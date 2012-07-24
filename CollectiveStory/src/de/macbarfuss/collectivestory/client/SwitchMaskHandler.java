package de.macbarfuss.collectivestory.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public final class SwitchMaskHandler implements ClickHandler {

    private AbstractMask targetMask;

    public SwitchMaskHandler(final AbstractMask newMask) {
        targetMask = newMask;
    }

    public void onClick(final ClickEvent event) {
        targetMask.show();
    }
}
