package seedu.address.model;
import seedu.address.model.login.Password;
import seedu.address.model.login.User;
import seedu.address.model.login.Username;
import seedu.address.storage.JsonLoginStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;



/**
 * Login Event
 */
public class LoginEvent {

    private JsonLoginStorage loginStorage;
    private User user;
    private boolean loginStatus;

    // Constructor to start user with temp username and password
    public LoginEvent() {

        final Path loginInfoPath = Paths.get("login.json");
        final Username username = new Username("test");
        final Password password = new Password("test");
        user = new User(username, password);
        loginStatus = false;

        try {
            loginStorage = new JsonLoginStorage(loginInfoPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        newUser(user);
    }

    /**
     * Creates new user in JSON file
     * @param user
     */
    public void newUser(User user) {
        String loginUsername = user.getUsername().toString();
        String loginPassword = user.getPassword().toString();
        try {
            loginStorage.newUser(loginUsername, loginPassword);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if user exists and logs into account
     * @param user
     */
    public void loginUser(User user) {
        String loginUsername = user.getUsername().toString();
        Map<String, String> accounts = loginStorage.getAccounts();

        if (accounts.containsKey(loginUsername)) {
            boolean passwordValid = false;
            String password = accounts.get(loginUsername);

            if (passwordValid) {
                this.user = user;
                loginStatus = true;
            }
        }
    }

    /**
     * Returns true if the user exists in the JSON file
     * @param user
     * @return
     */
    public boolean userExists(User user) {
        String loginUsername = user.getUsername().toString();
        Map<String, String> accounts = loginStorage.getAccounts();

        return accounts.containsKey(loginUsername);
    }

    public Username getUsername() {
        return user.getUsername();
    }

    // Remove user to prepare for next login
    public void logout() {
        loginStatus = false;
    }

    // Returns true if user is logged in
    public boolean getLoginStatus() {
        return loginStatus;
    }
}
