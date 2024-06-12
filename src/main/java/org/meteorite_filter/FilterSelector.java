package org.meteorite_filter;

import org.meteorite_filter.Filters.Filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Die FilterSelector-Klasse ermöglicht die Auswahl und Konfiguration von Filtern basierend auf Benutzereingaben.
 */
public class FilterSelector {

    /**
     * Ermöglicht dem Benutzer die Auswahl und Konfiguration von Filtern aus einer Liste verfügbarer Filter.
     *
     * @param scanner Der Scanner zum Lesen der Benutzereingaben.
     * @param availableFilters Die Liste der verfügbaren Filter.
     * @return Eine Liste der konfigurierten Filter.
     */
    public static List<Filter> selectFilters(Scanner scanner, List<String> availableFilters) {
        List<Filter> selectedFilters = new ArrayList<>();

        // Benutzereingabe auffordern, um Filter auszuwählen
        System.out.println("Available filters: " + "\u001B[33m" + String.join("; ", availableFilters) + "\u001B[0m");
        System.out.print("Enter the filters to use (semicolon separated): ");
        String[] chosenFilters = scanner.nextLine().split(";");
        System.out.println("For any help with the individual filters, enter h into the console. \n");

        // Filter validieren und Instanzen erstellen
        for (String filterName : chosenFilters) {
            filterName = filterName.trim();
            if (!availableFilters.contains(filterName)) {
                System.out.println("Invalid filter: " + filterName);
                continue;
            }
            Filter filter = FilterFactory.createFilter(filterName, scanner);
            if (filter != null) {
                selectedFilters.add(filter);
            }
        }

        return selectedFilters;
    }
}
