package lesson10.task01;

/*
Write an application displaying a GUI with a text area and two buttons — "Back"
and "Show words". The user enters an arbitrary text
and after clicking the "Show words" button a list of all words from the text is displayed
in the text area; each word in lower case and once only
Clicking now the "Back" button restores the original text in the text area, for further
editing.
1
Note: when the text area displays the text to be edited, "Back" button should be
disabled while when the area displays the list of words, "Show words" button shouldn’t
be active (button.setEnabled(true/false)).
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MyFrame::new);
    }
}

class MyFrame extends JFrame {

    private String currentText;
    MyFrame() {
        super("WORDS");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTextArea mainText = new JTextArea("", 20, 30);
        JScrollPane scroll = new JScrollPane(mainText);
        JButton back = new JButton("Back");
        JButton showWords = new JButton("Show words");
        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        buttons.add(showWords);
        buttons.add(back);
        add(buttons, BorderLayout.SOUTH);

        showWords.setEnabled(true);
        back.setEnabled(false);
        back.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainText.setText(currentText);
                showWords.setEnabled(true);
                back.setEnabled(false);
            }
        });

        showWords.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentText = mainText.getText();
                String processedText = process(currentText);
                mainText.setText(processedText);
                showWords.setEnabled(false);
                back.setEnabled(true);
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private String process(String text) {
        return text.replaceAll("\\P{L}+", "\n");
    }
}