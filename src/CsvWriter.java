import javafx.util.Pair;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CsvWriter {

    public CsvWriter() {
    }

    public boolean saveFoods (ArrayList<Food> foods) {

        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter("data/foods.csv"));
            for (Food food : foods){
                if (food.getType() == 'b') {
                    writer.write(Character.toString(food.getType()) + ',');
                    writer.write(food.getName() + ',');
                    writer.write(Double.toString(food.getCalorie()) + ',');
                    writer.write(Double.toString(food.getFat()) + ',');
                    writer.write(Double.toString(food.getCarb()) + ',');
                    writer.write(Double.toString(food.getProtein()));
                    writer.newLine();
                } else {
                    writer.write(Character.toString(food.getType()) + ',');
                    writer.write(food.getName() + ',');
                    Recipe recipe = (Recipe)food;
                    ArrayList<FoodCount> list = recipe.getFoods();
                    for (FoodCount food2: list) {
                        writer.write(food2.getFood().getName() + ',');
                        writer.write(Double.toString(food2.getCount()));
                        if(food2 != list.get(list.size() - 1)) {
                            writer.write(',');
                        }
                    }
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }

    public boolean saveExercises (ArrayList<Exercise> exercises) {
        ObjectGetter og = new ObjectGetter();
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter("data/exercises.csv"));
            for (Exercise exercise: exercises){
                writer.write(Character.toString(exercise.getType()) + ',');
                writer.write(exercise.getName() + ',');
                writer.write(Double.toString(exercise.getCalories()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }

    public boolean writeToLog (ArrayList<LogDay> days) {

        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter("data/log.csv"));
            for (LogDay day : days){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy,MM,dd");
                String dateStr = sdf.format( day.getDate() );
                writer.write(dateStr + ',');
                writer.write("w,");
                writer.write(Double.toString(day.getWeight()));
                writer.newLine();
                writer.write(dateStr + ',');
                writer.write("c,");
                writer.write(Double.toString(day.getCalorieLimit()));
                writer.newLine();

                for (FoodCount food : day.getFoods()){
                    writer.write(dateStr + ',');
                    writer.write("f,");
                    writer.write(food.getFood().getName() + ",");
                    writer.write(Double.toString(food.getCount()));
                    writer.newLine();
                }

                for (ExerciseCount exercise : day.getExercises()){
                    writer.write(dateStr + ',');
                    writer.write("e,");
                    writer.write(exercise.getExercise().getName() + ",");
                    writer.write(Double.toString(exercise.getCount()));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }
}