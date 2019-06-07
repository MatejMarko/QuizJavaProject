package screens;

import quiz.Quiz;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class WelcomeScreen extends JPanel
{
    private JButton jcomp4;
    private JButton addQuestionButton;
    private JPanel contentPane;

    public WelcomeScreen(JPanel panel, Quiz cle) {

        contentPane = panel;
        setOpaque(true);
        setBackground(Color.RED.darker().darker());
        //construct components
        jcomp4 = new JButton ("Play");
        jcomp4.addActionListener( new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.show(contentPane, "Game screen");
            }
        });
        add(jcomp4);

        addQuestionButton = new JButton("Add New Question");
        addQuestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.show(contentPane, "Add New Question");
            }
        });
        add(addQuestionButton);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return (new Dimension(500, 500));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Image backgroundImage = null;
        try {
            backgroundImage = ImageIO.read(new File("src/main/resources/images/millionaire.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(backgroundImage, 0, 0, this);
    }
}