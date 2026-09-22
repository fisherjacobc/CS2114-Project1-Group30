package hokiebytes.data;

import java.io.ByteArrayInputStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Tests the DataHandler class
 */
public class DataHandlerTest extends student.TestCase {
    ByteArrayInputStream simulatedInput = new ByteArrayInputStream(new byte[0]);

    /**
     * Setup tests
     */
    public void setUp() {
        System.setIn(simulatedInput);
    }

    /**
     * Tests the loadData() methods of the DataHandler class
     */
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

    /**
     * Tests the getReviews() method of the DataHandler class
     */
    public void testGetReviews() {
        assertNotNull(DataHandler.getReviews());
    }

    /**
     * Tests the getMeals() method of the DataHandler class
     */
    public void testGetMeals() {
        assertNotNull(DataHandler.getMeals());
    }

    /**
     * Tests the getKnownLocations() method of the DataHandler class
     */
    public void testGetKnownLocations() {
        assertNotNull(DataHandler.getKnownLocations());
    }
}
