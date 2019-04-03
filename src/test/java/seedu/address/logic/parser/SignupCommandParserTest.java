package seedu.address.logic.parser;

import seedu.address.logic.commands.SignupCommand;
import seedu.address.model.account.Password;
import seedu.address.model.account.User;
import seedu.address.model.account.Username;


import org.junit.Test;

import static seedu.address.logic.commands.CommandTestUtil.INVALID_PASSWORD_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_USERNAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PASSWORD_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.USERNAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PASSWORD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USERNAME;
import static seedu.address.logic.commands.SignupCommand.MESSAGE_INVALID_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class SignupCommandParserTest {

    private SignupCommandParser parserTest = new SignupCommandParser();

    @Test
    public void parseSuccess() {

        Username username = new Username(VALID_USERNAME);
        Password password = new Password(VALID_PASSWORD);
        User currentUser = new User(username, password);


        assertParseSuccess(parserTest, PREAMBLE_WHITESPACE + USERNAME_DESC + PASSWORD_DESC,
                new SignupCommand(currentUser));

        assertParseSuccess(parserTest, USERNAME_DESC + PASSWORD_DESC,
                new SignupCommand(currentUser));

    }

    @Test
    public void parse_missingField_failure() {

        String expectedMessage = String.format(MESSAGE_INVALID_FORMAT, SignupCommand.MESSAGE_USAGE);

        assertParseFailure(parserTest, USERNAME_DESC + VALID_PASSWORD, expectedMessage);

        assertParseFailure(parserTest, VALID_USERNAME + PASSWORD_DESC, expectedMessage);

        assertParseFailure(parserTest, VALID_USERNAME + VALID_PASSWORD, expectedMessage);
    }

    @Test
    public void parse_invalidDetails_failure() {

        assertParseFailure(parserTest, INVALID_USERNAME_DESC + PASSWORD_DESC,
                Username.MESSAGE_USERNAME_CONSTRAINTS);

        assertParseFailure(parserTest, USERNAME_DESC + INVALID_PASSWORD_DESC,
                Password.MESSAGE_PASSWORD_CONSTRAINTS);

        assertParseFailure(parserTest, INVALID_USERNAME_DESC + INVALID_PASSWORD_DESC,
                Username.MESSAGE_USERNAME_CONSTRAINTS);

        assertParseFailure(parserTest, PREAMBLE_NON_EMPTY + USERNAME_DESC + PASSWORD_DESC,
                String.format(MESSAGE_INVALID_FORMAT, SignupCommand.MESSAGE_USAGE));
    }




}
