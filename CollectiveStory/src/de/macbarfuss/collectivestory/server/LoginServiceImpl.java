package de.macbarfuss.collectivestory.server;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.query.Query;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.macbarfuss.collectivestory.client.LoginService;
import de.macbarfuss.collectivestory.server.util.BCrypt;
import de.macbarfuss.collectivestory.shared.LoginData;
import de.macbarfuss.collectivestory.shared.UserSessionInfo;

public final class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

    private static final String EQUALS_SESSION_ID = "sessionID =";
    private static final long serialVersionUID = 1L;
    private Datastore ds = PersistenceManager.getDatastore();

    public LoginServiceImpl() {
    }

    @Override
    public UserSessionInfo startSession(final LoginData data) {
        final UserSessionInfo userSessionInfo = new UserSessionInfo();

        if (isValidLoginData(data.getUsername(), data.getPassphrase())) {
            userSessionInfo.setUsername(data.getUsername());
            userSessionInfo.setSessionID(getSessionID(data.getUsername()));
            userSessionInfo.markActive();
            ds.save(userSessionInfo);
        }

        return userSessionInfo;
    }

    @Override
    public Boolean isValidSessionID(final String sessionID) {
        if (sessionID != null) {
            for (UserSessionInfo session : getSessions()) {
                if (sessionID.equals(session.getSessionID())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public UserSessionInfo closeSession(final String sid) {
        final Query<UserSessionInfo> q = ds.createQuery(UserSessionInfo.class);
        q.filter(EQUALS_SESSION_ID, sid);
        ds.delete(q);
        return null;
    }

    @Override
    public Iterable<UserSessionInfo> getSessions() {
        final Query<UserSessionInfo> query = ds.createQuery(UserSessionInfo.class);
        return query.fetch();
    }

    // H E L P E R
    private boolean isValidLoginData(final String username, final String passphrase) {
        final Query<User> query = ds.createQuery(User.class);
        // checks if there are any User set. Else access is open.
        if (query.countAll() > 0) {
//            final String prospectPassHash =
            query.filter("nickname =", username);
            final User u = query.get();
            return u != null && BCrypt.checkpw(passphrase, u.getPasshash());
        }
        return true;
    }

    private String getSessionID(final String userName) {
        final Query<UserSessionInfo> query = ds.createQuery(UserSessionInfo.class);
        query.filter("username =", userName);
        final UserSessionInfo usi = query.get();
        if (usi != null) {
            return usi.getSessionID();
        } else {
            return generateSessionID(userName);
        }
    }

    private String generateSessionID(final String userName) {
        String result;
        do {
            // TODO use stronger hash (jBCrypt)
            result = userName + String.valueOf(System.nanoTime());
            result = result.hashCode() + "";

            final Query<UserSessionInfo> query = ds.createQuery(UserSessionInfo.class);
            query.filter(EQUALS_SESSION_ID, result);
            if (query.get() != null) {
                result = "";
            }
        } while ("".equals(result));
        return result;
    }
}
