package seedu.address.storage;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;



public class JsonLoginStorage implements loginStorage {

    private String loginFilePath;
    private Map<String, String> accounts;

    public JsonLoginStorage(Path filePath) throws IOException {
     loginFilePath = "./" + filePath.toString();

     if (Files.notExists(filePath)) {
         createLoginInfoFile();
     }

     setAccount();
    }

    @Override
    public void newUser(String username, String password) throws IOException {
        JsonObject jsonObject = getJsonObject();
        jsonObject.addProperty(username, password);

        writeJson(new Gson(), jsonObject);
        setAccount();
    }

    @Override public Map<String, String> getAccounts() {
        return accounts;
    }

    private void setAccount() throws IOException {
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        accounts = new Gson().fromJson(new FileReader(loginFilePath), type);
    }

    private JsonObject getJsonObject() throws IOException {
        JsonParser parser = new JsonParser();
        JsonElement jsonElement = parser.parse(new FileReader(loginFilePath));

        return jsonElement.getAsJsonObject();
    }

    private void createLoginInfoFile() throws IOException  {
        JsonObject jsonObject = new JsonObject();
        writeJson(new Gson(), jsonObject);
    }

    private void writeJson(Gson gson, JsonObject jsonObject) throws IOException {
        String json = gson.toJson(jsonObject);
        FileWriter file = new FileWriter(loginFilePath);
        file.write(json);
        file.flush();
    }
}
