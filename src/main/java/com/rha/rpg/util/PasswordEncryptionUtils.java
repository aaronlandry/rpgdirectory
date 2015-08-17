package com.rha.rpg.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Utility for providing password encryption.  Right now it is just a wrapper for
 * BCryptPasswordEncoder.
 * @author Aaron
 */
public class PasswordEncryptionUtils {
    
    /**
     * Encrypts the passed string.  Current implementation uses the BCryptPasswordEncoder.
     * @param plaintext String to be encrypted
     * @return encrypted string
     */
    public static String encrypt(CharSequence plaintext) {
        return new BCryptPasswordEncoder().encode(plaintext);
    }
    
    /**
     * Determines if the rawPassword and the encoded password "match".
     * @param rawPassword Plain-text password
     * @param encoded Encoded password
     * @return 
     */
    public static Boolean matches(CharSequence rawPassword, String encoded) {
        return new BCryptPasswordEncoder().matches(rawPassword, encoded);
    }
 
    public static String simpleEncrypt(String plainText) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(plainText.getBytes());
        byte byteData[] = md.digest();
        //convert the byte to hex format method 1
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
    
    public static Boolean simpleMatches(String plainText, String encoded) throws NoSuchAlgorithmException {
        return encoded.equals(PasswordEncryptionUtils.simpleEncrypt(plainText));
    }
    
}
