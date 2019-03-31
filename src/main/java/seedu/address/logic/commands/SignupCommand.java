package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PASSWORD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_USERNAME;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.account.User;

/**
 * SignUp Command
 */
public class SignupCommand extends Command {

    public static final String COMMAND_WORD = "signup";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sign up for an Event Manager account. "
            + "Parameters: "
            + PREFIX_USERNAME + "USERNAME "
            + PREFIX_PASSWORD + "PASSWORD";

    public static final String MESSAGE_LOGGED = "You are logged in as %1$s!";
    public static final String MESSAGE_SUCCESS = "Signed up: %1$s";
    public static final String MESSAGE_USER_EXISTS = "Please try another username!";
    public static final String MESSAGE_ACCOUNT_EXISTS = "Account has already been created!";
    public static final String MESSAGE_INVALID_FORMAT = "Invalid format! "
                                                      + "Command Format: [signup u/USERNAME p/PASSWORD]";

    private final User signingUp;

    /**
     * Creates a SignupCommand to create a user
     *
     * @param user
     */
    public SignupCommand(User user) {
        requireNonNull(user);
        signingUp = user;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        // User is already logged in
        if (model.getLoginStatus()) {
            throw new CommandException(String.format(MESSAGE_LOGGED, model.getUsername().toString()));
        }
        // The account is already exist
        if (model.userExists(signingUp)) {
            throw new CommandException(MESSAGE_USER_EXISTS);
        }

        if (model.accountExists()) {
            throw new CommandException(MESSAGE_ACCOUNT_EXISTS);
        }
        // Signs up the user with a new account
        model.newUser(signingUp);
        return new CommandResult(String.format(MESSAGE_SUCCESS, signingUp.getUsername().toString()));

    }


}
