package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.task.PriorityContainsKeywordsPredicate;

/**
 * Finds and lists all task in task manager whose priority code contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindPriorityCommand extends Command {

    public static final String COMMAND_WORD = "findpriority";
    public static final String COMMAND_ALIAS = "fp";


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks whose priority code contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " 1";

    private final PriorityContainsKeywordsPredicate predicate;

    public FindPriorityCommand(PriorityContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);

        if (!model.accountExists() && !model.getAdminStatus()) {
            throw new CommandException(MESSAGE_ACCOUNT_DOES_NOT_EXIST);
        }

        if (!model.getLoginStatus() && !model.getAdminStatus()) {
            throw new CommandException(MESSAGE_LOGIN_REQUIRED);
        }

        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindPriorityCommand // instanceof handles nulls
                && predicate.equals(((FindPriorityCommand) other).predicate)); // state check
    }
}
