package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_TASKS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskManager;

import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindDateCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.DueContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindDateCommand}.
 */
public class FindDateCommandTest {
    private Model model = new ModelManager(getTypicalTaskManager(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTaskManager(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void equals() {
        DueContainsKeywordsPredicate firstPredicate =
                new DueContainsKeywordsPredicate(Collections.singletonList("first"));
        DueContainsKeywordsPredicate secondPredicate =
                new DueContainsKeywordsPredicate(Collections.singletonList("second"));

        FindDateCommand findFirstCommand = new FindDateCommand(firstPredicate);
        FindDateCommand findSecondCommand = new FindDateCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindDateCommand findFirstCommandCopy = new FindDateCommand(firstPredicate);
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
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        DueContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindDateCommand command = new FindDateCommand(predicate);
        expectedModel.updateFilteredTaskList(predicate);
        assertCommandSuccess(command, model, commandHistory, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTaskList());
    }

//    @Test
//    public void execute_multipleKeywords_multiplePersonsFound() {
//    String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 2);
//    DueContainsKeywordsPredicate predicate = preparePredicate("Lab Tutorial");
//    FindDateCommand command = new FindDateCommand(predicate);
//    expectedModel.updateFilteredTaskList(predicate);
//    assertCommandSuccess(command, model, commandHistory, expectedMessage, expectedModel);
//    assertEquals(Arrays.asList(05-03), model.getFilteredTaskList());
//    }

    /**
     * Parses {@code userInput} into a {@code DueContainsKeywordsPredicate}.
     */
    private DueContainsKeywordsPredicate preparePredicate(String userInput) {
        return new DueContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
