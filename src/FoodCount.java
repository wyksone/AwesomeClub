public class FoodCount {

    private Food food;
    private Double count;

    public FoodCount(Food food, Double count) {
        this.food = food;
        this.count = count;
    }

    @Override
    public String toString(){
        Food f = getFood();
        return String.format("Name:%s Calorie:%s Count:%s",
                f.getName(),
                f.getCalorie(),
                getCount());
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
