package seedu.address.model.account;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.Test;

public class PasswordTest {

    /**
     * Test for password constructor
     */
    @Test
    public void null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Password(null));
    }

    /**
     * Test for null password
     */
    @Test
    public void invalidPassword_throwsIllegalArgumentException() {
        String invalidPassword = "";
        assertThrows(IllegalArgumentException.class, () -> new Password(invalidPassword));
    }

    /**
     *  Test cases for null, invalid and valid password
     */
    @Test
    public void isValidPassword() {
        assertThrows(NullPointerException.class, () -> Password.isValidPassword(null));

        assertFalse(Password.isValidPassword(""));
        assertFalse(Password.isValidPassword(" "));
        assertFalse(Password.isValidPassword("\""));
        assertFalse(Password.isValidPassword("nicholas lee"));

        assertTrue(Password.isValidPassword("nicholas97"));
        assertTrue(Password.isValidPassword("!#$%&'*+/=?`{|}~^.@-"));
        assertTrue(Password.isValidPassword("nicholas97!#$%&'*+/=?`{|}~^.@-"));
    }

}
