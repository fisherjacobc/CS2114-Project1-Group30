package hokiebytes.data;

import hokiebytes.Meal;

/**
 * Tests DefaultData class
 */
public class DefaultDataTest extends student.TestCase {
    /**
     * Test that defaultMeals aren't null and of the correct type
     */
    public void testDefaultMeals() {
        assertNotNull(DefaultData.defaultMeals);
        assertEquals(Meal[].class, DefaultData.defaultMeals.getClass());
    }

    /**
     * Test that defaultKnownLocations aren't null and of the correct type
     */
    public void testDefaultKnownLocations() {
        assertNotNull(DefaultData.defaultKnownLocations);
        assertEquals(String[].class, DefaultData.defaultKnownLocations.getClass());
    }
}
