package seedu.address.storage;

import java.io.IOException;
import java.util.Map;

import seedu.address.model.task.Priority;

public interface NotesStorage {

    void newNotes(String heading, String content) throws IOException;

    Map<String, String> getNotes() throws IOException;
}
