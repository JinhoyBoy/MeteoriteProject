import org.meteorite_filter.Meteorite;
import org.meteorite_filter.Filters.RegionFilter;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Ein White-Box-Test für den RegionFilter.
 */
public class RegionFilterTest {

    private RegionFilter executeFilter;
    private RegionFilter configureFilter;

    /**
     * Einrichtungsmethode, die vor jedem Test aufgerufen wird.
     */
    @BeforeEach
    public void setUp() {
        // Setzt den RegionFilter mit einem Beispielwert
        executeFilter = new RegionFilter(50.0, 10.0, 2.0);
        configureFilter = new RegionFilter();
    }

    // configure test
    @Test
    void testValidInput() {
        String input = "48.8566,2.3522,10.0\n"; // Paris coordinates with a 10 km radius
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        configureFilter.configure(scanner);

        assertEquals(48.8566, configureFilter.getLatitude());
        assertEquals(2.3522, configureFilter.getLongitude());
        assertEquals(10.0, configureFilter.getRadius());
    }

    @Test
    void testHelpInput() {
        String input = "h\n48.8566,2.3522,10.0\n"; // Help input followed by valid input
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        // Redirecting System.out to check help message
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        configureFilter.configure(scanner);

        String expectedHelpMessage = "Here you can enter the latitude, as well as the longitude of the region, where the meteor hit.\n" +
                "Both should be a decimal number. The last parameter is the radius in which the meteor should lie (km). This should also be a decimal number.\n\n";
        assertTrue(outContent.toString().contains(expectedHelpMessage));
        assertEquals(48.8566, configureFilter.getLatitude());
        assertEquals(2.3522, configureFilter.getLongitude());
        assertEquals(10.0, configureFilter.getRadius());
    }

    @Test
    void testInvalidInputFormat() {
        String input = "48.8566,2.3522\n"; // Missing radius
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        // Redirecting System.out to check invalid input message
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        configureFilter.configure(scanner);

        String expectedMessage = "Invalid input format for Region filter. Expected input is (latitude,longitude,radius).";
        assertTrue(outContent.toString().contains(expectedMessage));
    }

    @Test
    void testNumberFormatException() {
        String input = "invalid,2.3522,10.0\n"; // Invalid latitude
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        // Redirecting System.out to check invalid type message
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        configureFilter.configure(scanner);

        String expectedMessage = "Invalid type of input. Expected input is a decimal number.";
        assertTrue(outContent.toString().contains(expectedMessage));
    }

    @Test
    void testUnexpectedException() {
        // Creating a Scanner that will cause an unexpected exception
        Scanner scanner = new Scanner(new InputStream() {
            @Override
            public int read() throws IOException {
                throw new IOException("Simulated IO exception");
            }
        });

        // Redirecting System.out to check unexpected error message
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        configureFilter.configure(scanner);

        String expectedMessage = "Unexpected error please try again.";
        assertTrue(outContent.toString().contains(expectedMessage));
    }

    // execute test
    /**
     * Testet den RegionFilter, wenn die Meteoriten innerhalb des Radius liegen.
     */
    @Test
    public void testRegionFilterWithinRadius() {
        List<Meteorite> input = List.of(
                new Meteorite("Test1", 50.0, 10.0),
                new Meteorite("Test2", 51.0, 11.0)
        );
        List<Meteorite> output = executeFilter.execute(input);
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
        List<Meteorite> output = executeFilter.execute(input);
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
        List<Meteorite> output = executeFilter.execute(input);
        assertEquals(0, output.size());
    }

    /**
     * Testet den RegionFilter mit einer leeren Eingabemenge.
     */
    @Test
    public void testEmptyInput() {
        List<Meteorite> input = new ArrayList<>();
        List<Meteorite> output = executeFilter.execute(input);
        assertEquals(0, output.size());
    }
}


