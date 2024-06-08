package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Die Klasse OutputFilter enthält eine Methode zum Schreiben einer Liste von Meteoriten in eine JSON-Datei.
 */
public class OutputFilter {

    /**
     * Schreibt eine Liste von Meteoriten in eine formatierte JSON-Datei.
     *
     * @param meteorites Die Liste von Meteoriten, die geschrieben werden soll.
     * @param filePath Der Pfad zur Ausgabedatei.
     */
    public static void write(List<Meteorite> meteorites, String filePath) {
        // Erstellen einer Gson-Instanz mit Pretty-Printing-Einstellung
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filePath)) {
            // Konvertieren der Meteoritenliste in JSON und Schreiben in die Datei
            gson.toJson(meteorites, writer);
        } catch (IOException e) {
            e.printStackTrace(); // Fehlerbehandlung: Ausdrucken des Stacktraces
        }
    }
}

