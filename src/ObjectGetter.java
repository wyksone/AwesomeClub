import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ObjectGetter {

    String csvFoodsFilename = "data/foods.csv";
    String csvExerciseFilename = "data/exercise.csv";
    String csvLog = "data/log.csv";
    BufferedReader br1 = null;
    BufferedReader br2 = null;
    String line = "";
    static String SPLIT_CHARACTER = ",";

    public ObjectGetter() {
    }

    public ArrayList<BasicFood> getFoods () {
        ArrayList<BasicFood> basicFoods = new ArrayList<>();

        try {

            br1 = new BufferedReader(new FileReader(csvFoodsFilename));
            while ((line = br1.readLine()) != null) {

                String[] splits = line.split(SPLIT_CHARACTER);
                char type = splits[0].charAt(0);

                if (type == 'b') {
                    String name = splits[1];
                    double calorie = Double.parseDouble(splits[2]);
                    double fat = Double.parseDouble(splits[3]);
                    double carb = Double.parseDouble(splits[4]);
                    double protein = Double.parseDouble(splits[5]);

                    BasicFood basicFood = new BasicFood(type, name, calorie, fat, carb, protein);
                    basicFoods.add(basicFood);
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (br1 != null) {
                try {
                    br1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return basicFoods;
    }

    public ArrayList<String> readFile(String filename) {
        try {
            FileReader fr = new FileReader(filename);
            BufferedReader br1 = new BufferedReader(fr);

            ArrayList<String> array = new ArrayList<>();
            while ((line = br1.readLine()) != null) {
                array.add(line);
            }
            return array;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HashMap<String, String> toHashMap(ArrayList<String> array) {
        HashMap<String, String> hashMap = new HashMap<>();
        array.forEach(str -> {
            String[] splits = str.split(SPLIT_CHARACTER);
            hashMap.put(splits[1], str);
        });
        return hashMap;
    }

    public BasicFood csvStringToFood(String str){
        String[] splits =str.split(SPLIT_CHARACTER);
        char type = splits[0].charAt(0);
        String name = splits[1];
        double calorie = Double.parseDouble(splits[2]);
        double fat = Double.parseDouble(splits[3]);
        double carb = Double.parseDouble(splits[4]);
        double protein = Double.parseDouble(splits[5]);

       return new BasicFood(type, name, calorie, fat, carb, protein);
    }

    public boolean isBasicFood(String str){
        return str.split(SPLIT_CHARACTER)[0].equals("b");
    }

    public boolean isRecipe(String str){
        return str.split(SPLIT_CHARACTER)[0].equals("r");
    }

    public Recipe csvStringToRecipe(String str, HashMap<String, String> allFood){
        String[] splits =str.split(SPLIT_CHARACTER);
        char recipeType = splits[0].charAt(0);
        String recipeName = splits[1];

        ArrayList<FoodCount> foods = new ArrayList<>();
        for (int i = 2; i < splits.length; i+=2) {

            String name = splits[i];
            String recipeCsv = allFood.get(name);

            if(this.isRecipe(recipeCsv)) {
                Recipe recipe = csvStringToRecipe(recipeCsv, allFood);
                foods.addAll(recipe.getFoods());
            }
            else{
                double count = Double.parseDouble(splits[i+1]);
                BasicFood food = csvStringToFood(allFood.get(name));
                FoodCount foodCount = new FoodCount(food, count);
                foods.add(foodCount);
            }
        }
        return new Recipe(recipeType, recipeName, foods);
    }



    public ArrayList<Food> getAllFoods() {
       ArrayList<String> foodsCsv = this.readFile(this.csvFoodsFilename);
       HashMap<String, String> foodsMap = this.toHashMap(foodsCsv);
       ArrayList<Food> foods = new ArrayList<>();

        for (String str:foodsCsv) {
            if(this.isBasicFood(str)) {
                foods.add(this.csvStringToFood(str));
            }

            if(this.isRecipe(str)){
                foods.add(this.csvStringToRecipe(str, foodsMap));
            }
        }

       return  foods;
    }

    /*
        public ArrayList<Recipe> getRecipes() {

            ArrayList<Recipe> recipes = new ArrayList<>();

            try {

                br2 = new BufferedReader(new FileReader(csvFoodsFilename));
                while ((line = br2.readLine()) != null) {

                    ArrayList<Pair> pairs = new ArrayList<>();
                    pairs.clear();
                    String[] splits = line.split(SPLIT_CHARACTER);
                    int ingNum = splits.length;
                    char type = splits[0].charAt(0);

                    if (type == 'r') {
                        String name = splits[1];

                        for (int i = 2; i < ingNum; i++) {
                            String foodName = splits[i];
                            i++;
                            double amount = Double.parseDouble(splits[i]);

                            for (BasicFood basicFood : getFoods()) {
                                if (foodName.equals(basicFood.getName())) {
                                    Pair<BasicFood, Double> pair = new Pair<>(basicFood, amount);
                                    pairs.add(pair);
                                }
                            }

                        }

                        recipes.add(new Recipe(type, name, pairs));
                    }

                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } finally {
                if (br2 != null) {
                    try {
                        br2.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }
            return recipes;
        }
    */
    public ArrayList<Exercise> getExercises() {

        ArrayList<Exercise> exercises = new ArrayList<>();

        try {

            br2 = new BufferedReader(new FileReader(csvExerciseFilename));
            while ((line = br2.readLine()) != null) {

                String[] splits = line.split(SPLIT_CHARACTER);
                char type = splits[0].charAt(0);

                if (type == 'e') {
                    String name = splits[1];
                    double calories = Double.parseDouble(splits[2]);

                    Exercise exercise = new Exercise(type, name, calories);
                    exercises.add(exercise);
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (br2 != null) {
                try {
                    br2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return exercises;

    }

    public ArrayList<LogDay> getDays() {

        ArrayList<LogDay> days = new ArrayList<>();
        boolean isNewDay = false;
        String dateString;
        LogDay newDay = null;
        ArrayList<FoodCount> dayFoods = new ArrayList<>();
        ArrayList<ExerciseCount> dayExercises = new ArrayList<>();
        String[] splits = null;

        try {

            br1 = new BufferedReader(new FileReader(csvLog));
            while ((line = br1.readLine()) != null) {

                splits = line.split(SPLIT_CHARACTER);

                if (splits[3].equals("w")) {
                    isNewDay = true;
                } else {
                    isNewDay = false;
                }

                if (isNewDay) {
                    if (newDay != null) {
                        newDay.setFoods(dayFoods);
                        newDay.setExercises(dayExercises);
                        dayFoods = new ArrayList<>();
                        dayExercises = new ArrayList<>();
                        days.add(newDay);
                    }
                    newDay = new LogDay();
                }

                if (splits[3].equals("w")) {
                    newDay.setWeight(Double.parseDouble(splits[4]));
                    Date date = parseDate(splits[0] + "-" + splits[1] + "-" + splits[2]);
                    newDay.setDate(date);
                } else if (splits[3].equals("c")) {
                    newDay.setCalorieLimit(Double.parseDouble(splits[4]));
                } else if (splits[3].equals("f")) {

                    for (Food food : getAllFoods()) {
                        if (food.getName().equals(splits[4])) {
                            FoodCount foodCount = new FoodCount(food, Double.parseDouble(splits[5]));
                            dayFoods.add(foodCount);
                        }
                    }

                } else if (splits[3].equals("e")) {

                    for (Exercise exercise : getExercises()) {
                        if (exercise.getName().equals(splits[4])) {
                            ExerciseCount exerciseCount = new ExerciseCount(exercise, Double.parseDouble(splits[5]));
                            dayExercises.add(exerciseCount);
                        }
                    }

                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {

            Date date = parseDate(splits[0] + "-" + splits[1] + "-" + splits[2]);
            newDay.setDate(date);
            newDay.setFoods(dayFoods);
            newDay.setExercises(dayExercises);
            days.add(newDay);

            if (br1 != null) {
                try {
                    br1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return days;

    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

}
