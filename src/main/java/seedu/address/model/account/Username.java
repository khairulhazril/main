//@@author nicholasleeeee
package seedu.address.model.account;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Username in User account
 */
public class Username {

    public static final String MESSAGE_USERNAME_CONSTRAINTS =
            "Username should be alphanumeric and it should not be blank!";
    public static final String USERNAME_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    public final String enteredUsername;

    /**
     * Constructs an username
     *
     * @param username
     */
    public Username(String username) {
        requireNonNull(username);
        checkArgument(isValidUsername(username), MESSAGE_USERNAME_CONSTRAINTS);
        enteredUsername = username;
    }

    /**
     * Test if username is valid
     *
     * @param test
     * @return true if username is valid
     */
    public static boolean isValidUsername(String test) {
        return test.matches(USERNAME_REGEX);
    }

    @Override
    public String toString() {
        return enteredUsername;
    }

    @Override
    public boolean equals(Object other) {
        return other == this || (other instanceof Username
                && enteredUsername.equals(((Username) other).enteredUsername));
    }

    @Override
    public int hashCode() {
        return enteredUsername.hashCode();
    }
}
