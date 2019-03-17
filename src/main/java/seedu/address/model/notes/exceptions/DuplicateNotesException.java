package seedu.address.model.notes.exceptions;

/**
 * Signals that the operation will result in duplicate Notes (Notes are considered duplicates if they have the same
 * identity).
 */
public class DuplicateNotesException extends RuntimeException {
    public DuplicateNotesException() {
        super("Operation would result in duplicate notes");
    }
}
