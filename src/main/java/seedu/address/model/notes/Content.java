package seedu.address.model.notes;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Note's content in the task manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidContent(String)}
 */
public class Content {

    public static final String MESSAGE_CONSTRAINTS =
            "Content should only contain alphanumeric characters and spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String realContent;

    /**
     * Constructs a {@code Content}.
     *
     * @param content A valid content.
     */
    public Content(String content) {
        requireNonNull(content);
        checkArgument(isValidContent(content), MESSAGE_CONSTRAINTS);
        realContent = content;
    }

    /**
     * Returns true if a given string is a valid content.
     */
    public static boolean isValidContent(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return realContent;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Content // instanceof handles nulls
                && realContent.equals(((Content) other).realContent)); // state check
    }

    @Override
    public int hashCode() {
        return realContent.hashCode();
    }
}
