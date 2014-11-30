package de.macbarfuss.collectivestory.converter;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * This is a dummy to allow unencoded passwords in Database.
 * 
 * Do not use this on a production server!
 * 
 * @author macbarfuss
 * 
 */
public final class PlainTextEncoder implements PasswordEncoder {

    public PlainTextEncoder() {
    }

    @Override
    public String encode(final CharSequence arg0) {
        return arg0.toString();
    }

    @Override
    public boolean matches(final CharSequence arg0, final String arg1) {
        if (arg0 != null) {
            return arg0.toString().equals(arg1);
        } else {
            return false;
        }
    }
}
