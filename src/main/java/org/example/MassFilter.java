package org.example;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Der MassFilter filtert eine Liste von Meteoriten basierend auf der angegebenen Massenbereich.
 */
public class MassFilter implements Filter {
    private double minMass;
    private double maxMass;

    /**
     * Konstruktor für den MassFilter.
     *
     * @param minMass Die minimale Masse eines Meteoriten, die im Filter enthalten sein soll.
     * @param maxMass Die maximale Masse eines Meteoriten, die im Filter enthalten sein soll.
     */
    public MassFilter(double minMass, double maxMass) {
        this.minMass = minMass;
        this.maxMass = maxMass;
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

