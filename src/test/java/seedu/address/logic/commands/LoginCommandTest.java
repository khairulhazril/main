package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertEquals;
import static seedu.address.testutil.TypicalAccounts.NICHOLAS;

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
import seedu.address.testutil.AccountBuilder;

public class LoginCommandTest {

    private static final CommandHistory EMPTY_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void nullUser() {
        thrown.expect(NullPointerException.class);
        new LoginCommand(null);
    }

    @Test
    public void successfulLogin() throws Exception {
        User user = new AccountBuilder().build();
        ModelStubTestUser modelStubTestUser;
        modelStubTestUser = new ModelStubTestUser(user);

        CommandResult commandResult = new LoginCommand(user).execute(modelStubTestUser, commandHistory);

        assertEquals(String.format(LoginCommand.MESSAGE_SUCCESS, user.getUsername().toString()),
                commandResult.getFeedbackToUser());

        assertEquals(EMPTY_HISTORY, commandHistory);
    }

    @Test
    public void failedLogin_noUser() throws Exception {
        User user = new AccountBuilder(NICHOLAS).build();
        User logginUser = new AccountBuilder().build();
        ModelStubTestUser modelStubTakeUser;
        modelStubTakeUser = new ModelStubTestUser(logginUser);

        LoginCommand loginCommand = new LoginCommand(user);

        thrown.expect(CommandException.class);
        thrown.expectMessage(LoginCommand.MESSAGE_INVALID_FORMAT);
        loginCommand.execute(modelStubTakeUser, commandHistory);
    }

    @Test
    public void failedLogin_alreadyLogged() throws Exception {
        User user = new AccountBuilder().build();
        ModelStubUser modelStubUser;
        modelStubUser = new ModelStubUser();
        LoginCommand loginCommand = new LoginCommand(user);

        thrown.expect(CommandException.class);
        thrown.expectMessage(String.format(LoginCommand.MESSAGE_LOGGED_USER, user.getUsername().toString()));
        loginCommand.execute(modelStubUser, commandHistory);

    }

    /**
     * Model Stub for failing methods from model
     */
    private class ModelStub implements Model {
        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public Path getTaskManagerFilePath() {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public void setTaskManagerFilePath(Path taskManagerFilePath) {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public ReadOnlyTaskManager getTaskManager() {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public void setTaskManager(ReadOnlyTaskManager taskManager) {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public boolean hasTask(Task task) {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public void deleteTask(Task target) {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public void addTask(Task task) {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public void setTask(Task target, Task editedTask) {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public void sortTask(String attribute) {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public boolean canUndoTaskManager() {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public boolean canRedoTaskManager() {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public void undoTaskManager() {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public void redoTaskManager() {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public void commitTaskManager() {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public ReadOnlyProperty<Task> selectedTaskProperty() {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public Task getSelectedTask() {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public void setSelectedTask(Task task) {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public boolean getLoginStatus() {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public boolean getAdminStatus() {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public Username getUsername() {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public void logout() {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public void loginUser(User loginInfo) {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public boolean userExists(User user) {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public void newUser(User user) {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public boolean accountExists() {
            throw new AssertionError("This method should not be called!");
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

        @Override
        public boolean hasNotes(Notes notes) {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public void addNotes(Notes notes) {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public void addJsonNotes(Notes notes) {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public ObservableList<Notes> getFilteredNotesList() {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public void updateFilteredNotesList(Predicate<Notes> predicate) {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public ReadOnlyProperty<Notes> selectedNotesProperty() {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public void setSelectedNotes(Notes notes) {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public Notes getSelectedNotes() {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public void deleteNotes(Notes target) {
            throw new AssertionError("This method should not be called!");
        }
    }

    /**
     * Model stub to test login
     */
    private class ModelStubTestUser extends ModelStub {
        private User user;
        private boolean isLogged = false;
        private boolean isNotAdmin = false;

        ModelStubTestUser(User user) {
            requireNonNull(user);
            this.user = user;
        }

        @Override
        public boolean userExists(User user) {
            requireNonNull(user);
            return user.getUsername().equals(this.user.getUsername());
        }

        @Override
        public void loginUser(User user) {
            isLogged = user.equals(this.user);
        }

        @Override
        public boolean getLoginStatus() {
            return isLogged;
        }

        @Override
        public boolean getAdminStatus() {
            return isNotAdmin;
        }
    }

    private class ModelStubUser extends ModelStub {
        private boolean isLogged;
        private boolean isCreated;
        //private boolean isNotAdmin;

        ModelStubUser() {
            isLogged = true;
            isCreated = true;
            //isNotAdmin = false;
        }

        @Override
        public boolean userExists(User user) {
            requireNonNull(user);
            return isCreated;
        }

        @Override
        public boolean getLoginStatus() {
            return isLogged;
        }

        @Override
        public Username getUsername() {
            return new Username("user");
        }


    }




}
