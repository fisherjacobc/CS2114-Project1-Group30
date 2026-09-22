package hokiebytes;

import java.util.ArrayList;
import java.util.Scanner;

import hokiebytes.data.DataHandler;

/**
 * Main Class
 *
 * <p>
 * Handles startup, menu navigation, and user interaction for HokieBytes.
 *
 * @author Ujay Bhuva (ujayb)
 * @version 2026.09.22
 */
public class Main {

    private static Scanner input = new Scanner(System.in);

    /**
     * Starts the HokieBytes program.
     *
     * @param args command line arguments
     * @throws InterruptedException if the startup pause is interrupted
     */
    public static void main(String[] args) throws InterruptedException {
        DataHandler.loadData();

        Thread.sleep(1000L);

        displayMainMenu();
    }

    /**
     * Displays the main menu.
     */
    private static void displayMainMenu() {
        boolean running = true;

        while (running) {
            clearScreen();

            System.out.println("┌──HokieBytes───────────────────────────┐");
            System.out.println("│                                       │");
            System.out.println("│ [1] View Reviews                      │");
            System.out.println("│ [2] Manage Reviews                    │");
            System.out.println("│ [3] Manage Meals                      │");
            System.out.println("│ [4] Manage Locations                  │");
            System.out.println("│ [5] Save Data                         │");
            System.out.println("│ [6] Save and Exit                     │");
            System.out.println("│                                       │");
            System.out.println("├───────────────────────────────────────┘");
            System.out.print("└ Choice: ");

            int choice = detectMenuInput();

            if (choice == 1) {
                displayReviews();
            } else if (choice == 2) {
                displayManageReviewMenu();
            } else if (choice == 3) {
                displayManageMealMenu();
            } else if (choice == 4) {
                displayManageKnownLocationsMenu();
            } else if (choice == 5) {
                DataHandler.saveData();
                pause();
            } else if (choice == 6) {
                DataHandler.saveData();
                running = false;
            } else {
                System.out.println("Invalid Choice! Please try again.");
                pause();
            }
        }
    }

    /**
     * Detects integer menu input.
     *
     * @return menu choice entered by the user
     */
    private static int detectMenuInput() {
        while (!input.hasNextInt()) {
            input.nextLine();
            System.out.print("Invalid input. Enter a number: ");
        }

        int choice = input.nextInt();
        input.nextLine();

        return choice;
    }

    /**
     * Displays all saved reviews.
     */
    private static void displayReviews() {
        clearScreen();

        ArrayList<Review> reviews = DataHandler.getReviews().getArrayList();

        System.out.println("┌──Reviews──────────────────────────────┐");

        if (reviews.isEmpty()) {
            System.out.println("│ No reviews have been added yet.        │");
        } else {
            for (int i = 0; i < reviews.size(); i++) {
                Review review = reviews.get(i);

                System.out.println();
                System.out.println("Review #" + (i + 1));
                System.out.println("Meal: " + review.meal().name());
                System.out.println(
                        "Location: " + review.meal().location());
                System.out.println("Rating: " + review.rating() + "/5");

                if (review.notes() != null
                        && !review.notes().isEmpty()) {
                    System.out.println("Notes: " + review.notes());
                }
            }
        }

        System.out.println();
        System.out.println("└───────────────────────────────────────┘");

        pause();
    }

    /**
     * Displays the menu for managing reviews.
     */
    private static void displayManageReviewMenu() {
        boolean managing = true;

        while (managing) {
            clearScreen();

            System.out.println("┌──Manage Reviews───────────────────────┐");
            System.out.println("│                                       │");
            System.out.println("│ [1] Add Review                        │");
            System.out.println("│ [2] Update Review                     │");
            System.out.println("│ [3] Remove Review                     │");
            System.out.println("│ [4] Back                              │");
            System.out.println("│                                       │");
            System.out.println("├───────────────────────────────────────┘");
            System.out.print("└ Choice: ");

            int choice = detectMenuInput();

            if (choice == 1) {
                addReview();
            } else if (choice == 2) {
                updateReview();
            } else if (choice == 3) {
                removeReview();
            } else if (choice == 4) {
                managing = false;
            } else {
                System.out.println("Invalid Choice!");
                pause();
            }
        }
    }

    /**
     * Adds a new review.
     */
    private static void addReview() {
        clearScreen();

        System.out.println("──Add Review──");

        System.out.print("Meal name: ");
        String mealName = input.nextLine();

        System.out.print("Location: ");
        String location = input.nextLine();

        int rating = getValidRating();

        System.out.print("Notes (optional): ");
        String notes = input.nextLine();

        if (notes.isEmpty()) {
            notes = null;
        }

        Meal meal = new Meal(mealName, location);
        Review review = new Review(meal, rating, notes);

        boolean added = DataHandler.getReviews().add(review);

        if (added) {
            DataHandler.getMeals().add(meal);
            DataHandler.getKnownLocations().add(location);

            System.out.println("Review successfully added.");
        } else {
            System.out.println(
                    "A review for this meal already exists.");
        }

        pause();
    }

