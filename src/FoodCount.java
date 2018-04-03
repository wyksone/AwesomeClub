public class FoodCount {

    Food food;
    Double count;

    public FoodCount(Food food, Double count) {
        this.food = food;
        this.count = count;
    }

    //region Getters and Setters
    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }
    //endregion
}
