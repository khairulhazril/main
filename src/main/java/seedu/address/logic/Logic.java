package seedu.address.logic;

import java.nio.file.Path;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ReadOnlyTaskManager;

import seedu.address.model.notes.Notes;
import seedu.address.model.task.Task;
import seedu.address.model.util.Month;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException   If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the TaskManager.
     *
     * @see seedu.address.model.Model#getTaskManager()
     */
    ReadOnlyTaskManager getTaskManager();

    /**
     * Returns an unmodifiable view of the filtered list of tasks
     */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Returns an unmodifiable view of the filtered list of notes
     */
    ObservableList<Notes> getFilteredNotesList();

    /**
     * Returns an unmodifiable view of the list of commands entered by the user.
     * The list is ordered from the least recent command to the most recent command.
     */
    ObservableList<String> getHistory();

    /**
     * Returns the user prefs' task manager file path.
     */
    Path getTaskManagerFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Selected task in the filtered task list.
     * null if no task is selected.
     *
     * @see seedu.address.model.Model#selectedTaskProperty()
     */
    ReadOnlyProperty<Task> selectedTaskProperty();

    /**
     * Current month of the calendar.
     * Current month according to system clock if not defined.
     *
     * @see seedu.address.model.Model#currentMonthProperty()
     */
    ReadOnlyProperty<Month> currentMonthProperty();

    /**
     * Sets the selected task in the filtered task list.
     *
     * @see seedu.address.model.Model#setSelectedTask(Task)
     */
    void setSelectedTask(Task task);

    /**
     * Selected notes in the filtered notes list.
     * null if no notes is selected.
     *
     * @see seedu.address.model.Model#selectedNotesProperty()
     */
    ReadOnlyProperty<Notes> selectedNotesProperty();

    /**
     * Sets the selected note in the filtered notes list.
     *
     * @see seedu.address.model.Model#setSelectedNotes(Notes)
     */
    void setSelectedNotes(Notes notes);

    /**
     * Returns the current month of the model.
     */
    Month getCurrentMonth();
}
