package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ReadOnlyTaskManager;

public class JsonNotesStorage implements NotesStorage {

    private String notesFilePath;
    private Map<String, String> notes;

    public JsonNotesStorage(Path filePath) throws IOException {
        notesFilePath = "./" + filePath.toString();

        if (Files.notExists(filePath)) {
            createNotesFile();
        }

    }

    /**
     *  Adds properties to the Json file
     */
    @Override
    public void newNotes(String heading, String content) throws IOException {
        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty(heading, content);

        writeJson(new Gson(), jsonObject);
        setNotes();
    }

    /**
     * Gets the accounts as maps
     * @return accounts
     */
    @Override public Map<String, String> getNotes() {
        return notes;
    }

    /**
     * Sets up user account
     * @throws IOException if account cannot be created
     */
    private void setNotes() throws IOException {
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        notes = new Gson().fromJson(new FileReader(notesFilePath), type);
    }

    /**
     * Convert user accounts as JSON objects
     * @return JSON objects
     * @throws IOException if obeject cannot be retrieved
     */
    private JsonObject getJsonObject() throws IOException {
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(new FileReader(notesFilePath));

        return jsonElement.getAsJsonObject();
    }

    /**
     * Creates user login account with JSON file
     * @throws IOException if cannot be written to JSON file
     */
    private void createNotesFile() throws IOException {
        JsonObject jsonObject = new JsonObject();
        writeJson(new Gson(), jsonObject);
    }

    /**
     * To write and store to JSON file
     * @param gson
     * @param jsonObject
     * @throws IOException
     */
    private void writeJson(Gson gson, JsonObject jsonObject) throws IOException {
        String json = gson.toJson(jsonObject);
        FileWriter file = new FileWriter(notesFilePath);
        file.write(json);
        file.flush();
    }


}
