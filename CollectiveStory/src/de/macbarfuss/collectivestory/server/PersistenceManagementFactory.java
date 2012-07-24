package de.macbarfuss.collectivestory.server;

import java.net.UnknownHostException;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public final class PersistenceManagementFactory {

    private PersistenceManagementFactory() { }

    public static Datastore getDatastore() {
    	return new Morphia().createDatastore(getMongo(), "collectivestory");
    }

    private static Mongo getMongo() {
    	Mongo m = null;
    	try {
			m = new Mongo();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}
    	return m;
    }
}
