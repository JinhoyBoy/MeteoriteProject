package org.example;

import java.util.List;

/**
 * Eine Pipeline zur Verarbeitung von Meteoritendaten.
 */
public class Pipeline {
    private List<Filter> filters;

    /**
     * Erstellt eine Pipeline mit den angegebenen Filtern.
     *
     * @param filters Die Liste der Filter, die in der Pipeline verwendet werden sollen.
     */
    public Pipeline(List<Filter> filters) {
        this.filters = filters;
    }

    /**
     * Verarbeitet die Meteoritendaten aus der Eingabedatei und schreibt das Ergebnis in die Ausgabedatei.
     *
     * @param inputFilePath  Der Pfad zur Eingabedatei mit den Meteoritendaten.
     * @param outputFilePath Der Pfad zur Ausgabedatei, in die die verarbeiteten Daten geschrieben werden sollen.
     */
    public void process(String inputFilePath, String outputFilePath) {
        // Meteoritendaten aus der Eingabedatei lesen
        List<Meteorite> meteorites = InputFilter.read(inputFilePath);

        // Meteoritendaten durch alle Filter in der Pipeline verarbeiten
        for (Filter filter : filters) {
            meteorites = filter.execute(meteorites);
        }

        // Verarbeitete Meteoritendaten in die Ausgabedatei schreiben
        OutputFilter.write(meteorites, outputFilePath);
    }
}
