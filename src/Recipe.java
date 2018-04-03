import javafx.util.Pair;
import java.util.ArrayList;

public class Recipe implements  Food{

    char type;
    String name;
    double calorie;
    double fat;
    double carb;
    double protein;
    ArrayList<FoodCount> foods;

    public Recipe (char type, String name, ArrayList<FoodCount> foods) {
        this.type = type;
        this.name = name;
        this.foods = foods;

        double calories = 0;
        double fats = 0;
        double carbs = 0;
        double proteins = 0;

        for (FoodCount foodCount: foods) {
            Food food = foodCount.getFood();
            double amount = foodCount.getCount();
            calories += food.getCalorie() * amount;
            fats += food.getFat() * amount;
            carbs += food.getCarb() * amount;
            proteins += food.getProtein() * amount;
        }

        this.calorie = round(calories, 1);
        this.fat = round(fats, 1);
        this.carb = round(carbs, 1);
        this.protein = round(proteins, 1);
    }

    public Recipe (char type, String name) {
        this.type = type;
        this.name = name;
    }

    //region Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<FoodCount> getFoods() { return foods; }

    public void setFoods(ArrayList<FoodCount> foods) {
        this.foods = foods;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public double getCalorie() {
        return calorie;
    }

    public void setCalorie(double calorie) {
        this.calorie = calorie;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarb() {
        return carb;
    }

    public void setCarb(double carb) {
        this.carb = carb;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }
    //endregion


    @Override
    public String toString() {
        return "Recipe{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", calorie=" + calorie +
                ", fat=" + fat +
                ", carb=" + carb +
                ", protein=" + protein +
                ", foods=" + foods +
                '}';
    }

    public void recalculateNutrients() {

        for (FoodCount foodCount: foods) {
            Food food = foodCount.getFood();
            double amount = foodCount.getCount();
            this.calorie += food.getCalorie() * amount;
            this.fat += food.getFat() * amount;
            this.carb += food.getCarb() * amount;
            this.protein += food.getProtein() * amount;
        }

        this.calorie = round(this.calorie, 1);
        this.fat = round(this.fat, 1);
        this.carb = round(this.carb, 1);
        this.protein = round(this.protein, 1);
    }

    public static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

}
