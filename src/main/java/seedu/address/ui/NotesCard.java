package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.notes.Notes;

/**
 * An UI component that displays information of a {@code Task}.
 */
public class NotesCard extends UiPart<Region> {
    private static final String FXML = "NotesListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on TaskManager level 4</a>
     */

    public final Notes notes;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label heading;
    @FXML
    private Label id;
    @FXML
    private Label content;


    public NotesCard(Notes notes, int displayedIndex) {
        super(FXML);
        this.notes = notes;
        id.setText(displayedIndex + ". ");
        heading.setText(notes.getHeading().realHeading);
        content.setText(notes.getContent().realContent);
        heading.setWrapText(true);
        content.setWrapText(true);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NotesCard)) {
            return false;
        }

        // state check
        NotesCard card = (NotesCard) other;
        return id.getText().equals(card.id.getText())
                && notes.equals(card.notes);
    }
}
