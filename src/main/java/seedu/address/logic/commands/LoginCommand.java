package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.account.User;

/**
 * Login command
 */

public class LoginCommand extends Command {
    public static final String COMMAND_WORD = "login";

    public static final String MESSAGE_USAGE = COMMAND_WORD + "USERNAME: "
                                             + PREFIX_USERNAME + "PASSWORD: "
                                             + PREFIX_PASSWORD;
    public static final String MESSAGE_SUCCESS = "Logged in as %1$s";
    public static final String MESSAGE_LOGGED_USER = "You are already logged in!";
    public static final String MESSAGE_LOGGED_ADMIN = "You are logged in as admin.";
    public static final String MESSAGE_NO_ACCOUNT = "Please sign up!";
    public static final String MESSAGE_INVALID_FORMAT = "Please Login again! Invalid username or password "
                                               + "Command Format: [login u/USERNAME p/PASSWORD]";

    private final User loginInfo;

    /**
     * Creates a LoginCommand to login user
     *
     * @param user
     */
    public LoginCommand(User user) {
        requireNonNull(user);
        loginInfo = user;
    }


    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        // Checks if user is already logged in
        if (model.getLoginStatus()) {
            throw new CommandException(MESSAGE_LOGGED_USER);
        }

        // Checks if admin is logged in
        if (model.getAdminStatus()) {
            throw new CommandException(MESSAGE_LOGGED_ADMIN);
        }

        // Checks if user exists and logs into account
        model.loginUser(loginInfo);

        // Incorrect password or username
        if (!model.getLoginStatus()) {
            throw new CommandException(MESSAGE_INVALID_FORMAT);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, loginInfo.getUsername().toString()));
    }
}
