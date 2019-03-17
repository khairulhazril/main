package seedu.address.logic.parser;

import seedu.address.logic.commands.LoginCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.loginInfo.Password;
import seedu.address.model.loginInfo.User;
import seedu.address.model.loginInfo.Username;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.LoginCommand.MESSAGE_FAILURE;
import static seedu.address.logic.commands.LoginCommand.PREFIX_PASSWORD;
import static seedu.address.logic.commands.LoginCommand.PREFIX_USERNAME;

public class LoginCommandParser implements Parser<LoginCommand> {

    // Parses arguments into login command and returns login command object to excute.
    public LoginCommand parse(String args) throws ParseException {

        ArgumentMultimap map = ArgumentTokenizer.tokenize(args, PREFIX_USERNAME, PREFIX_PASSWORD);

        if (!invalidPrefix(map, PREFIX_USERNAME, PREFIX_PASSWORD) || !map.getPreamble().isEmpty()){
            throw new ParseException(String.format(MESSAGE_FAILURE));
        }

        Username username = parseUsername(map.getValue(PREFIX_USERNAME).get());
        Password password = parsePassword(map.getValue(PREFIX_PASSWORD).get());

        User user = new User(username, password);

        return new LoginCommand(user);
    }

    // Returns true if none of the values are empty
    private static boolean invalidPrefix(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    // Makes sure username is valid and in the right form
    public static Username parseUsername(String username) throws ParseException {
        requireNonNull(username);
        String trimUsername = username.trim();

        if (!Username.isValidUsername(trimUsername)){
            throw new ParseException(Username.MESSAGE_USERNAME_CONSTRAINTS);
        }
        return new Username(trimUsername);
    }

    // Makes sure password is valid and in the right form
    public static Password parsePassword(String password) throws ParseException {
        requireNonNull(password);
        String trimPassword = password.trim();

        if (!Password.isValidPassword(trimPassword)){
            throw new ParseException(Password.MESSAGE_PASSWORD_CONSTRAINTS);
        }
        return new Password(trimPassword);
    }

}
