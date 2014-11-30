package de.macbarfuss.collectivestory.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import de.macbarfuss.collectivestory.model.dto.UserDto;

/**
 * Simple class for representing a user.
 * 
 * @author geoema
 * 
 */
@Document
public final class User implements UserDetails {

    private static final long serialVersionUID = 200983435760412310L;

    @Id
    private String id;

    private final String username;
    private String email = "test@example.com";
    private String password = "123";
    private Boolean enabled = true;
    private Set<Authority> roles = new HashSet<Authority>();

    public User(final String username) {
        this.username = username;
        roles.add(Authority.ROLE_USER);
        roles.add(Authority.ROLE_MANAGER); // for testing at the moment
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String newEmail) {
        email = newEmail;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final Boolean newEnabled) {
        enabled = newEnabled;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String toString() {
        return String.format("User[id=%s, username='%s', email='%s', enabled='%s']", id, username, email, enabled);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public void addAuthority(final Authority newAuthority) {
        roles.add(newAuthority);
    }

    public void removeAuthority(final Authority formerAuthority) {
        roles.remove(formerAuthority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(final String newPassword) {
        password = newPassword;
    }

    /**
     * In this environment accounts do not expire.
     * 
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * In this environment accounts can't get locked.
     * 
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * In this environment account credentials do not expire.
     * 
     * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public UserDto getDto() {
        final UserDto result = new UserDto();
        result.setUsername(getUsername());
        result.setEmail(getEmail());
        result.setEnabled(enabled);
        return result;
    }

    public Boolean isAdmin() {
        return roles.contains(Authority.ROLE_ADMIN);
    }
}
