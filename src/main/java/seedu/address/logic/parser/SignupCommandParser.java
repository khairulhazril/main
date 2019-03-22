package seedu.address.logic.parser;

import static seedu.address.logic.commands.LoginCommand.PREFIX_PASSWORD;
import static seedu.address.logic.commands.LoginCommand.PREFIX_USERNAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.SignupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.login.Password;
import seedu.address.model.login.User;
import seedu.address.model.login.Username;

/**
 * Sign Up Command Parser
 */
public class SignupCommandParser implements Parser<SignupCommand> {

    /**
     * Sign Up Command Parser
     */
    public static final String MESSAGE_INVALID_FORMAT = "Invalid format!";

    /**
     * Parses arguments into command and passes username and password into User class
     * @param args
     * @return
     * @throws ParseException
     */
    public SignupCommand parse(String args) throws ParseException {
        ArgumentMultimap map = ArgumentTokenizer.tokenize(args, PREFIX_USERNAME, PREFIX_PASSWORD);

        if (!invalidPrefix(map, PREFIX_USERNAME, PREFIX_PASSWORD) || !map.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_FORMAT));
        }

        Username username = LoginCommandParser.parseUsername(map.getValue(PREFIX_USERNAME).get());
        Password password = LoginCommandParser.parsePassword(map.getValue(PREFIX_PASSWORD).get());

        User user = new User(username, password);

        return new SignupCommand(user);
    }

    // Returns true if none of the values are empty
    private static boolean invalidPrefix(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}