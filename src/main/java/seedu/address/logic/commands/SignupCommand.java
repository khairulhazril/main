package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.loginInfo.User;

import com.fasterxml.jackson.annotation.JsonCreator;
import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.LoginCommand.PREFIX_PASSWORD;
import static seedu.address.logic.commands.LoginCommand.PREFIX_USERNAME;

public class SignupCommand extends Command {

    public static final String COMMAND_WORD = "signup";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sign up for an Event Manager account. "
            + "Parameters: "
            + PREFIX_USERNAME + "USERNAME "
            + PREFIX_PASSWORD + "PASSWORD";

    public static final String MESSAGE_LOGGED = "Logged in: %1$s!";
    public static final String MESSAGE_SUCCESS = "Signed up: %1$s";
    public static final String MESSAGE_EXISTS = "Please try another username";

    private final User signingUp;

    public SignupCommand(User user) {
        requireNonNull(user);
        signingUp = user;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.getLoginStatus()) {
            throw new CommandException(String.format(MESSAGE_LOGGED, model.getUsername().toString()));
        }

        if (model.userExists(signingUp)){
            throw new CommandException(MESSAGE_EXISTS);
        }

        model.newUser(signingUp);
        return new CommandResult(String.format(MESSAGE_SUCCESS, signingUp.getUsername().toString()));

    }


}
