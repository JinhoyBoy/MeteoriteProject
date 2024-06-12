package org.meteorite_filter;

import org.meteorite_filter.Filters.Filter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Die FilterFactory-Klasse erstellt und konfiguriert Filterobjekte basierend auf den verfügbaren Filtern.
 */
public class FilterFactory {

    private static Map<String, Class<? extends Filter>> filterRegistry;

    /**
     * Initialisiert die Filter-Registry basierend auf den verfügbaren Filtern aus der Konfigurationsdatei.
     *
     * @param availableFilters Die Liste der verfügbaren Filter.
     */
    public static void initialize(List<String> availableFilters) {
        filterRegistry = new HashMap<>();
        for (String filterName : availableFilters) {
            try {
                Class<? extends Filter> filterClass = (Class<? extends Filter>) Class.forName("org.meteorite_filter.Filters." + filterName + "Filter");
                filterRegistry.put(filterName, filterClass);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Erstellt ein Filterobjekt basierend auf dem Namen des Filters und den Benutzereingaben.
     *
     * @param filterName     Der Name des Filters.
     * @param consoleScanner Der Scanner zum Lesen von Benutzereingaben.
     * @return Das erstellte Filterobjekt oder null, wenn der Filtername unbekannt ist.
     */
    public static Filter createFilter(String filterName, Scanner consoleScanner) {
        if (filterRegistry == null) {
            System.err.println("FilterRegistry ist nicht initialisiert. Rufen Sie zuerst die initialize-Methode auf.");
            return null;
        }

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
