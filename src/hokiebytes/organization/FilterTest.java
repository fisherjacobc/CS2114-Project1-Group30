package hokiebytes.organization;

import java.util.ArrayList;

import hokiebytes.Meal;
import hokiebytes.Review;

/**
 * Tests the Filter class.
 */
public class FilterTest extends student.TestCase {

    private ArrayList<Review> reviews;
    private Review burger;
    private Review chicken;
    private Review pizza;

    /**
     * Creates reviews before each test.
     */
    public void setUp() {
        reviews = new ArrayList<Review>();

        burger = new Review(
                new Meal("Burger", "D2"),
                4,
                "Good");

        chicken = new Review(
                new Meal("Chicken Tenders", "D2"),
                5,
                "Great");

        pizza = new Review(
                new Meal("Pizza", "West End"),
                3,
                "Okay");

        reviews.add(burger);
        reviews.add(chicken);
        reviews.add(pizza);
    }

    /**
     * Tests filtering by D2.
     */
    public void testFilterByLocation() {
        ArrayList<Review> result = Filter.filterByLocation(
                reviews,
                "D2");

        assertEquals(2, result.size());

        assertTrue(result.contains(burger));
        assertTrue(result.contains(chicken));
        assertFalse(result.contains(pizza));
    }

    /**
     * Tests filtering by another location.
     */
    public void testDifferentLocation() {
        ArrayList<Review> result = Filter.filterByLocation(
                reviews,
                "West End");

        assertEquals(1, result.size());
        assertEquals(pizza, result.get(0));
    }

    /**
     * Tests case-insensitive filtering.
     */
    public void testCaseInsensitive() {
        ArrayList<Review> result = Filter.filterByLocation(
                reviews,
                "d2");

        assertEquals(2, result.size());
    }

    /**
     * Tests a location that does not exist.
     */
    public void testLocationNotFound() {
        ArrayList<Review> result = Filter.filterByLocation(
                reviews,
                "Owens");

        assertTrue(result.isEmpty());
    }

    /**
     * Tests a null location.
     */
    public void testNullLocation() {
        ArrayList<Review> result = Filter.filterByLocation(
                reviews,
                null);

        assertTrue(result.isEmpty());
    }

    /**
     * Tests filtering an empty list.
     */
    public void testEmptyList() {
        ArrayList<Review> empty = new ArrayList<Review>();

        ArrayList<Review> result = Filter.filterByLocation(
                empty,
                "D2");

        assertTrue(result.isEmpty());
    }
}