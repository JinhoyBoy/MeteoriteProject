import org.example.Meteorite;
import org.example.Pipeline;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class PipelineIntegrationTest {
    @Test
    public void testPipeline() {
        // Arrange
        String configFilePath = "test_resources/test_config.txt";
        String inputFilePath = "test_resources/test_input.json";
        String outputFilePath = "test_resources/test_output.json";
        String expectedOutputFilePath = "test_resources/expected_output.json";

        // Act
        Pipeline pipeline = new Pipeline(configFilePath);
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
