package org.example;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilterFactory {
    public static List<Filter> createFiltersFromConfigAndConsoleInput(String configFilePath) {
        List<Filter> filters = new ArrayList<>();

        List<String> availableFilters = loadConfig(configFilePath);
        Scanner consoleScanner = new Scanner(System.in);

        System.out.println("Available filters: " + String.join(", ", availableFilters));
        System.out.print("Enter the filters to use (comma separated): ");
        String[] chosenFilters = consoleScanner.nextLine().split(",");

        for (String filterName : chosenFilters) {
            filterName = filterName.trim();
            if (!availableFilters.contains(filterName)) {
                System.out.println("Invalid filter: " + filterName);
                continue;
            }

            filters.add(createFilter(filterName));
        }

        return filters;
    }

    private static List<String> loadConfig(String configFilePath) {
        List<String> availableFilters = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileReader(configFilePath))) {
            while (scanner.hasNextLine()) {
                availableFilters.add(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return availableFilters;
    }

    private static Filter createFilter(String filterName) {
        Scanner consoleScanner = new Scanner(System.in);

        switch (filterName) {
            case "Region":
                System.out.print("Enter parameters for Region (latitude,longitude,radius): ");
                String[] regionParams = consoleScanner.nextLine().split(",");
                double lat = Double.parseDouble(regionParams[0]);
                double lon = Double.parseDouble(regionParams[1]);
                double radius = Double.parseDouble(regionParams[2]);
                return new RegionFilter(lat, lon, radius);
            case "Mass":
                System.out.print("Enter parameters for Mass (minMass,maxMass): ");
                String[] massParams = consoleScanner.nextLine().split(",");
                double minMass = Double.parseDouble(massParams[0]);
                double maxMass = Double.parseDouble(massParams[1]);
                return new MassFilter(minMass, maxMass);
            case "Classification":
                System.out.print("Enter parameters for Classification (comma separated classes): ");
                String[] classifications = consoleScanner.nextLine().split(",");
                return new ClassificationFilter(classifications);
            case "Year":
                System.out.print("Enter parameters for Year (startYear,endYear): ");
                String[] yearParams = consoleScanner.nextLine().split(",");
                int startYear = Integer.parseInt(yearParams[0]);
                int endYear = Integer.parseInt(yearParams[1]);
                return new YearFilter(startYear, endYear);
            default:
                System.out.println("Unknown filter: " + filterName);
                return null;
        }
    }
}
