package seedu.address.testutil;

import seedu.address.model.notes.Content;
import seedu.address.model.notes.Heading;
import seedu.address.model.notes.Notes;
import seedu.address.model.task.Priority;

public class NotesBuilder {

    public static final String DEFAULT_CONTENT = "Buy eggs for dinner just like always";
    public static final String DEFAULT_HEADING = "Going to the market";
    public static final String DEFAULT_PRIORITY = "1";

    private Content content;
    private Heading heading;
    private Priority priority;

    public NotesBuilder() {
        content = new Content(DEFAULT_CONTENT);
        heading = new Heading(DEFAULT_HEADING);
        priority = new Priority(DEFAULT_PRIORITY);
    }

    /**
     * Initializes the TodoBuilder with the data of {@code todoToCopy}.
     */
    public NotesBuilder(Notes notes) {
        content = notes.getContent();
        heading = notes.getHeading();
        priority = notes.getPriority();
    }

    /**
     * Sets the {@code Content} of the {@code Todo} that we are building.
     */
    public NotesBuilder withContent(String content) {
        this.content = new Content(content);
        return this;
    }

    /**
     * Sets the {@code Title} of the {@code Todo} that we are building.
     */
    public NotesBuilder withHeading(String heading) {
        this.heading = new Heading(heading);
        return this;
    }

    public NotesBuilder withPriority(String priority) {
        this.priority = new Priority(priority);
        return this;
    }

    public Notes build() {
        return new Notes(heading, content, priority);
    }
}
