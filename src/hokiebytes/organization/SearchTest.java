package hokiebytes.organization;

import java.util.ArrayList;

import hokiebytes.Meal;
import hokiebytes.Review;

/**
 * Tests the Search class.
 */
public class SearchTest extends student.TestCase {

    private ArrayList<Review> reviews;
    private Review chicken;
    private Review burger;
    private Review pizza;

    /**
     * Creates reviews before each test.
     */
    public void setUp() {
        reviews = new ArrayList<Review>();

        chicken = new Review(
                new Meal("Chicken Tenders", "D2"),
                5,
                "Really crispy");

        burger = new Review(
                new Meal("Burger", "West End"),
                4,
                "Good fries");

        pizza = new Review(
                new Meal("Pepperoni Pizza", "Turner"),
                3,
                null);

        reviews.add(chicken);
        reviews.add(burger);
        reviews.add(pizza);
    }

    /**
     * Tests searching by meal name.
     */
    public void testSearchMealName() {
        ArrayList<Review> result = Search.searchForReview(
                reviews,
                "Chicken");

        assertEquals(1, result.size());
        assertEquals(chicken, result.get(0));
    }

    /**
     * Tests a partial search.
     */
    public void testPartialSearch() {
        ArrayList<Review> result = Search.searchForReview(
                reviews,
                "chick");

        assertEquals(1, result.size());
        assertEquals(chicken, result.get(0));
    }

    /**
     * Tests searching by location.
     */
    public void testSearchLocation() {
        ArrayList<Review> result = Search.searchForReview(
                reviews,
                "West End");

        assertEquals(1, result.size());
        assertEquals(burger, result.get(0));
    }

    /**
     * Tests searching notes.
     */
    public void testSearchNotes() {
        ArrayList<Review> result = Search.searchForReview(
                reviews,
                "crispy");

        assertEquals(1, result.size());
        assertEquals(chicken, result.get(0));
    }

    /**
     * Tests case-insensitive searching.
     */
    public void testCaseInsensitive() {
        ArrayList<Review> result = Search.searchForReview(
                reviews,
                "CHICKEN");

        assertEquals(1, result.size());
        assertEquals(chicken, result.get(0));
    }

    /**
     * Tests a search with no matches.
     */
    public void testNoMatch() {
        ArrayList<Review> result = Search.searchForReview(
                reviews,
                "Sushi");

        assertTrue(result.isEmpty());
    }

    /**
     * Tests a null search term.
     */
    public void testNullSearch() {
        ArrayList<Review> result = Search.searchForReview(
                reviews,
                null);

        assertTrue(result.isEmpty());
    }

    /**
     * Tests searching an empty list.
     */
    public void testEmptyList() {
        ArrayList<Review> empty = new ArrayList<Review>();

        ArrayList<Review> result = Search.searchForReview(
                empty,
                "Chicken");

        assertTrue(result.isEmpty());
    }

    /**
     * Tests a null list.
     */
    public void testNullList() {
        ArrayList<Review> result = Search.searchForReview(
                null,
                "Chicken");

        assertTrue(result.isEmpty());
    }
}