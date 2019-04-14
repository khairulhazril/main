//@@author nicholasleeeee-reused
package seedu.address.logic.parser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import seedu.address.logic.commands.IncorrectCommand;

/**
 * Generate Hash for User class
 */
public class GenerateHash {

    public static final String COMMAND_SIGNUP_ERROR = "Sign up error: Hashing error.";
    public static final String SALT = "nicholasSecretHash";

    /**
     * Generate hash for password
     * @param password
     * @return hashedPassword
     */
    public static String signUp(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String saltedPassword = SALT + password;
        String hashedPassword = generateHash(saltedPassword);
        return hashedPassword;
    }

    /**
     * Check if password matches stored password
     * @param loginPassword
     * @param storedPassword
     * @return true if password matches
     */
    public static Boolean login(String loginPassword, String storedPassword) {
        Boolean isAuthenticated;
        String saltedPassword = SALT + loginPassword;
        String hashedPassword = generateHash(saltedPassword);

        if (hashedPassword.equals(storedPassword)) {
            isAuthenticated = true;
        } else {
            isAuthenticated = false;
        }
        return isAuthenticated;
    }

    /**
     * Generate hash
     * @param input
     * @return hash string
     */
    public static String generateHash(String input) {

        StringBuilder hash = new StringBuilder();

        try {
            MessageDigest nic = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = nic.digest(input.getBytes());
            char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
            for (int index = 0; index < hashedBytes.length; ++index) {
                byte b = hashedBytes[index];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            new IncorrectCommand(String.format(COMMAND_SIGNUP_ERROR));
        }

        return hash.toString();
    }
}
