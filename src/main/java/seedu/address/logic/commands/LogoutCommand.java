package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.login.Username;

/**
 * Logout Command
 */
public class LogoutCommand extends Command {

    public static final String COMMAND_WORD = "logout";
    public static final String MESSAGE_SUCCESS = "Logged out of %1$s";
    public static final String MESSAGE_LOGIN = "You have already logged out. Please Login!";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        // User is already logged out
        if (!model.getLoginStatus() && !model.getAdminStatus()) {
            throw new CommandException(MESSAGE_LOGIN);
        }
        // Get username for printout statement
        Username loginUsername = model.getUsername();
        // Clear user
        model.logout();

        return new CommandResult(String.format(MESSAGE_SUCCESS, loginUsername.toString()));
    }
}
