package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class SanitizerTest {

    @Test
    @DisplayName("hashPassword should return SHA-512 encoded hex string")
    void hashPasswordProducesExpectedDigest() {
        String password = "password";

        String hashed = Sanitizer.hashPassword(password);

        assertNotNull(hashed, "The hashed password should never be null when SHA-512 is available");
        assertEquals(128, hashed.length(), "SHA-512 hashes must be 128 hex characters long");
        assertEquals(
                "b109f3bbbc244eb82441917ed06d618b9008dd09b3befd1b5e07394c706a8bb980b1d7785e5976ec049b46df5f1326af5a2ea6d103fd07c95385ffab0cacbc86",
                hashed,
                "The hash must match the expected SHA-512 digest");
    }

    @Nested
    @DisplayName("sanitizeInput")
    class SanitizeInput {

        @Test
        @DisplayName("returns false for null or empty input")
        void rejectsNullOrEmpty() {
            assertFalse(Sanitizer.sanitizeInput(null));
            assertFalse(Sanitizer.sanitizeInput(""));
        }

        @Test
        @DisplayName("returns false when input contains XSS characters")
        void rejectsXssCharacters() {
            assertFalse(Sanitizer.sanitizeInput("<script>"));
            assertFalse(Sanitizer.sanitizeInput("Hello & Goodbye"));
            assertFalse(Sanitizer.sanitizeInput("He said \"Hi\""));
            assertFalse(Sanitizer.sanitizeInput("It's me"));
        }

        @Test
        @DisplayName("returns true for safe input")
        void acceptsSafeInput() {
            assertTrue(Sanitizer.sanitizeInput("CinnamonStiches123"));
            assertTrue(Sanitizer.sanitizeInput("Hello World"));
        }
    }
}
