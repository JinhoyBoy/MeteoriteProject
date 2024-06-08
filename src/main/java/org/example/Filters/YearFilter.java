package org.example.Filters;

import org.example.Meteorite;

import java.util.List;
import java.util.NavigableMap;
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

    @Override
    public String getName() {
        return "Year";
    }

    @Override
    public void configure(Scanner consoleScanner) {
        try {
            System.out.print("Enter parameters for Year (startYear,endYear) or (year): ");
            String[] yearParams = consoleScanner.nextLine().split(",");
            if (yearParams.length == 1) {
                int year = Integer.parseInt(yearParams[0].trim());
                this.startYear = year;
                this.endYear = year;
            } else if (yearParams.length == 2) {
                this.startYear = Integer.parseInt(yearParams[0].trim());
                this.endYear = Integer.parseInt(yearParams[1].trim());
            } else {
                System.out.println("Invalid input for Year filter");
            }
        }
        catch(NumberFormatException e) {
            System.out.print("Invalid type of input. Expected input is a whole number.");
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
}
