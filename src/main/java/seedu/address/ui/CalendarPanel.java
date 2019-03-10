package seedu.address.ui;

import java.util.logging.Logger;

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

/**
 * Panel that displays a calendar in grid format.
 */
public class CalendarPanel extends UiPart<Region> {
    private static final String FXML = "CalendarPanel.fxml";

    private static Text monthLabel = new Text();

    private static final int COLS = 7; // 7 Days in a week
    private static final int ROWS = 8; // 6 Rows + Day Header + Month Header
    private static final int ROW_HEIGHT = 80;
    private static final int COL_WIDTH = 105;
    private static final int HEADER_HEIGHT = 20;
    private static final String[] HEADERS = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",
        "Friday", "Saturday" };

    private static final BackgroundFill backgroundFill = new BackgroundFill(Paint.valueOf("#FFFFFF"),
        CornerRadii.EMPTY, Insets.EMPTY);
    private static final Background background = new Background(backgroundFill);
    private static final Border border = new Border(new BorderStroke(Paint.valueOf("#0F0F0F"), BorderStrokeStyle.SOLID,
            CornerRadii.EMPTY, BorderStroke.THIN));

    private final Logger logger = LogsCenter.getLogger(CalendarPanel.class);

    @FXML
    private GridPane taskGridPane;

    public CalendarPanel() {
        super(FXML);
        buildCalendarPane();
    }

    /**
     * Builds calendar grid.
     */
    private void buildCalendarPane() {
        buildGrid();
        writeBox();
        writeMonthHeader("Month");
        writeDayHeaders();
        writeContents();
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
                }
            }
        }
    }

    /**
     * Writes month to top row of grid.
     */
    private void writeMonthHeader(String month) {
        for (Node node : taskGridPane.getChildren()) {
            if (GridPane.getRowIndex(node) == 0 && GridPane.getColumnIndex(node) == 0) {
                VBox box = (VBox) node;
                monthLabel.setText(month);

                box.setAlignment(Pos.CENTER);
                box.getChildren().add(monthLabel);

                break;
            }
        }
    }

    /**
     * Writes event information to each cell of the grid.
     */
    private void writeContents() {
        return;
    }

    /**
     * Populates grid with content cells.
     */
    private void writeBox() {
        for (int i = 0; i < COLS; i++) {
            for (int j = 0; j < ROWS; j++) {
                VBox box = new VBox();
                box.setBackground(background);
                box.setBorder(border);

                if (i == 0 && j == 0) {
                    taskGridPane.add(box, i, j, ROWS, 1);
                } else if (j == 0) {
                    continue;
                } else {
                    taskGridPane.add(box, i, j);
                }
            }
        }
    }

    /**
     * Assigns row/col dimension constraints to grid.
     */
    private void buildGrid() {
        RowConstraints row;
        ColumnConstraints column;

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

}
