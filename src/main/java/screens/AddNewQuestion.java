package screens;

import events.QuestionSubmitHandler;
import org.apache.log4j.Logger;
import quiz.Quiz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

public class AddNewQuestion extends JPanel {

    final static Logger logger = Logger.getLogger(AddNewQuestion.class);

    private JTextArea question = null;
    private ButtonGroup correctAnswerGroup = null;
    private JTextField optionOne = null;
    private JTextField optionTwo = null;
    private JTextField optionThree = null;
    private JTextField optionFour = null;
    private JTextField topic = null;
    private JComboBox difficultyComboBox;
    private int correctAnswerNumber = -1;
    private int difficultyValue = -1;

    public AddNewQuestion(JPanel panel, Quiz cle) {

        Font font = new Font("My font", 0, 16);

        setLayout(new GridLayout(0, 1));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        JPanel questionPanel = new JPanel(new GridLayout(0, 1));
        JPanel answersPanel = new JPanel(new GridLayout(0, 2));
        JPanel detailsPanel = new JPanel(new GridLayout(0, 2));
        add(questionPanel);
        add(answersPanel);
        add(detailsPanel);
        JPanel panel_a = new JPanel();
        JPanel panel_b = new JPanel();
        JPanel panel_c = new JPanel();
        JPanel panel_d = new JPanel();
        answersPanel.add(panel_a);
        answersPanel.add(panel_b);
        answersPanel.add(panel_c);
        answersPanel.add(panel_d);
        JPanel panel_topic = new JPanel(new GridLayout(0, 1));
        JPanel panel_difficulty = new JPanel(new GridLayout(0, 1));
        detailsPanel.add(panel_topic);
        detailsPanel.add(panel_difficulty);


        JPanel filler = new JPanel(new CardLayout());
        add(filler);
        filler.setBorder(new EmptyBorder(25, 0, 25, 0));

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) panel.getLayout();
                cardLayout.show(panel, "Welcome screen");
            }
        });
        questionPanel.add(backButton);


        JLabel questionLabel = new JLabel("Question");
        question = new JTextArea();
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setFont(font);
        question.setPreferredSize(new Dimension(200, 22));
        questionPanel.add(questionLabel);
        questionPanel.add(question);
        JLabel answerLabel = new JLabel("Answers");
        questionPanel.add(answerLabel);

        JRadioButton first = new JRadioButton("A");
        JRadioButton second = new JRadioButton("B");
        JRadioButton third = new JRadioButton("C");
        JRadioButton fourth = new JRadioButton("D");
        correctAnswerGroup = new ButtonGroup();
        correctAnswerGroup.add(first);
        correctAnswerGroup.add(second);
        correctAnswerGroup.add(third);
        correctAnswerGroup.add(fourth);

        optionOne = new JTextField();
        optionOne.setPreferredSize(new Dimension(300, 32));
        panel_a.add(first);
        panel_a.add(optionOne);

        optionTwo = new JTextField();
        optionTwo.setPreferredSize(new Dimension(300, 32));
        panel_b.add(second);
        panel_b.add(optionTwo);

        optionThree = new JTextField();
        optionThree.setPreferredSize(new Dimension(300, 32));
        panel_c.add(third);
        panel_c.add(optionThree);

        optionFour = new JTextField();
        optionFour.setPreferredSize(new Dimension(300, 32));
        panel_d.add(fourth);
        panel_d.add(optionFour);

        JLabel t = new JLabel("Topic");
        topic = new JTextField();
        panel_topic.add(t);
        panel_topic.add(topic);

        JLabel difficulty = new JLabel("Difficulty");
        String[] difficulties = new String[15];
        for (int i = 0; i < 15; i++) {
            difficulties[i] = (i + 1) + "";
        }
        difficultyComboBox = new JComboBox(difficulties);
        panel_difficulty.add(difficulty);
        panel_difficulty.add(difficultyComboBox);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean valid = checkIfValid();
                if (valid) {
                    QuestionSubmitHandler sqh = new QuestionSubmitHandler(question.getText(), correctAnswerNumber,
                            optionOne.getText(), optionTwo.getText(), optionThree.getText(), optionFour.getText(),
                            topic.getText(), Integer.parseInt(difficultyComboBox.getSelectedItem().toString()));
                    sqh.tryToInsert();

                }
            }
        });
        filler.add(submitButton);
        setVisible(true);
    }

    private boolean checkIfValid() {
        boolean valid = true;
        valid = correctAnswerSelected() != -1 && requiredFieldsFilled();
        return valid;
    }

    private int correctAnswerSelected() {
        for (Enumeration<AbstractButton> buttons = correctAnswerGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                int correctAnswer = -1;
                switch (button.getText().charAt(0)) {
                    case 'A':
                        correctAnswer = 0;
                        break;
                    case 'B':
                        correctAnswer = 1;
                        break;
                    case 'C':
                        correctAnswer = 2;
                        break;
                    case 'D':
                        correctAnswer = 3;
                        break;
                    default:
                        logger.warn("Correct answer not selected");
                        break;
                }
                difficultyValue =
                correctAnswerNumber = correctAnswer;
                return correctAnswer;
            }
        }
        return -1;
    }

    private boolean requiredFieldsFilled() {
        if ((question.getText() == null || question.getText().equals("")) ||
                (optionOne.getText() == null || optionOne.getText().equals("")) ||
                (optionTwo.getText() == null || optionTwo.getText().equals("")) ||
                (optionThree.getText() == null || optionThree.getText().equals("")) ||
                (optionFour.getText() == null || optionFour.getText().equals("")) ||
                (topic.getText() == null || topic.getText().equals(""))) {
            logger.warn("Not all required fields are filled");
            return false;
        }
        return true;
    }


}