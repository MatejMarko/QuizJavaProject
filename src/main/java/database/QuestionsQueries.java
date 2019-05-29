package database;

import objects.Question;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QuestionsQueries {

    public static Question getQuestionByDifficulty(int d) {

        Question q = null;

        // TODO ADD RANDOM
        String sql = "SELECT * FROM questions WHERE difficulty = ? LIMIT 1";

        try (Connection conn = DBCreator.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String question = rs.getString("question");
                String topic = rs.getString("topic");
                int difficulty = rs.getInt("difficulty");
                q = new Question(id, question, topic, difficulty);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return q;
    }

}
