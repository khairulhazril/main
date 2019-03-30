package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;
import static seedu.address.logic.parser.ParserUtil.parsePassword;
import static seedu.address.logic.parser.ParserUtil.parseUsername;

import java.util.stream.Stream;

import seedu.address.logic.commands.SignupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.account.Password;
import seedu.address.model.account.User;
import seedu.address.model.account.Username;

/**
 * SignupCommand Parser
 */
public class SignupCommandParser implements Parser<SignupCommand> {

    public static final String MESSAGE_INVALID_FORMAT = "Invalid format!";

    /**
     * Creates a SignupCommand parser to create a user
     *
     * @param args username and password
     * @return SignupCommand object with a User class containing username and password
     */

    public SignupCommand parse(String args) throws ParseException {

        ArgumentMultimap map = ArgumentTokenizer.tokenize(args, PREFIX_USERNAME, PREFIX_PASSWORD);

        if (!invalidPrefix(map, PREFIX_USERNAME, PREFIX_PASSWORD) || !map.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_FORMAT));
        }

        Username username = parseUsername(map.getValue(PREFIX_USERNAME).get());
        Password password = parsePassword(map.getValue(PREFIX_PASSWORD).get());

        User user = new User(username, password);

        return new SignupCommand(user);
    }

    /**
     * A check for valid prefix
     *
     * @param argumentMultimap
     * @param prefixes
     * @return true if all prefixes are valid
     */
    private static boolean invalidPrefix(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
