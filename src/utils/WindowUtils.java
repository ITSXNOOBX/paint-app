package utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class WindowUtils extends JFrame  {
    private static int mouseX, mouseY;
	
    public static void dlmToComboBoxAdd(DefaultListModel<?> dlm, JComboBox<Object> combo) {   
    	combo.removeAllItems(); // Easyer to clean and set again :D
    	for (int i = 0; i < dlm.getSize(); i++) {
            combo.addItem(dlm.getElementAt(i));
        }
    }
    
	public static void handleMouseDrag(JFrame frame, Boolean enable) {
		if (!enable) return;
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
	}
	
   public static void setWindowButtonStyle(ArrayList<JButton> list) {
        for (JButton button : list) {
                button.setBorderPainted(false);
        		button.setFocusPainted(false);
        		if (button.getText().isBlank()) button.setContentAreaFilled(false);
        		Rectangle b = button.getBounds();
        		Color bg = new Color(button.getColorModel().getRGB(ALLBITS));
        		button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                    	button.setBounds(b.x -1, b.y-1, b.width, b.height);
//                    	if (!button.getText().isBlank()) {
//                    		button.setBackground(new Color(bg.getRed() + 5, bg.getGreen() + 5, bg.getBlue() + 5));
//                    	}
                    }
                    public void mouseExited(MouseEvent e) {
                    	button.setBounds(b.x, b.y, b.width, b.height);
//                    	if (!button.getText().isBlank()) {
//                    		button.setBackground(bg);
//                    	}
                    }
                });
                // System.out.println("Button: " + button.getText() + " x: " +  b.x + " y: " + b.y);
        }
    }
}
