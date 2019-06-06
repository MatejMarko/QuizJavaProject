package events;

import database.AnswersQueries;
import database.QuestionsQueries;

public class QuestionSubmitHandler {

    private String question;
    private int correctAnswer;
    private String firstAnswer;
    private String secondAnswer;
    private String thirdAnswer;
    private String fourthAnswer;
    private String topic;
    private int difficulty;

    public QuestionSubmitHandler(String question, int correctAnswer, String firstAnswer, String secondAnswer,
                                 String thirdAnswer, String fourthAnswer, String topic, int difficulty) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.firstAnswer = firstAnswer;
        this.secondAnswer = secondAnswer;
        this.thirdAnswer = thirdAnswer;
        this.fourthAnswer = fourthAnswer;
        this.topic = topic;
        this.difficulty = difficulty;
    }

    public void tryToInsert() {
        int questionId = QuestionsQueries.insert(question, topic, difficulty);
        AnswersQueries.insert(new String[]{firstAnswer, secondAnswer, thirdAnswer, fourthAnswer}, questionId, correctAnswer+1);
    }

}
