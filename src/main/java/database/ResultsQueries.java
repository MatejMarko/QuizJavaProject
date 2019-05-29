package database;

import objects.Result;

import java.sql.*;
import java.util.ArrayList;

public class ResultsQueries {

    public static void insert(String name, int result, int time) {
        String sql = "INSERT INTO results(player_name, result, time_taken, quiz_date) VALUES(?,?,?,?)";

        try (Connection conn = DBCreator.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, result);
            pstmt.setInt(3, time);
            pstmt.setInt(4, 12);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Result> selectAll(){

        ArrayList<Result> results = new ArrayList<>();

        String sql = "SELECT id, player_name, result, time_taken, quiz_date FROM results";

        try (Connection conn = DBCreator.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("player_name");
                int result = rs.getInt("result");
                double time = rs.getDouble("time_taken");
                int quiz_date = rs.getInt("quiz_date");
                Result r = new Result(id, name, result, time, quiz_date);
                results.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return results;
    }

    public static ArrayList<Result> getTopTenResults(){

        ArrayList<Result> results = new ArrayList<>();

        String sql = "SELECT id, player_name, result, time_taken, quiz_date " +
                "FROM results " +
                "ORDER BY result DESC, time_taken ASC " +
                "LIMIT 10";

        try (Connection conn = DBCreator.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("player_name");
                int result = rs.getInt("result");
                double time = rs.getDouble("time_taken");
                int quiz_date = rs.getInt("quiz_date");
                Result r = new Result(id, name, result, time, quiz_date);
                results.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return results;
    }

    public static ArrayList<Result> getResultsGreaterThan(int resultLimit){

        ArrayList<Result> results = new ArrayList<>();

        String sql = "SELECT id, player_name, result, time_taken, quiz_date "
                + "FROM results WHERE result >= ?";

        try (Connection conn = DBCreator.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){

            pstmt.setDouble(1, resultLimit);
            ResultSet rs  = pstmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int result = rs.getInt("result");
                double time = rs.getDouble("time");
                int quizDate = rs.getInt("quiz_date");
                Result r = new Result(id, name, result, time, quizDate);
                results.add(r);
            }
            for(Result r: results) {
                System.out.println(r.getResult());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return results;
    }


}
