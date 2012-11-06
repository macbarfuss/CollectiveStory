package de.macbarfuss.collectivestory.server.util;

import org.junit.Assert;
import org.junit.Test;

public final class TestJBcrypt {

    private static final String CLEAR_TEXT = "ANotThatSavePassphrase";

    public TestJBcrypt() { }

    @Test
    public void hashAndCheckValid() {
        final String salt = BCrypt.gensalt();
        final String cipherText = BCrypt.hashpw(CLEAR_TEXT, salt);
        Assert.assertTrue(BCrypt.checkpw(CLEAR_TEXT, cipherText));
    }

    @Test
    public void hashAndCheckInvalid() {
        final String salt = BCrypt.gensalt();
        final String cipherText = BCrypt.hashpw(CLEAR_TEXT, salt);
        Assert.assertFalse(BCrypt.checkpw(CLEAR_TEXT + "WithChange", cipherText));
    }

}
