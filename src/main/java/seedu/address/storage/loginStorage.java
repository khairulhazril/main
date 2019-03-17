package seedu.address.storage;

import java.io.IOException;
import java.util.Map;

public interface loginStorage {

    // Creates user account
    void newUser(String username, String password) throws IOException;

    // Returns JSON Object with user account username and password
    Map<String, String> getAccounts() throws IOException;
}
