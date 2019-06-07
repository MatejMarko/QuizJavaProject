package quiz;

import database.DBCreator;
import database.QuestionsQueries;
import database.ResultsQueries;
import objects.Answer;
import objects.Combination;
import screens.AddNewQuestion;
import screens.GameScreen;
import screens.WelcomeScreen;

import javax.swing.*;
import java.awt.*;

public class Quiz {

    public static JPanel contentPane;
    private WelcomeScreen panel1;
    public static GameScreen panel2;
    private AddNewQuestion panel3;

    private void displayGUI()
    {
        JFrame frame = new JFrame("Card Layout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new CardLayout());

        panel1 = new WelcomeScreen(contentPane, this);
        panel2 = new GameScreen(contentPane,this);
        panel3 = new AddNewQuestion(contentPane, this);

        contentPane.add(panel1, "Welcome screen");
        contentPane.add(panel2, "Game screen");
        contentPane.add(panel3, "Add New Question");

        frame.getContentPane().add(contentPane, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String... args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new Quiz().displayGUI();
            }
        });
    }
}
