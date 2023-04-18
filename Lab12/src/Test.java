import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class Test extends JFrame {
    private ArrayList<Integer> scores;
    private int currentQuestion;
    private JLabel questionLabel;
    private ButtonGroup buttonGroup;
    private JRadioButton[] radioButtons;
    private JButton nextButton;
    private JTextArea resultArea;

    private String[] questions = {
            "Яка столиця Франції?",
            "Яка найбільша планета Сонячної системи?",
            "Яка найменша країна в світі?",
            "Яка найвища гора в світі?"
    };

    private String[][] options = {
            {"Париж", "Лондон", "Берлін", "Мадрид"},
            {"Юпітер", "Сатурн", "Марс", "Земля"},
            {"Ватикан", "Монако", "Науру", "Мальдіви"},
            {"Гора Еверест", "К2", "Деналі", "Кіліманджаро"}
    };

    private int[] answers = {0, 0, 0, 0};

    public Test() {
        super("Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        scores = new ArrayList<Integer>();
        currentQuestion = 0;

        questionLabel = new JLabel(questions[currentQuestion]);
        add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        buttonGroup = new ButtonGroup();
        radioButtons = new JRadioButton[4];
        for (int i = 0; i < 4; i++) {
            radioButtons[i] = new JRadioButton(options[currentQuestion][i]);
            radioButtons[i].setActionCommand(Integer.toString(i));
            buttonGroup.add(radioButtons[i]);
            optionsPanel.add(radioButtons[i]);
        }
        add(optionsPanel, BorderLayout.CENTER);

        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selected = Integer.parseInt(buttonGroup.getSelection().getActionCommand());
                if (selected == answers[currentQuestion]) {
                    scores.add(5);
                } else {
                    scores.add(2);
                }
                currentQuestion++;
                if (currentQuestion == questions.length) {
                    int totalScore = 0;
                    for (int score : scores) {
                        totalScore += score;
                    }
                    double averageScore = (double) totalScore / scores.size();
                    resultArea.setText(String.format("Ваш середній бал %.1f", averageScore));
                    nextButton.setEnabled(false);
                } else {
                    questionLabel.setText(questions[currentQuestion]);
                    for (int i = 0; i < 4; i++) {
                        radioButtons[i].setText(options[currentQuestion][i]);
                    }
                    buttonGroup.clearSelection();
                }
            }
        });
        add(nextButton, BorderLayout.SOUTH);

        resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.EAST);
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.setVisible(true);
    }
}
