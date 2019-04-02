package seedu.address.model.notes;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.task.Priority;

/**
 * Represents a Note in the task manager.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Notes {

    private final Heading heading;
    private final Content content;
    private final Priority priority;

    public Notes(Heading heading, Content content, Priority priority) {
        requireAllNonNull(heading, content, priority);
        this.heading = heading;
        this.content = content;
        this.priority = priority;

    }

    public Heading getHeading() {
        return heading;
    }

    public Content getContent() {
        return content;
    }

    public Priority getPriority() {
        return priority; }

    /**
     * Returns true if both notes of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two notes.
     */
    public boolean isSameNotes(Notes otherNotes) {
        if (otherNotes == this) {
            return true;
        }

        return otherNotes != null
                && otherNotes.getHeading().equals(getHeading())
                && (otherNotes.getContent().equals(getContent()));
    }

    /**
     * Returns true if both notes have the same identity and data fields.
     * This defines a stronger notion of equality between two notes.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Notes)) {
            return false;
        }

        Notes otherNotes = (Notes) other;
        return otherNotes.getHeading().equals(getHeading())
                && otherNotes.getContent().equals(getContent());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(heading, content, priority);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Heading: ")
                .append(getHeading())
                .append(" Content: ")
                .append(getContent())
                .append(" Priority: ")
                .append(getPriority());
        return builder.toString();
    }


}
