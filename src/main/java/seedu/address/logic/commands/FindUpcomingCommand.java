package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.task.DueContainsKeywordsPredicate;

/**
 * Finds and lists all tasks in task manager that are due in the next 7 days.
 * Keyword matching is case insensitive.
 */
public class FindUpcomingCommand extends Command {

    public static final String COMMAND_WORD = "findupcoming";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks that are due in the next 7 days\n";

    private final DueContainsKeywordsPredicate predicate;

    public FindUpcomingCommand(DueContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TASKS_LISTED_OVERVIEW, model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindUpcomingCommand // instanceof handles nulls
                && predicate.equals(((FindUpcomingCommand) other).predicate)); // state check
    }
}
