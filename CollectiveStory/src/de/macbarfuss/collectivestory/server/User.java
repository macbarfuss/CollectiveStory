package de.macbarfuss.collectivestory.server;

import com.google.code.morphia.annotations.Entity;

@Entity
public final class User {

    private String nickname;
    private String passhash;
    private String salt;

    protected User() { }

    public User(final String theNickname, final String thePassHash, final String theSalt) {
        nickname = theNickname;
        passhash = thePassHash;
        setSalt(theSalt);
    }

    public String getNickname() {
        return nickname;
    }

    public String getPasshash() {
        return passhash;
    }

    public String getSalt() {
        return salt;
    }

    public void setNickname(final String theNickname) {
        nickname = theNickname;
    }

    public void setPasshash(final String thePasshash) {
        passhash = thePasshash;
    }

    public void setSalt(final String theSalt) {
        salt = theSalt;
    }
}
