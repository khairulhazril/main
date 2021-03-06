package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.Test;

import seedu.address.logic.commands.FindPriorityCommand;
import seedu.address.model.task.PriorityContainsKeywordsPredicate;


public class FindPriorityCommandParserTest {

    private FindPriorityCommandParser parser = new FindPriorityCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindPriorityCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindPriorityCommand expectedFindPriorityCommand =
                new FindPriorityCommand(new PriorityContainsKeywordsPredicate(Arrays.asList("01-04", "21-04")));
        assertParseSuccess(parser, "01-04 21-04", expectedFindPriorityCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n 01-04 \n \t 21-04  \t", expectedFindPriorityCommand);
    }

}
