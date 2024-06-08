package org.example;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Die YearFilter-Klasse filtert Meteoriten basierend auf einem angegebenen Jahr oder Jahrgangsbereich.
 */
public class YearFilter implements Filter {
    private int startYear;
    private int endYear;

    /**
     * Konstruktor für einen Jahrgangsbereich.
     *
     * @param startYear Das Anfangsjahr des Bereichs.
     * @param endYear Das Endjahr des Bereichs.
     */
    public YearFilter(int startYear, int endYear) {
        this.startYear = startYear;
        this.endYear = endYear;
    }

    /**
     * Konstruktor für einen einzelnen Jahrgang.
     *
     * @param year Das Jahr, das gefiltert werden soll.
     */
    public YearFilter(int year) {
        this.startYear = year;
        this.endYear = year;
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
