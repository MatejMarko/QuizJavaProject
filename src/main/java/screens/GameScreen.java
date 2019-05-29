package screens;

import quiz.Quiz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameScreen extends JPanel
{

    private JButton jcomp1;
    private JPanel contentPane;
    private Color backgroundColour;
    private JComboBox choiceBox;

    JPanel progress_panel = new JPanel();
    JPanel question_panel = new JPanel();

    JLabel[] seznam = new JLabel[15];

    int progress = 0;
    static int MAX_QUESTIONS = 15;

    public GameScreen(JPanel panel, Color c, Quiz cle)
    {
        contentPane = panel;
        backgroundColour = c;
        choiceBox = cle.getChoiceBox();

        setOpaque(true);
        setBackground(backgroundColour);

        //construct components
        jcomp1 = new JButton ("Show New Panel");
        jcomp1.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (progress < MAX_QUESTIONS - 1) {
                    String changeToPanel = (String) choiceBox.getSelectedItem();
                    CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                    cardLayout.show(contentPane, changeToPanel);
                    seznam[progress].setBackground(null);
                    seznam[progress].setForeground(Color.WHITE);
                    progress++;
                    seznam[progress].setBackground(Color.YELLOW);
                    seznam[progress].setForeground(Color.BLACK);
                    seznam[progress].setOpaque(true);
                } else {
                    System.out.println("You won the game");
                }
            }
        });


        progress_panel.setBackground(Color.BLUE);
        question_panel.setBackground(Color.BLACK);
        setQuestionPanel();
        setLayout(new GridLayout());
        progress_panel.add(jcomp1);
        add(progress_panel);
        add(question_panel);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return (new Dimension(700, 500));
    }

    private void setQuestionPanel() {
        for (int i = 0; i < MAX_QUESTIONS; i++) {
            progress_panel.setLayout(new GridLayout(0,1));
            GridLayout gl = new GridLayout(0,1);
            JLabel p = new JLabel("Question " + (i+1));
            seznam[i] = p;
            seznam[i].setForeground(Color.WHITE);
            seznam[i].setBackground(null);
            seznam[i].setOpaque(true);
            p.setHorizontalAlignment(0);
        }
        for (int i = MAX_QUESTIONS - 1; i >= 0; i--) {
            progress_panel.add(seznam[i]);
        }
        seznam[0].setBackground(Color.YELLOW);
        seznam[0].setForeground(Color.BLACK);
    }
}