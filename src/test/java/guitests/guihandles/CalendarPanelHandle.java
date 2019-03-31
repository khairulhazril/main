package guitests.guihandles;

import static seedu.address.testutil.TypicalTasks.getTypicalTasks;

import java.time.LocalDate;
import java.time.YearMonth;

import javafx.beans.value.ObservableBooleanValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import seedu.address.model.task.Task;
import seedu.address.ui.CalendarCell;
import seedu.address.ui.CalendarPanel;

/**
 * Provides a handle for {@code CalendarPanelHandle} containing the grid of
 * {@code CalendarContentCell}.
 */
public class CalendarPanelHandle {
    private static final ObservableList<Task> TYPICAL_TASKS =
            FXCollections.observableList(getTypicalTasks());

    public static final String CALENDAR_PANEL_ID = "#calendarView";

    private static final int COLS = 7; // 7 Days in a week
    private static final int ROWS = 8; // 6 Rows + Day Header + Month Header

    private static final YearMonth yearMonth = YearMonth.now();
    private static LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);

    private CalendarPanel calendarPanel;

    public CalendarPanelHandle(CalendarPanel calendarPanel) {
        this.calendarPanel = calendarPanel;
    }

    /**
     * Returns presence of calendar grid.
     */
    public boolean isWindowPresent() {
        return (calendarPanel.getRoot() instanceof ScrollPane && calendarPanel.getTaskGridPane() != null);
    }

    public boolean isGridPresent() {
        return (calendarPanel.getTaskGridPane().getRowCount() == ROWS
                && calendarPanel.getTaskGridPane().getColumnCount() == COLS);
    }

}
