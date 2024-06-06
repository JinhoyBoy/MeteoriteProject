package org.example;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClassificationFilter implements Filter {
    private Set<String> classifications;

    public ClassificationFilter(String... classifications) {
        this.classifications = Stream.of(classifications).collect(Collectors.toSet());
    }

    @Override
    public List<Meteorite> execute(List<Meteorite> meteorites) {
        return meteorites.stream()
                .filter(m -> classifications.contains(m.getRecclass()))
                .collect(Collectors.toList());
    }
}
