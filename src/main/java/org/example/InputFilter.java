package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Die Klasse InputFilter enthält eine Methode zum Lesen einer Liste von Meteoriten aus einer JSON-Datei.
 */
public class InputFilter {

    /**
     * Liest die Liste der Meteoriten aus einer JSON-Datei ein.
     *
     * @param filePath Der Pfad zur JSON-Datei, die eingelesen werden soll.
     * @return Eine Liste von Meteoriten, die aus der Datei eingelesen wurden,
     *         oder null, falls ein Fehler auftritt.
     */
    public static List<Meteorite> read(String filePath) {
        Gson gson = new Gson(); // Gson-Instanz zur JSON-Deserialisierung
        try (FileReader reader = new FileReader(filePath)) {
            // Definiert den Typ der Liste von Meteoriten
            Type meteoriteListType = new TypeToken<List<Meteorite>>() {}.getType();
            // Liest die JSON-Datei und konvertiert sie in eine Liste von Meteoriten
            return gson.fromJson(reader, meteoriteListType);
        } catch (IOException e) {
            e.printStackTrace(); // Druckt den Stacktrace bei einem Fehler
            return null; // Gibt null zurück, falls ein Fehler auftritt
        }
    }
}

