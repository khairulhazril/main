package seedu.address.model.notes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class HeadingTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Heading(null));
    }

    @Test
    public void constructor_invalidHeading_throwsIllegalArgumentException() {
        String invalidHeading = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Heading(invalidHeading));
    }

    @Test
    public void isValidHeading() {

        Assert.assertThrows(NullPointerException.class, () -> Heading.isValidHeading(null));

        // negative testing
        assertFalse(Heading.isValidHeading("")); // empty string
        assertFalse(Heading.isValidHeading("  ")); //spaces only
        assertFalse(Heading.isValidHeading("-")); // only non-alphanumeric
        assertFalse(Heading.isValidHeading("Go Sheng-Shiong")); // contains non-alphanumeric characters

        // positive testing

        assertTrue(Heading.isValidHeading("visit the florist")); // small letters only
        assertTrue(Heading.isValidHeading("VISIT THE FLORIST")); // Capital letters only
        assertTrue(Heading.isValidHeading("767453")); // numbers only
        assertTrue(Heading.isValidHeading("Cindy 58th birthday")); // Alphanumeric
    }
}
