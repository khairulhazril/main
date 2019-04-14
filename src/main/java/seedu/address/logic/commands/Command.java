package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public abstract class Command {

    /**
     * Executes the command and returns the result message.
     *
     * @param model   {@code Model} which the command should operate on.
     * @param history {@code CommandHistory} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    public static final String MESSAGE_LOGIN_REQUIRED = "You need to login to use this command.";
    public static final String MESSAGE_ACCOUNT_DOES_NOT_EXIST = "Please sign up and login to use this command.";

    public abstract CommandResult execute(Model model, CommandHistory history) throws CommandException;

}
