package de.macbarfuss.collectivestory.client.admin;

import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import de.macbarfuss.collectivestory.client.Display;
import de.macbarfuss.collectivestory.client.MessageType;

public final class AdminMask extends Composite {

    interface MyUiBinder extends UiBinder<Widget, AdminMask> {
    }

    private static MyUiBinder binder = GWT.create(MyUiBinder.class);

    @UiField Button newUserButton;
    @UiField CheckBox isAdmin;
    @UiField TextBox newUserName;
    @UiField PasswordTextBox newUserPassword;
    @UiField ListBox users;

    public AdminMask() {
        initWidget(binder.createAndBindUi(this));
        refreshUserList();
    }

    @UiHandler("newUserButton")
    void handleAddUser(final ClickEvent e) {
        AdminService.Util.getInstance().addUser(
                newUserName.getText(), newUserPassword.getText(), new AsyncCallback<Void>() {

                    @Override
                    public void onSuccess(final Void result) {
                        refreshUserList();
                        Display.getInstance().setMessage("User created successfully.");
                        Display.getInstance().setMessageType(MessageType.INFO);
                    }

                    @Override
                    public void onFailure(final Throwable caught) {
                        Display.getInstance().setMessage(caught.getMessage());
                    }
                }
        );
    }

    private void refreshUserList() {
        AdminService.Util.getInstance().getAllUser(new GetAllUserCallback());
    }

    private final class GetAllUserCallback implements AsyncCallback<Set<String>> {
        public GetAllUserCallback() { }

        @Override
        public void onFailure(final Throwable caught) { }

        @Override
        public void onSuccess(final Set<String> result) {
            users.clear();
            for (String name : result) {
                users.addItem(name);
            }
        }
    }
}
