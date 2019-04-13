package seedu.address.model.account;

import seedu.address.testutil.AccountBuilder;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PASSWORD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USERNAME;
import static seedu.address.testutil.TypicalAccounts.KAYDEN;
import static seedu.address.testutil.TypicalAccounts.NICHOLAS;

public class UserTest {

    @Test
    public void equals() {

        /**
         * Same account details -> return true
         */
        User nicholasAccount = new AccountBuilder(NICHOLAS).build();
        assertTrue(NICHOLAS.equals(nicholasAccount));

        /**
         * Same object -> return true
         */
        assertTrue(NICHOLAS.equals(NICHOLAS));


        /**
         * Null information -> return false
         */
        assertFalse(NICHOLAS.equals(null));

        /**
         * Invalid parameters -> return false
         */
        assertFalse(NICHOLAS.equals(5));

        /**
         * Different user -> returns false
         */
        assertFalse(NICHOLAS.equals(KAYDEN));

        /**
         * Different username -> return false
         */
        User editedNicholas = new AccountBuilder(NICHOLAS).setUsername(VALID_USERNAME).build();
        assertFalse(NICHOLAS.equals(editedNicholas));

        /**
         * Different account -> return false
         */
        editedNicholas = new AccountBuilder(NICHOLAS).setPassword(VALID_PASSWORD).build();
        assertFalse(NICHOLAS.equals(editedNicholas));
    }


}
