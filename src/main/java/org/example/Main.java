package org.example;

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

        // Anzeige der verfügbaren Filter durch die Konfigurationsdatei
        List<String> availableFilters = FilterFactory.loadConfig(configFilePath);
        Scanner consoleScanner = new Scanner(System.in);

        // Auswählen der Filter durch Konsoleneingabe
        FilterSelector filterSelector = new FilterSelector(availableFilters);
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
