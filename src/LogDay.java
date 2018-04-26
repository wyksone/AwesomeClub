import java.util.ArrayList;
import java.util.Date;

public class LogDay {

    Date date;
    double weight;
    double calorieLimit;
    ArrayList<FoodCount> foods;
    ArrayList<ExerciseCount> exercises;

    public LogDay(double weight, double calorieLimit, ArrayList<FoodCount> foods, ArrayList<ExerciseCount> exercises) {
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

    public ArrayList<FoodCount> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<FoodCount> foods) {
        this.foods = foods;
    }

    public ArrayList<ExerciseCount> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<ExerciseCount> exercises) {
        this.exercises = exercises;
    }

    public double getTotalCalories() {

        double totalCal = 0;

        for (FoodCount food : this.getFoods()){
            totalCal += food.getFood().getCalorie() * food.getCount();
        }

        return totalCal;
    }

    public double getTotalFat() {

        double totalFat = 0;

        for (FoodCount food : this.getFoods()){
            totalFat += food.getFood().getFat() * food.getCount();
        }

        return totalFat;
    }

    public double getTotalCarb() {

        double totalCarb = 0;

        for (FoodCount food : this.getFoods()){
            totalCarb += food.getFood().getCarb() * food.getCount();
        }

        return totalCarb;
    }

    public double getTotalProtein() {

        double totalProtein = 0;

        for (FoodCount food : this.getFoods()){
            totalProtein += food.getFood().getProtein() * food.getCount();
        }

        return totalProtein;
    }

    public double getSpentCalories() {

        double totalSpentCalories = 0;

        for (ExerciseCount exercise : this.getExercises()){
            totalSpentCalories += exercise.getExercise().getCalories() * exercise.getCount();
        }

        return totalSpentCalories;
    }
    //endregion


    @Override
    public String toString() {
        return "LogDay{" +
                "date=" + date +
                ", weight=" + weight +
                ", calorieLimit=" + calorieLimit +
                ", foods=" + foods +
                ", exercises=" + exercises +
                '}';
    }
}
