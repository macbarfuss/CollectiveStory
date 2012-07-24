package de.macbarfuss.collectivestory.shared;

import java.io.Serializable;

/**
 * This object holds information about the current session with a user.
 * It includes the session id.
 *
 * @author MacBarfuss
 */
public final class UserSessionInfo implements Serializable {

    public static final String ENTITY_NAME = "UserSessionInfo";

    // A T T R I B U T E S
    private static final long serialVersionUID = 2L;

    private long lastActionDate;
    private String sessionID;
    private String username;


// C O N S T R U C T O R S
    /**
     * Return a new object with information about the current session. This
     * constructor can only be invoked on the server.
     *
     * @param newSessionID String
     */
    public UserSessionInfo(final String newSessionID) {
//        assert !GWT.isClient();
        setSessionID(newSessionID);
        markActive();
    }

	public UserSessionInfo() {	}


// G E T T E R S

    public long getLastActionDate() {
    	return lastActionDate;
    }

    public String getSessionID() {
        return sessionID;
    }

    public String getUsername() {
        return username;
    }

    public boolean hasSessionID() {
        return sessionID != null && !"".equals(sessionID);
    }


// S E T T E R S
    public void markActive() {
        lastActionDate = System.currentTimeMillis();
    }

    public void setSessionID(final String newValue) {
        sessionID = newValue;
    }

    public void setUsername(final String newValue) {
        username = newValue;
    }
}
