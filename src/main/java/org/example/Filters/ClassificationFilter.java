package org.example.Filters;

import org.example.Meteorite;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Scanner;

/**
 * Der ClassificationFilter filtert eine Liste von Meteoriten basierend auf den angegebenen Klassifikationen.
 */
public class ClassificationFilter implements Filter {
    private Set<String> classifications;

    /**
     * Standardkonstruktor.
     */
    public ClassificationFilter() {
    }

    @Override
    public String getName() {
        return "Classification";
    }

    @Override
    public void configure(Scanner consoleScanner) {
        System.out.print("Enter parameters for Classification (comma separated classes): ");
        String[] classifications = consoleScanner.nextLine().split(",");
        this.classifications = Stream.of(classifications)
                .map(String::trim)
                .collect(Collectors.toSet());
    }

    /**
     * Führt den Filter auf die gegebene Liste von Meteoriten aus und gibt die gefilterte Liste zurück.
     * Nur Meteoriten, deren Klassifikation in der angegebenen Menge enthalten ist, werden beibehalten.
     *
     * @param meteorites Die Liste der Meteoriten, die gefiltert werden sollen.
     * @return Eine neue Liste von Meteoriten, die den angegebenen Klassifikationen entsprechen.
     */
    @Override
    public List<Meteorite> execute(List<Meteorite> meteorites) {
        return meteorites.stream()
                .filter(m -> classifications.contains(m.getRecclass()))
                .collect(Collectors.toList());
    }
}
