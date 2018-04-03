public class BasicFood  implements Food{

    char type;
    String name;
    double calorie;
    double fat;
    double carb;
    double protein;

    public BasicFood(char type, String name, double calorie, double fat, double carb, double protein) {
        this.type = type;
        this.name = name;
        this.calorie = calorie;
        this.fat = fat;
        this.carb = carb;
        this.protein = protein;
    }

    public BasicFood (char type, String name) {
        this.name = name;
        this.type = type;
    }

    //region Getters and Setters
    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "BasicFood{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", calorie=" + calorie +
                ", fat=" + fat +
                ", carb=" + carb +
                ", protein=" + protein +
                '}';
    }

}
