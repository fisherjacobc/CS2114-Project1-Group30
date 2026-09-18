package hokiebytes;

public class MealTest extends student.TestCase {
    Meal meal1;
    Meal meal2;
    Meal meal3;
    Meal meal4;
    Meal meal5;

    public void setUp() {
        meal1 = new Meal("Burger", "Dining Hall 1");
        meal2 = new Meal("Better Burger", "Dining Hall 2");
        meal3 = new Meal("Burger", "Dining Hall 2");
        meal4 = new Meal("Better Burger", "Dining Hall 1");
        meal5 = new Meal("Burger", "Dining Hall 1");
    }

    public void testEquals() {
        assertTrue(meal1.equals(meal1));
        assertFalse(meal1.equals(meal2));
        assertFalse(meal1.equals(meal3));
        assertFalse(meal1.equals(meal4));
        assertTrue(meal1.equals(meal5));
        assertFalse(meal1.equals(null));
        assertFalse(meal1.equals(new Object()));
    }
}
