package de.macbarfuss.collectivestory.shared;

import java.io.Serializable;

/**
 * Container to transport LoginData between Client and Server.
 *
 * @author MacBarfuss
 */
public final class LoginData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The passphrase of the user in plain text.
     */
    private String passphrase;
    
    private String username;

    public LoginData() { }
    
    public String getPassphrase() {
        return passphrase;
    }

    public String getUsername() {
        return username;
    }
    
    public void setPassphrase(final String newPassphrase) {
        passphrase = newPassphrase;
    }

    public void setUsername(final String newUsername) {
        username = newUsername;
    }
}