    /**
     * Updates an existing review.
     */
    private static void updateReview() {
        clearScreen();

        ArrayList<Review> reviews = DataHandler.getReviews().getArrayList();

        if (reviews.isEmpty()) {
            System.out.println("There are no reviews to update.");
            pause();
            return;
        }

        showReviewNumbers(reviews);

        System.out.print("Choose review number to update: ");
        int choice = detectMenuInput();

        if (choice < 1 || choice > reviews.size()) {
            System.out.println("Invalid review number.");
            pause();
            return;
        }

        Review oldReview = reviews.get(choice - 1);

        System.out.print("New meal name: ");
        String mealName = input.nextLine();

        System.out.print("New location: ");
        String location = input.nextLine();

        int rating = getValidRating();

        System.out.print("New notes (optional): ");
        String notes = input.nextLine();

        if (notes.isEmpty()) {
            notes = null;
        }

        Meal newMeal = new Meal(mealName, location);
        Review updatedReview = new Review(newMeal, rating, notes);

        /*
         * Review.equals() only compares the meal, so removing the old
         * review first allows its rating and notes to be updated.
         */
        DataHandler.getReviews().remove(oldReview);

        boolean updated = DataHandler.getReviews().add(updatedReview);

        if (updated) {
            DataHandler.getMeals().add(newMeal);
            DataHandler.getKnownLocations().add(location);

            System.out.println("Review successfully updated.");
        } else {
            DataHandler.getReviews().add(oldReview);

            System.out.println(
                    "Review could not be updated because "
                            + "that meal already has a review.");
        }

        pause();
    }

    /**
     * Removes an existing review.
     */
    private static void removeReview() {
        clearScreen();

        ArrayList<Review> reviews = DataHandler.getReviews().getArrayList();

        if (reviews.isEmpty()) {
            System.out.println("There are no reviews to remove.");
            pause();
            return;
        }

        showReviewNumbers(reviews);

        System.out.print("Choose review number to remove: ");
        int choice = detectMenuInput();

        if (choice < 1 || choice > reviews.size()) {
            System.out.println("Invalid review number.");
            pause();
            return;
        }

        Review review = reviews.get(choice - 1);

        DataHandler.getReviews().remove(review);

        System.out.println("Review successfully removed.");

        pause();
    }

    /**
     * Displays the menu for managing meals.
     */
    private static void displayManageMealMenu() {
        boolean managing = true;

        while (managing) {
            clearScreen();

            System.out.println("┌──Manage Meals─────────────────────────┐");
            System.out.println("│                                       │");
            System.out.println("│ [1] Add Meal                          │");
            System.out.println("│ [2] Update Meal                       │");
            System.out.println("│ [3] Remove Meal                       │");
            System.out.println("│ [4] Back                              │");
            System.out.println("│                                       │");
            System.out.println("├───────────────────────────────────────┘");
            System.out.print("└ Choice: ");

            int choice = detectMenuInput();

            if (choice == 1) {
                addMeal();
            } else if (choice == 2) {
                updateMeal();
            } else if (choice == 3) {
                removeMeal();
            } else if (choice == 4) {
                managing = false;
            } else {
                System.out.println("Invalid Choice!");
                pause();
            }
        }
    }

    /**
     * Adds a meal.
     */
    private static void addMeal() {
        clearScreen();

        System.out.print("Meal name: ");
        String name = input.nextLine();

        System.out.print("Location: ");
        String location = input.nextLine();

        Meal meal = new Meal(name, location);

        if (DataHandler.getMeals().add(meal)) {
            DataHandler.getKnownLocations().add(location);
            System.out.println("Meal successfully added.");
        } else {
            System.out.println("That meal already exists.");
        }

        pause();
    }

    /**
     * Updates a meal.
     */
    private static void updateMeal() {
        clearScreen();

        ArrayList<Meal> meals = DataHandler.getMeals().getArrayList();

        if (meals.isEmpty()) {
            System.out.println("There are no meals to update.");
            pause();
            return;
        }

        showMealNumbers(meals);

        System.out.print("Choose meal number to update: ");
        int choice = detectMenuInput();

        if (choice < 1 || choice > meals.size()) {
            System.out.println("Invalid meal number.");
            pause();
            return;
        }

        Meal oldMeal = meals.get(choice - 1);

        System.out.print("New meal name: ");
        String name = input.nextLine();

        System.out.print("New location: ");
        String location = input.nextLine();

        Meal updatedMeal = new Meal(name, location);

        boolean updated = DataHandler.getMeals().update(oldMeal, updatedMeal);

        if (updated) {
            DataHandler.getKnownLocations().add(location);
            System.out.println("Meal successfully updated.");
        } else {
            System.out.println(
                    "Meal could not be updated.");
        }

        pause();
    }

