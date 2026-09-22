package hokiebytes;

/**
 * Review Record
 * 
 * <p>
 * Record Data Type that stores meal, rating, and notes data for serializing a
 * Review
 * 
 * @param meal   {@link Meal} Data
 * @param rating rating (1-5)
 * @param notes  notes field for user to input any string they want
 * 
 * @author Jacob Fisher (fisherjc)
 * @version 2026.09.18
 */
public record Review(Meal meal, int rating, String notes) {
    /**
     * Compare two {@link Review}s to see if they are equal
     * 
     * @param other the other Object to compare
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Review otherReview = (Review) other;

        return meal.equals(otherReview.meal);
    }
}
