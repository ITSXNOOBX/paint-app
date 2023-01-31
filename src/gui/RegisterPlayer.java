package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.WindowUtils;

public class RegisterPlayer extends JFrame {

	static RegisterPlayer frame;
	private ArrayList<JButton> windowButtons = new ArrayList<JButton>();
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new RegisterPlayer();
					frame.setUndecorated(true); // Disable windows controlls
					WindowUtils.handleMouseDrag(frame, true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void close() {
		if (frame == null) return;
		WindowUtils.handleMouseDrag(frame, false);
		frame.setVisible(false);
		frame.dispose();
	}

	/**
	 * Create the frame.
	 */
	public RegisterPlayer() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainForm.class.getResource("/assets/painballogo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 885, 559);
//        try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) { } // https://stackoverflow.com/a/11426036/15384495
        setTitle("Paintball Fed 2023 | Register Team");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 780, 450);
        setMinimumSize(new Dimension(690, 500));
        setMaximumSize(new Dimension(1920, 1080));
        
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		
		WindowUtils.setWindowButtonStyle(windowButtons);
	}

}
