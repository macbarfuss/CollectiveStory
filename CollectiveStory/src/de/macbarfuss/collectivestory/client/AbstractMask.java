package de.macbarfuss.collectivestory.client;

import com.google.gwt.user.client.ui.Panel;

public abstract class AbstractMask {

// C O N S T R U C T O R

    public AbstractMask() {
        buildUI();
    }

// A B S T R A C T   B E H A V I O U R
    protected abstract void buildUI();

    public abstract Panel getContentPanel();


// H A N D L E R   C L A S S E S

    public final void show() {
        Display.getInstance().show(this);
    }

}
