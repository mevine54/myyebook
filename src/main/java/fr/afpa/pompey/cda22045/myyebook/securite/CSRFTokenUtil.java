package fr.afpa.pompey.cda22045.myyebook.securite;

import java.security.SecureRandom;
import java.util.Base64;

public class CSRFTokenUtil {
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateCSRFToken() {
        byte[] randomBytes = new byte[32];
        secureRandom.nextBytes(randomBytes);
        return Base64.getEncoder().encodeToString(randomBytes);
    }
}
