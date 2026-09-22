package hokiebytes;

/**
 * Meal Record
 * 
 * <p>
 * Record Data Type that stores name and location data for serializing a meal
 * 
 * @param name     Name of the meal (e.g. Bacon)
 * @param location Location of the meal (e.g. Dietrick Hall - D2 - Gauchos)
 * 
 * @author Jacob Fisher (fisherjc)
 * @version 2026.09.18
 */
public record Meal(String name, String location) {
    /**
     * Compare two {@link Meal}s to see if they are equal
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

        Meal otherMeal = (Meal) other;

        return name.equals(otherMeal.name) && location.equals(otherMeal.location);
    }
}
