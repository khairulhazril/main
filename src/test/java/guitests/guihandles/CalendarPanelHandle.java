package guitests.guihandles;

import guitests.GuiRobot;

import javafx.scene.Node;

/**
 * Provides a handle for {@code CalendarPanelHandle} containing the grid of
 * {@code CalendarContentCell}.
 */
public class CalendarPanelHandle extends NodeHandle<Node> {
    public static final String CALENDAR_PANEL_ID = "#calendarPanel";

    public CalendarPanelHandle(Node calendarPanelNode) {
        super(calendarPanelNode);
    }

    /**
     * Returns presence of calendar grid.
     */
    public static boolean isWindowPresent() {
        return new GuiRobot().isWindowShown(CALENDAR_PANEL_ID);
    }
}
