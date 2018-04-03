import javafx.util.Pair;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CsvWriter {

    public CsvWriter() {
    }
/*
    public boolean saveFoods (ArrayList<BasicFood> basicFoods, ArrayList<Recipe> recipes) {

        ObjectGetter og = new ObjectGetter();
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter("data/testFoods.csv"));
            for (BasicFood basicFood : basicFoods){
                writer.write(Character.toString(basicFood.getType()) + ',');
                writer.write(basicFood.getName() + ',');
                writer.write(Double.toString(basicFood.getCalorie()) + ',');
                writer.write(Double.toString(basicFood.getFat()) + ',');
                writer.write(Double.toString(basicFood.getFat()) + ',');
                writer.write(Double.toString(basicFood.getCarb()) + ',');
                writer.write(Double.toString(basicFood.getProtein()));
                writer.newLine();
            }

            for (Recipe recipe: recipes) {
                writer.write(Character.toString(recipe.getType()) + ',');
                writer.write(recipe.getName() + ',');

                ArrayList<Pair> list = recipe.getFoods();

                for (Pair pair: list) {
                    writer.write(((BasicFood)pair.getKey()).getName() + ',');
                    double amount = (Double)pair.getValue();
                    writer.write(Double.toString(amount));

                    if (list.indexOf(pair) != list.size()-1 ){
                        writer.write(',');
                    }
                }
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
*/
    public boolean saveExercises (ArrayList<Exercise> exercises) {
        ObjectGetter og = new ObjectGetter();
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter("data/testExercises.csv"));
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
}