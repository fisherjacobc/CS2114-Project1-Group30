package hokiebytes;

/**
 * Tests the Review class
 */
public class ReviewTest extends student.TestCase {
    Meal meal1;
    Meal meal2;
    Meal meal3;
    Meal meal4;
    Meal meal5;

    Review review1;
    Review review2;
    Review review3;
    Review review4;
    Review review5;

    /**
     * Setup tests
     */
    public void setUp() {
        meal1 = new Meal("Burger", "Dining Hall 1");
        meal2 = new Meal("Better Burger", "Dining Hall 2");
        meal3 = new Meal("Burger", "Dining Hall 2");
        meal4 = new Meal("Better Burger", "Dining Hall 1");
        meal5 = new Meal("Burger", "Dining Hall 1");

        review1 = new Review(meal1, 5, "Really good burger");
        review2 = new Review(meal2, 3, "Not a better burger");
        review3 = new Review(meal3, 4, null);
        review4 = new Review(meal4, 1, "");
        review5 = new Review(meal5, 2, "Same but worse");
    }

    /**
     * Test the equals() method of the Review class
     */
    public void testEquals() {
        assertTrue(review1.equals(review1));
        assertFalse(review1.equals(review2));
        assertFalse(review1.equals(review3));
        assertFalse(review1.equals(review4));
        assertTrue(review1.equals(review5));
        assertFalse(review1.equals(null));
        assertFalse(review1.equals(meal1));
    }
}
