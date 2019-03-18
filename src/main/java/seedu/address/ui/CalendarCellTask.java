package seedu.address.ui;

import javafx.scene.text.Text;

/**
 * Individual text for filling CalendarCell
 */
public class CalendarCellTask extends Text {
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
     * Custom comparator to allow for sorting by priority
     *
     * @param task CalendarCellTask object to compare current CalendarCellTask against
     * @return 0 if equal, <0 if current task's priority is lower, >0 if current task's priority is higher
     */
    public int compareTo(CalendarCellTask task) {
        return Integer.compare(getPriority(), task.getPriority());
    }

}
