package seedu.address.ui;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static seedu.address.testutil.TypicalTasks.getTypicalTasks;

import java.util.Collections;

import org.junit.Test;

import guitests.guihandles.PersonListPanelHandle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.task.Due;
import seedu.address.model.task.Module;
import seedu.address.model.task.Name;
import seedu.address.model.task.Priority;
import seedu.address.model.task.Task;

public class TaskListPanelTest extends GuiUnitTest {
    private static final ObservableList<Task> TYPICAL_TASKS =
            FXCollections.observableList(getTypicalTasks());

    private static final long CARD_CREATION_AND_DELETION_TIMEOUT = 2500;

    private final SimpleObjectProperty<Task> selectedTask = new SimpleObjectProperty<>();
    private PersonListPanelHandle taskListPanelHandle;

    /*
    @Test
    public void display() {
    initUi(TYPICAL_TASKS);

    for (int i = 0; i < TYPICAL_TASKS.size(); i++) {
    personListPanelHandle.navigateToCard(TYPICAL_TASKS.get(i));
    Task expectedTask = TYPICAL_TASKS.get(i);
    PersonCardHandle actualCard = personListPanelHandle.getPersonCardHandle(i);

    assertCardDisplaysPerson(expectedTask, actualCard);
    assertEquals(Integer.toString(i + 1) + ". ", actualCard.getId());
    }
    }
    @Test
    public void selection_modelSelectedPersonChanged_selectionChanges() {
    initUi(TYPICAL_TASKS);
    Task secondTask = TYPICAL_TASKS.get(INDEX_SECOND_TASK.getZeroBased());
    guiRobot.interact(() -> selectedTask.set(secondTask));
    guiRobot.pauseForHuman();

    PersonCardHandle expectedPerson = personListPanelHandle.getPersonCardHandle(INDEX_SECOND_TASK.getZeroBased());
    PersonCardHandle selectedPerson = personListPanelHandle.getHandleToSelectedCard();
    assertCardEquals(expectedPerson, selectedPerson);
    }
    */

    /**
     * Verifies that creating and deleting large number of persons in {@code TaskListPanel} requires lesser than
     * {@code CARD_CREATION_AND_DELETION_TIMEOUT} milliseconds to execute.
     */
    @Test
    public void performanceTest() {
        ObservableList<Task> backingList = createBackingList(10000);

        assertTimeoutPreemptively(ofMillis(CARD_CREATION_AND_DELETION_TIMEOUT), () -> {
            initUi(backingList);
            guiRobot.interact(backingList::clear);
        }, "Creation and deletion of task cards exceeded time limit");
    }

    /**
     * Returns a list of persons containing {@code personCount} persons that is used to populate the
     * {@code TaskListPanel}.
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
        TaskListPanel taskListPanel =
                new TaskListPanel(backingList, selectedTask, selectedTask::set);
        uiPartRule.setUiPart(taskListPanel);

        taskListPanelHandle = new PersonListPanelHandle(getChildNode(taskListPanel.getRoot(),
                PersonListPanelHandle.PERSON_LIST_VIEW_ID));
    }
}
