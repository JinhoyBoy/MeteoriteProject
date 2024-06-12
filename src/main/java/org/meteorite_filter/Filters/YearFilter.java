package org.meteorite_filter.Filters;

import org.meteorite_filter.Meteorite;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Die YearFilter-Klasse filtert Meteoriten basierend auf einem angegebenen Jahr oder Jahrgangsbereich.
 */
public class YearFilter implements Filter {
    private int startYear;
    private int endYear;

    /**
     * Standardkonstruktor.
     */
    public YearFilter() {
    }

    /**
     * Gibt den Namen des Filters zurück.
     *
     * @return Der Name des Filters.
     */
    @Override
    public String getName() {
        return "Year";
    }

    /**
     * Konfiguriert den Filter basierend auf Benutzereingaben.
     *
     * @param consoleScanner Der Scanner zum Einlesen der Benutzereingaben.
     */
    @Override
    public void configure(Scanner consoleScanner) {
        try {
            System.out.print("Enter parameters for Year " +"\u001B[33m"+ "startYear; endYear" +"\u001B[0m"+ " or " +"\u001B[33m"+ "year"+ "\u001B[0m" + ": ");
            String[] yearParams = consoleScanner.nextLine().split(";");
            if (yearParams[0].equals("h")) {
                helpUser(consoleScanner);
            } else if (yearParams.length == 1) {
                int year = Integer.parseInt(yearParams[0].trim());
                this.startYear = year;
                this.endYear = year;
            } else if (yearParams.length == 2) {
                this.startYear = Integer.parseInt(yearParams[0].trim());
                this.endYear = Integer.parseInt(yearParams[1].trim());
            } else {
                System.out.println("Invalid input format for Year filter. Expected input is (startYear;endYear) or (year).");
            }
        }
        catch(NumberFormatException e) {
            System.out.println("Invalid type of input. Expected input is a whole number.");
        }
        catch(Exception e) {
            System.out.println("Unexpected error please try again.");
        }
    }

    /**
     * Führt den Filter aus und gibt eine Liste der gefilterten Meteoriten zurück.
     *
     * @param meteorites Die Liste der Meteoriten, die gefiltert werden soll.
     * @return Eine Liste der Meteoriten, die innerhalb des angegebenen Jahresbereichs liegen.
     */
    @Override
    public List<Meteorite> execute(List<Meteorite> meteorites) {
        // Filtert die Meteoriten basierend auf dem Jahr oder Jahrgangsbereich
        return meteorites.stream()
                .filter(m -> {
                    int year = m.getYearAsInt();
                    return year >= startYear && year <= endYear;
                })
                .collect(Collectors.toList());
    }

    /**
     * Gibt dem Benutzer Hilfestellung zur Eingabe der Year-Parameter.
     *
     * @param consoleScanner Der Scanner zum Einlesen der Benutzereingaben.
     */
    @Override
    public void helpUser(Scanner consoleScanner) {
        System.out.println("Here you can enter the start year and the end year of the meteors impact. The output meteors lie within this range");
        System.out.println("You can also enter just a single year and the output will be just meteors that hit in this year.");
        System.out.println("The years should be input as whole numbers.");
        System.out.println();
        configure(consoleScanner);
    }
}
