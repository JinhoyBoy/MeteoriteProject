package org.meteorite_filter.Filters;

import org.meteorite_filter.Meteorite;

import java.util.List;
import java.util.Scanner;

/**
 * Die Filter-Schnittstelle definiert eine Methode zum Filtern einer Liste von Meteoriten.
 * Implementierungen dieser Schnittstelle können verschiedene Filterstrategien anwenden,
 * um die Liste der Meteoriten nach bestimmten Kriterien zu filtern.
 */
public interface Filter {

    /**
     * Führt den Filter auf die gegebene Liste von Meteoriten aus und gibt die gefilterte Liste zurück.
     *
     * @param meteorites Die Liste der Meteoriten, die gefiltert werden sollen.
     * @return Eine neue Liste von Meteoriten, die den Filterkriterien entsprechen.
     */
    List<Meteorite> execute(List<Meteorite> meteorites);

    /**
     * Gibt den Namen des Filters zurück.
     *
     * @return Der Name des Filters.
     */
    String getName();

    /**
     * Konfiguriert den Filter basierend auf Benutzereingaben.
     *
     * @param consoleScanner Der Scanner zum Lesen von Benutzereingaben.
     */
    void configure(Scanner consoleScanner);

    void helpUser(Scanner consoleScanner);
}

