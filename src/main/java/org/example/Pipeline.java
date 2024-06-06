package org.example;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class Pipeline {
    private List<Filter> filters;

    public Pipeline(String configFilePath) {
        filters = new ArrayList<>();
        loadConfig(configFilePath);
    }

    private void loadConfig(String configFilePath) {
        try (Scanner scanner = new Scanner(new FileReader(configFilePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(":");
                String filterType = parts[0];
                String[] params = parts[1].split(",");

                switch (filterType) {
                    case "Region":
                        double lat = Double.parseDouble(params[0]);
                        double lon = Double.parseDouble(params[1]);
                        double radius = Double.parseDouble(params[2]);
                        filters.add(new RegionFilter(lat, lon, radius));
                        break;
                    case "Mass":
                        double minMass = Double.parseDouble(params[0]);
                        double maxMass = Double.parseDouble(params[1]);
                        filters.add(new MassFilter(minMass, maxMass));
                        break;
                    case "Classification":
                        filters.add(new ClassificationFilter(params));
                        break;
                    case "Year":
                        int startYear = Integer.parseInt(params[0]);
                        int endYear = Integer.parseInt(params[1]);
                        filters.add(new YearFilter(startYear, endYear));
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void process(String inputFilePath, String outputFilePath) {
        List<Meteorite> meteorites = InputFilter.read(inputFilePath);

        for (Filter filter : filters) {
            meteorites = filter.execute(meteorites);
        }

        OutputFilter.write(meteorites, outputFilePath);
    }
}
