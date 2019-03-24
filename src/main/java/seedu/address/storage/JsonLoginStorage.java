package seedu.address.storage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * To access the LoginEvent stored as a JSON file
 */
public class JsonLoginStorage implements LoginStorage {

    private String loginFilePath;
    private Map<String, String> accounts;

    public JsonLoginStorage(Path filePath) throws IOException {
        loginFilePath = "./" + filePath.toString();

        if (Files.notExists(filePath)) {
            createLoginInfoFile();
        }

        setAccount();
    }

    /**
     *  Adds properties to the Json file
     */
    @Override
    public void newUser(String username, String password) throws IOException {
        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty(username, password);

        writeJson(new Gson(), jsonObject);
        setAccount();
    }

    /**
     * Gets the accounts as maps
     * @return accounts
     */
    @Override public Map<String, String> getAccounts() {
        return accounts;
    }

    /**
     * Sets up user account
     * @throws IOException if account cannot be created
     */
    private void setAccount() throws IOException {
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        accounts = new Gson().fromJson(new FileReader(loginFilePath), type);
    }

    /**
     * Convert user accounts as JSON objects
     * @return JSON objects
     * @throws IOException if obeject cannot be retrieved
     */
    private JsonObject getJsonObject() throws IOException {
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(new FileReader(loginFilePath));

        return jsonElement.getAsJsonObject();
    }

    /**
     * Creates user login account with JSON file
     * @throws IOException if cannot be written to JSON file
     */
    private void createLoginInfoFile() throws IOException {
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
        FileWriter file = new FileWriter(loginFilePath);
        file.write(json);
        file.flush();
    }
}
