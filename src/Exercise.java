public class Exercise {

    char type;
    String name;
    double calories;

    public Exercise(char type, String name, double calories) {
        this.type = type;
        this.name = name;
        this.calories = calories;
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

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }
    //endregion

    @Override
    public String toString() {
        return "Exercise{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", calories=" + calories +
                '}';
    }
}
