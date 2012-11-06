package de.macbarfuss.collectivestory.server;

import java.util.HashSet;
import java.util.Set;

import com.google.code.morphia.query.Query;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.macbarfuss.collectivestory.client.admin.AdminService;
import de.macbarfuss.collectivestory.server.util.BCrypt;

public final class AdminServiceImpl extends RemoteServiceServlet implements AdminService {

    private static final long serialVersionUID = 1L;

    public AdminServiceImpl() { }

    @Override
    public void addUser(final String nickname, final String password) {
        final String salt = BCrypt.gensalt();
        final User newUser = new User(nickname, BCrypt.hashpw(password, salt), salt);
        PersistenceManager.getDatastore().save(newUser);
    }

    @Override
    public Set<String> getAllUser() {
        final Query<User> q = PersistenceManager.getDatastore().createQuery(User.class);
        final Set<String> result = new HashSet<String>();
        for (User u : q.fetch()) {
            result.add(u.getNickname());
        }
        return result;
    }

}
