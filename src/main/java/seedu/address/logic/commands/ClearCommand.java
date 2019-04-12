package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TaskManager;

/**
 * Clears the task manager.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String COMMAND_ALIAS = "c";

    public static final String MESSAGE_SUCCESS = "Task Manager has been cleared!";


    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {

        if (!model.getLoginStatus() && !model.getAdminStatus()) {
            throw new CommandException(MESSAGE_LOGIN_REQUIRED);
        }

        requireNonNull(model);
        model.setTaskManager(new TaskManager());
        model.commitTaskManager();
        model.setSelectedTask(null);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
