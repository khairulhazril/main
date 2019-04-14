package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEADING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.notes.Notes;


/**
* Adds a note to the task manager.
*/
public class AddNotesCommand extends Command {

    public static final String COMMAND_WORD = "note";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a note to the address book. "
        + "Parameters: "
        + PREFIX_HEADING + "HEADING "
        + PREFIX_CONTENT + "CONTENT "
        + PREFIX_PRIORITY + "PRIORITY\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_HEADING + "STATIONARY "
        + PREFIX_CONTENT + "REMEMBER TO BUY BLUE PEN "
        + PREFIX_PRIORITY + "3 ";

    public static final String MESSAGE_SUCCESS = "New note added: %1$s";
    public static final String MESSAGE_DUPLICATE_NOTE = "This note already exists in the task list";

    private final Notes wantAdd;

    /**
    * Creates an AddCommand to add the specified {@code Task}
    */
    public AddNotesCommand(Notes notes) {
        requireNonNull(notes);
        wantAdd = notes;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.accountExists()) {
            throw new CommandException(MESSAGE_ACCOUNT_DOES_NOT_EXIST);
        }

        if (!model.getLoginStatus() && !model.getAdminStatus()) {
            throw new CommandException(MESSAGE_LOGIN_REQUIRED);
        }

        if (model.hasNotes(wantAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_NOTE);
        }

        model.addNotes(wantAdd);
        model.commitTaskManager();
        model.addJsonNotes(wantAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, wantAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof seedu.address.logic.commands.AddNotesCommand // instanceof handles nulls
            && wantAdd.equals(((AddNotesCommand) other).wantAdd));
    }
}


