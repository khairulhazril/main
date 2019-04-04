package seedu.address.ui;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import seedu.address.model.task.Task;

/**
 * Individual cell for CalendarPanel.
 */
public class CalendarCell extends UiPart<Region> {
    private static final String FXML = "CalendarCell.fxml";

    private static final BackgroundFill backgroundFill = new BackgroundFill(Paint.valueOf("#FFFFFF"),
            CornerRadii.EMPTY, Insets.EMPTY);
    private static final Background background = new Background(backgroundFill);
    private static final Border border = new Border(new BorderStroke(Paint.valueOf("#0F0F0F"), BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY, BorderStroke.THIN));

    private static final int CELL_WIDTH = 105;

    private String date;
    private String month;

    private Task selectedTask;

    private ArrayList<CalendarCellTask> cellTasks = new ArrayList<CalendarCellTask>();

    @FXML
    private Text cellDate;

    @FXML
    private ScrollPane cellTasksPane;

    @FXML
    private VBox cellContent;

    /**
     * Creates a cell for CalendarPanel.
     *
     * @param date Date to be displayed on the cell
     * @param taskList List of tasks currently in Task Manager
     */
    public CalendarCell(String date, String month, ObservableList<Task> taskList, Task selectedTask) {
        super(FXML);
        this.selectedTask = selectedTask;
        setDate(date);
        setMonth(month);
        getTasks(taskList);
        setAppearance();
    }

    /**
     * Adds the date to the cell
     */
    private void setDate(String date) {
        this.date = date;
        cellDate.setText(date);
    }

    /**
     * Sets the month parameter of the cell
     */
    private void setMonth(String month) {
        this.month = month;
    }

    /**
     * Compiles a list of tasks to be added to the cell
     *
     * @param taskList List of tasks currently in Task Manager
     */
    private void getTasks(ObservableList<Task> taskList) {
        for (Task task : taskList) {
            String currFullDate = task.getDue().toString();
            String currDateString = currFullDate.substring(0, currFullDate.indexOf("-"));
            String currMonthString = currFullDate.substring(currFullDate.indexOf("-") + 1);

            int currDate = Integer.parseInt(currDateString);
            int currMonth = Integer.parseInt(currMonthString);

            if (currDate == Integer.parseInt(date) && currMonth == Integer.parseInt(month)) {
                boolean selected = task.isSameTask(selectedTask);

                CalendarCellTask newTask = new CalendarCellTask(task, selected);
                newTask.setWrappingWidth(CELL_WIDTH - 5); //add a bit of padding for better text wrapping

                cellTasks.add(newTask);
            }
        }

        addTasksToCell();
    }

    /**
     * Sets the background, border and scrollbars of the cell
     */
    private void setAppearance() {
        getRoot().setBackground(background);
        getRoot().setBorder(border);
        cellTasksPane.setHbarPolicy(ScrollBarPolicy.NEVER);
        cellTasksPane.setVbarPolicy(ScrollBarPolicy.NEVER);
    }

    /**
     * Adds tasks to the calendar cell, tasks sorted by priority in descending order
     */
    private void addTasksToCell() {
        //sort tasks by priority
        cellTasks.sort(CalendarCellTask::compareTo);
        for (CalendarCellTask newTask : cellTasks) {
            cellContent.getChildren().add(newTask);
        }
    }

}
