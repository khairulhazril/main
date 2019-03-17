package seedu.address.model.loginInfo;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Username {

    public static final String MESSAGE_USERNAME_CONSTRAINTS = "It should not be blank!";
    public static final String USERNAME_REGEX = "[\\w]*";
    public final String enteredUsername;

    public Username(String username) {
        requireNonNull(username);
        checkArgument(isValidUsername(username), MESSAGE_USERNAME_CONSTRAINTS);
        enteredUsername = username;
    }

    public static boolean isValidUsername(String test) {
        return test.matches(USERNAME_REGEX);
    }

    @Override
    public String toString() { return enteredUsername; }

}
