package org.meteorite_filter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Die ConfigLoader-Klasse lädt die verfügbaren Filter aus einer Konfigurationsdatei.
 */
public class ConfigLoader {
    /**
     * Lädt die verfügbaren Filter aus einer Konfigurationsdatei.
     *
     * @param configFilePath Der Pfad zur Konfigurationsdatei.
     * @return Eine Liste der Namen der verfügbaren Filter.
     */
    public static List<String> loadConfig(String configFilePath) {
        List<String> availableFilters = null;
        try (FileReader reader = new FileReader(configFilePath)) {
            // Verwendet Gson, um die JSON-Datei zu parsen
            Gson gson = new Gson();
            Type listType = new TypeToken<List<FilterConfig>>(){}.getType();
            List<FilterConfig> filters = gson.fromJson(reader, listType);

            // Extrahiert die Namen der Filter
            availableFilters = new ArrayList<>();
            for (FilterConfig filter : filters) {
                availableFilters.add(filter.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return availableFilters;
    }

    /**
     * Innere Klasse zur Repräsentation eines Filters in der JSON-Datei.
     */
    private static class FilterConfig {
        private String name;

        /**
         * Gibt den Namen des Filters zurück.
         *
         * @return Der Name des Filters.
         */
        public String getName() {
            return name;
        }
    }
}
