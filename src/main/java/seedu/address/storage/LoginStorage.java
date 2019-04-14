//@@author nicholasleeeee
package seedu.address.storage;

import java.io.IOException;
import java.util.Map;

import seedu.address.logic.commands.exceptions.CommandException;
/**
 * Login Storage
 */
public interface LoginStorage {

    /**
     * Creates a new user account
     * @param username
     * @param password
     * @throws IOException if account cannot be created
     */
    void newUser(String username, String password) throws IOException;

    /**
     * Retrieves the accounts in a map
     * @return map of accounts, JSON object with user account username and password
     * @throws IOException if accounts cannot be retrieved from JSON file
     */
    Map<String, String> getAccounts() throws IOException;


    /**
     * Deletes the account in the JSON file
     * @throws CommandException if the JSON file cannot be deleted
     */
    void deleteAccount() throws CommandException;
}
