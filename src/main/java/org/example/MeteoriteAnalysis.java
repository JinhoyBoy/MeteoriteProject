package org.example;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Eine Klasse zur Analyse von Meteoritendaten.
 */
public class MeteoriteAnalysis {
    public static void main(String[] args) {
        String configFilePath = "config.txt"; // Pfad zur Konfigurationsdatei
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
        Pipeline pipeline = new Pipeline(filters);
        String inputFilePath = "input.json"; // Pfad zur Eingabedatei
        String outputFilePath = "output.json"; // Pfad zur Ausgabedatei
        pipeline.process(inputFilePath, outputFilePath);
    }
}
