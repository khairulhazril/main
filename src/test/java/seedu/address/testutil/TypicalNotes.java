package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.TaskManager;
import seedu.address.model.notes.Notes;

/**
 * A utility class containing a list of notes used in tests.
 */
public class TypicalNotes {

    public static final Notes MEETUP = new NotesBuilder().withHeading("Meetup")
            .withContent("meet friends for dinner")
            .withPriority("3").build();

    public static final Notes ERRAND = new NotesBuilder().withHeading("Errand")
            .withContent("Buy apples home")
            .withPriority("2").build();

    public static final Notes BUSINESS = new NotesBuilder().withHeading("Business")
            .withContent("Sell off ST Engineering stocks once it hits 4")
            .withPriority("1").build();

    public static final Notes LEISURE = new NotesBuilder().withHeading("leisure")
            .withContent("Watch Avengers endgame")
            .withPriority("1").build();


    private TypicalNotes() {
    } // prevents instantiation

    /**
     * Returns an {@code TaskManager} with all the typical notes.
     */
    public static TaskManager getTypicalTaskManager() {
        TaskManager tm = new TaskManager();
        for (Notes notes : getTypicalNotes()) {
            tm.addNotes(notes);
        }
        return tm;
    }

    public static List<Notes> getTypicalNotes() {

        return new ArrayList<>(Arrays.asList(MEETUP, ERRAND, BUSINESS, LEISURE));
    }

}
