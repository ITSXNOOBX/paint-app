package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainForm extends JFrame {
    private static int mouseX;
    private static int mouseY;
    private JPanel contentWrapper;
    private JPanel contentPanel;
    private JPanel navPanel;
    private JPanel iconPane;
    private JButton settingsButton;
    private JButton button2;
    private JButton homeButton;
    private JLabel replaceWithImage;
    private JButton closeButton;

    public MainForm() {
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        loadStyling();
    }

    public void iteratePanels(Container container) {
        for (Component c : container.getComponents()) {
            if (c instanceof JPanel) {
                JPanel panel = (JPanel) c;
                for (Component b : panel.getComponents()) {
                    if (b instanceof JButton) {
                        JButton button = (JButton) b;
                        // Perform action on button
                        button.setBorderPainted(false);
                        button.setFocusPainted(false);
                        button.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseEntered(MouseEvent e) {
                                if (button.getText().isBlank()) return;
                                button.setBackground(new Color(0,142,244));
                            }
                            public void mouseExited(MouseEvent e) {
                                if (button.getText().isBlank()) return;
                                button.setBackground(new Color(0,122,204));
                            }
                        });
                    }
                }
                iteratePanels(panel);
            }
        }
    }

    private void loadStyling() {
        iteratePanels(contentWrapper);
    }

    /**
     * * Main() deitutako lehenengo funtzioa
     * * Egin pantaila berri bat eta erroreak ematekotan arrapatu errorea
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            JFrame frame = new JFrame("MainForm");
            MainForm gui = new MainForm();
            //frame.setUndecorated(true);
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // https://stackoverflow.com/a/11426036/15384495
            frame.setTitle("Paintball Fed 2023");
//          ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("resources/logo.png"));
//          frame.setIconImage(img.getImage());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setBounds(100, 100, 780, 450);
            frame.setMinimumSize(new Dimension(690, 500));
            frame.setMaximumSize(new Dimension(1920, 1080));
            frame.setContentPane(gui.contentWrapper);
            frame.pack();
            frame.setVisible(true);

            // Handle mouse drag
            frame.addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    frame.setLocation(frame.getX() + e.getX() - mouseX, frame.getY() + e.getY() - mouseY);
                }
            });
            frame.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    mouseX = e.getX();
                    mouseY = e.getY();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
