package seedu.address.model.account;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class UsernameTest {

    /**
     * Test for username constructor
     */
    @Test
    public void null_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Username(null));
    }

    /**
     * Test for blank username
     */
    @Test
    public void invalidUsername_throwIllegalArgumentException() {
        String invalidUsername = "";
        assertThrows(IllegalArgumentException.class, () -> new Username(invalidUsername));
    }

    /**
     * Test cases for null, invalid and valid usernames
     */
    @Test
    public void isValidUsername() {
        assertThrows(NullPointerException.class, () -> Username.isValidUsername(null));

        assertFalse(Username.isValidUsername(""));
        assertFalse(Username.isValidUsername(" "));
        assertFalse(Username.isValidUsername("*"));
        assertFalse(Username.isValidUsername("_n_"));

        assertTrue(Username.isValidUsername("Nicholas Lee"));
        assertTrue(Username.isValidUsername("nicholas lee"));
        assertTrue(Username.isValidUsername("n"));
        assertTrue(Username.isValidUsername("9773 3403"));
        assertTrue(Username.isValidUsername("Nicholas The Great"));

    }



}