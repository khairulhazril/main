package seedu.address.ui;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.model.task.Task;

/**
 * Panel that displays a calendar in grid format.
 */
public class CalendarPanel extends UiPart<Region> {
    private static final String FXML = "CalendarPanel.fxml";

    private static Text monthLabel = new Text();

    private static int currDate = 0;

    private static final int COLS = 7; // 7 Days in a week
    private static final int ROWS = 8; // 6 Rows + Day Header + Month Header
    private static final int ROW_HEIGHT = 80;
    private static final int COL_WIDTH = 105;
    private static final int HEADER_HEIGHT = 20;
    private static final String[] HEADERS = new String[] { "SUNDAY", "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY",
        "FRIDAY", "SATURDAY" };

    private static final BackgroundFill backgroundFill = new BackgroundFill(Paint.valueOf("#FFFFFF"),
            CornerRadii.EMPTY, Insets.EMPTY);
    private static final Background background = new Background(backgroundFill);
    private static final Border border = new Border(new BorderStroke(Paint.valueOf("#0F0F0F"), BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY, BorderStroke.THIN));

    //get current year and month and date as set by system clock
    private static final YearMonth yearMonth = YearMonth.now();
    private static LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);

    private final Logger logger = LogsCenter.getLogger(CalendarPanel.class);

    private Logic logic;

    @FXML
    private GridPane taskGridPane;

    public CalendarPanel(ObservableList<Task> taskList, Logic logic) {
        super(FXML);
        this.logic = logic;
        buildCalendarPane(taskList);

        taskList.addListener((ListChangeListener<? super Task>) (observable) -> {
            ObservableList<Task> newTaskList = logic.getFilteredTaskList();
            createCalendarCells(newTaskList);
        });
    }

    /**
     * Builds calendar.
     */
    private void buildCalendarPane(ObservableList<Task> taskList) {
        buildGrid();
        createHeaderCells();
        writeMonthHeader();
        writeDayHeaders();
        createCalendarCells(taskList);
    }

    /**
     * Assigns row/col dimension constraints to grid.
     */
    private void buildGrid() {
        RowConstraints row;
        ColumnConstraints column;

        //create empty rows and columns
        for (int i = 0; i < COLS; i++) {
            column = new ColumnConstraints(COL_WIDTH);
            column.setHgrow(Priority.ALWAYS);
            taskGridPane.getColumnConstraints().add(column);
        }

        for (int i = 0; i < ROWS; i++) {
            if (i == 0 || i == 1) {
                row = new RowConstraints(HEADER_HEIGHT);
            } else {
                row = new RowConstraints(ROW_HEIGHT);
            }
            taskGridPane.getRowConstraints().add(row);
        }
    }

    /**
     * Populates grid with header cells.
     */
    private void createHeaderCells() {
        for (int col = 0; col < COLS; col++) {
            for (int row = 0; row < ROWS; row++) {
                if (col == 0 && row == 0) {
                    taskGridPane.add(new VBox(), col, row, COLS, 1); //month header
                } else if (row == 0) {
                    continue;
                } else if (row == 1) {
                    taskGridPane.add(new VBox(), col, row, 1, 1); //day headers
                }
            }
        }
    }

    /**
     * Writes month to top row of grid.
     */
    private void writeMonthHeader() {
        String month = String.valueOf(calendarDate.getMonth());
        String year = String.valueOf(calendarDate.getYear());

        for (Node node : taskGridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == 0 && GridPane.getColumnIndex(node) == 0) {
                VBox box = (VBox) node;
                monthLabel.setText(month + " " + year);

                box.setAlignment(Pos.CENTER);
                box.getChildren().add(monthLabel);
                box.setBackground(background);
                box.setBorder(border);

                break;
            }
        }
    }

    /**
     * Writes day headers to second-highest row of grid.
     */
    private void writeDayHeaders() {
        for (int i = 0; i < COLS; i++) {
            for (Node node : taskGridPane.getChildren()) {
                if (GridPane.getRowIndex(node) == 1 && GridPane.getColumnIndex(node) == i) {
                    VBox box = (VBox) node;
                    Text header = new Text(HEADERS[i]);

                    box.setAlignment(Pos.CENTER);
                    box.getChildren().add(header);
                    box.setBackground(background);
                    box.setBorder(border);
                }
            }
        }
    }

    /**
     * Populates grid with calendar cells to correspond to the appropriate date
     *
     * @param taskList List of tasks currently being displayed
     */
    private void createCalendarCells(ObservableList<Task> taskList) {
        while (!calendarDate.getDayOfWeek().toString().equals("SUNDAY")) { //get previous month's dates to be displayed
            calendarDate = calendarDate.minusDays(1);
        }

        for (int row = 2; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                String date = String.valueOf(calendarDate.getDayOfMonth());
                String month = String.valueOf(calendarDate.getMonthValue());
                taskGridPane.add(new CalendarCell(date, month, taskList).getRoot(), col, row);
                calendarDate = calendarDate.plusDays(1);
            }
        }

        calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
    }

}
