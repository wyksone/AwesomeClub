public class ExerciseCount {

    private Exercise exercise;
    private Double count;

    @Override
    public String toString() {
        return "ExerciseCount{" +
                "exercise=" + exercise +
                ", count=" + count +
                '}';
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public ExerciseCount(Exercise exercise, Double count) {
        this.exercise = exercise;
        this.count = count;

    }

}
