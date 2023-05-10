package lesson09.task01;

/*
Write a program displaying user interfaces shown below. Use various layout managers
(BorderLayout, FlowLayout, GridLayout,) and objects of class JPanel for grouping
elements of the GUI. The interfaces needn’t have any particular functionality. The
GUIs should behave sensibly when main windows are resized.
 */

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MyFrame::new);
    }
}

class ButtonsGrid extends JPanel {
    ButtonsGrid() {
        setLayout(new java.awt.GridLayout(3, 3, 10, 10));
        for (int i = 1; i <= 9; ++i)
            add(new JButton("B0" + i));
    }
}

class ButtonsAndText extends JPanel {
    ButtonsAndText() {
        setLayout(new BorderLayout());
        add(new ButtonsGrid(), BorderLayout.EAST);
        add(new TextField("Text area 1"), BorderLayout.WEST);
    }
}

class MyFrame extends JFrame {
    MyFrame() {
        super("Buttons and text");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new TextField("Text 1"));
        add(new TextField("Text 2"));
        add(new ButtonsAndText());
        add(new TextField("Text 3"));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}