package de.macbarfuss.collectivestory.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import de.macbarfuss.collectivestory.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(final String email);
    User findByUsername(final String username);
}
