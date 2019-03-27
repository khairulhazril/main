package seedu.address.storage;

import seedu.address.logic.commands.exceptions.CommandException;

import java.io.IOException;
import java.util.Map;

/**
 * Login Storage
 */
public interface LoginStorage {

    // Creates user account
    void newUser(String username, String password) throws IOException;

    // Returns JSON Object with user account username and password
    Map<String, String> getAccounts() throws IOException;

    // Deletes JSON file with accounts in it
    void deleteAccount() throws CommandException;
}
