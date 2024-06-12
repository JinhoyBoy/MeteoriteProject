package org.meteorite_filter.Filters;

import org.meteorite_filter.Meteorite;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Der MassFilter filtert eine Liste von Meteoriten basierend auf der angegebenen Massenbereich.
 */
public class MassFilter implements Filter {
    private double minMass;
    private double maxMass;

    /**
     * Standardkonstruktor.
     */
    public MassFilter() {
    }

    /**
     * Gibt den Namen des Filters zurück.
     *
     * @return Der Name des Filters.
     */
    @Override
    public String getName() {
        return "Mass";
    }

    /**
     * Konfiguriert den Filter basierend auf Benutzereingaben.
     *
     * @param consoleScanner Der Scanner zum Einlesen der Benutzereingaben.
     */
    @Override
    public void configure(Scanner consoleScanner) {
        try {
            System.out.print("Enter parameters for Mass "+ "\u001B[33m" +"minMass; maxMass" + "\u001B[0m" +": ");
            String[] massParams = consoleScanner.nextLine().split(";");
            if (massParams.length == 2) {
                this.minMass = Double.parseDouble(massParams[0].trim());
                this.maxMass = Double.parseDouble(massParams[1].trim());
            } else if(massParams[0].equals("h")) {
                helpUser(consoleScanner);
            } else {
                System.out.println("Invalid input format for Mass filter. Expected input is (minMass; maxMass).");
            }
        }
        catch(NumberFormatException e) {
            System.out.println("Invalid type of input. Expected input is a decimal number.");
        }
        catch(Exception e) {
            System.out.println("Unexpected error please try again.");
        }
    }

    /**
     * Führt den Filter auf die gegebene Liste von Meteoriten aus und gibt die gefilterte Liste zurück.
     * Nur Meteoriten, deren Masse im angegebenen Bereich liegt, werden beibehalten.
     *
     * @param meteorites Die Liste der Meteoriten, die gefiltert werden sollen.
     * @return Eine neue Liste von Meteoriten, die den angegebenen Massenbereich entsprechen.
     */
    @Override
    public List<Meteorite> execute(List<Meteorite> meteorites) {
        return meteorites.stream()
                .filter(m -> m.getMass() >= minMass && m.getMass() <= maxMass)
                .collect(Collectors.toList());
    }

    /**
     * Gibt dem Benutzer Hilfestellung zur Eingabe der Mass-Parameter.
     *
     * @param consoleScanner Der Scanner zum Einlesen der Benutzereingaben.
     */
    @Override
    public void helpUser(Scanner consoleScanner) {
        System.out.println("Here you can enter the minimal and maximal weight of the meteor. The output will be meteors within this range.");
        System.out.println("Both should be a decimal number (g).");
        System.out.println();
        configure(consoleScanner);
    }
}
