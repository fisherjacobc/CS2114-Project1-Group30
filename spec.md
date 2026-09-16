# Specification
## Class Design 

Description/Purpose, Data & State, and Method Signatures 

### Main Class 

- Entry Point 

- Handles startup 

- Handles declaration/instantiation & initialization of other classes 

- Handles menu navigation 

- No Properties 

- Methods 

    - detectMenuInput 

        - Private Static 

        - No Parameters 

        - Returns an int 

        - It serves the purpose of detecting the menu input (an int) when prompted, if the user inputs something else, then it re-prompts them 

    - displayMainMenu 

        - Private Static 

        - No Parameters 

        - Returns Nothing 

        - It serves the purpose of displaying the main menu, where the user can select to view reviews; add, update, or delete a review, add, update, or delete a meal; add, update, or delete a known location; save data; or save and exit the program 

    - displayReviews 

        - Private Static 

        - No Parameters 

        - Returns Nothing 

        - It serves the purpose of handling the displaying of the reviews 

    - displayManageReviewMenu 

        - Private Static 

        - No Parameters 

        - Returns Nothing 

        - It serves the purpose of handling adding, updating, or deleting a review in the CLI 

            - Adding a review will also add a meal 

            - Updating a review aill also update a meal if meal properties were modified 

            - Deleting a review will not delete a meal 

    - displayManageMealMenu 

        - Private Static 

        - No Parameters 

        - Returns Nothing 

        - It serves the purpose of handling adding, updating, or deleting a meal in the CLI 

    - displayManageKnownLocationsMenu 

        - Private Static 

        - No Parameters 

        - Returns Nothing 

        - It serves the purpose of handling adding, updating, or deleting a known location in the CLI 

### DataBuilder Class 

- Class that handles adding, updating, and removing data for one part of the program 

- This is a generic class that takes in a Type Argument, T 

- Properties 

    - ArrayList<T> data 

- Methods 

    - getArrayList 

        - Public 

        - No Parameters 

        - Returns the ArrayList<T> data property 

    - add 

        - Public 

        - Parameters 

            - T item 

        - Returns a boolean 

            - True if OK, false if that item already exists. The user can update it if they want. 

        - It serves the purpose to add an item into the ArrayList 

    - update 

        - Public 

        - Parameters 

            - T item 

        - Returns a boolean 

            - True if OK, false if that item doesn't exist. 

        - It serves the purpose to update an item in the ArrayList 

    - remove 

        - Public 

        - Parameters 

            - T item 

        - Returns a T data type 

            - Returns the removed item, or null if it was never in the ArrayList  

        - It serves the purpose to remove an item from the reviews ArrayList 

### DataHandler Class 

- Class that handles loading/reading and saving data to a file on the computer 

- Use Gson (Google’s Java Library for reading/saving JSON) 

- Properties 

    - Gson instance 

    - DataBuilder<Review> reviews 

    - DataBuilder<Meal> meals 

    - DataBuilder<String> knownLocations 

        - This is the source of truth for filtering, but a meal is allowed to have a location not listed here if the user chooses to do so 

- Methods 

    - loadData 

        - Private (called in the constructor or if an issue occurs it prompts the user to attempt again) 

        - Parameters 

            - boolean loadWithBlankData 

                - When true, load the application fresh/with the default data (empty reviews array, certain locations & meals already added) 

                    - Set’s an “initialized” boolean to true once that is done 

                - When false, attempt to load application data from file 

                    - Function overload option, if no boolean argument is passed: loadData(), then that is the same as loadData(false) 

        - Returns nothing 

        - It serves the purpose of filling in reviews, locations, and meals to their respective ArrayLists. 

    - saveData 

        - Public

        - No Parameters 

            - Uses existing instance properties instead 

        - Returns nothing 

        - It serves the purpose of saving data from the reviews, locations, and meals ArrayLists to file. 

    - getReviews 

        - Public 

        - No Parameters 

        - Returns the reviews property 

    - getMeals 

        - Public 

        - No Parameters 

        - Returns the meals property 

    - getKnownLocations 

        - Public 

        - No Parameters 

        - Returns the knownLocations property 

