package org.example;

import java.util.List;

public class MeteoriteAnalysis {
    public static void main(String[] args) {
        String configFilePath = "config.txt"; // Pfad zur Konfigurationsdatei
        List<Filter> filters = FilterFactory.createFiltersFromConfigAndConsoleInput(configFilePath);
        Pipeline pipeline = new Pipeline(filters);
        String inputFilePath = "input.json"; // Pfad zur Eingabedatei
        String outputFilePath = "output.json"; // Pfad zur Ausgabedatei
        pipeline.process(inputFilePath, outputFilePath);
    }
}
