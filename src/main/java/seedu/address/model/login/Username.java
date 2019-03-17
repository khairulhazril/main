package seedu.address.model.login;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * UserName
 */
public class Username {

    public static final String MESSAGE_USERNAME_CONSTRAINTS = "It should not be blank!";
    public static final String USERNAME_REGEX = "[\\w]*";
    public final String enteredUsername;

    // Checks if username is valid and stores it.
    public Username(String username) {
        requireNonNull(username);
        checkArgument(isValidUsername(username), MESSAGE_USERNAME_CONSTRAINTS);
        enteredUsername = username;
    }

    // Returns true of the username is valid
    public static boolean isValidUsername(String test) {
        return test.matches(USERNAME_REGEX);
    }

    @Override
    public String toString() {
        return enteredUsername;
    }

}
