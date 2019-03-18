package seedu.address.model.login;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * User
 */
public class User {

    private final Username username;
    private final Password password;

    // Makes sure username and password is valid and puts into User
    public User(Username username, Password password) {
        requireAllNonNull(username, password);
        this.username = username;
        this.password = password;
    }

    public Username getUsername() {
        return username;
    }

    public Password getPassword() {
        return password;
    }
}
