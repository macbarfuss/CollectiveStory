package de.macbarfuss.collectivestory.shared;

public class User {

	private String nickname;
	private String passhash;

	public User(final String theNickname, final String thePassHash) {
		nickname = theNickname;
		passhash = thePassHash;
	}

	public String getNickname() {
		return nickname;
	}

	public String getPasshash() {
		return passhash;
	}

	public void setNickname(final String theNickname) {
		nickname = theNickname;
	}

	public void setPasshash(final String thePasshash) {
		passhash = thePasshash;
	}
}
