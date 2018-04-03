public class Test {
    public static void main(String[] args) {
        ObjectGetter og = new ObjectGetter();
        CsvWriter csvWriter = new CsvWriter();
        /*
        for (BasicFood food: og.getFoods()){
            System.out.println(food.toString());
        }

        for (Recipe recipe: og.getRecipes()){
            System.out.println(recipe.toString());
        }

        for (Exercise exercise: og.getExercises()){
            System.out.println(exercise.toString());
        }

        csvWriter.saveFoods(og.getFoods(), og.getRecipes());
        csvWriter.saveExercises(og.getExercises());*/

        for (Food food: og.getAllFoods()){
            System.out.println(food.toString());
        }
    }
}
