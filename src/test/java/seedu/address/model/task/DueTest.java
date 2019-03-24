package seedu.address.model.task;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class DueTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Due(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Due(invalidEmail));
    }

    @Test
    public void isValidDate() {
        // null email
        Assert.assertThrows(NullPointerException.class, () -> Due.isValidDate(null));

        // blank email
        assertFalse(Due.isValidDate("")); // empty string
        assertFalse(Due.isValidDate(" ")); // spaces only

        // missing parts
        assertFalse(Due.isValidDate("0110")); // missing '-' symbol

        // invalid parts
        assertFalse(Due.isValidDate("01-30")); // month greater than 12
        assertFalse(Due.isValidDate("01-00")); // month less than 01
        assertFalse(Due.isValidDate("32-01")); // date greater than 31 for month with 31 days
        assertFalse(Due.isValidDate("31-04")); // date greater than 30 for month with 30 days
        assertFalse(Due.isValidDate("30-02")); // date greater than 28 for Feb
        assertFalse(Due.isValidDate("00-10")); // date less than 01

        // valid date
        assertTrue(Due.isValidDate("01-01"));
        assertTrue(Due.isValidDate("31-01"));
        assertTrue(Due.isValidDate("30-04"));
        assertTrue(Due.isValidDate("28-02"));
    }
}
