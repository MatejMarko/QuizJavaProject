package screens;

import quiz.Quiz;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class AddNewQuestion extends JPanel {

    public AddNewQuestion(JPanel panel, Quiz cle) {

        setLayout(new GridLayout(0, 1));
        JPanel questionPanel = new JPanel(new GridLayout(0, 1));
        JPanel answersPanel = new JPanel(new GridLayout(0, 3));
        JPanel detailsPanel = new JPanel(new GridLayout(0, 1));
        add(questionPanel);
        add(answersPanel);
        add(detailsPanel);

        JLabel q = new JLabel("Question");
        JTextField question = new JTextField();
        question.setPreferredSize(new Dimension(200, 22));
        questionPanel.add(q);
        questionPanel.add(question);

        JRadioButton first = new JRadioButton("A");
        JRadioButton second = new JRadioButton("B");
        JRadioButton third = new JRadioButton("C");
        JRadioButton fourth = new JRadioButton("D");
        ButtonGroup bg = new ButtonGroup();
        bg.add(first);
        bg.add(second);
        bg.add(third);
        bg.add(fourth);

        JLabel a = new JLabel("A");
        JTextField optionOne = new JTextField();
        optionOne.setPreferredSize(new Dimension(200, 22));
        answersPanel.add(a);
        answersPanel.add(optionOne);
        answersPanel.add(first);

        JLabel b = new JLabel("B");
        JTextField optionTwo = new JTextField();
        optionTwo.setPreferredSize(new Dimension(200, 22));
        answersPanel.add(b);
        answersPanel.add(optionTwo);
        answersPanel.add(second);

        JLabel c = new JLabel("C");
        JTextField optionThree = new JTextField();
        optionThree.setPreferredSize(new Dimension(200, 22));
        answersPanel.add(c);
        answersPanel.add(optionThree);
        answersPanel.add(third);

        JLabel d = new JLabel("D");
        JTextField optionFour = new JTextField();
        optionFour.setPreferredSize(new Dimension(200, 22));
        answersPanel.add(d);
        answersPanel.add(optionFour);
        answersPanel.add(fourth);

        JLabel t = new JLabel("Topic");
        JTextField topic = new JTextField();
        detailsPanel.add(t);
        detailsPanel.add(topic);

        JLabel difficulty = new JLabel("Difficulty");
        JComboBox difficultyComboBox;
        String[] difficulties = new String[15];
        for (int i = 0; i < 15; i++) {
            difficulties[i] = (i+1) + "";
        }
        difficultyComboBox = new JComboBox(difficulties);
        detailsPanel.add(difficulty);
        detailsPanel.add(difficultyComboBox);
        //setSize(300, 300);
        //f.setLayout(null);
        setVisible(true);
    }


}