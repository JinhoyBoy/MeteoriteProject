package org.example.Filters;

import org.example.Meteorite;

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

    @Override
    public String getName() {
        return "Mass";
    }

    @Override
    public void configure(Scanner consoleScanner) {
        try {
            System.out.print("Enter parameters for Mass (minMass,maxMass): ");
            String[] massParams = consoleScanner.nextLine().split(",");
            if (massParams.length == 2) {
                this.minMass = Double.parseDouble(massParams[0].trim());
                this.maxMass = Double.parseDouble(massParams[1].trim());
            } else {
                System.out.println("Invalid input format for Mass filter. Expected input is (minMass,maxMass).");
            }
        }
        catch(NumberFormatException e) {
            System.out.print("Invalid type of input. Expected input is a decimal number.");
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
}
