package database;

import objects.Answer;
import objects.Combination;
import objects.Question;

import java.sql.*;
import java.util.ArrayList;

public class QuestionsQueries {

    public static Combination getQuestionByDifficulty(int d) {

        Question question = null;
        ArrayList<Answer> answers = null;

        // TODO ADD RANDOM
        String sql = "SELECT * FROM questions WHERE difficulty = ? LIMIT 1";
        int id = -1;

        try (Connection conn = DBCreator.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, d);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");
                String q = rs.getString("question");
                String topic = rs.getString("topic");
                int difficulty = rs.getInt("difficulty");
                question = new Question(id, q, topic, difficulty);
            }
            if (id != -1)
                answers = AnswersQueries.getPossibleAnswers(id);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return new Combination(question, answers);
    }

    public static int insert(String question, String topic, int difficulty) {
        int newId = -1;
        String sql = "INSERT INTO questions(question, topic, difficulty) VALUES(?,?,?)";

        try (Connection conn = DBCreator.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, question);
            pstmt.setString(2, topic);
            pstmt.setInt(3, difficulty);
            pstmt.executeUpdate();

            String newIdSql = "SELECT id FROM questions order by id DESC limit 1";
            try (Connection conn2 = DBCreator.connect();
                 Statement stmt = conn2.createStatement();
                 ResultSet rs = stmt.executeQuery(newIdSql)) {
                while (rs.next()) {
                    newId = rs.getInt("id");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return newId;
    }
}
