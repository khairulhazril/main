package seedu.address.model.login;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.logic.parser.GenerateHash;

/**
 * Password in User Account
 */
public class Password {

    public static final String MESSAGE_PASSWORD_CONSTRAINTS = "It should not be blank!";
    private static final String PASSWORD_REGEX = "[\\w]*";
    public final String enteredPassword;

    /**
     * Constructs a password
     *
     * @param password
     */
    public Password(String password) {
        requireNonNull(password);
        checkArgument(isValidPassword(password), MESSAGE_PASSWORD_CONSTRAINTS);
        enteredPassword = password;
    }

    /**
     * Test if password is valid
     *
     * @param test
     *
     * @return true if password is valid
     */
    public static Boolean isValidPassword(String test) { return test.matches(PASSWORD_REGEX); }

    @Override
    public String toString() { return enteredPassword; }
}
