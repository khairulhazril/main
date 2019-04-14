package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Sorts the task list in the task manager.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String COMMAND_ALIAS = "s";


    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the task list in the task manager.\n"
            + "Parameters: attribute\n"
            + "Example: " + COMMAND_WORD + " name";

    public static final String MESSAGE_SUCCESS = "Task list sorted by %1$s";
    public static final String MESSAGE_INVALID =
            "Attribute should be one of the following: name, date, module or priority\n" + MESSAGE_USAGE;

    private final String toSortBy;

    public SortCommand(String attribute) {
        requireNonNull(attribute);
        toSortBy = attribute;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {

        //@@author nicholasleeeee
        if (!model.accountExists() && !model.getAdminStatus()) {
            throw new CommandException(MESSAGE_ACCOUNT_DOES_NOT_EXIST);
        }

        if (!model.getLoginStatus() && !model.getAdminStatus()) {
            throw new CommandException(MESSAGE_LOGIN_REQUIRED);
        }
        //@@author

        requireNonNull(model);
        if (toSortBy.equals("name") || toSortBy.equals("module") || toSortBy.equals("date")
                || toSortBy.equals("priority")) {
            model.sortTask(toSortBy);
            model.commitTaskManager();
            return new CommandResult(String.format(MESSAGE_SUCCESS, toSortBy));
        } else {
            throw new CommandException(MESSAGE_INVALID);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && toSortBy.equals(((SortCommand) other).toSortBy));

    }
}
