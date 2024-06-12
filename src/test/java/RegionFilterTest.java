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
        // Standard RegionFilter-Konstruktor
        configureFilter = new RegionFilter();
    }

    /**
     * Testet die Konfiguration des Filters mit gültiger Eingabe.
     */
    @Test
    void testValidInput() {
        String input = "48.8566;2.3522;10.0\n"; // Paris Koordinaten mit einem Radius von 10 km
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        configureFilter.configure(scanner);

        assertEquals(48.8566, configureFilter.getLatitude());
        assertEquals(2.3522, configureFilter.getLongitude());
        assertEquals(10.0, configureFilter.getRadius());
    }

    /**
     * Testet die Konfiguration des Filters mit einer Hilfseingabe gefolgt von gültiger Eingabe.
     */
    @Test
    void testHelpInput() {
        String input = "h\n48.8566;2.3522;10.0\n"; // Hilfseingabe gefolgt von gültiger Eingabe
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        // Umleiten von System.out, um die Hilfemeldung zu überprüfen
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

    /**
     * Testet die Konfiguration des Filters mit ungültigem Eingabeformat.
     */
    @Test
    void testInvalidInputFormat() {
        String input = "48.8566;2.3522\n"; // Fehlender Radius
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        // Umleiten von System.out, um die Fehlermeldung für ungültige Eingabe zu überprüfen
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        configureFilter.configure(scanner);

        String expectedMessage = "Invalid input format for Region filter. Expected input is (latitude; longitude; radius).";
        assertTrue(outContent.toString().contains(expectedMessage));
    }

    /**
     * Testet die Konfiguration des Filters mit ungültigem Zahleneingabeformat.
     */
    @Test
    void testNumberFormatException() {
        String input = "invalid;2.3522;10.0\n"; // Ungültige Breite
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        // Umleiten von System.out, um die Fehlermeldung für ungültigen Typ zu überprüfen
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        configureFilter.configure(scanner);

        String expectedMessage = "Invalid type of input. Expected input is a decimal number.";
        assertTrue(outContent.toString().contains(expectedMessage));
    }

    /**
     * Testet die Konfiguration des Filters bei unerwarteter Ausnahme.
     */
    @Test
    void testUnexpectedException() {
        // Erstellen eines Scanners, der eine unerwartete Ausnahme auslöst
        Scanner scanner = new Scanner(new InputStream() {
            @Override
            public int read() throws IOException {
                throw new IOException("Simulierte IO-Ausnahme");
            }
        });

        // Umleiten von System.out, um die Fehlermeldung für unerwartete Fehler zu überprüfen
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        configureFilter.configure(scanner);

        String expectedMessage = "Unexpected error please try again.";
        assertTrue(outContent.toString().contains(expectedMessage));
    }

    // Ab hier Test der Ausführung des Filters

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
                new Meteorite("Test3", 50.0, 10.02798),  // Das ist an der Kante
                new Meteorite("Test4", 48.0, 8.0)    // Außerhalb der Kante
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
