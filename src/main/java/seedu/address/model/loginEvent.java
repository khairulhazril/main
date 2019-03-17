package seedu.address.model;

import seedu.address.model.loginInfo.Password;
import seedu.address.model.loginInfo.User;
import seedu.address.model.loginInfo.Username;
import seedu.address.storage.JsonLoginStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class loginEvent {

    private JsonLoginStorage loginStorage;
    private User user;
    private boolean loginStatus;

    public loginEvent() {

        final Path loginInfoPath = Paths.get("loginInfo.json");
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

    public void newUser(User user) {
        String loginUsername = user.getUsername().toString();
        String loginPassword = user.getPassword().toString();
        try {
            loginStorage.newUser(loginUsername, loginPassword);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loginUser(User user) {
        String loginUsername = user.getUsername().toString();
        Map<String,String> accounts = loginStorage.getAccounts();

        if (accounts.containsKey(loginUsername)) {
            boolean passwordValid = false;
            String password = accounts.get(loginUsername);

            if (passwordValid) {
                this.user = user;
                loginStatus = true;
            }
        }
    }

    public boolean userExists(User user) {
        String loginUsername = user.getUsername().toString();
        Map<String, String> accounts = loginStorage.getAccounts();

        return accounts.containsKey(loginUsername);
    }

    public Username getUsername() {
        return user.getUsername();
    }

    public void logout() {
        loginStatus = false;
    }

    public boolean getLoginStatus() {
        return loginStatus;
    }
}
