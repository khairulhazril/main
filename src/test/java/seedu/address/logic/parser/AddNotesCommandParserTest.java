package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.CONTENT_DESC_MARKET;
import static seedu.address.logic.commands.CommandTestUtil.CONTENT_DESC_POPULAR;
import static seedu.address.logic.commands.CommandTestUtil.HEADING_DESC_MARKET;
import static seedu.address.logic.commands.CommandTestUtil.HEADING_DESC_POPULAR;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CONTENT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_HEADING_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PRIORITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_MARKET;
import static seedu.address.logic.commands.CommandTestUtil.PRIORITY_DESC_POPULAR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTENT_MARKET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HEADING_MARKET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_MARKET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_TUTORIAL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalNotes.MARKET;

import org.junit.Test;

import seedu.address.logic.commands.AddNotesCommand;
import seedu.address.model.notes.Content;
import seedu.address.model.notes.Heading;
import seedu.address.model.notes.Notes;
import seedu.address.model.task.Priority;
import seedu.address.testutil.NotesBuilder;

public class AddNotesCommandParserTest {

    private AddNotesCommandParser parser = new AddNotesCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Notes expectedNotes = new NotesBuilder(MARKET).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + HEADING_DESC_MARKET + CONTENT_DESC_MARKET
                + PRIORITY_DESC_MARKET, new AddNotesCommand(expectedNotes));

        // multiple names - last name accepted
        assertParseSuccess(parser, HEADING_DESC_POPULAR + HEADING_DESC_MARKET + CONTENT_DESC_MARKET
                + PRIORITY_DESC_MARKET, new AddNotesCommand(expectedNotes));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, HEADING_DESC_MARKET + CONTENT_DESC_MARKET + PRIORITY_DESC_POPULAR
                + PRIORITY_DESC_MARKET, new AddNotesCommand(expectedNotes));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, HEADING_DESC_MARKET + CONTENT_DESC_POPULAR + CONTENT_DESC_MARKET
                + PRIORITY_DESC_MARKET, new AddNotesCommand(expectedNotes));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNotesCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_HEADING_MARKET + CONTENT_DESC_MARKET
                + PRIORITY_DESC_MARKET, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, HEADING_DESC_MARKET + VALID_CONTENT_MARKET
                + PRIORITY_DESC_MARKET, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, HEADING_DESC_MARKET + CONTENT_DESC_MARKET
                + VALID_PRIORITY_MARKET, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_HEADING_MARKET + VALID_CONTENT_MARKET
                + VALID_PRIORITY_TUTORIAL, expectedMessage);
    }


    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_HEADING_DESC + CONTENT_DESC_MARKET
                + PRIORITY_DESC_MARKET, Heading.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, HEADING_DESC_MARKET + INVALID_CONTENT_DESC
                + PRIORITY_DESC_MARKET, Content.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, HEADING_DESC_MARKET + CONTENT_DESC_MARKET
                + INVALID_PRIORITY_DESC, Priority.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_HEADING_DESC + CONTENT_DESC_MARKET
                + INVALID_PRIORITY_DESC, Heading.MESSAGE_CONSTRAINTS);
    }
}
