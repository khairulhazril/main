package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Task}'s {@code Due} matches any of the keywords given.
 */
public class DueContainsKeywordsPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public DueContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getDue().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DueContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((DueContainsKeywordsPredicate) other).keywords)); // state check
    }

}
