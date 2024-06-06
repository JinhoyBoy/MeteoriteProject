package org.example;

import java.util.List;

public interface Filter {
    List<Meteorite> execute(List<Meteorite> meteorites);
}
