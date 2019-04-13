package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PASSWORD_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_USERNAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PASSWORD_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.USERNAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PASSWORD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USERNAME;
import static seedu.address.logic.commands.SignupCommand.MESSAGE_INVALID_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
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
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.SignupCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyTaskManager;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.account.Password;
import seedu.address.model.account.User;
import seedu.address.model.account.Username;
import seedu.address.model.notes.Notes;
import seedu.address.model.task.Task;
import seedu.address.model.util.Month;
import seedu.address.testutil.AccountBuilder;

public class SignupCommandParserTest {

    private static final CommandHistory emptyCommandHistory = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();
    private SignupCommandParser parserTest = new SignupCommandParser();
    
    @Test
    public void parseSuccess() throws CommandException {

        User user = new AccountBuilder(NICHOLAS).build();
        User currentUser = new AccountBuilder().build();
        ModelStubTestUser modelStubTestUser = new ModelStubTestUser(currentUser);

        CommandResult commandResult = new SignupCommand(user).execute(modelStubTestUser, commandHistory);

        assertEquals(String.format(SignupCommand.MESSAGE_SUCCESS, user.getUsername().toString()),
                commandResult.getFeedbackToUser());

        assertEquals(emptyCommandHistory, commandHistory);

        // Parses Correctly but fails with size of User
        // Username username = new Username(VALID_USERNAME);
        // Password password = new Password(VALID_PASSWORD);
        // User currentUser = new User(username,password);

        //  assertParseSuccess(parserTest, PREAMBLE_WHITESPACE + USERNAME_DESC + PASSWORD_DESC,
        //        new SignupCommand(currentUser));

        //  assertParseSuccess(parserTest, USERNAME_DESC + PASSWORD_DESC,
        //        new SignupCommand(currentUser));

    }

    @Test
    public void parse_missingField_failure() {

        String expectedMessage = String.format(MESSAGE_INVALID_FORMAT, SignupCommand.MESSAGE_USAGE);

        assertParseFailure(parserTest, USERNAME_DESC + VALID_PASSWORD, expectedMessage);

        assertParseFailure(parserTest, VALID_USERNAME + PASSWORD_DESC, expectedMessage);

        assertParseFailure(parserTest, VALID_USERNAME + VALID_PASSWORD, expectedMessage);
    }

    @Test
    public void parse_invalidDetails_failure() {

        assertParseFailure(parserTest, INVALID_USERNAME_DESC + PASSWORD_DESC,
                Username.MESSAGE_USERNAME_CONSTRAINTS);

        assertParseFailure(parserTest, USERNAME_DESC + INVALID_PASSWORD_DESC,
                Password.MESSAGE_PASSWORD_CONSTRAINTS);

        assertParseFailure(parserTest, INVALID_USERNAME_DESC + INVALID_PASSWORD_DESC,
                Username.MESSAGE_USERNAME_CONSTRAINTS);

        assertParseFailure(parserTest, PREAMBLE_NON_EMPTY + USERNAME_DESC + PASSWORD_DESC,
                String.format(MESSAGE_INVALID_FORMAT, SignupCommand.MESSAGE_USAGE));
    }


    /**
     * Model Stub for failing methods from model
     */
    private class ModelStub implements Model {

        @Override
        public void newUser(User user) {
            throw new AssertionError("This method should not be called!");
        }

        @Override
        public boolean accountExists() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void deleteAccount() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public boolean getLoginStatus() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public boolean getAdminStatus() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public boolean userExists(User user) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void loginUser(User user) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public Username getUsername() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void logout() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public boolean hasNotes(Notes notes) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void addNotes(Notes notes) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void addJsonNotes(Notes notes) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public ObservableList<Notes> getFilteredNotesList() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void updateFilteredNotesList(Predicate<Notes> predicate) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public ReadOnlyProperty<Notes> selectedNotesProperty() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void setSelectedNotes(Notes notes) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public Notes getSelectedNotes() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void deleteNotes(Notes target) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void addTask(Task task) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void setTaskManager(ReadOnlyTaskManager newData) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public Path getTaskManagerFilePath() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void setTaskManagerFilePath(Path taskManagerFilePath) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public ReadOnlyTaskManager getTaskManager() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public boolean hasTask(Task task) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void deleteTask(Task target) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void setTask(Task target, Task editedTask) {

            throw new AssertionError("This method should not be called"); }

        @Override
        public void sortTask(String attribute) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public boolean canUndoTaskManager() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public boolean canRedoTaskManager() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void undoTaskManager() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void redoTaskManager() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void commitTaskManager() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public ReadOnlyProperty<Task> selectedTaskProperty() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public Task getSelectedTask() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void setSelectedTask(Task task) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public ReadOnlyProperty<Month> currentMonthProperty() {
            throw new AssertionError("This method should not be called"); }

        @Override
        public void setMonth(Month month) {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public Month getMonth() {
            throw new AssertionError("This method should not be called");
        }
    }

    /**
     * Model Stub to test signup
     */
    private class ModelStubTestUser extends ModelStub {
        private User user;
        private boolean isLoggedIn;
        //private boolean isAdminLoggedIn;
        private boolean accountExist = false;

        ModelStubTestUser(User user) {
            requireNonNull(user);
            this.user = user;
            isLoggedIn = false;
            //isAdminLoggedIn = false;
        }

        @Override
        public boolean userExists(User user) {
            requireNonNull(user);
            return user.getUsername().equals(this.user.getUsername());
        }

        @Override
        public boolean accountExists() {
            return accountExist;
        }

        @Override
        public void newUser(User user) {
            requireNonNull(user);
        }

        @Override
        public boolean getLoginStatus() {
            return isLoggedIn;
        }

        @Override
        public Username getUsername() {
            return new Username("user");
        }
    }

}
