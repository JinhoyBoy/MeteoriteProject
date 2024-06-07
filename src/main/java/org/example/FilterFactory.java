package org.example;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilterFactory {
    public static List<String> loadConfig(String configFilePath) {
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

    public static Filter createFilter(String filterName, Scanner consoleScanner) {
        switch (filterName) {
            case "Region":
                System.out.print("Enter parameters for Region (latitude,longitude,radius): ");
                String[] regionParams = consoleScanner.nextLine().split(",");
                double lat = Double.parseDouble(regionParams[0].trim());
                double lon = Double.parseDouble(regionParams[1].trim());
                double radius = Double.parseDouble(regionParams[2].trim());
                return new RegionFilter(lat, lon, radius);
            case "Mass":
                System.out.print("Enter parameters for Mass (minMass,maxMass): ");
                String[] massParams = consoleScanner.nextLine().split(",");
                double minMass = Double.parseDouble(massParams[0].trim());
                double maxMass = Double.parseDouble(massParams[1].trim());
                return new MassFilter(minMass, maxMass);
            case "Classification":
                System.out.print("Enter parameters for Classification (comma separated classes): ");
                String[] classifications = consoleScanner.nextLine().split(",");
                for (int i = 0; i < classifications.length; i++) {
                    classifications[i] = classifications[i].trim();
                }
                return new ClassificationFilter(classifications);
            case "Year":
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
                System.out.println("Unknown filter: " + filterName);
                return null;
        }
    }
}
