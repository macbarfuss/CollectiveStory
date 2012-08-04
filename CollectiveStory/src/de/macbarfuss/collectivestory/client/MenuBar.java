package de.macbarfuss.collectivestory.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class MenuBar extends Composite {

	interface MyUiBinder extends UiBinder<Widget, MenuBar> { }

	private static MyUiBinder binder = GWT.create(MyUiBinder.class);

	@UiField Button adminBtn;
	@UiField Button homeBtn;
	@UiField Button listProjectsBtn;
	@UiField Button logoutBtn;

	public MenuBar() {
		initWidget(binder.createAndBindUi(this));
	}

	@UiHandler("adminBtn")
	void showAdmin(ClickEvent e) {
		Display.getInstance().show(new AdminMask());
	}

    @UiHandler("homeBtn")
    void showHomeMask(ClickEvent e) {
    	Display.getInstance().show(new WelcomeMask());
    }

    @UiHandler("listProjectsBtn")
    void showProjects(ClickEvent e) {
    	Display.getInstance().show(new ProjectsMask());
    }

	@UiHandler("logoutBtn")
	void fireLogoutHandler(ClickEvent e) {
		LogoutHandler lh = new LogoutHandler();
		lh.onClick(e);
	}
}
