package seedu.address.model.login;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.logic.parser.GenerateHash;

/**
 * Password
 */
public class Password {

    public static final String MESSAGE_PASSWORD_CONSTRAINTS = "It should not be blank!";
    private static final String PASSWORD_REGEX = "[\\w]*";
    public final String hashedPassword;

    // hashes password and makes sure it is valid
    public Password(String password) {
        requireNonNull(password);
        checkArgument(isValidPassword(password), MESSAGE_PASSWORD_CONSTRAINTS);
        hashedPassword = GenerateHash.generateHash(password);
    }

    // Returns true of the password is valid
    public static Boolean isValidPassword(String test) {
        return test.matches(PASSWORD_REGEX);
    }


    @Override
    public String toString() {
        return hashedPassword;
    }
}
