package org.example;

import java.util.List;
import java.util.stream.Collectors;

public class YearFilter implements Filter {
    private int startYear;
    private int endYear;

    public YearFilter(int startYear, int endYear) {
        this.startYear = startYear;
        this.endYear = endYear;
    }

    // Konstruktor für einen einzelnen Jahrgang
    public YearFilter(int year) {
        this.startYear = year;
        this.endYear = year;
    }

    @Override
    public List<Meteorite> execute(List<Meteorite> meteorites) {
        return meteorites.stream()
                .filter(m -> {
                    int year = m.getYearAsInt();
                    return year >= startYear && year <= endYear;
                })
                .collect(Collectors.toList());
    }
}
