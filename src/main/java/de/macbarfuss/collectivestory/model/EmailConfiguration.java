package de.macbarfuss.collectivestory.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class EmailConfiguration extends AbstractConfiguration {

    public static final String HOST = "host";
    public static final String PASSWORD = "password";
    public static final String PORT = "port";
    public static final String USERNAME = "username";

    private final Set<Object> validAttributes = new HashSet<Object>();
    private final Map<String, Object> defaultValues = new HashMap<String, Object>();

    public EmailConfiguration() {
        validAttributes.add(HOST);
        validAttributes.add(PASSWORD);
        validAttributes.add(PORT);
        validAttributes.add(USERNAME);

        defaultValues.put(HOST, "localhost");
        defaultValues.put(PASSWORD, "");
        defaultValues.put(PORT, 25);
        defaultValues.put(USERNAME, "");
    }

    @Override
    public Set<Object> getValidAttributes() {
        return validAttributes;
    }

    /**
     * Default is: sending to localhost:25 and there is no user needed.
     * 
     * This case should suit on most servers out there at least for testing.
     * 
     * @see de.macbarfuss.collectivestory.model.AbstractConfiguration#getDefault()
     */
    @Override
    public Map<String, Object> getDefault() {
        return defaultValues;
    }

}
