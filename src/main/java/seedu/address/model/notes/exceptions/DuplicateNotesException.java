package seedu.address.model.notes.exceptions;

public class DuplicateNotesException extends RuntimeException {
    public DuplicateNotesException() {
        super("Operation would result in duplicate notes");
    }
}
