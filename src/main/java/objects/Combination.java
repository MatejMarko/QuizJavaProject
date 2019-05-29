package objects;

import java.util.ArrayList;

public class Combination {

    private Question question;
    private ArrayList<Answer> answers;

    public Combination(Question question, ArrayList<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public Question getQuestion() {
        return question;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

}
