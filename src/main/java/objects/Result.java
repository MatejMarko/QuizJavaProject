package objects;

public class Result {
    private int id;
    private String name;
    private int result;
    private double time;
    private int quizDate;

    public Result(int id, String name, int result, double time, int quizDate) {
        this.id = id;
        this.name = name;
        this.result = result;
        this.time = time;
        this.quizDate = quizDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getResult() {
        return result;
    }

    public double getTime() {
        return time;
    }

    public double getQuizDate() {
        return quizDate;
    }
}
