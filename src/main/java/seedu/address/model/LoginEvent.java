package seedu.address.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.GenerateHash;
import seedu.address.model.login.Password;
import seedu.address.model.login.User;
import seedu.address.model.login.Username;
import seedu.address.storage.JsonLoginStorage;

/**
 * Login Event
 */
public class LoginEvent {

    private JsonLoginStorage loginStorage;
    private User user;
    private boolean loginStatus;
    private Logger logger = LogsCenter.getLogger(LoginEvent.class);

    /**
     * Constructor to start user with stub username and password
     */

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
     * Creates a new user in JSON file
     * @param user
     */
    public void newUser(User user) {
        String loginUsername = user.getUsername().toString();
        String loginPassword = user.getPassword().toString();

        try {
            String hashedPassword = GenerateHash.signUp(loginPassword);
            loginStorage.newUser(loginUsername, hashedPassword);
        } catch (FileNotFoundException e) {
            logger.warning("login.json cannot be found in file path" + StringUtil.getDetails(e));
        } catch (IOException e) {
           logger.warning("User storage is unable to read or write to Json file" + StringUtil.getDetails(e));
        }
    }

    /**
     * Checks if user exists and logs into account
     * @param user
     */
    public void loginUser(User user) {
        String loginUsername = user.getUsername().toString();
        String loginPassword = user.getPassword().toString();
        Map<String, String> accounts = loginStorage.getAccounts();

        if (accounts.containsKey(loginUsername)) {
            boolean passwordAuthenticate;
            String storedPassword = accounts.get(loginUsername);

            passwordAuthenticate = GenerateHash.login(loginPassword, storedPassword);

            if (passwordAuthenticate) {
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
