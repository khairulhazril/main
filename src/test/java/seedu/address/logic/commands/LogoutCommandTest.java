//@@author nicholasleeeee
package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAccounts.NICHOLAS;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.account.User;
import seedu.address.testutil.AccountBuilder;

public class LogoutCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void failedLogout() throws Exception {
        Model model = new ModelManager();
        LogoutCommand command = new LogoutCommand();
        thrown.expect(CommandException.class);
        thrown.expectMessage(LogoutCommand.MESSAGE_LOGIN);
        command.execute(model, commandHistory);
    }

    @Test
    public void successfulLogout() {

        User user = new AccountBuilder(NICHOLAS).build();
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        model.newUser(user);
        expectedModel.newUser(user);
        model.loginUser(user);
        expectedModel.loginUser(user);
        expectedModel.logout();

        assertCommandSuccess(new LogoutCommand(), model, commandHistory,
                String.format(LogoutCommand.MESSAGE_SUCCESS, user.getUsername().toString()), expectedModel);

    }


}
