import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class ObjectGetter {

    String csvFoods = "data/foods.csv";
    String csvExercise = "data/exercise.csv";
    String csvLog = "data/log.csv";
    BufferedReader br1 = null;
    BufferedReader br2 = null;
    String line = "";
    String cvsSplitBy = ",";

    public ObjectGetter() {
    }

    /*public ArrayList<BasicFood> getFoods () {
        ArrayList<BasicFood> basicFoods = new ArrayList<>();

        try {

            br1 = new BufferedReader(new FileReader(csvFoods));
            while ((line = br1.readLine()) != null) {

                String[] splits = line.split(cvsSplitBy);
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
    }*/

    public ArrayList<Food> getAllFoods() {

        ArrayList<Food> foods = new ArrayList<>();
        ArrayList<Food> tempFoods = new ArrayList<>();

        try {
            FileReader fr = new FileReader(csvFoods);
            br1 = new BufferedReader(fr);

            while ((line = br1.readLine()) != null) {

                String[] splits = line.split(cvsSplitBy);
                char type = splits[0].charAt(0);
                String name = splits[1];

                if (type == 'b') {
                    tempFoods.add(new BasicFood(type, name));
                } else if (type == 'r') {
                    tempFoods.add(new Recipe(type, name));
                }
            }

            FileReader fr2 = new FileReader(csvFoods);
            BufferedReader br2 = new BufferedReader(fr2);

            while ((line = br2.readLine()) != null) {

                String[] splits = line.split(cvsSplitBy);
                char type = splits[0].charAt(0);

                if (type == 'b') {

                    String name = splits[1];
                    double calorie = Double.parseDouble(splits[2]);
                    double fat = Double.parseDouble(splits[3]);
                    double carb = Double.parseDouble(splits[4]);
                    double protein = Double.parseDouble(splits[5]);

                    BasicFood food1 = new BasicFood(type, name, calorie, fat, carb, protein);
                    foods.add(food1);

                } else if (type == 'r') {

                    //r,Hot Dog-Bun-Mustard-Ketchup,Hot Dog,1.0,Hot Dog Bun,1.0,Mustard,1.5,Ketchup,1.0

                    ArrayList<Pair> pairs = new ArrayList<>();
                    pairs.clear();
                    int ingNum = splits.length;

                    String name = splits[1];

                    ArrayList<FoodCount> foodCounts = new ArrayList<>();

                    for (int i = 2; i < ingNum; i++) {

                        for (Food food: tempFoods) {
                            if (food.getName().equals(splits[i])) {
                                foodCounts.add(new FoodCount(food, Double.parseDouble(splits[i+1])));
                            }
                        }
                    }
                    foods.add(new Recipe(type, name, foodCounts));
                }
            }

            ArrayList<Food> tempFoods2 = (ArrayList<Food>) foods.clone();


            FileReader fr3 = new FileReader(csvFoods);
            BufferedReader br3 = new BufferedReader(fr3);

            while ((line = br3.readLine()) != null) {
            for (Food food: foods) {
                String[] splits = line.split(cvsSplitBy);
                char type = splits[0].charAt(0);

                if (food.getType() == 'r') {
                    String name = splits[1];
                    Recipe recipe = (Recipe)food;
                    for (FoodCount tempFood: recipe.getFoods()){
                        if (name.equals(tempFood.getFood().getName())) {
                            for (Food tempFood2: tempFoods2) {
                                if (tempFood2.getName().equals(tempFood.getFood().getName())) {
                                    tempFood.getFood().setCalorie(tempFood2.getCalorie());
                                    tempFood.getFood().setCarb(tempFood2.getCarb());
                                    tempFood.getFood().setFat(tempFood2.getFat());
                                    tempFood.getFood().setProtein(tempFood2.getProtein());
                                    recipe.recalculateNutrients();
                                }
                            }
                        }
                    }
                }
            }}

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
        return foods;

    }
/*
    public ArrayList<Recipe> getRecipes() {

        ArrayList<Recipe> recipes = new ArrayList<>();

        try {

            br2 = new BufferedReader(new FileReader(csvFoods));
            while ((line = br2.readLine()) != null) {

                ArrayList<Pair> pairs = new ArrayList<>();
                pairs.clear();
                String[] splits = line.split(cvsSplitBy);
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

            br1 = new BufferedReader(new FileReader(csvExercise));
            while ((line = br1.readLine()) != null) {

                String[] splits = line.split(cvsSplitBy);
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
            if (br1 != null) {
                try {
                    br1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return exercises;

    }
/*
    public ArrayList<LogDay> getDays() {

        ArrayList<LogDay> days = new ArrayList<>();
        boolean isNewDay = false;
        LogDay newDay = null;

        try {

            br1 = new BufferedReader(new FileReader(csvLog));
            while ((line = br1.readLine()) != null) {

                String[] splits = line.split(cvsSplitBy);

                if (splits[3].equals('w')) {
                    isNewDay = true;
                } else {
                    isNewDay = false;
                }

                if (isNewDay) {
                    newDay = new LogDay();
                }

                if (splits[3].equals('w')) {
                    newDay.setWeight(Double.parseDouble(splits[4]));
                } else if (splits[3].equals('c')) {
                    newDay.setCalorieLimit(Double.parseDouble(splits[4]));
                } else if (splits[3].equals('f')) {
                    for (Food food : getFoods()) {
                        if (foodName.equals(basicFood.getName())) {
                            Pair<BasicFood, Double> pair = new Pair<>(basicFood, amount);
                            pairs.add(pair);
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
*/
}
