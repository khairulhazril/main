package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import seedu.address.commons.core.LogsCenter;

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

    private final Logger logger = LogsCenter.getLogger(CalendarCell.class);

    private int row;
    private int col;

    @FXML
    private Text cellDate;

    @FXML
    private ListView<Text> cellTasks;

    public CalendarCell(int row, int col) {
        super(FXML);
        setRow(row);
        setCol(col);
        setDate();
        addTask();
    }

    /**
     * Adds the name of a task to the cell
     */
    public void addTask() {
        Text newTask = new Text();
        newTask.setText("Test Task");

        cellTasks.getItems().add(newTask);
    }

    private void setDate() {
        cellDate.setText("Test Date");
    }

    private void setRow(int row) {
        this.row = row;
    }

    private void setCol(int row) {
        this.row = row;
    }

}
