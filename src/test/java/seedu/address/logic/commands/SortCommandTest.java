package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.SortCommand.MESSAGE_USAGE;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskManager;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.task.Task;

public class SortCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Model model = new ModelManager(getTypicalTaskManager(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTaskManager(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

//    @Test
//    public void execute_sortName_success() {
//        String method = "name";
//        SortCommand sortCommand = new SortCommand(method);
//        String expectedMessage = String.format(sortCommand.MESSAGE_SUCCESS, method);
//
//        ModelManager expectedModel = new ModelManager(getTypicalTaskManager(), new UserPrefs());
//        expectedModel.sortTask(method);
//        expectedModel.commitTaskManager();
//        assertCommandSuccess(sortCommand, model, commandHistory, expectedMessage, expectedModel);
//        Task task1 = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
//        Task task2 = model.getFilteredTaskList().get(INDEX_SECOND_TASK.getZeroBased());
//        Task task3 = model.getFilteredTaskList().get(INDEX_THIRD_TASK.getZeroBased());
//        assertEquals("CS2113T", task1.getModule().toString());
//        assertEquals("CS2101", task2.getModule().toString());
//        assertEquals("CG2023", task3.getModule().toString());
//    }

    @Test
    public void invalid_input() {
        SortCommand sortCommand = new SortCommand("help");

        assertCommandFailure(sortCommand, model, commandHistory, MESSAGE_USAGE);

    }
}
