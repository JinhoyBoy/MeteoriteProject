import org.example.Meteorite;
import org.example.Filters.RegionFilter;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import java.util.List;

/**
 * Ein White-Box-Test für den RegionFilter.
 */
public class RegionFilterTest {

    private RegionFilter filter;

    /**
     * Einrichtungsmethode, die vor jedem Test aufgerufen wird.
     */
    @BeforeEach
    public void setUp() {
        // Setzt den RegionFilter mit einem Beispielwert
        filter = new RegionFilter(50.0, 10.0, 2.0);
    }

    /**
     * Testet den RegionFilter, wenn die Meteoriten innerhalb des Radius liegen.
     */
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

    /**
     * Testet den RegionFilter, wenn die Meteoriten sich an der Grenze des Radius befinden.
     */
    @Test
    public void testRegionFilterOnBorder() {
        List<Meteorite> input = List.of(
                new Meteorite("Test3", 50.0, 10.02798),  // das ist an der Kante
                new Meteorite("Test4", 48.0, 8.0)    // außerhalb der Kante
        );
        List<Meteorite> output = filter.execute(input);
        assertEquals("Test3", output.get(0).getName()); // Die Kante wird noch mitgezählt
    }

    /**
     * Testet den RegionFilter, wenn die Meteoriten außerhalb des Radius liegen.
     */
    @Test
    public void testRegionFilterOutsideRadius() {
        List<Meteorite> input = List.of(
                new Meteorite("Test5", 55.0, 15.0),
                new Meteorite("Test6", 45.0, 5.0)
        );
        List<Meteorite> output = filter.execute(input);
        assertEquals(0, output.size());
    }

    /**
     * Testet den RegionFilter mit einer leeren Eingabemenge.
     */
    @Test
    public void testEmptyInput() {
        List<Meteorite> input = new ArrayList<>();
        List<Meteorite> output = filter.execute(input);
        assertEquals(0, output.size());
    }
}


