package de.macbarfuss.collectivestory.client;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;

public final class AdminMask extends AbstractMask {

    private Panel contentPanel;

    public AdminMask() { }

    @Override
    protected void buildUI() {
        contentPanel = new FlowPanel();
        contentPanel.add(new HTMLPanel("<h1>Administration</h1>"));
        contentPanel.add(new Label("Users:"));
    }

    @Override
    public Panel getContentPanel() {
        return contentPanel;
    }
}
