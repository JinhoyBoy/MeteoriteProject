package org.meteorite_filter.Filters;

import org.meteorite_filter.Meteorite;

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

    /**
     * Gibt den Namen des Filters zurück.
     *
     * @return Der Name des Filters.
     */
    @Override
    public String getName() {
        return "Classification";
    }

    /**
     * Konfiguriert den Filter basierend auf der Benutzereingabe.
     *
     * @param consoleScanner Ein Scanner-Objekt zum Lesen der Benutzereingabe.
     */
    @Override
    public void configure(Scanner consoleScanner) {
        try {
            System.out.print("Enter parameters for Classification " + "\u001B[33m" + "semicolon separated classes" + "\u001B[0m" + ": ");
            String[] classifications = consoleScanner.nextLine().split(";");
            if (classifications[0].equals("h")) {
                helpUser(consoleScanner);
            } else {
                this.classifications = Stream.of(classifications)
                        .map(String::trim)
                        .collect(Collectors.toSet());
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid type of input. Expected input is a text.");
        } catch (Exception e) {
            System.out.println("Unexpected error please try again.");
        }
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

    /**
     * Gibt dem Benutzer Hilfestellung zur Eingabe der Klassifikationen.
     *
     * @param consoleScanner Ein Scanner-Objekt zum Lesen der Benutzereingabe.
     */
    @Override
    public void helpUser(Scanner consoleScanner) {
        System.out.println("Here you can enter the classification of different meteorites.");
        System.out.println("This should be a String (e.g. H5).");
        System.out.println();
        configure(consoleScanner);
    }
}
