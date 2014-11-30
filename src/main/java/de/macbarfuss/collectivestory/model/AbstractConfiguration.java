package de.macbarfuss.collectivestory.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "config")
public abstract class AbstractConfiguration {

    @Id
    private String id;

    private String name;

    private Map<String, Object> data = new HashMap<String, Object>();

    public AbstractConfiguration() {
        name = this.getClass().getName();
    }

    public final String getName() {
        return name;
    }

    public abstract Set<Object> getValidAttributes();

    /**
     * Returning the values to be stored in the database for default case.
     * 
     * These values are used if the database does not provide the necessary
     * fields, e.g. the applications boots for the very first time and the
     * database is empty.
     * 
     * @return
     */
    public abstract Map<String, Object> getDefault();

    public final Object get(final String key) {
        return data.get(key);
    }

    public final String getString(final String key) {
        return (String) data.get(key);
    }

    public final Integer getInteger(final String key) {
        return (Integer) data.get(key);
    }

    public final void set(final String key, final Object value) {
        if (getValidAttributes().contains(key)) {
            data.put(key, value);
        }
    }

    public void setDefault() {
        final Map<String, Object> defaults = getDefault();
        for (String key : defaults.keySet()) {
            set(key, defaults.get(key));
        }
    }

    public Boolean fillWithDefaults() {
        Boolean changed = false;
        final Map<String, Object> defaults = getDefault();
        for (String key : defaults.keySet()) {
            if (get(key) == null) {
                set(key, defaults.get(key));
                changed = true;
            }
        }
        return changed;
    }
}
