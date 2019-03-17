package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.collections.ObservableList;
import seedu.address.commons.util.InvalidationListenerManager;
import seedu.address.model.notes.Notes;
import seedu.address.model.notes.UniqueNotesList;
import seedu.address.model.task.SortTaskList;
import seedu.address.model.task.Task;
import seedu.address.model.task.UniqueTaskList;

/**
 * Wraps all data at the task-manager level
 * Duplicates are not allowed (by .isSameTask comparison)
 */
public class TaskManager implements ReadOnlyTaskManager {

    private final UniqueTaskList tasks;
    private final UniqueNotesList unotes;
    private final InvalidationListenerManager invalidationListenerManager = new InvalidationListenerManager();

    {
        tasks = new UniqueTaskList();
        unotes = new UniqueNotesList();

    }

    public TaskManager() {
    }

    /**
     * Creates an TaskManager using the Tasks in the {@code toBeCopied}
     */
    public TaskManager(ReadOnlyTaskManager toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the task list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks.setTasks(tasks);
        indicateModified();
    }

    /**
     * Resets the existing data of this {@code TaskManager} with {@code newData}.
     */
    public void resetData(ReadOnlyTaskManager newData) {
        requireNonNull(newData);

        setTasks(newData.getTaskList());
    }

    //// task-level operations

    /**
     * Returns true if a task with the same identity as {@code task} exists in the address book.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }

    /**
     * Adds a task to the address book.
     * The task must not already exist in the address book.
     */
    public void addTask(Task p) {
        tasks.add(p);
        indicateModified();
    }

    /**
     * Replaces the given task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the task manager.
     * The task identity of {@code editedTask} must not be the same as another existing task in the task manager.
     */
    public void setTask(Task target, Task editedTask) {
        requireNonNull(editedTask);

        tasks.setTask(target, editedTask);
        indicateModified();
    }

    /**
     * Removes {@code key} from this {@code TaskManager}.
     * {@code key} must exist in the task manager.
     */
    public void removeTask(Task key) {
        tasks.remove(key);
        indicateModified();
    }

    /**
     * Sorts the task list according to the attribute
     */
    public void sortTask(String attribute) {
        requireNonNull(attribute);
        SortTaskList sortList = new SortTaskList();
        ObservableList<Task> copyList = sortList.sortTask(obtainModifiableObservableList(), attribute);
        UniqueTaskList updateList = new UniqueTaskList();
        updateList.setTasks(copyList);
        tasks.setTasks(updateList);
    }

    @Override
    public void addListener(InvalidationListener listener) {
        invalidationListenerManager.addListener(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        invalidationListenerManager.removeListener(listener);
    }

    /**
     * Notifies listeners that the address book has been modified.
     */
    protected void indicateModified() {
        invalidationListenerManager.callListeners(this);
    }

    //// util methods

    @Override
    public String toString() {
        return tasks.asUnmodifiableObservableList().size() + " tasks";
        // TODO: refine later
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return tasks.asUnmodifiableObservableList();
    }

    public ObservableList<Task> obtainModifiableObservableList() {
        return tasks.obtainObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskManager // instanceof handles nulls
                && tasks.equals(((TaskManager) other).tasks));
    }

    @Override
    public int hashCode() {
        return tasks.hashCode();
    }

    //================Notes==================================================

    /**
     * Returns true if a note with the same identity as {@code note} exists in the task manager.
     */
    public boolean hasNotes(Notes notes) {
        requireNonNull(notes);
        return unotes.contains(notes);
    }

    /**
     * Adds a note to the task manager.
     * The note must not already exist in the task manager.
     */
    public void addNotes(Notes n) {
        unotes.add(n);
        indicateModified();
    }

    @Override
    public ObservableList<Notes> getNotesList() {
        return unotes.asUnmodifiableObservableList();
    }


}
