package org.example;

import org.example.Filters.Filter;

import java.util.List;
import java.util.Scanner;

/**
 * Eine Klasse zur Analyse von Meteoritendaten.
 */
public class Main extends ConfigLoader {
    public static void main(String[] args) {
        String configFilePath = "src/main/resources/config.json";
        String inputFilePath = "src/main/resources/input.json";
        String outputFilePath = "src/main/resources/output.json";

        // Initialisierung des Scanner-Objekts
        Scanner consoleScanner = new Scanner(System.in);

        // Laden der verfügbaren Filter
        List<String> availableFilters = ConfigLoader.loadConfig(configFilePath);

        // Initialisierung der FilterFactory mit den verfügbaren Filtern
        FilterFactory.initialize(availableFilters);

        // Auswahl der Filter mit dem FilterSelector
        List<Filter> filters = FilterSelector.selectFilters(consoleScanner, availableFilters);

        // Meteoritendaten aus der Eingabedatei lesen
        List<Meteorite> meteorites = InputFilter.read(inputFilePath);

        // Meteoritendaten durch alle Filter verarbeiten
        for (Filter filter : filters) {
            meteorites = filter.execute(meteorites);
        }

        // Verarbeitete Meteoritendaten in die Ausgabedatei schreiben
        OutputFilter.write(meteorites, outputFilePath);
    }
}
