package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.MonthCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses
 */
public class MonthCommandParser implements Parser<MonthCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindDateCommand
     * and returns an FindDateCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public MonthCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MonthCommand.MESSAGE_USAGE));
        }

        String date = trimmedArgs;

        return new MonthCommand(date);
    }
}
