package org.example;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Die FilterFactory-Klasse ist verantwortlich für das Laden von Filterkonfigurationen
 * aus einer Datei und das Erstellen von Filterobjekten basierend auf Benutzerinput.
 */
public class FilterFactory {

    /**
     * Lädt die verfügbaren Filter aus einer Konfigurationsdatei.
     *
     * @param configFilePath Der Pfad zur Konfigurationsdatei.
     * @return Eine Liste der Namen der verfügbaren Filter.
     */
    public static List<String> loadConfig(String configFilePath) {
        List<String> availableFilters = new ArrayList<>();

        // Versucht, die Konfigurationsdatei zu lesen und die Filter hinzuzufügen
        try (Scanner scanner = new Scanner(new FileReader(configFilePath))) {
            while (scanner.hasNextLine()) {
                availableFilters.add(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return availableFilters;
    }

    /**
     * Erstellt ein Filterobjekt basierend auf dem Namen des Filters und den Benutzereingaben.
     *
     * @param filterName Der Name des Filters.
     * @param consoleScanner Der Scanner zum Lesen von Benutzereingaben.
     * @return Das erstellte Filterobjekt oder null, wenn der Filtername unbekannt ist.
     */
    public static Filter createFilter(String filterName, Scanner consoleScanner) {
        switch (filterName) {
            case "Region":
                // Liest die Parameter für den RegionFilter von der Konsole
                System.out.print("Enter parameters for Region (latitude,longitude,radius): ");
                String[] regionParams = consoleScanner.nextLine().split(",");
                double lat = Double.parseDouble(regionParams[0].trim());
                double lon = Double.parseDouble(regionParams[1].trim());
                double radius = Double.parseDouble(regionParams[2].trim());
                return new RegionFilter(lat, lon, radius);
            case "Mass":
                // Liest die Parameter für den MassFilter von der Konsole
                System.out.print("Enter parameters for Mass (minMass,maxMass): ");
                String[] massParams = consoleScanner.nextLine().split(",");
                double minMass = Double.parseDouble(massParams[0].trim());
                double maxMass = Double.parseDouble(massParams[1].trim());
                return new MassFilter(minMass, maxMass);
            case "Classification":
                // Liest die Parameter für den ClassificationFilter von der Konsole
                System.out.print("Enter parameters for Classification (comma separated classes): ");
                String[] classifications = consoleScanner.nextLine().split(",");
                for (int i = 0; i < classifications.length; i++) {
                    classifications[i] = classifications[i].trim();
                }
                return new ClassificationFilter(classifications);
            case "Year":
                // Liest die Parameter für den YearFilter von der Konsole
                System.out.print("Enter parameters for Year (startYear,endYear): ");
                String[] yearParams = consoleScanner.nextLine().split(",");
                if (yearParams.length == 1) {
                    int year = Integer.parseInt(yearParams[0].trim());
                    return new YearFilter(year);
                } else if (yearParams.length == 2) {
                    int startYear = Integer.parseInt(yearParams[0].trim());
                    int endYear = Integer.parseInt(yearParams[1].trim());
                    return new YearFilter(startYear, endYear);
                } else {
                    System.out.println("Invalid input for Year filter");
                    return null;
                }
            default:
                // Gibt eine Fehlermeldung aus, wenn der Filtername unbekannt ist
                System.out.println("Unknown filter: " + filterName);
                return null;
        }
    }
}
