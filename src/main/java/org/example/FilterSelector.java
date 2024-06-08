package org.example;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilterSelector {
    private final List<String> availableFilters;

    public FilterSelector(String configFilePath) {
        this.availableFilters = loadConfig(configFilePath);
    }

    /**
     * Lädt die verfügbaren Filter aus einer Konfigurationsdatei.
     *
     * @param configFilePath Der Pfad zur Konfigurationsdatei.
     * @return Eine Liste der Namen der verfügbaren Filter.
     */
    private List<String> loadConfig(String configFilePath) {
        List<String> availableFilters = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileReader(configFilePath))) {
            while (scanner.hasNextLine()) {
                availableFilters.add(scanner.nextLine().trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return availableFilters;
    }

    public List<Filter> selectFilters(Scanner scanner) {
        List<Filter> selectedFilters = new ArrayList<>();

        // Prompt the user to choose filters
        System.out.println("Available filters: " + String.join(", ", availableFilters));
        System.out.print("Enter the filters to use (comma separated): ");
        String[] chosenFilters = scanner.nextLine().split(",");

        // Validate and create filter instances
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

