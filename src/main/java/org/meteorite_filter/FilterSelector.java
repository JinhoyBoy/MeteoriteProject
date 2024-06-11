package org.meteorite_filter;

import org.meteorite_filter.Filters.Filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilterSelector {


    public static List<Filter> selectFilters(Scanner scanner, List<String> availableFilters) {
        List<Filter> selectedFilters = new ArrayList<>();

        // Prompt the user to choose filters
        System.out.println("Available filters: " + String.join(", ", availableFilters));
        System.out.print("Enter the filters to use (comma separated): ");
        String[] chosenFilters = scanner.nextLine().split(",");
        System.out.println("For any help with the individual filters, enter h into the console. \n");

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

