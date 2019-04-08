package seedu.address.model.notes;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.address.testutil.Assert;

public class ContentTest {

        @Test
        public void constructor_null_throwsNullPointerException() {
            Assert.assertThrows(NullPointerException.class, () -> new Content(null));
        }

        @Test
        public void constructor_invalidContent_throwsIllegalArgumentException() {
            String invalidContent = "";
            Assert.assertThrows(IllegalArgumentException.class, () -> new Content(invalidContent));
        }

        @Test
        public void isValidContent() {

            Assert.assertThrows(NullPointerException.class, () -> Content.isValidContent(null));

            // negative testing
            assertFalse(Content.isValidContent("")); // empty string
            assertFalse(Content.isValidContent("  ")); //spaces only
            assertFalse(Content.isValidContent("-")); // only non-alphanumeric
            assertFalse(Content.isValidContent("Visit Jordan_Liew")); // contains non-alphanumeric characters

            // positive testing

            assertTrue(Content.isValidContent("buy some eggs")); // small letters only
            assertTrue(Content.isValidContent("BUY SOME EGGS")); // Capital letters only
            assertTrue(Content.isValidContent("45634")); // numbers only
            assertTrue(Content.isValidContent("Meet friends for dinner at Bedok 85")); // Alphanumeric
            assertTrue(Content.isValidContent("Buy eggs chicken beef pork papaya milk pokka green tea ribena and all the infinity stones")); //long content
        }
}
