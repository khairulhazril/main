package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.login.User;
import seedu.address.model.login.Username;
import seedu.address.model.notes.Notes;
import seedu.address.model.task.Task;

/**
 * The API of the Model component.
 */
public interface Model {

    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;
    Predicate<Notes> PREDICATE_SHOW_ALL_NOTES = unused -> true;

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' task manager file path.
     */
    Path getTaskManagerFilePath();

    /**
     * Sets the user prefs' task manager file path.
     */
    void setTaskManagerFilePath(Path taskManagerFilePath);

    /**
     * Returns the TaskManager
     */
    ReadOnlyTaskManager getTaskManager();

    /**
     * Replaces task manager data with the data in {@code taskManager}.
     */
    void setTaskManager(ReadOnlyTaskManager taskManager);

    /**
     * Returns true if a task with the same identity as {@code task} exists in the task manager.
     */
    boolean hasTask(Task task);

    /**
     * Deletes the given task.
     * The task must exist in the task manager.
     */
    void deleteTask(Task target);

    /**
     * Adds the given task.
     * {@code task} must not already exist in the task manager.
     */
    void addTask(Task task);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in the task manager.
     * The task identity of {@code editedTask} must not be the same as another existing task in the task manager.
     */
    void setTask(Task target, Task editedTask);

    /**
     * Sorts the model's task manager according the chosen attribute
     */
    void sortTask(String attribute);

    /**
     * Returns an unmodifiable view of the filtered task list
     */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);

    /**
     * Returns true if the model has previous task manager states to restore.
     */
    boolean canUndoTaskManager();

    /**
     * Returns true if the model has undone task manager states to restore.
     */
    boolean canRedoTaskManager();

    /**
     * Restores the model's task manger to its previous state.
     */
    void undoTaskManager();

    /**
     * Restores the model's task manager to its previously undone state.
     */
    void redoTaskManager();

    /**
     * Saves the current task manager state for undo/redo.
     */
    void commitTaskManager();

    /**
     * Selected task in the filtered task list.
     * null if no task is selected.
     */
    ReadOnlyProperty<Task> selectedTaskProperty();

    /**
     * Returns the selected task in the filtered task list.
     * null if no task is selected.
     */
    Task getSelectedTask();

    /**
     * Sets the selected task in the filtered task list.
     */
    void setSelectedTask(Task task);

    //===================== Login Information ========================================================

    /**
     * Returns true if the user is logged in.
     */
    boolean getLoginStatus();

    /**
     * Return Username of logged in user.
     */
    Username getUsername();

    /**
     *  User logs out of Task Manager.
     */
    void logout();

    /**
     * User login to Task Manager.
     */
    void loginUser(User loginInfo);

    /**
     *  Returns true if user exists
     */
    boolean userExists(User user);

    /**
     * A new user is registered in Task Manager
     */
    void newUser(User user);

    //=====================Notes========================================================

    /**
     * Returns true if a note with the same identity as {@code notes} exists in the task manager.
     */
    boolean hasNotes(Notes notes);


    /**
     * Adds the given note.
     * {@code notes} must not already exist in the task manager.
     */
    void addNotes(Notes notes);


    /**
     * Returns an unmodifiable view of the filtered notes list
     */
    ObservableList<Notes> getFilteredNotesList();


    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     *
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredNotesList(Predicate<Notes> predicate);

    /**
     * Selected task in the filtered task list.
     * null if no task is selected.
     */
    ReadOnlyProperty<Notes> selectedNotesProperty();

    /**
     * Sets the selected task in the filtered task list.
     */
    void setSelectedNotes(Notes notes);

    /**
     * Returns the selected task in the filtered task list.
     * null if no task is selected.
     */
    Notes getSelectedNotes();
}
