package seedu.address.model.notes;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Heading {

    public static final String MESSAGE_CONSTRAINTS =
            "Headings should only contain alphanumeric characters and spaces, and it should not be blank";


    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String realHeading;

    /**
     * Constructs a {@code Heading}.
     *
     * @param heading A valid heading.
     */
    public Heading(String heading) {
        requireNonNull(heading);
        checkArgument(isValidHeading(heading), MESSAGE_CONSTRAINTS);
        realHeading = heading;
    }

    /**
     * Returns true if a given string is a valid heading.
     */
    public static boolean isValidHeading(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return realHeading;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Heading // instanceof handles nulls
                && realHeading.equals(((Heading) other).realHeading)); // state check
    }

    @Override
    public int hashCode() {
        return realHeading.hashCode();
    }


}
