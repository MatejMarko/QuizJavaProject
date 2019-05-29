package database;

import objects.Answer;

import java.sql.*;
import java.util.ArrayList;

public class AnswersQueries {

    public static ArrayList<Answer> getPossibleAnswers(int question_id) {

        ArrayList<Answer> answers = new ArrayList<>();

        // TODO ADD RANDOM
        String sql = "SELECT * FROM answers WHERE question_id = ?";

        try (Connection conn = DBCreator.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, question_id);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String answer = rs.getString("answer");
                int correct = rs.getInt("correct");
                int q_id = rs.getInt("question_id");
                Answer a = new Answer(id, answer, correct, q_id);
                answers.add(a);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return answers;
    }

    public static void insert(String[] answers, int newQuestionId, int correctOption) {
        String sql = "INSERT INTO answers(answer, correct, question_id) VALUES(?,?,?)";

        for (int i = 1; i <= answers.length; i++) {
            int correct = i == correctOption ? 1 : 0;
            try (Connection conn = DBCreator.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, answers[i - 1]);
                pstmt.setInt(2, correct);
                pstmt.setInt(3, newQuestionId);
                pstmt.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
