package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sanitizer {
    private static final char[] XSS_CHARS = {'<', '>', '\"', '\'', '&'};

    public static String hashPassword(String pass) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] hash = digest.digest(pass.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static boolean sanitizeInput(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        for (char c : input.toCharArray()) {
            for (char xssChar : XSS_CHARS) {
                if (c == xssChar) {
                    return false;
                }
            }
        }
        return true;
    }
}
