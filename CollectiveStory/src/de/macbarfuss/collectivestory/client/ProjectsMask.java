package de.macbarfuss.collectivestory.client;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;

public final class ProjectsMask extends AbstractMask {

    private Panel contentPanel;

    public ProjectsMask() { }

    @Override
    protected void buildUI() {
        contentPanel = new FlowPanel();
        contentPanel.add(new HTMLPanel("<h1>Project List</h1>"));
        contentPanel.add(new Label("Here they are:"));
    }

    @Override
    public Panel getContentPanel() {
        return contentPanel;
    }
}
