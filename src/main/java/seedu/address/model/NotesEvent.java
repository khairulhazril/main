package seedu.address.model;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.StringUtil;
import seedu.address.model.notes.Notes;
import seedu.address.storage.JsonNotesStorage;

/**
 * A notes event
 */
public class NotesEvent {

    private JsonNotesStorage notesStorage;
    private Notes notes;
    private Logger logger = LogsCenter.getLogger(NotesEvent.class);

    /**
     * Constructor to start user with stub username and password
     */

    public NotesEvent() {
        final Path notesPath = Paths.get("notes.json");

        try {
            notesStorage = new JsonNotesStorage(notesPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Creates a new user in JSON file
     * @param notes
     */
    public void newNotes(Notes notes) {
        String newheading = notes.getHeading().toString();
        String newcontent = notes.getContent().toString();

        try {
            notesStorage.newNotes(newheading, newcontent);
        } catch (IOException e) {
            logger.warning("User storage is unable to read or write to Json file" + StringUtil.getDetails(e));
        }
    }

}
