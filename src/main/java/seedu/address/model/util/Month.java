package seedu.address.model.util;

import static java.util.Objects.requireNonNull;

/**
 * Represents the calendar's month.
 */
public class Month {
    private String month;

    public Month(String month) {
        requireNonNull(month);
        this.month = month;
    }

    public void setValue(Month month) {
        this.month = month.toString();
    }

    public int toInt() {
        return Integer.parseInt(month);
    }

    @Override
    public String toString() {
        return month;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Month // instanceof handles nulls
                && month.equals(((Month) other).month)); // state check
    }

    @Override
    public int hashCode() {
        return month.hashCode();
    }

}
