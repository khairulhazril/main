package seedu.address.model.notes;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.notes.exceptions.DuplicateNotesException;

public class UniqueNotesList implements Iterable<Notes> {

    private final ObservableList<Notes> internalList = FXCollections.observableArrayList();
    private final ObservableList<Notes> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);


    /**
     * Returns true if the list contains an equivalent note as the given argument.
     */
    public boolean contains(Notes toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameNotes);
    }

    /**
     * Adds a note to the list.
     * The note must not already exist in the list.
     */
    public void add(Notes toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateNotesException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the list.
     * The task identity of {@code editedTask} must not be the same as another existing task in the list.
     */
    /*public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TaskNotFoundException();
        }

        if (!target.isSameTask(editedTask) && contains(editedTask)) {
            throw new DuplicateTaskException();
        }

        internalList.set(index, editedTask);
    }*/

    /*public void setTasks(UniqueTaskList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }*/

    /**
     * Replaces the contents of this list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    /*public void setTasks(List<Task> tasks) {
        requireAllNonNull(tasks);
        if (!tasksAreUnique(tasks)) {
            throw new DuplicateTaskException();
        }

        internalList.setAll(tasks);
    }*/

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Notes> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Notes> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueNotesList // instanceof handles nulls
                && internalList.equals(((UniqueNotesList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code tasks} contains only unique notes.
     */
    private boolean notesAreUnique(List<Notes> notes) {
        for (int i = 0; i < notes.size() - 1; i++) {
            for (int j = i + 1; j < notes.size(); j++) {
                if (notes.get(i).isSameNotes(notes.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}


