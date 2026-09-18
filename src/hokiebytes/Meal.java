package hokiebytes;

public record Meal(String name, String location) {
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
