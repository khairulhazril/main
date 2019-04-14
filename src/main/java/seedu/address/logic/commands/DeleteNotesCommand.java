package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.notes.Notes;

/**
 * Deletes a note identified using it's displayed index from the task manger.
 */
public class DeleteNotesCommand extends Command {

    public static final String COMMAND_WORD = "deletenote";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the note identified by the index number used in the displayed notes list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_NOTE_SUCCESS = "Deleted Note: %1$s";

    private final Index targetIndex;

    public DeleteNotesCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        //@@author nicholasleeeee
        if (!model.accountExists() && !model.getAdminStatus()) {
            throw new CommandException(MESSAGE_ACCOUNT_DOES_NOT_EXIST);
        }

        if (!model.getLoginStatus() && !model.getAdminStatus()) {
            throw new CommandException(MESSAGE_LOGIN_REQUIRED);
        }
        //@@author

        List<Notes> lastShownList = model.getFilteredNotesList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_NOTES_DISPLAYED_INDEX);
        }

        Notes notesToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteNotes(notesToDelete);
        model.commitTaskManager();
        return new CommandResult(String.format(MESSAGE_DELETE_NOTE_SUCCESS, notesToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteNotesCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteNotesCommand) other).targetIndex)); // state check
    }
}

