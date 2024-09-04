import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Calculator implements ActionListener {

    JFrame frame = new JFrame("Swing Calculator");
    JPanel firstView = new JPanel(new BorderLayout());
    JTextField textField = new JTextField();
    JPanel secondView = new JPanel(new GridLayout(5, 4, 10, 10)); 
    
    private String fullString = "";
    public Calculator() {
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);

        textField.setPreferredSize(new Dimension(350, 100));
        textField.setFont(new Font("Arial", Font.BOLD, 24));
        textField.setBackground(Color.BLACK);
        textField.setForeground(Color.WHITE);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        
        firstView.setBackground(Color.BLACK);
        firstView.add(textField, BorderLayout.CENTER);

        String[] buttons = {
            "AC", "%", "Del", "\u00F7", 
            "7", "8", "9", "\u00D7", 
            "4", "5", "6", "-",
            "1", "2", "3", "+",
            "00", "0", ".", "="
        };

        Dimension buttonSize = new Dimension(60, 60); 

        for (String text : buttons) {
            JButton button = createRoundButton(text);
            button.setPreferredSize(buttonSize); 

            if (text.equals("AC") || text.equals("%") || text.equals("Del") || text.equals("\u00F7") ||
                    text.equals("\u00D7") || text.equals("-") || text.equals("+") || text.equals("=")) {
                Color customDarkGrey = new Color(25, 25, 25);
                button.setBackground(customDarkGrey);
            } else {
                Color customDark = new Color(43, 43, 43);
                button.setBackground(customDark);
            }

            if (text.equals("=")) {
                Color customPink = new Color(243, 1, 93); 
                button.setBackground(customPink);
            }

            secondView.add(button);
            button.addActionListener(this);
        }

        secondView.setBackground(Color.BLACK);
        secondView.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        frame.add(firstView, BorderLayout.NORTH);
        frame.add(secondView, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public boolean isInArray(String text, String[] array) {
        for (String item : array) {
            if (item.equals(text)) {
                return true;
            }
        }
        return false;
    }

    public JButton createRoundButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                int size = Math.min(getWidth(), getHeight());
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillOval((getWidth() - size) / 2, (getHeight() - size) / 2, size, size);
                g2.setColor(getForeground());
                FontMetrics fm = g2.getFontMetrics();
                g2.drawString(getText(), (getWidth() - fm.stringWidth(getText())) / 2, 
                                 (getHeight() + fm.getAscent()) / 2 - 3);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        Color customWhite = new Color(249, 249, 249);
        button.setForeground(customWhite); 
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String getText = ((JButton) e.getSource()).getText();

        if (getText.equals("AC")) {
            fullString = "";
            textField.setText("");
            return;
        }
        if (getText.equals("Del")) {
            if (fullString.length() > 0) {
                fullString = fullString.substring(0, fullString.length() - 1);
                textField.setText(fullString);
            }
            return;
        }

        fullString += getText;
        textField.setText(fullString);

        if (fullString.endsWith("=")) {
            String expression = fullString.substring(0, fullString.length() - 1); 
            String findOperators = "(\\d+(\\.\\d+)?)([\\+\\-×÷%])(\\d+(\\.\\d+)?)";
            Pattern pattern = Pattern.compile(findOperators);
            Matcher matcher = pattern.matcher(expression);

            if (matcher.find()) {
                double operand1 = Double.parseDouble(matcher.group(1));
                String operator = matcher.group(3); 
                double operand2 = Double.parseDouble(matcher.group(4));
                double result = 0;

                switch (operator) {
                    case "+":
                        result = operand1 + operand2;
                        break;
                    case "-":
                        result = operand1 - operand2;
                        break;
                    case "×": // Multiplication sign
                        result = operand1 * operand2;
                        break;
                    case "÷": // Division sign
                        if (operand2 != 0) {
                            result = operand1 / operand2;
                        } else {
                            JOptionPane.showMessageDialog(null, "Cannot divide by zero", "Error", JOptionPane.ERROR_MESSAGE);
                            result = 0;
                        }
                        break;
                    case "%" :
                        result = operand1 * operand2 / 100;
                        break;
                }

                textField.setText(String.valueOf(result));
                fullString = String.valueOf(result); 
            } else {
                JOptionPane.showMessageDialog(null, "Invalid expression", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
