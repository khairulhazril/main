package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.scene.Node;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import seedu.address.commons.core.LogsCenter;

/**
 * Panel that displays a calendar in grid format.
 */
public class CalendarPanel extends UiPart<Region> {
    private static final String FXML = "CalendarPanel.fxml";
    private static final int COLS = 7; // 7 Days in a week
    private static final int ROWS = 6; // 5 Rows + header
    private static final int ROW_HEIGHT = 80;
    private static final int COL_WIDTH = 105;
    private static final String[] HEADERS = new String[] { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",
            "Friday", "Saturday" };

    private final Logger logger = LogsCenter.getLogger(CalendarPanel.class);

    @FXML
    private GridPane taskGridPane;

    public CalendarPanel() {
        super(FXML);
        buildGridPane();
    }

    /**
     * Builds calendar grid.
     */
    private void buildGridPane() {
        buildGrid();
        writeBox();
        writeHeaders();
    }

    /**
     * Writes headers to top row of grid.
     */
    private void writeHeaders() {
        for (int i = 0; i < COLS; i++) {
            for (Node node : taskGridPane.getChildren()) {
                if (GridPane.getRowIndex(node) == 0 && GridPane.getColumnIndex(node) == i) {
                    VBox box = (VBox) node;
                    Text header = new Text(HEADERS[i]);
                    box.setAlignment(Pos.CENTER);
                    box.getChildren().add(header);
                }
            }
        }
    }

    /**
     * Populates grid with boxes.
     */
    private void writeBox() {
        BackgroundFill backgroundFill = new BackgroundFill(Paint.valueOf("#FFFFFF"), CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);

        Border border = new Border(new BorderStroke(Paint.valueOf("#0F0F0F"), BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, BorderStroke.THIN));

        for (int i = 0; i < COLS; i++) {
            for (int j = 0; j < ROWS; j++) {
                VBox box = new VBox();
                box.setBackground(background);
                box.setBorder(border);
                taskGridPane.add(box, i, j);
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
            if (i == 0) {
                row = new RowConstraints();
            } else {
                row = new RowConstraints(ROW_HEIGHT);
            }
            taskGridPane.getRowConstraints().add(row);
        }
    }

}