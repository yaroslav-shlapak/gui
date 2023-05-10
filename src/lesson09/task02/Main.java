package lesson09.task02;

/*
Write an application displaying a GUI with two buttons and one text field.
Expected functionality:
• Clicking the left button copies the text from this button to the text field;
• Pressing ENTER in the text field copies its text onto the left button;
• Clicking the right button changes the title of the window to the text from the
text field.
The listener of the right button should be an object of a separate class, while listeners
of the left button and the text field should be implemented using lambdas.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TitleChanger::new);
    }
}

class TitleChanger extends JFrame {
    TitleChanger() {
        super("TitleChanger");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        JButton city = new JButton("Warsaw");
        JButton changeCity = new JButton("Change title...");
        JTextField newCity = new JTextField(null, "Paris", 10);
        add(city);
        add(changeCity);
        add(newCity);
        changeCity.addActionListener(getL(city, newCity));
        newCity.addActionListener(getL(city, newCity));
        city.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newCity.setText(city.getText());
            }
        });
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private static AbstractAction getL(JButton city, JTextField newCity) {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                city.setText(newCity.getText());
            }
        };
    }
}