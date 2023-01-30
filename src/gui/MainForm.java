package gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import utils.RoundedButton;
import utils.WindowUtils;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.CardLayout;
import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

public class MainForm extends JFrame {

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private ArrayList<JButton> windowButtons = new ArrayList<JButton>();
	private Integer currentWindow = 0;
	private JPanel contentPanel;
	private JPanel homePanelWindow;
	private JPanel leaguePanelWindow;
	private JPanel setupLeagueWindow;
	private JPanel settingsPanelWindow;
	JLabel lblnAppRoute;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm frame = new MainForm();
					frame.setUndecorated(true); // Disable windows controls
					WindowUtils.handleMouseDrag(frame, true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainForm() {
		setFont(new Font("Arial", Font.PLAIN, 12));
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainForm.class.getResource("/assets/painballogo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 885, 559);
//        try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) { } // https://stackoverflow.com/a/11426036/15384495
        setTitle("Paintball Fed 2023");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int x = (int) (screenSize.getWidth()/2 - 780/2); // Center x
        int y = (int) (screenSize.getHeight()/2 - 450/2); // Center y
        setBounds(x, y, 780, 450);
        setMinimumSize(new Dimension(690, 500));
        setMaximumSize(new Dimension(1920, 1080));
//        setUndecorated(true);
//		 Area shape1 = new Area(new RoundRectangle2D.Double(0, 0, 200, 252, 30, 30));
//		 setShape(shape1);
        
        
		contentPanel = new JPanel();
		contentPanel.setBackground(new Color(37, 37, 38));
		contentPanel.setForeground(new Color(45, 45, 48));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		JPanel navPanel = new JPanel();
		navPanel.setBackground(new Color(45, 45, 48));
		navPanel.setBounds(0, 0, 181, 500);
		contentPanel.add(navPanel);
		navPanel.setLayout(null);
		
//		 JButton homeButton = new RoundedButton("Home");//new JButton("Home");
//		 // homeButton.setBorder(new RoundedBorder(5));
//		 homeButton.setBounds(10, 162, 161, 30);
//		 navPanel.add(homeButton);
		 JButton homeButton = new JButton("Home");
		 homeButton.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		updateCurrentWindow(0);
		 	}
		 });
		 homeButton.setForeground(new Color(255, 255, 255));
		 homeButton.setBackground(new Color(70, 70, 75));
		 homeButton.setBounds(10, 162, 161, 30);
		 navPanel.add(homeButton);
		 windowButtons.add(homeButton);
		 
