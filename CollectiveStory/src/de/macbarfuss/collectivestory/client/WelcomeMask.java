package de.macbarfuss.collectivestory.client;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;

public final class WelcomeMask extends AbstractMask {

    private Panel contentPanel;

    public WelcomeMask() {
    }

    @Override
    protected void buildUI() {
        contentPanel = new FlowPanel();
        contentPanel.add(new HTMLPanel("<h1>Welcome</h1>"));
        contentPanel.add(new Label("SessionID"));
        contentPanel.add(new Label(Display.getInstance().getSessionInfo().getSessionID()));
    }

    @Override
    public Panel getContentPanel() {
        return contentPanel;
    }
}
