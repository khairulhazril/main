package seedu.address.model.notes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.TypicalNotes.BUSINESS;
import static seedu.address.testutil.TypicalNotes.ERRAND;
import static seedu.address.testutil.TypicalNotes.LEISURE;
import static seedu.address.testutil.TypicalNotes.MEETUP;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.testutil.NotesBuilder;


public class NotesTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void isSameNotes() {
        // same object -> returns true
        assertTrue(MEETUP.isSameNotes(MEETUP));

        // null -> returns false
        assertFalse(MEETUP.isSameNotes(null));
    }

    @Test
    public void equals() {


        // same object
        assertTrue(LEISURE.equals(LEISURE));

        //new variable assigned with same object
        Notes samenote = new NotesBuilder(BUSINESS).build();
        assertTrue(BUSINESS.equals(samenote));

        // null
        assertFalse(ERRAND.equals(null));

        // different type
        assertFalse(LEISURE.equals(99));

        // different objects
        assertFalse(BUSINESS.equals(MEETUP));

        // different heading
        Notes othernotes = new NotesBuilder().withHeading("visiting").withContent("meet friends for dinner")
                .withPriority("3").build();

        assertFalse(MEETUP.equals(othernotes));

        // different content
        othernotes = new NotesBuilder().withHeading("leisure").withContent("Watch Power Rangers")
                .withPriority("1").build();

        assertFalse(LEISURE.equals(othernotes));

        //different priority
        othernotes = new NotesBuilder().withHeading("Errand").withContent("Buy apples home").withPriority("1").build();
        assertTrue(ERRAND.equals(othernotes));

    }
}
