package org.example;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OutputFilter {
    public static void write(List<Meteorite> meteorites, String filePath) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(meteorites, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
