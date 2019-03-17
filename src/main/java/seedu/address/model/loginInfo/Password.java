package seedu.address.model.loginInfo;

import seedu.address.logic.parser.generateHash;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Password {

    public static final String MESSAGE_PASSWORD_CONSTRAINTS = "It should not be blank!" ;
    private static final String PASSWORD_REGEX = "[\\w]*";
    public final String hashedPassword;

    public Password(String password) {
        requireNonNull(password);
        checkArgument(isValidPassword(password), MESSAGE_PASSWORD_CONSTRAINTS);
        hashedPassword = generateHash.generateHash(password);
    }

    public static Boolean isValidPassword(String test) {
        return test.matches(PASSWORD_REGEX);
    }


    @Override
    public String toString() {return hashedPassword; }
}
