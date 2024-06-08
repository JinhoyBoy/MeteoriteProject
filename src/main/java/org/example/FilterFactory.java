package org.example;

import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Die FilterFactory-Klasse ist verantwortlich für das Laden von Filterkonfigurationen
 * aus einer Datei und das Erstellen von Filterobjekten basierend auf Benutzerinput.
 */
public class FilterFactory {

    private static final Map<String, Class<? extends Filter>> filterRegistry = new HashMap<>();

    static {
        // Registriere hier alle verfügbaren Filter-Klassen
        registerFilter("Region", RegionFilter.class);
        registerFilter("Mass", MassFilter.class);
        registerFilter("Classification", ClassificationFilter.class);
        registerFilter("Year", YearFilter.class);
    }

    /**
     * Registriert eine Filterklasse in der Filterfabrik.
     *
     * @param filterName Der Name des Filters.
     * @param filterClass Die Klasse des Filters.
     */
    private static void registerFilter(String filterName, Class<? extends Filter> filterClass) {
        filterRegistry.put(filterName, filterClass);
    }
    /*
    /**
     * Lädt die verfügbaren Filter aus einer Konfigurationsdatei.
     *
     * @param configFilePath Der Pfad zur Konfigurationsdatei.
     * @return Eine Liste der Namen der verfügbaren Filter.
     */
    /*
    public static List<String> loadConfig(String configFilePath) {
        List<String> availableFilters = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileReader(configFilePath))) {
            while (scanner.hasNextLine()) {
                availableFilters.add(scanner.nextLine().trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return availableFilters;
    }*/
    /**
     * Erstellt ein Filterobjekt basierend auf dem Namen des Filters und den Benutzereingaben.
     *
     * @param filterName Der Name des Filters.
     * @param consoleScanner Der Scanner zum Lesen von Benutzereingaben.
     * @return Das erstellte Filterobjekt oder null, wenn der Filtername unbekannt ist.
     */
    public static Filter createFilter(String filterName, Scanner consoleScanner) {
        Class<? extends Filter> filterClass = filterRegistry.get(filterName);
        if (filterClass != null) {
            try {
                Filter filter = filterClass.getDeclaredConstructor().newInstance();
                filter.configure(consoleScanner);
                return filter;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Unknown filter: " + filterName);
        }
        return null;
    }
}
