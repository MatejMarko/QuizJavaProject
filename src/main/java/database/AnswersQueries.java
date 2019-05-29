package database;

import objects.Answer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AnswersQueries {

    public static ArrayList<Answer> getPossibleAnswers(int question_id){

        ArrayList<Answer> answers = new ArrayList<>();

        // TODO ADD RANDOM
        String sql = "SELECT * FROM answers WHERE question_id = ?";

        try (Connection conn = DBCreator.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

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
}
