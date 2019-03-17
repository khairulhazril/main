package seedu.address.storage;

import java.io.IOException;
import java.util.Map;

public interface loginStorage {

    void newUser(String username, String password) throws IOException;

    Map<String, String> getAccounts() throws IOException;
}
