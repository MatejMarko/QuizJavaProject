package database;

import constants.Database;
import objects.Result;

import java.sql.*;
import java.util.ArrayList;

public class DBCreator {

    public static void createNewDatabase(String fileName) {

        String url = Database.PATH + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createTables() {

        String path = Database.PATH + Database.DB_NAME;

        String sql_results = "CREATE TABLE IF NOT EXISTS results ("
                + "	id INTEGER PRIMARY KEY,"
                + "	player_name TEXT NOT NULL,"
                + "	result INTEGER NOT NULL,"
                + "	time_taken INTEGER NOT NULL,"
                + "	quiz_date INTEGER NOT NULL"
                + ");";

        String sql_questions = "CREATE TABLE IF NOT EXISTS questions (" +
                "id INTEGER PRIMARY KEY, " +
                "question TEXT NOT NULL, " +
                "topic TEXT NOT NULL, " +
                "difficulty INTEGER NOT NULL" +
                ")";

        String sql_answers = "CREATE TABLE IF NOT EXISTS answers (" +
                "id INTEGER PRIMARY KEY, " +
                "answer TEXT NOT NULL, " +
                "correct INTEGER NOT NULL, " +
                "question_id INTEGER NOT NULL" +
                ")";

        try (Connection conn = DriverManager.getConnection(path);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql_results);
            //stmt.execute(sql_questions);
            //stmt.execute(sql_answers);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection connect() {
        String url = Database.PATH + Database.DB_NAME;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void main(String[] args) {
        createTables();
        ResultsQueries rw = new ResultsQueries();
        //rw.insert("Matej", 6, 13);
        //rw.selectAll();
        ArrayList<Result> alr = rw.getTopTenResults();

    }

}
