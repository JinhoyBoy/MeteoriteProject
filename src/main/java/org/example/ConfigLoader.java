package org.example;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConfigLoader {
    /**
     * Lädt die verfügbaren Filter aus einer Konfigurationsdatei.
     *
     * @param configFilePath Der Pfad zur Konfigurationsdatei.
     * @return Eine Liste der Namen der verfügbaren Filter.
     */
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
    }
}

