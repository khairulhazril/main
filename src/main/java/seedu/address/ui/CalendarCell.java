package seedu.address.ui;

import java.util.logging.Logger;

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
import seedu.address.commons.core.LogsCenter;
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

    private final Logger logger = LogsCenter.getLogger(CalendarCell.class);

    private String date;
    private String month;

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
     * @param taskList List of tasks currently being displayed
     */
    public CalendarCell(String date, String month, ObservableList<Task> taskList) {
        super(FXML);

        setDate(date);
        setMonth(month);
        addTask(taskList);
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
     * Adds the name of a task to the cell
     */
    private void addTask(ObservableList<Task> taskList) {
        for (Task task : taskList) {
            String currFullDate = task.getDate().toString();
            String currDateString = currFullDate.substring(0, currFullDate.indexOf("-"));
            String currMonthString = currFullDate.substring(currFullDate.indexOf("-") + 1);

            int currDate = Integer.parseInt(currDateString);
            int currMonth = Integer.parseInt(currMonthString);

            if (currDate == Integer.parseInt(date) && currMonth == Integer.parseInt(month)) {
                Text newTask = new Text();
                newTask.setText("- " + task.getName().toString());
                newTask.setWrappingWidth(CELL_WIDTH);

                cellContent.getChildren().add(newTask);
            }
        }
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

}
