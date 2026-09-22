package hokiebytes.organization;

import java.util.ArrayList;

import hokiebytes.Review;

/**
 * Provides methods for searching reviews.
 */
public class Search {

    /**
     * Searches meal names, locations, and notes.
     *
     * @param reviews
     *                   the reviews to search
     * @param searchTerm
     *                   the text to search for
     * @return reviews containing the search term
     */
    public static ArrayList<Review> searchForReview(
            ArrayList<Review> reviews,
            String searchTerm) {

        ArrayList<Review> result = new ArrayList<Review>();

        if (reviews == null || searchTerm == null) {
            return result;
        }

        String term = searchTerm.toLowerCase();

        for (Review review : reviews) {

            String mealName = review.meal().name().toLowerCase();

            String location = review.meal().location().toLowerCase();

            String notes = "";

            if (review.notes() != null) {
                notes = review.notes().toLowerCase();
            }

            if (mealName.contains(term)
                    || location.contains(term)
                    || notes.contains(term)) {

                result.add(review);
            }
        }

        return result;
    }
}
