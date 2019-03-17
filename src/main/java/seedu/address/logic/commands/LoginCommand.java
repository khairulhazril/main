package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.login.User;

/**
 * Login command
 */
public class LoginCommand extends Command {
    public static final String COMMAND_WORD = "login";

    public static final Prefix PREFIX_USERNAME = new Prefix("u/");
    public static final Prefix PREFIX_PASSWORD = new Prefix("p/");
    public static final String MESSAGE_USAGE = COMMAND_WORD + "USERNAME: " + PREFIX_USERNAME + "PASSWORD: "
            + PREFIX_PASSWORD;
    public static final String MESSAGE_SUCCESS = "Logged in as %1$s";
    public static final String MESSAGE_LOGGED = "You are already logged in!";
    public static final String MESSAGE_FAILURE = "Please Login again!";

    private final User loginInfo;

    // Draws user info in command line
    public LoginCommand(User user) {
        requireNonNull(user);
        loginInfo = user;
    }


    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.getLoginStatus()) {
            throw new CommandException(String.format(MESSAGE_LOGGED, model.getUsername().toString()));
        }

        model.loginUser(loginInfo);

        if (!model.getLoginStatus()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, loginInfo.getUsername().toString()));
    }
}
