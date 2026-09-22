package hokiebytes.organization;

import java.util.ArrayList;

import hokiebytes.Meal;
import hokiebytes.Review;

/**
 * Tests the Sort class.
 */
public class SortTest extends student.TestCase {

    private ArrayList<Review> reviews;
    private Review burger;
    private Review pizza;
    private Review tacos;

    /**
     * Creates reviews before each test.
     */
    public void setUp() {
        reviews = new ArrayList<Review>();

        burger = new Review(
                new Meal("Burger", "D2"),
                3,
                "Good burger");

        pizza = new Review(
                new Meal("Pizza", "West End"),
                5,
                "Great pizza");

        tacos = new Review(
                new Meal("Tacos", "Turner"),
                4,
                "Good tacos");

        reviews.add(burger);
        reviews.add(pizza);
        reviews.add(tacos);
    }

    /**
     * Tests most recent first.
     */
    public void testSortByMostRecent() {
        ArrayList<Review> result = Sort.sortByMostRecent(reviews, false);

        assertEquals(3, result.size());
        assertEquals(tacos, result.get(0));
        assertEquals(pizza, result.get(1));
        assertEquals(burger, result.get(2));
    }

    /**
     * Tests least recent first.
     */
    public void testSortByLeastRecent() {
        ArrayList<Review> result = Sort.sortByMostRecent(reviews, true);

        assertEquals(3, result.size());
        assertEquals(burger, result.get(0));
        assertEquals(pizza, result.get(1));
        assertEquals(tacos, result.get(2));
    }

    /**
     * Tests alphabetical sorting from A to Z.
     */
    public void testSortAlphabetically() {
        ArrayList<Review> result = Sort.sortAlphabetically(reviews, false);

        assertEquals(burger, result.get(0));
        assertEquals(pizza, result.get(1));
        assertEquals(tacos, result.get(2));
    }

    /**
     * Tests alphabetical sorting from Z to A.
     */
    public void testSortAlphabeticallyReversed() {
        ArrayList<Review> result = Sort.sortAlphabetically(reviews, true);

        assertEquals(tacos, result.get(0));
        assertEquals(pizza, result.get(1));
        assertEquals(burger, result.get(2));
    }

    /**
     * Tests rating from highest to lowest.
     */
    public void testSortByRating() {
        ArrayList<Review> result = Sort.sortByRating(reviews, false);

        assertEquals(5, result.get(0).rating());
        assertEquals(4, result.get(1).rating());
        assertEquals(3, result.get(2).rating());
    }

    /**
     * Tests rating from lowest to highest.
     */
    public void testSortByRatingReversed() {
        ArrayList<Review> result = Sort.sortByRating(reviews, true);

        assertEquals(3, result.get(0).rating());
        assertEquals(4, result.get(1).rating());
        assertEquals(5, result.get(2).rating());
    }

    /**
     * Tests an empty list.
     */
    public void testEmptyList() {
        ArrayList<Review> empty = new ArrayList<Review>();

        assertTrue(
                Sort.sortByMostRecent(empty, false).isEmpty());

        assertTrue(
                Sort.sortAlphabetically(empty, false).isEmpty());

        assertTrue(
                Sort.sortByRating(empty, false).isEmpty());
    }

    /**
     * Tests a null list.
     */
    public void testNullList() {
        assertTrue(
                Sort.sortByMostRecent(null, false).isEmpty());

        assertTrue(
                Sort.sortAlphabetically(null, false).isEmpty());

        assertTrue(
                Sort.sortByRating(null, false).isEmpty());
    }
}