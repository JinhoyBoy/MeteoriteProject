package org.example;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Der ClassificationFilter filtert eine Liste von Meteoriten basierend auf den angegebenen Klassifikationen.
 */
public class ClassificationFilter implements Filter {
    private Set<String> classifications;

    /**
     * Konstruktor für den ClassificationFilter.
     *
     * @param classifications Eine oder mehrere Klassifikationen, die zum Filtern der Meteoriten verwendet werden.
     */
    public ClassificationFilter(String... classifications) {
        this.classifications = Stream.of(classifications).collect(Collectors.toSet());
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

