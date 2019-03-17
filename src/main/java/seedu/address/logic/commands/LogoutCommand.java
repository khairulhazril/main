package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.loginInfo.Username;

import static java.util.Objects.requireNonNull;

public class LogoutCommand extends Command {

    public static final String COMMAND_WORD = "logout";
    public static final String MESSAGE_SUCCESS = "Logged out of %1$s";
    public static final String MESSAGE_LOGIN = "Please Login!";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws  CommandException{
        requireNonNull(model);

        if (!model.getLoginStatus()){
            throw new CommandException(MESSAGE_LOGIN);
        }

        Username loginUsername = model.getUsername();
        model.logout();

        return new CommandResult(String.format(MESSAGE_SUCCESS, loginUsername.toString()));
    }
}
