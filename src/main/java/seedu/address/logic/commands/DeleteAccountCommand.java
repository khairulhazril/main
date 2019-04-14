//@@author nicholasleeeee
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
    public static final String MESSAGE_ADMIN_LOGIN = "You need to log in as an admin to use this command";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {

        if (!model.getAdminStatus()) {
            throw new CommandException(MESSAGE_ADMIN_LOGIN);
        }

        Username loginUsername = model.getUsername();

        model.deleteAccount();

        model.setSelectedTask(null);
        return new CommandResult(String.format(MESSAGE_SUCCESS, loginUsername.toString()));
    }
}
