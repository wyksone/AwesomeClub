import java.util.ArrayList;
import java.util.Date;

public class LogDay {

    Date date;
    double weight;
    double calorieLimit;
    ArrayList<Food> foods;
    ArrayList<Exercise> exercises;

    public LogDay(double weight, double calorieLimit, ArrayList<Food> foods, ArrayList<Exercise> exercises) {
        this.weight = weight;
        this.calorieLimit = calorieLimit;
        this.foods = foods;
        this.exercises = exercises;
    }

    public LogDay () {}

    //region Getters and Setters

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getCalorieLimit() {
        return calorieLimit;
    }

    public void setCalorieLimit(double calorieLimit) {
        this.calorieLimit = calorieLimit;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }
    //endregion

    @Override
    public String toString() {
        return "LogDay{" +
                "weight=" + weight +
                ", calorieLimit=" + calorieLimit +
                ", foods=" + foods +
                ", exercises=" + exercises +
                '}';
    }
}
