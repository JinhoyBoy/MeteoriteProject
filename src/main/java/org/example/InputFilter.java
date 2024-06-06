package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class InputFilter {
    public static List<Meteorite> read(String filePath) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            Type meteoriteListType = new TypeToken<List<Meteorite>>() {}.getType();
            return gson.fromJson(reader, meteoriteListType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
