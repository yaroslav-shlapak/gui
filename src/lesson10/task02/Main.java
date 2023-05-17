package lesson10.task02;

/*
Write a program which displays a GUI with
• a text area with the content of a text file;
• a panel with two buttons: pressing one of them closes the application, pressing
the other displays a dialog of type JFileChooser allowing the user to select
a file — only files with extension txt (or, e.g., java) should appear;
• a label with the name of the file currently shown in the text area.
Selecting a file will look like this:
and after a file has been selected, we should see its content:
2
Note: You can force the text area to scroll to the bottom after a call to append like
this:
textArea.setCaretPosition(textArea.getDocument().getLength());
 */

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MyFrame::new);
    }
}

class MyFrame extends JFrame {
    MyFrame() {
        super("WORDS");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTextArea mainText = new JTextArea("", 20, 30);
        JScrollPane scroll = new JScrollPane(mainText);
        JButton exit = new JButton("Exit");
        JButton selectFile = new JButton("Select file");

        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        buttons.add(selectFile);
        buttons.add(exit);
        add(buttons, BorderLayout.SOUTH);

        exit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainText.setText("");
                MyFrame.this.setTitle("");
            }
        });

        selectFile.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new FileNameExtensionFilter("text files", "txt", "java"));
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    MyFrame.this.setTitle(selectedFile.getName());
                    try {
                        String content = Files.readString(selectedFile.toPath());
                        mainText.setText(content);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
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