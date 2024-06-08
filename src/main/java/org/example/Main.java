package org.example;

import org.example.Filters.Filter;

import java.util.List;
import java.util.Scanner;

/**
 * Eine Klasse zur Analyse von Meteoritendaten.
 */
public class Main {
    public static void main(String[] args) {
        String configFilePath = "config.txt";
        String inputFilePath = "input.json";
        String outputFilePath = "output.json";

        // Erstellen des FilterSelectors mit dem Pfad zur Konfigurationsdatei
        FilterSelector filterSelector = new FilterSelector(configFilePath);

        // Initialisierung des Scanner-Objekts
        Scanner consoleScanner = new Scanner(System.in);

        // Auswahl der Filter mit dem FilterSelector
        List<Filter> filters = filterSelector.selectFilters(consoleScanner);

        // Meteoritendaten aus der Eingabedatei lesen
        List<Meteorite> meteorites = InputFilter.read(inputFilePath);

        // Meteoritendaten durch alle Filter in der Pipeline verarbeiten
        for (Filter filter : filters) {
            meteorites = filter.execute(meteorites);
        }

        // Verarbeitete Meteoritendaten in die Ausgabedatei schreiben
        OutputFilter.write(meteorites, outputFilePath);
    }
}
