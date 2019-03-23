package seedu.address.model.notes;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.notes.exceptions.DuplicateNotesException;
import seedu.address.model.notes.exceptions.NotesNotFoundException;

/**
 * A list of notes that enforces uniqueness between its elements and does not allow nulls.
 * A note is considered unique by comparing using {@code Notes#isSameNotes(Notes)}. As such, adding and updating of
 * notes uses Notes#isSameNotes(Notes) for equality so as to ensure that the note being added or updated is
 * unique in terms of identity in the UniqueNotesList.
 * <p>
 * Supports a minimal set of list operations.
 *
 * @see Notes#isSameNotes(Notes)
 */
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
     * Removes the equivalent note from the list.
     * The note must exist in the list.
     */
    public void remove(Notes toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new NotesNotFoundException();
        }
    }

    /**
     * Replaces the task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the list.
     * The task identity of {@code editedTask} must not be the same as another existing task in the list.
     */
    public void setNotes(Notes target, Notes editedNotes) {
        requireAllNonNull(target, editedNotes);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new NotesNotFoundException();
        }

        if (!target.isSameNotes(editedNotes) && contains(editedNotes)) {
            throw new DuplicateNotesException();
        }

        internalList.set(index, editedNotes);
    }

    public void setNotes(UniqueNotesList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    public void setTasks(List<Notes> notes) {
        requireAllNonNull(notes);
        if (!NotesAreUnique(notes)) {
            throw new DuplicateNotesException();
        }

        internalList.setAll(notes);
    }

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
     * Returns true if {@code notes} contains only unique notes.
     */
    private boolean NotesAreUnique(List<Notes> notes) {
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


