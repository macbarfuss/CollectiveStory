package de.macbarfuss.collectivestory.shared;

import java.io.Serializable;

public final class UserConfiguration implements Serializable {

    private static final long serialVersionUID = 7165375781278723364L;

    private Boolean isAdmin;

    public UserConfiguration() { }

    public Boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(final Boolean whetherIsAdmin) {
        isAdmin = whetherIsAdmin;
    }

}
