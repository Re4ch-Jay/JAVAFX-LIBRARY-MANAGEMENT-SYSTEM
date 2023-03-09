package librarymanagementsystem;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncryption {

    // Set the number of bcrypt rounds to use
    private static final int BCRYPT_ROUNDS = 12;

    /**
     * Generates a new, random salt value.
     *
     * @return The salt value.
     */
    public static String generateSalt() {
        return BCrypt.gensalt(BCRYPT_ROUNDS);
    }

    /**
     * Encrypts a password using bcrypt and a given salt value.
     *
     * @param password The plaintext password to encrypt.
     * @param salt     The salt value to use.
     * @return The encrypted password.
     */
    public static String encryptPassword(String password, String salt) {
        return BCrypt.hashpw(password, salt);
    }

    /**
     * Checks if a plaintext password matches a stored hashed password.
     *
     * @param password        The plaintext password to check.
     * @param storedPassword  The stored hashed password.
     * @return True if the passwords match, false otherwise.
     */
    public static boolean checkPassword(String password, String storedPassword) {
        return BCrypt.checkpw(password, storedPassword);
    }
}