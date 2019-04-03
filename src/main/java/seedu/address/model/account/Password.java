package seedu.address.model.account;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Password in User Account
 */
public class Password {

    public static final String MESSAGE_PASSWORD_CONSTRAINTS = "Password should be alphanumeric and it should not be blank!";
    private static final String SPECIAL_CHARACTERS = "!#$%&'*+/=?`{|}~^.@-";
    private static final String PASSWORD_REGEX = "^[\\w" + SPECIAL_CHARACTERS + "]+";
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
    public static boolean isValidPassword(String test) {
        return test.matches(PASSWORD_REGEX);
    }

    @Override
    public String toString() {
        return enteredPassword;
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof Password
                && enteredPassword.equals(((Password) other).enteredPassword));
    }

    @Override
    public int hashCode() {
        return enteredPassword.hashCode();
    }
}
