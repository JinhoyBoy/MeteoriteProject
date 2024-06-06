import org.example.Meteorite;
import org.example.RegionFilter;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;


//White-Box Test für RegionFilter

public class RegionFilterTest {
    @Test
    public void testRegionFilter() {
        RegionFilter filter = new RegionFilter(50.0, 10.0, 2.0);
        List<Meteorite> input = List.of(
                new Meteorite("Test1", 50.0, 10.0),
                new Meteorite("Test2", 51.0, 11.0)
        );
        List<Meteorite> output = filter.execute(input);
        assertEquals(1, output.size());
        assertEquals("Test1", output.get(0).getName());
    }
}
