package seedu.address.model.account;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * User Account
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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof User)) {
            return false;
        }

        User user = (User) other;
        return this.username.equals(user.getUsername()) && this.password.equals(user.getPassword());
    }
}
