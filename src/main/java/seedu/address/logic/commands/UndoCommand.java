package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Reverts the {@code model}'s task manager to its previous state.
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";
    public static final String COMMAND_ALIAS = "u";
    public static final String MESSAGE_SUCCESS = "Undo success!";
    public static final String MESSAGE_FAILURE = "No more commands to undo!";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        //@@author nicholasleeeee
        if (!model.accountExists() && !model.getAdminStatus()) {
            throw new CommandException(MESSAGE_ACCOUNT_DOES_NOT_EXIST);
        }

        if (!model.getLoginStatus() && !model.getAdminStatus()) {
            throw new CommandException(MESSAGE_LOGIN_REQUIRED);
        }
        //@@author

        if (!model.canUndoTaskManager()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        model.undoTaskManager();
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
