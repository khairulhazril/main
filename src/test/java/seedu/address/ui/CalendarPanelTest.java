package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

import static seedu.address.testutil.TypicalTasks.getTypicalTasks;

import java.util.Collections;

import org.junit.Test;

import guitests.guihandles.CalendarPanelHandle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.Logic;
import seedu.address.model.task.Due;
import seedu.address.model.task.Module;
import seedu.address.model.task.Name;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;

public class CalendarPanelTest extends GuiUnitTest {
    private static final ObservableList<Task> TYPICAL_TASKS =
            FXCollections.observableList(getTypicalTasks());

    private static final long CARD_CREATION_AND_DELETION_TIMEOUT = 2500;

    private final SimpleObjectProperty<Task> selectedTask = new SimpleObjectProperty<>();
    private CalendarPanelHandle calendarPanelHandle;
    private Logic logic;

    /**
     * Verifies that creating the calendarPanel works
     */
    @Test
    public void initTest() {
        initUi(createBackingList(1));
        assertTrue(calendarPanelHandle.isWindowPresent());
    }

    @Test
    public void gridTest() {
        initUi(createBackingList(1));
        assertTrue(calendarPanelHandle.isGridPresent());
    }

    @Test(timeout = 1000)
    public void timingTest() {
        initUi(createBackingList(1000));
    }

    /**
     * Returns a list of persons containing {@code personCount} persons that is used to populate the
     * {@code CalendarPanel}.
     */
    private ObservableList<Task> createBackingList(int personCount) {
        ObservableList<Task> backingList = FXCollections.observableArrayList();
        for (int i = 0; i < personCount; i++) {
            Name name = new Name(i + "a");
            Module module = new Module("CS2113");
            Due due = new Due("01-03");
            Priority priority = new Priority("1");
            Task task = new Task(name, module, due, priority, Collections.emptySet());
            backingList.add(task);
        }
        return backingList;
    }

    /**
     * Initializes {@code personListPanelHandle} with a {@code TaskListPanel} backed by {@code backingList}.
     * Also shows the {@code Stage} that displays only {@code TaskListPanel}.
     */
    private void initUi(ObservableList<Task> backingList) {
        CalendarPanel calendarPanel =
                new CalendarPanel(backingList, selectedTask, logic);
        uiPartRule.setUiPart(calendarPanel);

        calendarPanelHandle = new CalendarPanelHandle(calendarPanel);
    }
}
