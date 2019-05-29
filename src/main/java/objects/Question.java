package objects;

public class Question {

    private int id;
    private String question;
    private String topic;
    private int difficulty;

    public Question(int id, String question, String topic, int difficulty) {
        this.id = id;
        this.question = question;
        this.topic = topic;
        this.difficulty = difficulty;
    }

    public int getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getTopic() {
        return topic;
    }

    public int getDifficulty() {
        return difficulty;
    }

}