    /**
     * Removes a meal.
     */
    private static void removeMeal() {
        clearScreen();

        ArrayList<Meal> meals = DataHandler.getMeals().getArrayList();

        if (meals.isEmpty()) {
            System.out.println("There are no meals to remove.");
            pause();
            return;
        }

        showMealNumbers(meals);

        System.out.print("Choose meal number to remove: ");
        int choice = detectMenuInput();

        if (choice < 1 || choice > meals.size()) {
            System.out.println("Invalid meal number.");
            pause();
            return;
        }

        Meal meal = meals.get(choice - 1);

        DataHandler.getMeals().remove(meal);

        System.out.println("Meal successfully removed.");

        pause();
    }

    /**
     * Displays the menu for managing known locations.
     */
    private static void displayManageKnownLocationsMenu() {
        boolean managing = true;

        while (managing) {
            clearScreen();

            System.out.println("┌──Manage Locations─────────────────────┐");
            System.out.println("│                                       │");
            System.out.println("│ [1] Add Location                      │");
            System.out.println("│ [2] Update Location                   │");
            System.out.println("│ [3] Remove Location                   │");
            System.out.println("│ [4] Back                              │");
            System.out.println("│                                       │");
            System.out.println("├───────────────────────────────────────┘");
            System.out.print("└ Choice: ");

            int choice = detectMenuInput();

            if (choice == 1) {
                addLocation();
            } else if (choice == 2) {
                updateLocation();
            } else if (choice == 3) {
                removeLocation();
            } else if (choice == 4) {
                managing = false;
            } else {
                System.out.println("Invalid Choice!");
                pause();
            }
        }
    }

    /**
     * Adds a known location.
     */
    private static void addLocation() {
        clearScreen();

        System.out.print("Location name: ");
        String location = input.nextLine();

        if (DataHandler.getKnownLocations().add(location)) {
            System.out.println("Location successfully added.");
        } else {
            System.out.println("That location already exists.");
        }

        pause();
    }

    /**
     * Updates a known location.
     */
    private static void updateLocation() {
        clearScreen();

        ArrayList<String> locations = DataHandler.getKnownLocations().getArrayList();

        if (locations.isEmpty()) {
            System.out.println("There are no locations to update.");
            pause();
            return;
        }

        showLocationNumbers(locations);

        System.out.print("Choose location number to update: ");
        int choice = detectMenuInput();

        if (choice < 1 || choice > locations.size()) {
            System.out.println("Invalid location number.");
            pause();
            return;
        }

        String oldLocation = locations.get(choice - 1);

        System.out.print("New location name: ");
        String newLocation = input.nextLine();

        if (DataHandler.getKnownLocations()
                .update(oldLocation, newLocation)) {

            System.out.println("Location successfully updated.");
        } else {
            System.out.println(
                    "Location could not be updated.");
        }

        pause();
    }

    /**
     * Removes a known location.
     */
    private static void removeLocation() {
        clearScreen();

        ArrayList<String> locations = DataHandler.getKnownLocations().getArrayList();

        if (locations.isEmpty()) {
            System.out.println("There are no locations to remove.");
            pause();
            return;
        }

        showLocationNumbers(locations);

        System.out.print("Choose location number to remove: ");
        int choice = detectMenuInput();

        if (choice < 1 || choice > locations.size()) {
            System.out.println("Invalid location number.");
            pause();
            return;
        }

        String location = locations.get(choice - 1);

        DataHandler.getKnownLocations().remove(location);

        System.out.println("Location successfully removed.");

        pause();
    }

    /**
     * Gets a valid rating from 1 through 5.
     *
     * @return valid rating
     */
    private static int getValidRating() {
        while (true) {
            System.out.print("Rating (1-5): ");

            if (input.hasNextInt()) {
                int rating = input.nextInt();
                input.nextLine();

                if (rating >= 1 && rating <= 5) {
                    return rating;
                }
            } else {
                input.nextLine();
            }

            System.out.println(
                    "Invalid rating. Please enter an integer from 1 to 5.");
        }
    }

    /**
     * Displays reviews with numbers.
     *
     * @param reviews reviews to display
     */
    private static void showReviewNumbers(
            ArrayList<Review> reviews) {

        for (int i = 0; i < reviews.size(); i++) {
            Review review = reviews.get(i);

            System.out.println(
                    "[" + (i + 1) + "] "
                            + review.meal().name()
                            + " - "
                            + review.meal().location()
                            + " - "
                            + review.rating()
                            + "/5");
        }
    }

    /**
     * Displays meals with numbers.
     *
     * @param meals meals to display
     */
    private static void showMealNumbers(
            ArrayList<Meal> meals) {

        for (int i = 0; i < meals.size(); i++) {
            System.out.println(
                    "[" + (i + 1) + "] "
                            + meals.get(i).name()
                            + " - "
                            + meals.get(i).location());
        }
    }

    /**
     * Displays locations with numbers.
     *
     * @param locations locations to display
     */
    private static void showLocationNumbers(
            ArrayList<String> locations) {

        for (int i = 0; i < locations.size(); i++) {
            System.out.println(
                    "[" + (i + 1) + "] "
                            + locations.get(i));
        }
    }

    /**
     * Pauses until the user presses Enter.
     */
    private static void pause() {
        System.out.println();
        System.out.print("Press Enter to continue...");
        input.nextLine();
    }

    /**
     * Clears the terminal screen.
     */
    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}