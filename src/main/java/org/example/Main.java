package org.example;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Eine Klasse zur Analyse von Meteoritendaten.
 */
public class Main {
    public static void main(String[] args) {
        String configFilePath = "config.txt";
        String inputFilePath = "input.json";
        String outputFilePath = "output.json";

        List<String> availableFilters = FilterFactory.loadConfig(configFilePath);

        Scanner consoleScanner = new Scanner(System.in);

        // Zeigt die verfügbaren Filter an
        System.out.println("Available filters: " + String.join(", ", availableFilters));
        System.out.print("Enter the filters to use (comma separated): ");
        String[] chosenFilters = consoleScanner.nextLine().split(",");

        List<Filter> filters = new ArrayList<>();
        for (String filterName : chosenFilters) {
            filterName = filterName.trim();
            if (!availableFilters.contains(filterName)) {
                // Gibt eine Fehlermeldung aus, wenn der Filter ungültig ist
                System.out.println("Invalid filter: " + filterName);
                continue;
            }
            // Erstellt den Filter basierend auf dem Namen und den Konsoleneingaben
            Filter filter = FilterFactory.createFilter(filterName, consoleScanner);
            if (filter != null) {
                filters.add(filter);
            }
        }

        // Erstellt und führt die Pipeline aus
        /*
        Pipeline pipeline = new Pipeline(filters);
        String inputFilePath = "input.json"; // Pfad zur Eingabedatei
        String outputFilePath = "output.json"; // Pfad zur Ausgabedatei
        pipeline.process(inputFilePath, outputFilePath);
        */
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