		 JButton btnLeague = new JButton("League");
		 btnLeague.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		updateCurrentWindow(1);
		 	}
		 });
		 btnLeague.setForeground(new Color(255, 255, 255));
		 btnLeague.setBackground(new Color(70, 70, 75));
		 btnLeague.setBounds(10, 202, 161, 30);
		 navPanel.add(btnLeague);
		 windowButtons.add(btnLeague);

		 
		 JButton btnSetup = new JButton("Setup");
		 btnSetup.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		updateCurrentWindow(2);
		 	}
		 });
		 btnSetup.setForeground(new Color(255, 255, 255));
		 btnSetup.setBackground(new Color(70, 70, 75));
		 btnSetup.setBounds(10, 242, 161, 30);
		 navPanel.add(btnSetup);
		windowButtons.add(btnSetup);
		
		JButton settingsButton = new JButton("Settings");
		settingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateCurrentWindow(3);
			}
		});
		settingsButton.setForeground(new Color(255, 255, 255));
		settingsButton.setBackground(new Color(70, 70, 75));
		settingsButton.setBounds(10, 460, 161, 30);
		navPanel.add(settingsButton);
		windowButtons.add(settingsButton);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(62, 62, 66));
		panel.setBounds(0, 0, 181, 104);
		navPanel.add(panel);
		panel.setLayout(null);
		
		JButton btnIconImage = new JButton("");
		btnIconImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateCurrentWindow(0);
			}
		});
		btnIconImage.setIcon(new ImageIcon(MainForm.class.getResource("/assets/painballogo.png")));
		btnIconImage.setBounds(10, 10, 161, 84);
		panel.add(btnIconImage);
		windowButtons.add(btnIconImage);
		
		JPanel controlPanel = new JPanel();
		controlPanel.setBackground(new Color(45, 45, 48));
		controlPanel.setBounds(181, 0, 600, 30);
		contentPanel.add(controlPanel);
		controlPanel.setLayout(null);
		
		JButton closeButton = new JButton("");
		closeButton.setBounds(566, 2, 24, 24);
		controlPanel.add(closeButton);
		closeButton.setBackground(new Color(45, 45, 48));
		closeButton.setIcon(new ImageIcon(MainForm.class.getResource("/assets/close-24.png")));
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		windowButtons.add(closeButton);
		
		lblnAppRoute = new JLabel("");
		lblnAppRoute.setForeground(new Color(255, 255, 255));
		lblnAppRoute.setFont(new Font("Arial", Font.BOLD, 15));
		lblnAppRoute.setBounds(10, 2, 366, 30);
		controlPanel.add(lblnAppRoute);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(191, 40, 580, 450);
		contentPanel.add(layeredPane);
		
		homePanelWindow = new JPanel();
		homePanelWindow.setBackground(new Color(45, 45, 48));
		layeredPane.setLayer(homePanelWindow, 0);
		homePanelWindow.setBounds(0, 0, 580, 450);
		layeredPane.add(homePanelWindow);
		homePanelWindow.setLayout(null);
		
		JButton btnNewButton = new JButton("Hom");
		btnNewButton.setBounds(52, 85, 85, 21);
		homePanelWindow.add(btnNewButton);
		
		leaguePanelWindow = new JPanel();
		leaguePanelWindow.setBackground(new Color(45, 45, 48));
		leaguePanelWindow.setBounds(0, 0, 580, 450);
		layeredPane.add(leaguePanelWindow);
		leaguePanelWindow.setLayout(null);
		
		setupLeagueWindow = new JPanel();
		setupLeagueWindow.setBackground(new Color(45, 45, 48));
		layeredPane.setLayer(setupLeagueWindow, 2);
		setupLeagueWindow.setBounds(0, 0, 580, 450);
		layeredPane.add(setupLeagueWindow);
		setupLeagueWindow.setLayout(null);
		
		JPanel homePanelWindow_1 = new JPanel();
		homePanelWindow_1.setLayout(null);
		homePanelWindow_1.setBackground(new Color(45, 45, 48));
		homePanelWindow_1.setBounds(0, 0, 585, 450);
		setupLeagueWindow.add(homePanelWindow_1);
		
		JButton btnNewButton_2 = new JButton("Hom");
		btnNewButton_2.setBounds(52, 85, 85, 21);
		homePanelWindow_1.add(btnNewButton_2);
		
		settingsPanelWindow = new JPanel();
		settingsPanelWindow.setBackground(new Color(45, 45, 48));
		layeredPane.setLayer(settingsPanelWindow, 3);
		settingsPanelWindow.setBounds(0, 0, 580, 450);
		layeredPane.add(settingsPanelWindow);
		settingsPanelWindow.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(35, 31, 85, 21);
		settingsPanelWindow.add(btnNewButton_1);
		
		
		WindowUtils.setWindowButtonStyle(windowButtons);
		updateCurrentWindow(currentWindow);
	}
	
	private void updateCurrentWindow(int window) {
		currentWindow = window;
		switch (window) {
		case 0:
			lblnAppRoute.setText("PaintApp \\ Home");
			homePanelWindow.setVisible(true);
			
			leaguePanelWindow.setVisible(false);
			setupLeagueWindow.setVisible(false);
			settingsPanelWindow.setVisible(false);
			break;
		case 1:
			lblnAppRoute.setText("PaintApp \\ League");
			homePanelWindow.setVisible(false);
			
			leaguePanelWindow.setVisible(true);
			
			setupLeagueWindow.setVisible(false);
			settingsPanelWindow.setVisible(false);
			break;
		case 2:
			lblnAppRoute.setText("PaintApp \\ SetUp");
			homePanelWindow.setVisible(false);
			leaguePanelWindow.setVisible(false);
			
			setupLeagueWindow.setVisible(true);
			
			settingsPanelWindow.setVisible(false);
			break;
		case 3:
			lblnAppRoute.setText("PaintApp \\ Settings");
			homePanelWindow.setVisible(false);
			leaguePanelWindow.setVisible(false);
			setupLeagueWindow.setVisible(false);
			
			settingsPanelWindow.setVisible(true);
			break;
		}
	}
}
