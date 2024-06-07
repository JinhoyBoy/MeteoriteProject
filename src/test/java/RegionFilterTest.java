import org.example.Meteorite;
import org.example.RegionFilter;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;


//White-Box Test für RegionFilter

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class RegionFilterTest {

    private RegionFilter filter;

    @BeforeEach
    public void setUp() {
        // Setze den RegionFilter mit einem Beispielwert
        filter = new RegionFilter(50.0, 10.0, 2.0);
    }

    @Test
    public void testRegionFilterWithinRadius() {
        List<Meteorite> input = List.of(
                new Meteorite("Test1", 50.0, 10.0),
                new Meteorite("Test2", 51.0, 11.0)
        );
        List<Meteorite> output = filter.execute(input);
        assertEquals(1, output.size());
        assertEquals("Test1", output.get(0).getName());
    }

    @Test
    public void testRegionFilterOnBorder() {
        List<Meteorite> input = List.of(
                new Meteorite("Test3", 50.0, 10.02798),  // das ist an der Kante
                new Meteorite("Test4", 48.0, 8.0)    // außerhalb der Kante
        );
        List<Meteorite> output = filter.execute(input);
        assertEquals("Test3", output.get(0).getName()); // Kante wird noch mitgezählt
    }

    @Test
    public void testRegionFilterOutsideRadius() {
        List<Meteorite> input = List.of(
                new Meteorite("Test5", 55.0, 15.0),
                new Meteorite("Test6", 45.0, 5.0)
        );
        List<Meteorite> output = filter.execute(input);
        assertEquals(0, output.size());
    }

    @Test
    public void testEmptyInput() {
        List<Meteorite> input = new ArrayList<>();
        List<Meteorite> output = filter.execute(input);
        assertEquals(0, output.size());
    }
}

