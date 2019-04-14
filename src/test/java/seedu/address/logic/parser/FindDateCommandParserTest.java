package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.Test;

import seedu.address.logic.commands.FindDateCommand;
import seedu.address.model.task.DueContainsKeywordsPredicate;

public class FindDateCommandParserTest {

    private FindDateCommandParser parser = new FindDateCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindDateCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindDateCommand expectedFindDateCommand =
                new FindDateCommand(new DueContainsKeywordsPredicate(Arrays.asList("01-04", "21-04")));
        assertParseSuccess(parser, "01-04 21-04", expectedFindDateCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n 01-04 \n \t 21-04  \t", expectedFindDateCommand);
    }

}
