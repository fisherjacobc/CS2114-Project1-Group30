package hokiebytes.data;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataHandlerTest extends student.TestCase {
    ByteArrayInputStream simulatedInput = new ByteArrayInputStream(new byte[0]);

    public void setUp() {
        System.setIn(simulatedInput);
    }

    public void testLoadData() {
        // Load without a file (prompt)
        try {
            Files.deleteIfExists(Path.of("data.json"));
        } catch (Exception e) {
            // File Already Doesn't exist, which is OK
        }
        simulatedInput = new ByteArrayInputStream("1".getBytes());
        System.setIn(simulatedInput);
        assertTrue(DataHandler.loadData());

        // Load with Default Data
        assertTrue(DataHandler.loadData(true));

        // Load with Saved Data
        assertTrue(DataHandler.loadData(false));
    }

    public void testGetReviews() {
        assertNotNull(DataHandler.getReviews());
    }

    public void testGetMeals() {
        assertNotNull(DataHandler.getMeals());
    }

    public void testGetKnownLocations() {
        assertNotNull(DataHandler.getKnownLocations());
    }
}
