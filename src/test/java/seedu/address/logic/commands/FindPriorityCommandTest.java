package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAccounts.NICHOLAS;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskManager;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.account.User;
import seedu.address.model.task.PriorityContainsKeywordsPredicate;
import seedu.address.testutil.AccountBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code FindPriorityCommand}.
 */
public class FindPriorityCommandTest {
    private Model model = new ModelManager(getTypicalTaskManager(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTaskManager(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void equals() {
        PriorityContainsKeywordsPredicate firstPredicate =
                new PriorityContainsKeywordsPredicate(Collections.singletonList("first"));
        PriorityContainsKeywordsPredicate secondPredicate =
                new PriorityContainsKeywordsPredicate(Collections.singletonList("second"));

        FindPriorityCommand findFirstCommand = new FindPriorityCommand(firstPredicate);
        FindPriorityCommand findSecondCommand = new FindPriorityCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindPriorityCommand findFirstCommandCopy = new FindPriorityCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noTaskFound() {
        User user = new AccountBuilder(NICHOLAS).build();
        model.newUser(user);
        model.loginUser(user);
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        PriorityContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindPriorityCommand command = new FindPriorityCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

    //@Test
    //public void execute_multipleKeywords_multiplePersonsFound() {
    //String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 2);
    //DueContainsKeywordsPredicate predicate = preparePredicate("Lab Tutorial");
    //FindDateCommand command = new FindDateCommand(predicate);
    //expectedModel.updateFilteredTaskList(predicate);
    //assertCommandSuccess(command, model, commandHistory, expectedMessage, expectedModel);
    //assertEquals(Arrays.asList(05-03), model.getFilteredTaskList());
    //}

    /**
     * Parses {@code userInput} into a {@code ModuleContainsKeywordsPredicate}.
     */
    private PriorityContainsKeywordsPredicate preparePredicate(String userInput) {
        return new PriorityContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
