package seedu.address.testutil;


import seedu.address.model.account.Password;
import seedu.address.model.account.User;
import seedu.address.model.account.Username;

/**
 *  Test utility to build account objects for testing
 */

public class AccountBuilder {

    public static final String SET_USERNAME = "user";
    public static final String SET_PASSWORD = "pass";

    private Username username;
    private Password password;

    public AccountBuilder() {
        username = new Username(SET_USERNAME);
        password = new Password(SET_PASSWORD);
    }

    /**
     * Starts AccountBuilder with getData
     */
    public AccountBuilder(User getData) {
        username = getData.getUsername();
        password = getData.getPassword();
    }

    /**
     * Sets the username of the account built
     */
    public AccountBuilder setUsername(String username) {
        this.username = new Username(username);
        return this;
    }

    /**
     * Sets the password of the account built
     */
    public AccountBuilder setPassword(String password) {
        this.password = new Password(password);
        return this;
    }

    public User build() {
        return new User(username, password);
    }

}
