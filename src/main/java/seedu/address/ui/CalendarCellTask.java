package seedu.address.ui;

import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

/**
 * Individual text for filling CalendarCell
 */
public class CalendarCellTask extends Text {
    //Colours for priority, in order for highest to lowest. In order, red, orange, blue.
    private static final String[] PRIORITY_COLOURS = new String[] { "#FF2D00", "#FFA000", "#0000FF" };

    private int priority;

    /**
     * Creates a text object for CalendarCell
     *
     * @param text Text to display
     * @param priority Priority of task
     */
    public CalendarCellTask(String text, int priority) {
        super();
        setPriority(priority);
        setText(text);
        setTextColour();
    }

    /**
     * Sets the priority of the CalendarCellTask
     *
     * @param priority Priority of task
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Retrieves the priority value of the task
     *
     * @return Integer value of priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets the colour of the text based on the priority of the task.
     */
    private void setTextColour() {
        setFill(Paint.valueOf(PRIORITY_COLOURS[priority - 1]));
    }

    /**
     * Custom comparator to allow for sorting by priority
     *
     * @param task CalendarCellTask object to compare current CalendarCellTask against
     * @return 0 if equal, <0 if current task's priority is lower, >0 if current task's priority is higher
     */
    public int compareTo(CalendarCellTask task) {
        return Integer.compare(getPriority(), task.getPriority());
    }

}
