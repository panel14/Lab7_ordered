package itmo.service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * class for hashing user passwords
 */
public class HashCoder {
    private static final String ALGORYTHM = "SHA-224";

    /**
     * SHA-224 algorythm hashing
     * @param data
     * @return String represent of hash-password
     */
    public static String encodeSHA224(String data) {
        try {
            MessageDigest messageDigest =MessageDigest.getInstance(ALGORYTHM);
            byte[] buffer = messageDigest.digest(data.getBytes(StandardCharsets.UTF_8));
            BigInteger value = new BigInteger(1, buffer);
            StringBuilder hash = new StringBuilder(value.toString(16));

            while (hash.length() < 32){
                hash.insert(0, "0");
            }
            return hash.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Ошибка хеширования: " + e.getMessage());
            return null;
        }
    }
}
