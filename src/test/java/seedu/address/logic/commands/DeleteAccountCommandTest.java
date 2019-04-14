//@@author nicholasleeeee
package seedu.address.logic.commands;

import static org.junit.Assert.assertEquals;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyTaskManager;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.account.User;
import seedu.address.model.account.Username;
import seedu.address.model.notes.Notes;
import seedu.address.model.task.Task;
import seedu.address.model.util.Month;

public class DeleteAccountCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void successfulDeleteAccount() throws CommandException {

        ModelStubUser modelStubUser = new ModelStubUser();

        CommandResult commandResult = new DeleteAccountCommand().execute(modelStubUser, commandHistory);

        assertEquals(String.format(DeleteAccountCommand.MESSAGE_SUCCESS), commandResult.getFeedbackToUser());
    }

    @Test
    public void failedDeleteAccount_notAdmin() throws Exception {

        ModelStubUser_NotAdmin modelStubUser = new ModelStubUser_NotAdmin();

        DeleteAccountCommand deleteAccountCommand = new DeleteAccountCommand();

        thrown.expect(CommandException.class);
        thrown.expectMessage(DeleteAccountCommand.MESSAGE_ADMIN_LOGIN);
        deleteAccountCommand.execute(modelStubUser, commandHistory);
    }

    @Test
    public void failedDeleteAccount_notLogged() throws Exception {

        ModelStubUser_NotLogged modelStubUser = new ModelStubUser_NotLogged();

        DeleteAccountCommand deleteAccountCommand = new DeleteAccountCommand();

        thrown.expect(CommandException.class);
        thrown.expectMessage(DeleteAccountCommand.MESSAGE_ADMIN_LOGIN);
        deleteAccountCommand.execute(modelStubUser, commandHistory);
    }
    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getTaskManagerFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTaskManagerFilePath(Path taskManagerFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTaskManager getTaskManager() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTaskManager(ReadOnlyTaskManager taskManager) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTask(Task target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTask(Task target, Task editedTask) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortTask(String attribute) { }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoTaskManager() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoTaskManager() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoTaskManager() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoTaskManager() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitTaskManager() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyProperty<Task> selectedTaskProperty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Task getSelectedTask() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSelectedTask(Task task) {
        }

        @Override
        public boolean getLoginStatus() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean getAdminStatus() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Username getUsername() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void logout() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void loginUser(User loginInfo) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean userExists(User user) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void newUser(User user) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean accountExists() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteAccount() {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public ReadOnlyProperty<Month> currentMonthProperty() {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public void setMonth(Month month) {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public Month getMonth() {
            throw new AssertionError("This method should not be called!");
        }

        public boolean hasNotes(Notes notes) {
            throw new AssertionError("This method should not be called.");
        }

        public void addNotes(Notes notes) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteNotes(Notes target) {
            throw new AssertionError("This method should not be called!");
        }

        public void addJsonNotes(Notes notes) {
        }

        public ObservableList<Notes> getFilteredNotesList() {
            throw new AssertionError("This method should not be called.");
        }

        public void updateFilteredNotesList(Predicate<Notes> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        public ReadOnlyProperty<Notes> selectedNotesProperty() {
            throw new AssertionError("This method should not be called.");
        }

        public void setSelectedNotes(Notes notes) {
            throw new AssertionError("This method should not be called.");
        }

        public Notes getSelectedNotes() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub account logged in as admin
     */
    private class ModelStubUser extends ModelStub {

        @Override
        public void deleteAccount() {
        }

        @Override
        public Username getUsername() {
            return new Username("user");
        }
        @Override
        public boolean getLoginStatus() {
            return true;
        }

        @Override
        public boolean getAdminStatus() {
            return true;
        }

        @Override
        public boolean accountExists() {
            return true;
        }
    }

    /**
     * A Model stub account not logged in as admin
     */
    private class ModelStubUser_NotAdmin extends ModelStub {

        @Override
        public void deleteAccount() {
        }

        @Override
        public Username getUsername() {
            return new Username("user");
        }
        @Override
        public boolean getLoginStatus() {
            return true;
        }

        @Override
        public boolean getAdminStatus() {
            return false;
        }

        @Override
        public boolean accountExists() {
            return true;
        }
    }

    /**
     * A Model stub account not logged in
     */
    private class ModelStubUser_NotLogged extends ModelStub {

        @Override
        public void deleteAccount() {
        }

        @Override
        public Username getUsername() {
            return new Username("user");
        }
        @Override
        public boolean getLoginStatus() {
            return false;
        }

        @Override
        public boolean getAdminStatus() {
            return false;
        }

        @Override
        public boolean accountExists() {
            return true;
        }
    }
}
