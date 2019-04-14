package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showTaskAtIndex;
import static seedu.address.testutil.TypicalAccounts.NICHOLAS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskManager;

import org.junit.Before;
import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.account.User;
import seedu.address.testutil.AccountBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;
    private CommandHistory commandHistory = new CommandHistory();


    @Before
    public void setUp() {
        model = new ModelManager(getTypicalTaskManager(), new UserPrefs());
        expectedModel = new ModelManager(model.getTaskManager(), new UserPrefs());
        User user = new AccountBuilder(NICHOLAS).build();
        model.loginUser(user);
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        User user = new AccountBuilder(NICHOLAS).build();
        model.newUser(user);
        model.loginUser(user);
        assertCommandSuccess(new ListCommand(), model, commandHistory, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        User user = new AccountBuilder(NICHOLAS).build();
        model.newUser(user);
        model.loginUser(user);
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        assertCommandSuccess(new ListCommand(), model, commandHistory, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
