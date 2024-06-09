package org.example;

import org.example.Filters.Filter;

import java.util.List;
import java.util.Scanner;

/**
 * Eine Klasse zur Analyse von Meteoritendaten.
 */
public class Main {
    public static void main(String[] args) {
        String configFilePath = "config.json";
        String inputFilePath = "input.json";
        String outputFilePath = "output.json";

        // Initialisierung availableFilters
        List<String> availableFilters;

        // Initialisierung des Scanner-Objekts
        Scanner consoleScanner = new Scanner(System.in);

        // Laden der verfügbaren Filter
        availableFilters = ConfigLoader.loadConfig(configFilePath);

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