### Meal Record 

- Record Data Structure Type 

- Properties 

    - String name; 

    - String location; 

- Methods 

    - equals 

        - Public 

        - Parameters 

            - Object other 

        - Returns a boolean 

            - True if the name and location matches another meal’s name and location 

        - It serves the purpose of checking if two meals are the same, see review record for more detail 

### Review Record 

- Record Data Structure Type 

- Properties 

    - Meal meal; 

    - int rating; 

    - String notes; 

        - Can be null! 

- Methods 

    - equals 

        - Public 

        - Parameters 

            - Object other 

        - Returns a boolean 

            - True if the meals match, false otherwise 

        - It serves the purpose of checking if a review matches another review, like in the case to see if a review already exists in an ArrayList. 

### Sort 

- Class that handles sorting an ArrayList of Reviews 

- No Properties 

- Methods 

    - sortByMostRecent 

        - Public Static 

        - Parameters 

            - ArrayList reviews 

            - Boolean reversed 

        - Returns an ArrayList of reviews sorted in order by most (or least) recent 

    - sortAlphabetically 

        - Public Static 

        - Parameters 

            - ArrayList reviews 

            - Boolean reversed 

        - Returns an ArrayList of reviews sorted in order alphabetically A-Z (or Z-A) 

    - sortByRating 

        - Public Static 

        - Parameters 

            - ArrayList reviews 

            - Boolean reversed 

        -Returns an ArrayList of reviews sorted in order by highest (or lowest) rating 

### Search 

- Class that handles searching through an ArrayList of Reviews 

- No Properties 

- Methods 

    - SearchForReview 

        - Public Static 

        - Parameters 

            - ArrayList reviews 

            - String searchTerm 

        - Returns an ArrayList only with reviews that have a partial match of either meal, location, or notes to the search term 

### Filter 

- Class that handles filtering an ArrayList of Reviews 

- No Properties 

- Methods 

    - filterByLocation 

        - Public Static 

        - Parameters 

            - ArrayList reviews 

            - String location 

        - Returns an ArrayList only with reviews that strictly match the location inputted 

## System Diagram 

 

## Where Validation Lives 

Validation will mainly happen when the user enters a new review. The program will check the input before creating and saving a Review. 

- Rating validation 

    - The rating must be an integer between 1 and 5. 

    - Examples of invalid input include: -1, 0, 6, “five,” 4.5 

    - The class or method responsible for accepting user input will check the rating before creating the Review. 

    - If the user enters a number outside the range of 1–5, the program will display an error message and ask the user to enter the rating again. 

    - If the user enters something that is not an integer, such as letters or a decimal, the program will also display an error message and re-prompt the user. 

    - The review will not be added to the ArrayList<Review> until a valid rating is entered. 

- Location validation 

    - The user will be shown existing dining hall locations from the ArrayList<String> of locations, but custom locations are allowed. 

    - Because of this, a location does not have to already exist in the list to be considered valid. 

    - If the user enters a new location, the program can accept the string and optionally add it to the stored list of locations. 

- Meal validation 

    - Meal names work similarly to locations.  

    - The program can suggest meals already stored in the ArrayList<String> of meals, but the user is still allowed to enter a custom meal. 

    - A meal will not be rejected because it does not already exist in the list. 

- Notes Validation 

    - Notes can contain any string input and are optional. 

    - Because the notes property in the Review record can be null, the user can leave the notes section blank without causing an error. 

    - If no notes are entered, the program can store either null or an empty string depending on how the group decides to implement it. 

- Data file validation 

    - The Data class will handle problems that occur while loading saved data. 

    - When loadData(false) is called, the program will attempt to read the existing JSON file using Gson. 

    - If the file is missing, corrupted, or cannot be read, the program will not immediately crash. Instead, it will inform the user that the saved data could not be loaded and give them the option to try loading again or start with blank/default data using loadData(true); 

    - When blank/default data is loaded, the reviews list will start empty while the default locations and meals will be added. 

    - Once the data has been successfully loaded, the initialized value will be set to true. 

