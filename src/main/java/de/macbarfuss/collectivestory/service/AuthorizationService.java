package de.macbarfuss.collectivestory.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.macbarfuss.collectivestory.model.User;

/**
 * This service returns whether a specific user is allowed to do something.
 * 
 * The general naming convention for the check-methods is "can" + action +
 * targetClass. The check is done for the current user.
 * 
 * @author macbarfuss
 * 
 */
@Service
public class AuthorizationService {

    // private static final Logger LOG =
    // LoggerFactory.getLogger(AuthorizationService.class);

    @Autowired
    private AuthenticationService authenticationService;

    public AuthorizationService() {
    }

    public Boolean canEditUser(final String username) {
        final User actor = authenticationService.getCurrentUser();
        if (actor.getUsername().equals(username)) {
            return true;
        } else {
            return actor.isAdmin();
        }
    }
}
