package ru.nicetu.messagerecorder.app;

import ru.nicetu.messagerecorder.logging.LogSystem;
import ru.nicetu.messagerecorder.logging.Messenger;
import ru.nicetu.messagerecorder.logging.SourceType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by trusov on 21.03.2015.
 */
public final class AppMain {

    private AppMain() {
        throw new UnsupportedOperationException();
    }


    public static void main(String[] args) {
        final LogSystem system = LogSystem.getInstance();
        system.init();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                system.stop();
            }
        }));

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                final JFrame frame = new JFrame();
                frame.setLayout(new BorderLayout());

                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(JOptionPane.getRootFrame());
                frame.setTitle("Messenger test");
                final JTextField textField = new JTextField(50);
                JButton button = new JButton("Send text");
                button.addActionListener(new AbstractAction() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String text = textField.getText().trim();
                        if (text.isEmpty()) {
                            JOptionPane.showMessageDialog(frame,
                                    "You should enter some text", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        Messenger.recordMessage(text, SourceType.USER);
                        JOptionPane.showMessageDialog(frame,
                                "Thanks, your text is sent to Messenger",
                                "Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                });

                JLabel label = new JLabel("Please, enter some text:");
                final JPanel contentPanel = new JPanel(new BorderLayout());
                contentPanel.add(label, BorderLayout.NORTH);
                contentPanel.add(textField, BorderLayout.SOUTH);
                frame.add(contentPanel, BorderLayout.NORTH);
                frame.add(button, BorderLayout.SOUTH);
                frame.setSize(300, 300);

                frame.setVisible(true);
            }
        });
    }
}
