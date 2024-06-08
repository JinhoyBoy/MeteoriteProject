package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilterSelector {
    private final List<String> availableFilters;

    public FilterSelector(List<String> availableFilters) {
        this.availableFilters = availableFilters;
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

