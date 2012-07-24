package de.macbarfuss.collectivestory.shared;

public final class FieldVerifier {

    private static final int NAME_MIN_LENGTH = 3;

    /**
     * Hidden constructor.
     */
    private FieldVerifier() { }

    public static boolean isValidName(final String name) {
        if (name == null) {
            return false;
        }
        return name.length() > NAME_MIN_LENGTH;
    }
}
