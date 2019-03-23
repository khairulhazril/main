package seedu.address.ui;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.notes.Notes;
/**
 * Panel containing the list of notes.
 */
public class NotesListPanel extends UiPart<Region> {

    private static final String FXML = "NotesListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(NotesListPanel.class);

    @javafx.fxml.FXML
    private ListView<Notes> notesListView;

    public NotesListPanel(ObservableList<Notes> NotesList, ObservableValue<Notes> selectedNotes,
                         Consumer<Notes> onSelectedNotesChange) {
        super(FXML);
        notesListView.setItems(NotesList);
        notesListView.setCellFactory(listView -> new NotesListPanel.NotesListViewCell());
        notesListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            logger.fine("Selection in notes list panel changed to : '" + newValue + "'");
            onSelectedNotesChange.accept(newValue);
        });
        selectedNotes.addListener((observable, oldValue, newValue) -> {
            logger.fine("Selected notes changed to: " + newValue);

            // Don't modify selection if we are already selecting the selected task,
            // otherwise we would have an infinite loop.
            if (Objects.equals(notesListView.getSelectionModel().getSelectedItem(), newValue)) {
                return;
            }

            if (newValue == null) {
                notesListView.getSelectionModel().clearSelection();
            } else {
                int index = notesListView.getItems().indexOf(newValue);
                notesListView.scrollTo(index);
                notesListView.getSelectionModel().clearAndSelect(index);
            }
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Task} using a {@code TaskCard}.
     */
    class NotesListViewCell extends ListCell<Notes> {
        @Override
        protected void updateItem(Notes notes, boolean empty) {
            super.updateItem(notes, empty);

            if (empty || notes == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new NotesCard(notes, getIndex() + 1).getRoot());
            }
        }
    }

}
