package seedu.address.ui;

import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import seedu.address.model.task.Task;

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
     * @param task task to display
     */
    public CalendarCellTask(Task task, boolean selected) {
        super();
        setPriority(task.getPriority().toInt());
        setText(task.getName().toString());
        setTextProperties(selected);
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
     * Sets the colour of the text based on the priority of the task. Highlights the task if it is selected.
     */
    private void setTextProperties(boolean selected) {
        setFill(Paint.valueOf(PRIORITY_COLOURS[priority - 1]));
        if (selected) {
            setUnderline(true);
            setFont(Font.font(Font.getDefault().getName(), FontWeight.BOLD, Font.getDefault().getSize()));
        }
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
