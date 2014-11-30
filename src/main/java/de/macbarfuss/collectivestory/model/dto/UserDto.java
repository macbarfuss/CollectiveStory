package de.macbarfuss.collectivestory.model.dto;

public final class UserDto {

    private String username;
    private String email;
    private Boolean enabled;
    private String password;

    public UserDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String theUsername) {
        username = theUsername;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String theEmail) {
        email = theEmail;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(final Boolean isEnabled) {
        enabled = isEnabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

}
