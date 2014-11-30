package de.macbarfuss.collectivestory.model;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {
    ROLE_ADMIN, ROLE_MANAGER, ROLE_USER;

    @Override
    public String getAuthority() {
        return this.toString();
    }
}
