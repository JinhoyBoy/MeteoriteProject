package org.example;

import java.util.List;
import java.util.stream.Collectors;

public class MassFilter implements Filter {
    private double minMass;
    private double maxMass;

    public MassFilter(double minMass, double maxMass) {
        this.minMass = minMass;
        this.maxMass = maxMass;
    }

    @Override
    public List<Meteorite> execute(List<Meteorite> meteorites) {
        return meteorites.stream()
                .filter(m -> m.getMass() >= minMass && m.getMass() <= maxMass)
                .collect(Collectors.toList());
    }
}
