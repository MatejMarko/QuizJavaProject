package quiz;

import screens.GameScreen;
import screens.WelcomeScreen;

import javax.swing.*;
import java.awt.*;

public class Quiz {

    private JPanel contentPane;
    private WelcomeScreen panel1;
    private GameScreen panel2;
    private GameScreen panel3;
    private JComboBox choiceBox;
    private String[] choices = {
            "Panel 1",
            "Panel 2",
            "Panel 3"
    };

    private void displayGUI()
    {
        JFrame frame = new JFrame("Card Layout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new CardLayout());

        choiceBox = new JComboBox(choices);

        panel1 = new WelcomeScreen(contentPane, this);
        panel2 = new GameScreen(contentPane
                , Color.GREEN.darker().darker(), this);
        panel3 = new GameScreen(contentPane
                , Color.DARK_GRAY, this);

        contentPane.add(panel1, "Welcome screen");
        contentPane.add(panel2, "Game screen");
        contentPane.add(panel3, "Panel 3");

        frame.getContentPane().add(choiceBox, BorderLayout.PAGE_START);
        frame.getContentPane().add(contentPane, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public JComboBox getChoiceBox()
    {
        return choiceBox;
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