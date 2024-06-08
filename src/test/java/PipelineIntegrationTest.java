import org.example.*;

import static org.junit.jupiter.api.Assertions.*;

import org.example.Filter;
import org.example.MassFilter;
import org.example.RegionFilter;
import org.junit.jupiter.api.Test;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class PipelineIntegrationTest {
    @Test
    public void testPipeline() {
        // Arrange
        //String configFilePath = "test_resources/test_config.txt";
        String inputFilePath = "test_resources/test_input.json";
        String outputFilePath = "test_resources/test_output.json";
        String expectedOutputFilePath = "test_resources/expected_output.json";

        // Act
        List<Filter> filters = new ArrayList<>();
        RegionFilter regionFilter = new RegionFilter (56, 10, 100);
        filters.add(regionFilter);
        MassFilter massFilter = new MassFilter (500, 2000);
        filters.add(massFilter);
        //...
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
