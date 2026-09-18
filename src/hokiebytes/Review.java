package hokiebytes;

public record Review(Meal meal, int rating, String notes) {
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
