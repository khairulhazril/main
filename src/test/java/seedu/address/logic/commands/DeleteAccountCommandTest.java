package seedu.address.logic.commands;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.account.User;
import seedu.address.testutil.AccountBuilder;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class DeleteAccountCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void successfulDeleteAccount() throws CommandException {
        User user = new AccountBuilder().setUsername("admin").setPassword("admin").build();
        Model model = new ModelManager();
        model.loginUser(user);

        CommandResult commandResult = new DeleteAccountCommand().execute(model, commandHistory);

        assertEquals(String.format(DeleteAccountCommand.MESSAGE_SUCCESS), commandResult.getFeedbackToUser());
    }

    @Test
    public void failedDeleteAccount_notAdmin() throws Exception {
        User user = new AccountBuilder().build();
        Model model = new ModelManager();
        model.loginUser(user);

        DeleteAccountCommand deleteAccountCommand = new DeleteAccountCommand();

        thrown.expect(CommandException.class);
        thrown.expectMessage(DeleteAccountCommand.MESSAGE_ADMIN_LOGIN);
        deleteAccountCommand.execute(model, commandHistory);
    }

    @Test
    public void failedDeleteAccount_notLogged() throws Exception {

        Model model = new ModelManager();

        DeleteAccountCommand deleteAccountCommand = new DeleteAccountCommand();

        thrown.expect(CommandException.class);
        thrown.expectMessage(DeleteAccountCommand.MESSAGE_ADMIN_LOGIN);
        deleteAccountCommand.execute(model, commandHistory);
    }
}