## Test Plan 
- Sort by Most Recent 

    - Normal: Give it several reviews. It should return them from newest to oldest. 

    - Bad input: Give it an empty list. It should return an empty list without crashing. 

- Sort Alphabetically 

    - Normal: Give it reviews for Pizza, Burger, and Tacos. It should return Burger, Pizza, Tacos. 

    - Bad input: Give it an empty list. It should return an empty list. 

- Sort by Rating 

    - Normal: Give it reviews rated 2, 5, and 4. It should return them as 5, 4, 2. 

    - Bad input: Give it an empty list. It should return an empty list. 

- Search for Review 

    - Normal: Search for “Chicken” when there is a Chicken Tenders review. It should return that review. 

    - Bad input: Search for something that does not exist. It should return an empty list. 

- Filter by Location 

    - Normal: Filter by “D2.” It should return only reviews from D2. 

    - Bad input: Enter a location with no reviews. It should return an empty list. 

- Adding a Review 

    - Normal: Enter a rating between 1 and 5. The review should be accepted. 

    - Bad input: Enter a rating like 0, 6, or “five.” The program should reject it and ask for a valid rating. 

- Loading Data 

    - Normal: Load a valid saved file. The saved reviews, meals, and locations should load. 

    - Bad input: Try to load a missing or corrupted file. The program should show an error instead of crashing. 

## Division of Work 

Jacob will be focusing on the initialization/foundation part of this project, making sure that data is safely and correctly loaded, saved, and accessible to the rest of the project, and making sure that everything works together in harmony. Sreenidhi will work on sorting, searching, and filtering through reviews. This will allow the end-user to more easily find exactly what they are looking for, so they can know what they want before they go to a location. Ujay will be in charge of the more intricate Data class methods, such as adding, updating, and removing reviews, adding meals, and adding locations. This is what the command line will work with to display reviews and allow the user to interface with it. Finally, Jonathan will work on the main command line interface. This is what the user will see, and from here they can do everything else that has been built from the other classes. 

## Revised Scope
Our main goal for HokieBytes has stayed the same. The program will allow Virginia Tech students to record meals they have eaten, give them a rating from 1–5, add notes, and look through their previous reviews. However, while creating our specification, we made several changes and clarified parts of our original scope. 

- JSON file storage using Gson 

    - Our original scope only said that the meal information would be stored locally on the computer. We decided to use JSON files and Gson to save and load the application's data. 

    - This gives us a specific way to store reviews, locations, and meal names between uses of the program. 

    - Origin: Group discussion. 

- Use location terminology instead of dining hall 

    - Our original scope specified that a review would contain the dining hall, which can present more generically when many dining halls have different locations inside of them. 

    - This allows us to specify “Perry’s Place – Smoke," rather than “Perry’s Place.” 

    - Origin: Group discussion. 

- Review record 

    - We decided to represent each review using a Review record containing the location, meal, rating, and notes. 

    - This gives us one structure that can be stored in an DataBuilder<Review> and passed between the different parts of the program. 

    - Origin: Group discussion. 

- Separate Sort, Search, and Filter classes 

    - The original scope listed sorting, searching, and filtering as features but did not explain how they would be organized in the program. 

    - We separated them into Sort, Search, and Filter classes so that each class has one specific responsibility. 

    - Origin: Group discussion 

- Expanded search 

    - The original scope said that searching would use a partial match of the meal name. We expanded the search so that the search term can partially match the meal name, location, or notes. 

    - This makes the search feature more useful because users can find reviews using more than just the meal name. 

    - Origin: Group discussion 

- Combined sorting methods 

    - Instead of creating separate methods for each direction of a sort, we decided to use a boolean reversed parameter. For example, the same alphabetical sorting method can handle both A–Z and Z–A. 

    - This reduces the number of methods while still supporting all of the sorting options from our original scope 

    - Origin: Group discussion  

- More specific data-loading behavior 

    - We added a loadData(boolean loadWithBlankData) method. If loadWithBlankData is true, the application starts with an empty review list and default meal and location information. If it is false, the program attempts to load previously saved information. 

    - We also decided that the program should handle a missing or corrupted save file instead of crashing. 

    - Origin: Group discussion  