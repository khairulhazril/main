package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.SortCommand.MESSAGE_INVALID;
import static seedu.address.testutil.TypicalAccounts.NICHOLAS;
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
import seedu.address.model.account.User;
import seedu.address.model.task.Task;
import seedu.address.testutil.AccountBuilder;

public class SortCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Model model = new ModelManager(getTypicalTaskManager(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTaskManager(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_sortName_success() {
        User user = new AccountBuilder(NICHOLAS).build();
        model.newUser(user);
        model.loginUser(user);

        String method = "name";
        SortCommand sortCommand = new SortCommand(method);
        String expectedMessage = String.format(sortCommand.MESSAGE_SUCCESS, method);

        ModelManager expectedModel = new ModelManager(getTypicalTaskManager(), new UserPrefs());
        expectedModel.sortTask(method);
        expectedModel.commitTaskManager();
        assertCommandSuccess(sortCommand, model, commandHistory, expectedMessage, expectedModel);
        Task task1 = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task task2 = model.getFilteredTaskList().get(INDEX_SECOND_TASK.getZeroBased());
        Task task3 = model.getFilteredTaskList().get(INDEX_THIRD_TASK.getZeroBased());
        assertEquals("CG2027", task1.getModule().toString());
        assertEquals("CG2023", task2.getModule().toString());
        assertEquals("CS2113", task3.getModule().toString());
    }

    @Test
    public void execute_sortDate_success() {
        User user = new AccountBuilder(NICHOLAS).build();
        model.newUser(user);
        model.loginUser(user);

        String method = "date";
        SortCommand sortCommand = new SortCommand(method);
        String expectedMessage = String.format(sortCommand.MESSAGE_SUCCESS, method);

        ModelManager expectedModel = new ModelManager(getTypicalTaskManager(), new UserPrefs());
        expectedModel.sortTask(method);
        expectedModel.commitTaskManager();
        assertCommandSuccess(sortCommand, model, commandHistory, expectedMessage, expectedModel);
        Task task1 = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task task2 = model.getFilteredTaskList().get(INDEX_SECOND_TASK.getZeroBased());
        Task task3 = model.getFilteredTaskList().get(INDEX_THIRD_TASK.getZeroBased());
        assertEquals("CG2023", task1.getModule().toString());
        assertEquals("CS2113", task2.getModule().toString());
        assertEquals("CG2027", task3.getModule().toString());
    }

    @Test
    public void execute_sortPriority_success() {
        User user = new AccountBuilder(NICHOLAS).build();
        model.newUser(user);
        model.loginUser(user);

        String method = "priority";
        SortCommand sortCommand = new SortCommand(method);
        String expectedMessage = String.format(sortCommand.MESSAGE_SUCCESS, method);

        ModelManager expectedModel = new ModelManager(getTypicalTaskManager(), new UserPrefs());
        expectedModel.sortTask(method);
        expectedModel.commitTaskManager();
        assertCommandSuccess(sortCommand, model, commandHistory, expectedMessage, expectedModel);
        Task task1 = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task task2 = model.getFilteredTaskList().get(INDEX_SECOND_TASK.getZeroBased());
        Task task3 = model.getFilteredTaskList().get(INDEX_THIRD_TASK.getZeroBased());
        assertEquals("CS2113", task1.getModule().toString());
        assertEquals("CG2023", task2.getModule().toString());
        assertEquals("CG2027", task3.getModule().toString());
    }

    @Test
    public void execute_sortModule_success() {
        User user = new AccountBuilder(NICHOLAS).build();
        model.newUser(user);
        model.loginUser(user);

        String method = "module";
        SortCommand sortCommand = new SortCommand(method);
        String expectedMessage = String.format(sortCommand.MESSAGE_SUCCESS, method);

        ModelManager expectedModel = new ModelManager(getTypicalTaskManager(), new UserPrefs());
        expectedModel.sortTask(method);
        expectedModel.commitTaskManager();
        assertCommandSuccess(sortCommand, model, commandHistory, expectedMessage, expectedModel);
        Task task1 = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task task2 = model.getFilteredTaskList().get(INDEX_SECOND_TASK.getZeroBased());
        Task task3 = model.getFilteredTaskList().get(INDEX_THIRD_TASK.getZeroBased());
        assertEquals("CG2023", task1.getModule().toString());
        assertEquals("CG2027", task2.getModule().toString());
        assertEquals("CS2113", task3.getModule().toString());
    }

    @Test
    public void invalid_input() {
        User user = new AccountBuilder(NICHOLAS).build();
        model.newUser(user);
        model.loginUser(user);
        SortCommand sortCommand = new SortCommand("help");

        assertCommandFailure(sortCommand, model, commandHistory, MESSAGE_INVALID);

    }
}
