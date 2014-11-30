package de.macbarfuss.collectivestory.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import de.macbarfuss.collectivestory.model.AbstractConfiguration;

@Repository
public interface ConfigRepository extends MongoRepository<AbstractConfiguration, String> {
    AbstractConfiguration findByName(final String name);
}
