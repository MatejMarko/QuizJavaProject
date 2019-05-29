package objects;

public class Answer {

    private int id;
    private String answer;
    private int correct;
    private int question_id;

    public Answer(int id, String answer, int correct, int question_id) {
        this.id = id;
        this.answer = answer;
        this.correct = correct;
        this.question_id = question_id;
    }

    public int getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public int getCorrect() {
        return correct;
    }

    public int getQuestion_id() {
        return question_id;
    }

}
