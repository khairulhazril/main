package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.testutil.TypicalAccounts.NICHOLAS;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.LoginEvent;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyTaskManager;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.TaskManager;
import seedu.address.model.account.User;
import seedu.address.model.account.Username;
import seedu.address.model.notes.Notes;
import seedu.address.model.task.Task;
import seedu.address.model.util.Month;
import seedu.address.testutil.AccountBuilder;
import seedu.address.testutil.NotesBuilder;

public class AddNotesCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddNotesCommand(null);
    }

    @Test
    public void execute_notesAccepted() throws Exception {
        ModelStubAcceptingNotesAdded modelStub = new ModelStubAcceptingNotesAdded();
        Notes validNotes = new NotesBuilder().build();

        User user = new AccountBuilder(NICHOLAS).build();
        modelStub.newUser(user);
        modelStub.loginUser(user);

        CommandResult commandResult = new AddNotesCommand(validNotes).execute(modelStub, commandHistory);

        assertEquals(String.format(AddNotesCommand.MESSAGE_SUCCESS, validNotes), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validNotes), modelStub.notesAdded);
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_duplicateNotes_throwsCommandException() throws Exception {
        Notes validNotes = new NotesBuilder().build();
        AddNotesCommand addnotesCommand = new AddNotesCommand(validNotes);
        ModelStub modelStub = new ModelStubWithNotes(validNotes);

        User user = new AccountBuilder(NICHOLAS).build();
        modelStub.newUser(user);
        modelStub.loginUser(user);

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddNotesCommand.MESSAGE_DUPLICATE_NOTE);
        addnotesCommand.execute(modelStub, commandHistory);
    }

    @Test
    public void equals() {
        Notes market = new NotesBuilder().withHeading("Market").build();
        Notes popular = new NotesBuilder().withHeading("Popular").build();
        AddNotesCommand addMarketCommand = new AddNotesCommand(market);
        AddNotesCommand addPopularCommand = new AddNotesCommand(popular);

        // same object -> returns true
        assertTrue(addMarketCommand.equals(addMarketCommand));

        // same values -> returns true
        AddNotesCommand addMarketCopy = new AddNotesCommand(market);
        assertTrue(addMarketCommand.equals(addMarketCopy));

        // different types -> returns false
        assertFalse(addPopularCommand.equals(1));

        // null -> returns false
        assertFalse(addPopularCommand.equals(null));

        // different task -> returns false
        assertFalse(addMarketCommand.equals(addPopularCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        private final LoginEvent loginEvent = new LoginEvent();

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
            throw new AssertionError("This method should not be called.");
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
        public Username getUsername() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void logout() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void loginUser(User loginInfo) {
            requireNonNull(loginInfo);
            loginEvent.loginUser(loginInfo);
        }

        @Override
        public boolean userExists(User user) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void newUser(User user) {
            requireNonNull(user);
            loginEvent.newUser(user);
        }

        @Override
        public boolean accountExists() {
            return loginEvent.accountExists();
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
     * A Model stub that contains a single note.
     */
    private class ModelStubWithNotes extends ModelStub {
        private final Notes notes;

        ModelStubWithNotes(Notes notes) {
            requireNonNull(notes);
            this.notes = notes;
        }

        public boolean hasNotes(Notes notes) {
            requireNonNull(notes);
            return this.notes.isSameNotes(notes);
        }
    }

    /**
     * A Model stub that always accept the note being added.
     */
    private class ModelStubAcceptingNotesAdded extends ModelStub {
        final ArrayList<Notes> notesAdded = new ArrayList<>();

        public boolean hasNotes(Notes notes) {
            requireNonNull(notes);
            return notesAdded.stream().anyMatch(notes::isSameNotes);
        }

        public void addNotes(Notes notes) {
            requireNonNull(notes);
            notesAdded.add(notes);
        }

        public void commitTaskManager() {
            // called by {@code AddCommand#execute()}
        }

        public ReadOnlyTaskManager getTaskManager() {
            return new TaskManager();
        }
    }
}
