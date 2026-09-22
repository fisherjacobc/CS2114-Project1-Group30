package hokiebytes.data;

import hokiebytes.Meal;

/**
 * Tests the DataBuilder class
 */
public class DataBuilderTest extends student.TestCase {
    DataBuilder<Meal> dataBuilder;
    Meal meal;
    Meal meal2;

    /**
     * Setup tests
     */
    public void setUp() {
        dataBuilder = new DataBuilder<>();
        meal = new Meal("Burger", "Dining Hall 1");
        meal2 = new Meal("Better Burger", "Dining Hall 2");
    }

    /**
     * Test the getArrayList() method of the DataBuilder class
     */
    public void testGetArrayList() {
        assertNotNull(dataBuilder.getArrayList());
    }

    /**
     * Test the add() method of the DataBuilder class
     */
    public void testAdd() {
        assertFalse(dataBuilder.add(null));
        assertTrue(dataBuilder.add(meal));
        assertFalse(dataBuilder.add(meal));
    }

    /**
     * Tests the update() method of the DataBuilder class
     */
    public void testUpdate() {
        dataBuilder.add(meal);

        assertFalse(dataBuilder.update(null, null));
        assertFalse(dataBuilder.update(meal, null));
        assertFalse(dataBuilder.update(null, meal));
        assertFalse(dataBuilder.update(meal, meal));
        assertFalse(dataBuilder.update(meal2, meal));
        assertTrue(dataBuilder.update(meal, meal2));
    }

    /**
     * Tests the remove() method of the DataBuilder class
     */
    public void testRemove() {
        dataBuilder.add(meal);

        assertNull(dataBuilder.remove(null));
        assertNull(dataBuilder.remove(meal2));

        Meal removed = dataBuilder.remove(meal);
        assertNotNull(removed);
        assertEquals(meal, removed);
    }
}
