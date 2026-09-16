# Scope
## What are you building 
We are building a Virginia Tech dining hall food tracking app that helps students keep track of what they eat on campus and remember which meals they liked or disliked. After eating a meal at one of our dining halls, the user can input their meal, add a rating, add notes, and store it locally to the computer that can be viewed later. They can later look through their meal history, search for specific foods, and sort entries to quickly find their meals. The goal is to make it easier for students to remember what they have tried and decide what they might want to eat the next time they go to a dining hall.
## MVP 

- Input a meal with:  

    - Location 

        - String type; autofill options, but allow custom String input 

    - Meal name 

        - String type; autofill options, but allow custom String input 

    - Rating (1-5 star system) 

        - Int type; require 1-5 when inputting 

    - Notes 

        - String type, allow any String input 

- Scroll through a list with all the past meals/ratings visible 

    - By default, this would be sorted from most recent 

- Sort and search 

    - Search  

        - Partial match of the meal name 

    - Sort Options 

        - **Most recent (default)**, Least recent 

        - Alphabetical (A-Z), Alphabetical (Z-A) 

        - Rating (Highest-Lowest), Rating (Lowest-Highest) 

        - Filter by location 

## Stretch Goals 

- List with all locations and meals offered at VT 

    - This is something that generative AI could do to compile a list based upon the data structure we set out in front of it 

    - This would be used to “search” for options when adding a meal, but also allow people to input custom meals that may have been missed when compiling a list, or if they are new after the fact. 

- Recommendations for new meals the user hasn’t had yet 

- Sort meals into different sets of categories, such as: 

    - Culture (e.g. American Food, Asian Food, European Food) 

- Add in a Graphical User Interface instead of it being command line based 

    - We could leave in the CLI if they pass in a –nogui argument 

## What bad input must it survive 
- Personal Rating System would require an input between 1 and 5 stars.

    - Examples of bad inputs would be –1 stars, 0 stars, or 6 stars. 
    
    - The program will reject the input and ask them to re-enter a rating between 1-5 

    - If user inputs a non-integer or letters, program displays an error and asks to re-enter an input between 1-5 

- There would not be any bad inputs for food/menu items & locations, since we would allow users to input their own Strings for that that do not conform to the default options 

    - E.g. a new menu item or location is added after creating the app 

- If someone were to edit the JSON file saved by the program and corrupt the data, the program would try and read what parts it can from it. If it is unable to do anything with the way the file has been modified, it will give the user a choice to start fresh or exit the program if they would like to fix the file themselves. 

## What don't you know how to do yet? 
We aren’t sure how to tackle GUIs yet, but it is something we are looking to add to our app. 