package seedu.address.logic.parser;

import seedu.address.logic.commands.SignupCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.loginInfo.Password;
import seedu.address.model.loginInfo.User;
import seedu.address.model.loginInfo.Username;

import java.util.stream.Stream;

import static seedu.address.logic.commands.LoginCommand.PREFIX_PASSWORD;
import static seedu.address.logic.commands.LoginCommand.PREFIX_USERNAME;


public class SignupCommandParser implements Parser<SignupCommand> {

    public static final String MESSAGE_INVALID_FORMAT = "Invalid format!";

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
