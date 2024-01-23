package javaapplication4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**

 */
public class UnitConverter extends JFrame {
    private JComboBox<String> fromComboBox;
    private JComboBox<String> toComboBox;
    private JTextField quantityField;
    private JButton convertButton;
    private JLabel resultLabel;

    public UnitConverter() {
        setTitle("Unit Converter");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        styleComponents();
        addComponentsToFrame();

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performConversion();
            }
        });
    }

    private void initComponents() {
        String[] units = {"Feet", "Meters", "Pounds", "Kilograms", "Fahrenheit", "Celsius"};

        fromComboBox = new JComboBox<>(units);
        toComboBox = new JComboBox<>(units);
        quantityField = new JTextField(10);
        resultLabel = new JLabel("Result will be shown here");

        // Create a custom button with a dark green to gold gradient background
        convertButton = new JButton("Convert") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();
                Color color1 = new Color(0, 100, 0); // Dark Green
                Color color2 = new Color(255, 215, 0); // Gold

                GradientPaint gradient = new GradientPaint(0, 0, color1, width, height, color2);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, width, height);

                super.paintComponent(g);
            }
        };
    }

   private void styleComponents() {
    Font labelFont = new Font("Arial", Font.BOLD, 14);
    Font textFieldFont = new Font("Arial", Font.PLAIN, 12);
    Font buttonFont = new Font("Arial", Font.BOLD, 12);

    resultLabel.setForeground(Color.BLACK);
    resultLabel.setFont(labelFont);

    quantityField.setFont(textFieldFont);

    // Background color of the button
    convertButton.setBackground(new Color(0, 100, 0)); // Dark Green
    convertButton.setOpaque(true); // Ensure that the button is opaque to show the background color
    convertButton.setBorderPainted(false); // Remove border for better appearance
    convertButton.setFont(buttonFont);
    convertButton.setForeground(Color.WHITE);
}
    private void addComponentsToFrame() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Convert From:"));
        panel.add(fromComboBox);
        panel.add(new JLabel("Convert To:"));
        panel.add(toComboBox);
        panel.add(new JLabel("Enter Quantity:"));
        panel.add(quantityField);
        panel.add(new JLabel());
        panel.add(convertButton);

        JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        resultPanel.add(resultLabel);
        panel.add(resultPanel);

        add(panel);
    }

    private void performConversion() {
        String fromUnit = (String) fromComboBox.getSelectedItem();
        String toUnit = (String) toComboBox.getSelectedItem();
        double quantity;

        try { //exception for user input valid numbers only.
            quantity = Double.parseDouble(quantityField.getText());
        } catch (NumberFormatException e) {
            resultLabel.setText("Please enter a valid number");
            return;
        }
        double result = 0; // as a step index 

        if (fromUnit.equals("Feet") && toUnit.equals("Meters")) {
            result = feetToMeters(quantity);
        } else if (fromUnit.equals("Meters") && toUnit.equals("Feet")) {
            result = metersToFeet(quantity);
        } else if (fromUnit.equals("Pounds") && toUnit.equals("Kilograms")) {
            result = poundsToKilograms(quantity);
        } else if (fromUnit.equals("Kilograms") && toUnit.equals("Pounds")) {
            result = kilogramsToPounds(quantity);
        } else if (fromUnit.equals("Fahrenheit") && toUnit.equals("Celsius")) {
            result = fahrenheitToCelsius(quantity);
        } else if (fromUnit.equals("Celsius") && toUnit.equals("Fahrenheit")) {
            result = celsiusToFahrenheit(quantity);
        } else {
            resultLabel.setText("Conversion not supported");
            return;
        }
             String roundedOff = String.format("%.2f", result); //rounded to 2 decimal places 
            resultLabel.setText(quantity + " " + fromUnit + " is equal to " + roundedOff + " " + toUnit);    
    }

    private double feetToMeters(double feet) {
        return feet * 0.3048;
    }

    private double metersToFeet(double meters) {
        return meters / 0.3048;
    }

    private double poundsToKilograms(double pounds) {
        return pounds * 0.453592;
    }

    private double kilogramsToPounds(double kilograms) {
        return kilograms / 0.453592;
    }

    private double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    private double celsiusToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UnitConverter().setVisible(true);
            }
        });
    }
}
