import org.example.Filter;
import org.example.FilterFactory;
import org.example.Meteorite;
import org.example.Pipeline;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Scanner;

public class PipelineIntegrationTest {
    @Test
    public void testPipeline() {
        // Arrange
        String configFilePath = "test_resources/test_config.txt";
        String inputFilePath = "test_resources/test_input.json";
        String outputFilePath = "test_resources/test_output.json";
        String expectedOutputFilePath = "test_resources/expected_output.json";

        // Act
        List<String> availableFilters = FilterFactory.loadConfig(configFilePath);

        String[] chosenFilters = {"Region", "Mass"};
        Scanner consoleScanner = new Scanner(System.in);

        List<Filter> filters = new ArrayList<>();
        for (String filterName : chosenFilters) {
            filterName = filterName.trim();
            if (!availableFilters.contains(filterName)) {
                System.out.println("Invalid filter: " + filterName);
                continue;
            }
            Filter filter = FilterFactory.createFilter(filterName, consoleScanner);
            if (filter != null) {
                filters.add(filter);
            }
        }
        Pipeline pipeline = new Pipeline(filters);
        pipeline.process(inputFilePath, outputFilePath);

        // Assert
        List<Meteorite> actualOutput = readJsonFile(outputFilePath);
        List<Meteorite> expectedOutput = readJsonFile(expectedOutputFilePath);
        // Check if the actual output has the same number of elements as the expected output
        assertEquals(expectedOutput.size(), actualOutput.size());
    }

    private List<Meteorite> readJsonFile(String filePath) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            Type meteoriteListType = new TypeToken<List<Meteorite>>() {}.getType();
            return gson.fromJson(reader, meteoriteListType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
