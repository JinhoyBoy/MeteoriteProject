package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class OutputFilter {
    // Methode zum Schreiben der Meteoritenliste in eine formatierte JSON-Datei
    public static void write(List<Meteorite> meteorites, String filePath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(meteorites, writer);
        } catch (IOException e) {
            e.printStackTrace(); // Fehlerbehandlung
        }
    }
}
