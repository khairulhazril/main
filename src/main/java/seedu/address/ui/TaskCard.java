package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.task.Task;

/**
 * An UI component that displays information of a {@code Task}.
 */
public class TaskCard extends UiPart<Region> {

    private static final String FXML = "TaskListCard.fxml";

    private static final String[] PRIORITYNAMES = new String[] { "High", "Medium", "Low" };
    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on TaskManager level 4</a>
     */

    public final Task task;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label module;
    @FXML
    private Label due;
    @FXML
    private Label priority;
    @FXML
    private Label daysLeft;
    @FXML
    private FlowPane tags;

    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        name.setText(task.getName().fullName);
        module.setText(task.getModule().value);
        due.setText(task.getDue().value);
        priority.setText(PRIORITYNAMES[Integer.parseInt(task.getPriority().value) - 1]);
        name.setWrapText(true);
        if (task.getDaysRemaining() == -1) {
            daysLeft.setText("Task is overdue!!!");
        } else if (task.getDaysRemaining() == 0) {
            daysLeft.setText("Task is due today!");
        } else {
            daysLeft.setText("Days Left: " + Integer.toString(task.getDaysRemaining()));
        }
        task.getTags().forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        int daysRemaining = task.getDaysRemaining();

        if (daysRemaining < 0) {
            id.setStyle("-fx-text-fill: grey");
            name.setStyle("-fx-text-fill: grey");
            module.setStyle("-fx-text-fill: grey");
            due.setStyle("-fx-text-fill: grey");
            priority.setStyle("-fx-text-fill: grey");
            daysLeft.setStyle("-fx-text-fill: red");

        } else if (daysRemaining == 0) {
            id.setStyle("-fx-text-fill: orange");
            name.setStyle("-fx-text-fill: orange");
            module.setStyle("-fx-text-fill: orange");
            due.setStyle("-fx-text-fill: orange");
            priority.setStyle("-fx-text-fill: orange");
            daysLeft.setStyle("-fx-text-fill: orange");

        } else if (daysRemaining <= 7) {
            id.setStyle("-fx-text-fill: lightgreen");
            name.setStyle("-fx-text-fill: lightgreen");
            module.setStyle("-fx-text-fill: lightgreen");
            due.setStyle("-fx-text-fill: lightgreen");
            priority.setStyle("-fx-text-fill: lightgreen");
            daysLeft.setStyle("-fx-text-fill: lightgreen");
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskCard)) {
            return false;
        }

        // state check
        TaskCard card = (TaskCard) other;
        return id.getText().equals(card.id.getText())
                && task.equals(card.task);
    }
}
