package seedu.address.logic.parser;

import static seedu.address.logic.commands.LoginCommand.MESSAGE_FAILURE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;
import static seedu.address.logic.parser.ParserUtil.parsePassword;
import static seedu.address.logic.parser.ParserUtil.parseUsername;

import java.util.stream.Stream;

import seedu.address.logic.commands.LoginCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.account.Password;
import seedu.address.model.account.User;
import seedu.address.model.account.Username;



/**
 * Login Command Parser
 */
public class LoginCommandParser implements Parser<LoginCommand> {

    /**
     * Creates a LoginCommand parser to log the user in
     *
     * @param args username and password
     * @return LoginCommand object with a User class containing username and password
     */
    public LoginCommand parse(String args) throws ParseException {

        ArgumentMultimap map = ArgumentTokenizer.tokenize(args, PREFIX_USERNAME, PREFIX_PASSWORD);

        if (!invalidPrefix(map, PREFIX_USERNAME, PREFIX_PASSWORD) || !map.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_FAILURE));
        }

        Username username = parseUsername(map.getValue(PREFIX_USERNAME).get());
        Password password = parsePassword(map.getValue(PREFIX_PASSWORD).get());

        User user = new User(username, password);

        return new LoginCommand(user);
    }

    /**
     * A check for valid prefix
     *
     * @param argumentMultimap
     * @param prefixes
     * @return true if none of the values are empty
     */
    private static boolean invalidPrefix(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
