package hokiebytes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Provides methods for sorting reviews.
 */
public class Sort {

    /**
     * Sorts reviews based on when they were added.
     * The last review in the list is the most recent.
     *
     * @param reviews
     *            the reviews to sort
     * @param reversed
     *            false for most recent first,
     *            true for least recent first
     * @return a sorted copy of the reviews
     */
    public static ArrayList<Review> sortByMostRecent(
        ArrayList<Review> reviews,
        boolean reversed) {

        ArrayList<Review> result = new ArrayList<Review>();

        if (reviews == null) {
            return result;
        }

        result.addAll(reviews);

        if (!reversed) {
            Collections.reverse(result);
        }

        return result;
    }


    /**
     * Sorts reviews alphabetically by meal name.
     *
     * @param reviews
     *            the reviews to sort
     * @param reversed
     *            false for A-Z, true for Z-A
     * @return a sorted copy of the reviews
     */
    public static ArrayList<Review> sortAlphabetically(
        ArrayList<Review> reviews,
        boolean reversed) {

        ArrayList<Review> result = new ArrayList<Review>();

        if (reviews == null) {
            return result;
        }

        result.addAll(reviews);

        result.sort(new Comparator<Review>() {
            @Override
            public int compare(Review first, Review second) {
                return first.meal().name().compareToIgnoreCase(
                    second.meal().name());
            }
        });

        if (reversed) {
            Collections.reverse(result);
        }

        return result;
    }


    /**
     * Sorts reviews by rating.
     *
     * @param reviews
     *            the reviews to sort
     * @param reversed
     *            false for highest to lowest,
     *            true for lowest to highest
     * @return a sorted copy of the reviews
     */
    public static ArrayList<Review> sortByRating(
        ArrayList<Review> reviews,
        boolean reversed) {

        ArrayList<Review> result = new ArrayList<Review>();

        if (reviews == null) {
            return result;
        }

        result.addAll(reviews);

        result.sort(new Comparator<Review>() {
            @Override
            public int compare(Review first, Review second) {
                return second.rating() - first.rating();
            }
        });

        if (reversed) {
            Collections.reverse(result);
        }

        return result;
    }
}
