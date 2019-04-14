package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Lists all the commands entered by user from the start of app launch.
 */
public class HistoryCommand extends Command {

    public static final String COMMAND_WORD = "history";
    public static final String MESSAGE_SUCCESS = "Entered commands (from most recent to earliest):\n%1$s";
    public static final String MESSAGE_NO_HISTORY = "You have not yet entered any commands.";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(history);
        ArrayList<String> previousCommands = new ArrayList<>(history.getHistory());

        if (!model.accountExists() && !model.getAdminStatus()) {
            throw new CommandException(MESSAGE_ACCOUNT_DOES_NOT_EXIST);
        }

        if (!model.getLoginStatus() && !model.getAdminStatus()) {
            throw new CommandException(MESSAGE_LOGIN_REQUIRED);
        }

        if (previousCommands.isEmpty()) {
            return new CommandResult(MESSAGE_NO_HISTORY);
        }

        Collections.reverse(previousCommands);
        return new CommandResult(String.format(MESSAGE_SUCCESS, String.join("\n", previousCommands)));
    }

}
