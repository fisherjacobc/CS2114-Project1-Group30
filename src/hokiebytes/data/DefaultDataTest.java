package hokiebytes.data;

import hokiebytes.Meal;

public class DefaultDataTest extends student.TestCase {
    public void testDefaultMeals() {
        assertNotNull(DefaultData.defaultMeals);
        assertEquals(Meal[].class, DefaultData.defaultMeals.getClass());
    }

    public void testDefaultKnownLocations() {
        assertNotNull(DefaultData.defaultKnownLocations);
        assertEquals(String[].class, DefaultData.defaultKnownLocations.getClass());
    }
}
