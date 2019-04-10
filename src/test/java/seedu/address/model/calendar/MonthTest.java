package seedu.address.model.calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import seedu.address.model.util.Month;
import seedu.address.testutil.Assert;

public class MonthTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Month(null));
    }

    @Test
    public void equals() {
        Month month1 = new Month("1");
        Month month2 = new Month("1");

        assertEquals(month1, month2);
    }

    @Test
    public void outputTest() {
        String testString = "11";
        int testInt = Integer.parseInt(testString);
        Month month1 = new Month(testString);

        assertEquals(month1.toString(), testString);
        assertEquals(month1.toInt(), testInt);
    }

    @Test
    public void setValueTest() {
        Month month1 = new Month("2");
        Month month2 = new Month("12");

        assertNotEquals(month1.toString(), month2.toString());
        assertNotEquals(month1.toInt(), month2.toInt());

        month1.setValue(month2);

        assertEquals(month1.toString(), month2.toString());
        assertEquals(month1.toInt(), month2.toInt());
    }
}
