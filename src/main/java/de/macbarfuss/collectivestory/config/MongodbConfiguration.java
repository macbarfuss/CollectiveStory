package de.macbarfuss.collectivestory.config;

import java.net.UnknownHostException;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories(basePackages = "de.macbarfuss.collectivestory.model.repository")
public class MongodbConfiguration extends AbstractMongoConfiguration {

    public MongodbConfiguration() {
    }

    @Override
    protected String getDatabaseName() {
        return "collectivestory";
    }

    @Override
    public MongoClient mongo() throws UnknownHostException {
        return new MongoClient();
    }

    @Override
    protected String getMappingBasePackage() {
        return "de.macbarfuss.collectivestory.model";
    }

}
