import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FunctionTable extends JFrame {
    private JTextField aField;
    private JTextField xStartField;
    private JTextField xEndField;
    private JTextField stepField;
    private JTextArea resultArea;

    FunctionTable() {
        super("Таблиця функції");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        setSize(400, 400);
        Container c = getContentPane();
        JPanel pane = new JPanel(new GridLayout(5, 2, 5, 5));
        c.add(pane, BorderLayout.NORTH);

        // Додавання компонентів на верхню панель
        pane.add(new JLabel("A: "));
        aField = new JTextField("1");
        pane.add(aField);
        pane.add(new JLabel("X початкове: "));
        xStartField = new JTextField("0");
        pane.add(xStartField);
        pane.add(new JLabel("X кінцеве: "));
        xEndField = new JTextField("10");
        pane.add(xEndField);
        pane.add(new JLabel("Крок: "));
        stepField = new JTextField("1");
        pane.add(stepField);
        JButton computeButton = new JButton("Обчислити");
        pane.add(computeButton);

        // Додавання поля JTextArea та полоси прокрутки JScrollPane
        resultArea = new JTextArea(15, 40);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        c.add(scrollPane, BorderLayout.CENTER);

        // Додавання обробників подій
        computeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                computeTable();
            }
        });

        WindowListener wndCloser = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        addWindowListener(wndCloser);
        setVisible(true);
    }

    public void computeTable() {
        try {
            double a = Double.parseDouble(aField.getText());
            double xStart = Double.parseDouble(xStartField.getText());
            double xEnd = Double.parseDouble(xEndField.getText());
            double step = Double.parseDouble(stepField.getText());

            resultArea.setText(""); // Очищення поля JTextArea

            // Виведення заголовка таблиці
            resultArea.append(String.format("%-10s%-10s\n", "X", "Y"));

            // Обчислення та виведення значень функції
            for (double x = xStart; x <= xEnd; x += step) {
                double y = a * Math.sqrt(x) * Math.sin(a * x);
                resultArea.append(String.format("%-10.2f%-10.2f\n", x, y));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Неправильний формат числа", "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new FunctionTable();
    }
}
