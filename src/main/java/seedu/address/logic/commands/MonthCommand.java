package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.util.Month;

/**
 * Changes the displayed month of the calendar
 */
public class MonthCommand extends Command {

    public static final String COMMAND_WORD = "month";
    public static final String COMMAND_ALIAS = "m";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Changes the month displayed on the calendar.\n"
            + "Parameters: MONTH (must be an integer between 1 and 12 inclusive)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String VALIDATION_REGEX = "[\\d]+";

    public static final String MESSAGE_MONTH_CHANGE_SUCCESS = "Month changed";
    public static final String MESSAGE_DUPLICATE_MONTH = "The requested month is already being displayed";
    public static final String MESSAGE_INVALID_MONTH = "Invalid month input!\n" + MESSAGE_USAGE;

    private static final int MONTH_MIN = 1;
    private static final int MONTH_MAX = 12;

    private final String month;

    public MonthCommand(String month) {
        this.month = month;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.getLoginStatus() && !model.getAdminStatus()) {
            throw new CommandException(MESSAGE_LOGIN_REQUIRED);
        }

        Month newMonth = new Month(month);

        if (newMonth.equals(model.getMonth())) {
            throw new CommandException(MESSAGE_DUPLICATE_MONTH);
        }

        if (!isValidMonth(newMonth.toString())) {
            throw new CommandException(MESSAGE_INVALID_MONTH);
        }

        model.setMonth(newMonth);
        return new CommandResult(MESSAGE_MONTH_CHANGE_SUCCESS);
    }

    /**
     * Returns if a given string is a valid month.
     */
    private boolean isValidMonth(String test) {
        if (test.matches(VALIDATION_REGEX)) {
            int month = Integer.parseInt(test);

            if (month >= MONTH_MIN && month <= MONTH_MAX) {
                return true;
            }
        }
        return false;
    }
}
