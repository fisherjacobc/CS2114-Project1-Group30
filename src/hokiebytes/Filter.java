package hokiebytes;

import java.util.ArrayList;

/**
 * Provides methods for filtering reviews.
 */
public class Filter {

    /**
     * Filters reviews by dining location.
     *
     * @param reviews
     *            the reviews to filter
     * @param location
     *            the location to find
     * @return reviews from the given location
     */
    public static ArrayList<Review> filterByLocation(
        ArrayList<Review> reviews,
        String location) {

        ArrayList<Review> result =
            new ArrayList<Review>();

        if (reviews == null || location == null) {
            return result;
        }

        for (Review review : reviews) {

            if (review.meal().location()
                .equalsIgnoreCase(location)) {

                result.add(review);
            }
        }

        return result;
    }
}