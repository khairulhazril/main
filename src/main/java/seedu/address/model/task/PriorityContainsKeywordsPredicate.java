package seedu.address.model.task;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

/**
 * Tests that a {@code Task}'s {@code Name} matches any of the keywords given.
 */
public class PriorityContainsKeywordsPredicate implements Predicate<Task> {
    private final List<String> keywords;

    public PriorityContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Task task) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(task.getPriority().value, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PriorityContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PriorityContainsKeywordsPredicate) other).keywords)); // state check
    }

}
