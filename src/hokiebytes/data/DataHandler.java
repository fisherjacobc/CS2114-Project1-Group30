package hokiebytes.data;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

import com.google.gson.Gson;

import hokiebytes.Meal;
import hokiebytes.Review;

/**
 * DataHandler Class
 * 
 * <p>
 * Responsible for handling and serializing/deserializing data
 * 
 * @author Jacob Fisher (fisherjc)
 * @version 2026.09.18
 */
public final class DataHandler {
    private DataHandler() {
    }

    private static Gson gson = new Gson();

    private static DataBuilder<Review> reviews = new DataBuilder<>();
    private static DataBuilder<Meal> meals = new DataBuilder<>();
    private static DataBuilder<String> knownLocations = new DataBuilder<>();

    private static record Data(Review[] reviews, Meal[] meals, String[] knownLocations) {
    }

    /**
     * Load Data (running this method is the same as {@code loadData(false)})
     * 
     * <p>
     * See {@link #loadData(boolean loadWithdefaultData)}
     */
    public static boolean loadData() {
        return loadData(false);
    }

    /**
     * Load Data from the data.json file, or start from scratch
     * 
     * @param loadWithdefaultData Load with default meals and knownLocations
     */
    public static boolean loadData(boolean loadWithdefaultData) {
        if (loadWithdefaultData) {
            meals.getArrayList().addAll(Arrays.asList(DefaultData.defaultMeals));
            knownLocations.getArrayList().addAll(Arrays.asList(DefaultData.defaultKnownLocations));

            saveData();

            return true;
        } else {
            try {
                String fileContent = Files.readString(Path.of("data.json"));

                Data importedData = gson.fromJson(fileContent, Data.class);

                if (importedData == null || importedData.reviews == null || importedData.meals == null
                        || importedData.knownLocations == null) {
                    Scanner input = new Scanner(System.in);
                    boolean validInput = false;

                    while (!validInput) {
                        System.out.print("\033[H\033[2J");
                        System.out.flush();

                        System.out.println("┌──HokieBytes───────────────────────────┐");
                        System.out.println("│                                       │");
                        System.out.println("│ Previously saved file is corrupted    │");
                        System.out.println("│ would you like to generate a new one? │");
                        System.out.println("│                                       │");
                        System.out.println("│ [1] Yes                               │");
                        System.out.println("│ [2] No (Exit Program)                 │");
                        System.out.println("│                                       │");
                        System.out.println("├───────────────────────────────────────┘");
                        System.out.print("└ Choice: ");

                        String choice = input.next();

                        if (choice.equals("1")) {
                            validInput = true;
                            input.close();
                            return loadData(true);
                        } else if (choice.equals("2")) {
                            validInput = true;
                            input.close();
                            System.exit(0);
                        } else {
                            System.out.println("Invalid Choice! Please try again.");
                        }
                    }

                    input.close();

                    return false;
                }

                System.out.print("\033[H\033[2J");
                System.out.flush();

                System.out.println("┌──HokieBytes──────────────────┐");
                System.out.println("│                              │");
                System.out.println("│ Previously saved file found! │");
                System.out.println("│                              │");
                System.out
                        .println("│ Loaded in " + String.format("%04d", importedData.reviews.length)
                                + " review(s),    │");
                System.out.println(
                        "│ " + String.format("%04d", importedData.meals.length) + " meal(s), and            │");
                System.out.println(
                        "│ " + String.format("%04d", importedData.knownLocations.length)
                                + " known locations(s).     │");
                System.out.println("│                              │");
                System.out.println("└──────────────────────────────┘");

                return true;
            } catch (IOException e) {
                Scanner input = new Scanner(System.in);
                boolean validInput = false;

                while (!validInput) {
                    System.out.print("\033[H\033[2J");
                    System.out.flush();

                    System.out.println("┌──HokieBytes───────────────────────────┐");
                    System.out.println("│                                       │");
                    System.out.println("│ No previously saved file found,       │");
                    System.out.println("│ would you like to generate a new one? │");
                    System.out.println("│                                       │");
                    System.out.println("│ [1] Yes                               │");
                    System.out.println("│ [2] No/Try Again                      │");
                    System.out.println("│                                       │");
                    System.out.println("├───────────────────────────────────────┘");
                    System.out.print("└ Choice: ");

                    String choice = input.next();

                    if (choice.equals("1")) {
                        validInput = true;
                        input.close();
                        return loadData(true);
                    } else if (choice.equals("2")) {
                        validInput = true;
                        input.close();
                        return loadData(false);
                    } else {
                        System.out.println("Invalid Choice! Please try again.");
                    }
                }

                input.close();
            }

            return false;
        }
    }

    /**
     * Serialize and save data to file
     */
    public static void saveData() {
        Review[] reviewsArray = new Review[reviews.getArrayList().size()];
        reviews.getArrayList().toArray(reviewsArray);

        Meal[] mealsArray = new Meal[meals.getArrayList().size()];
        meals.getArrayList().toArray(mealsArray);

        String[] knownLocationsArray = new String[knownLocations.getArrayList().size()];
        knownLocations.getArrayList().toArray(knownLocationsArray);

        String result = gson.toJson(new Data(reviewsArray, mealsArray, knownLocationsArray));

        try {
            FileWriter fileWriter = new FileWriter("data.json");
            fileWriter.write(result);
            fileWriter.close();

            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("┌──HokieBytes─────────────┐");
            System.out.println("│                         │");
            System.out.println("│ Data Successfully Saved │");
            System.out.println("│                         │");
            System.out.println("└─────────────────────────┘");
        } catch (Exception e) {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println("┌──HokieBytes──────────┐");
            System.out.println("│                      │");
            System.out.println("│ Data Failed to Save! │");
            System.out.println("│                      │");
            System.out.println("└──────────────────────┘");
        }
    }

    /**
     * Get the Reviews Data
     * 
     * @return the reviews {@link DataBuilder}
     */
    public static DataBuilder<Review> getReviews() {
        return reviews;
    }

    /**
     * Get the Meals Data
     * 
     * @return the meals {@link DataBuilder}
     */
    public static DataBuilder<Meal> getMeals() {
        return meals;
    }

    /**
     * Get the Known Locations Data
     * 
     * @return the knownLocations {@link DataBuilder}
     */
    public static DataBuilder<String> getKnownLocations() {
        return knownLocations;
    }
}
