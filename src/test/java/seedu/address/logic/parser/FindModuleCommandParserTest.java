package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.Test;

import seedu.address.logic.commands.FindDateCommand;
import seedu.address.logic.commands.FindModuleCommand;
import seedu.address.model.task.ModuleContainsKeywordsPredicate;


public class FindModuleCommandParserTest {

    private FindModuleCommandParser parser = new FindModuleCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindModuleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindModuleCommand expectedFindModuleCommand =
                new FindModuleCommand(new ModuleContainsKeywordsPredicate(Arrays.asList("CS2113T", "CS2101")));
        assertParseSuccess(parser, "CS2113T CS2101", expectedFindModuleCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n CS2113T \n \t CS2101  \t", expectedFindModuleCommand);
    }

}
