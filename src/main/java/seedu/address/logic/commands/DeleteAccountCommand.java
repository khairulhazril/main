package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.account.Username;

/**
 * DeleteAccountCommand
 */

public class DeleteAccountCommand extends Command {

    public static final String COMMAND_WORD = "deleteacc";
    public static final String MESSAGE_SUCCESS = "Account has been deleted!";
    public static final String MESSAGE_ADMIN_LOGIN = "You need to be an admin to use this command";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {

        // Not an admin loggin in
        if (!model.getAdminStatus()) {
            throw new CommandException(MESSAGE_ADMIN_LOGIN);
        }

        // Get username for printout statement
        Username loginUsername = model.getUsername();

        // Delete JSON file
        model.deleteAccount();

        return new CommandResult(String.format(MESSAGE_SUCCESS, loginUsername.toString()));
    }
}
