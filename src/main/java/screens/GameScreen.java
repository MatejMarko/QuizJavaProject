package screens;

import database.QuestionsQueries;
import objects.Combination;
import quiz.Quiz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameScreen extends JPanel
{

    private JTextArea won;

    private Combination combination = null;
    private int correctAnswer = -1;
    private JTextArea question;
    private JButton a1;
    private JButton a2;
    private JButton a3;
    private JButton a4;

    private JButton jcomp1;
    private JPanel contentPane;
    private Color backgroundColour;

    private JPanel progress_panel = new JPanel();
    private JPanel question_panel = new JPanel();

    private JLabel[] progressList = new JLabel[15];

    private int progress = 0;
    private static int MAX_QUESTIONS = 15;

    public GameScreen(JPanel panel, Quiz cle)
    {

        combination = QuestionsQueries.getQuestionByDifficulty(1);

        contentPane = panel;

        setOpaque(true);
        setBackground(backgroundColour);

        //construct components
        jcomp1 = new JButton ("Quit");
        jcomp1.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.show(contentPane, "Welcome screen");
                progress = 0;
                combination = QuestionsQueries.getQuestionByDifficulty(1);
                setQuestion();
                resetProgressList();
                progressList[0].setBackground(Color.YELLOW);
                progressList[0].setForeground(Color.BLACK);
                won.setVisible(false);
                a1.setEnabled(true);
                a2.setEnabled(true);
                a3.setEnabled(true);
                a4.setEnabled(true);
            }
        });


        progress_panel.setBackground(Color.BLUE);
        question_panel.setLayout(new GridLayout(0, 1));
        setQuestionPanel();
        setLayout(new GridLayout());
        progress_panel.add(jcomp1);
        add(progress_panel);
        add(question_panel);


        JPanel questionP = new JPanel(new BorderLayout());
        JPanel answersP = new JPanel(new GridLayout(0, 1));
        question = new JTextArea();
        question.setFont(new Font("q", 0, 20));
        question.setEditable(false);
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        questionP.add(question, BorderLayout.PAGE_END);
        questionP.setBackground(Color.WHITE);
        question_panel.add(questionP);

        won = new JTextArea("Congratulations, you won!");
        won.setForeground(Color.GREEN);
        won.setVisible(false);
        won.setEditable(false);
        won.setWrapStyleWord(true);
        won.setLineWrap(true);
        won.setFont(new Font("won", 0, 25));
        questionP.add(won, BorderLayout.PAGE_START);

        a1 = new JButton();
        a2 = new JButton();
        a3 = new JButton();
        a4 = new JButton();
        answersP.add(a1);
        answersP.add(a2);
        answersP.add(a3);
        answersP.add(a4);
        a1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer(0);
            }
        });
        a2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer(1);
            }
        });
        a3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer(2);
            }
        });
        a4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer(3);
            }
        });
        answersP.setBackground(Color.WHITE);
        question_panel.add(answersP);
        questionP.setBorder(new EmptyBorder(30, 30, 30, 30));
        answersP.setBorder(new EmptyBorder(30, 30, 30, 30));

        setQuestion();
    }

    @Override
    public Dimension getPreferredSize()
    {
        return (new Dimension(500, 500));
    }

    private void resetProgressList() {
        for (int i = 0; i < MAX_QUESTIONS; i++) {
            progressList[i].setBackground(null);
            progressList[i].setForeground(Color.WHITE);
        }
    }

    private void setQuestionPanel() {
        for (int i = 0; i < MAX_QUESTIONS; i++) {
            progress_panel.setLayout(new GridLayout(0,1));
            JLabel p = new JLabel("Question " + (i+1));
            progressList[i] = p;
            progressList[i].setForeground(Color.WHITE);
            progressList[i].setBackground(null);
            progressList[i].setOpaque(true);
            p.setHorizontalAlignment(0);
        }
        for (int i = MAX_QUESTIONS - 1; i >= 0; i--) {
            progress_panel.add(progressList[i]);
        }
        progressList[0].setBackground(Color.YELLOW);
        progressList[0].setForeground(Color.BLACK);
    }

    private void checkAnswer(int i) {
        if (i == correctAnswer) {
            if (progress < MAX_QUESTIONS - 1) {
                progressList[progress].setBackground(null);
                progressList[progress].setForeground(Color.WHITE);
                progress++;
                progressList[progress].setBackground(Color.YELLOW);
                progressList[progress].setForeground(Color.BLACK);
                progressList[progress].setOpaque(true);
                combination = QuestionsQueries.getQuestionByDifficulty(progress + 1);
                setQuestion();
            } else {
                won.setVisible(true);
            }
        }
        else {
            won.setText("Sorry, you got it wrong!");
            won.setForeground(Color.RED);
            won.setVisible(true);
            a1.setEnabled(false);
            a2.setEnabled(false);
            a3.setEnabled(false);
            a4.setEnabled(false);
        }
    }

    private void setQuestion() {
        question.setText(combination.getQuestion().getQuestion());
        for (int i = 0; i < combination.getAnswers().size(); i++) {
            if (combination.getAnswers().get(i).getCorrect() == 1)
                correctAnswer = i;
        }
        a1.setText(combination.getAnswers().get(0).getAnswer());
        a2.setText(combination.getAnswers().get(1).getAnswer());
        a3.setText(combination.getAnswers().get(2).getAnswer());
        a4.setText(combination.getAnswers().get(3).getAnswer());
    }


}