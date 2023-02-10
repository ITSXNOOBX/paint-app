package gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import utils.DataUtils;
import utils.FileUtils;
import utils.NotifyUtils;
import utils.RoundedButton;
import utils.WindowUtils;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.CardLayout;
import javax.swing.JTabbedPane;
import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionListener;

import matches.match;
import matches.score;
import person.coach;
import person.player;
import teams.classification;
import teams.team;

import javax.swing.event.ListSelectionEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ListSelectionModel;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import javax.swing.JScrollBar;

public class MainForm extends JFrame {

	public static Boolean should_not_save = false;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static MainForm frame;
	private ArrayList<JButton> windowButtons = new ArrayList<JButton>();
	private Integer currentWindow = 0;
	private JPanel contentPanel;
	private JPanel homePanelWindow;
	private JPanel leaguePanelWindow;
	private JPanel setupLeagueWindow;
	private JPanel settingsPanelWindow;
	JLabel lblnAppRoute;
	private JTextField homeCoachTextfield;
	private JTextField homePointsTextfield;
	private JTextField homeClasificationTextfield;
	private JTextField homePlayerNameTextbox;
	private JTextField homePPointsTextbox;
	private JTextField homeHeadlineTexfield;
	private JTextField homeAgeTextbox;

	private static DefaultListModel<team> homeTeamData = new DefaultListModel<team>();
	private DefaultListModel<player> homePlayerData = new DefaultListModel<player>();
	private DefaultListModel<String> homeEmpty = new DefaultListModel<String>();

	JList homePlayerList;
	JList homeTeamList;

	static JComboBox<Object> setupSelectTeam;
	static JComboBox<Object> setupSelectPlayer;
	static JLabel setupSelectCoach;
	static JButton setupDeleteTeam;
	static JButton setupCreatePlayer;
	static JButton setupDeletePlayer;
	static JButton setupCreateCoach;
	static JButton setupDeleteCoach;
	static JTextField leagueLocalPlayerPoints;
	static JTextField leagueOutsiderPlayerPoints;

	static JComboBox<Object> leagueLocalTeamList;
	static JComboBox<Object> leagueOutsiderTeamList;
	static JPanel leagueClassification;
	static JTabbedPane tabbedPane;
	static DefaultListModel<player> leagueLocalPlayerListData = new DefaultListModel<player>();
	static DefaultListModel<player> leagueOutsiderPlayerListData = new DefaultListModel<player>();
	static DefaultListModel<score> leagueMatchLogData = new DefaultListModel<score>();
	
	static JButton leagueLocalSetPlayerPoints;
	static JButton leagueOutsiderSetPlayerPoints;
	static JList leagueOutsiderPlayerList;
	static JList leagueLocalPlayerList;
	static JButton leagueSaveMatch;
	static JButton leagueCancelMatch;
	
	static DefaultListModel<classification> leagueTeamClassificationData = new DefaultListModel<classification>();
	static DefaultListModel<match> leagueMatchListData = new DefaultListModel<match>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainForm();
					frame.setUndecorated(true); // Disable windows controls
					WindowUtils.handleMouseDrag(frame, true);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void close() {
		if (frame == null)
			return;
		WindowUtils.handleMouseDrag(frame, false);
		frame.setVisible(false);
		frame.dispose();
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public MainForm() {
		setFont(new Font("Arial", Font.BOLD, 12));
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainForm.class.getResource("/assets/painballogo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 885, 559);
//        try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) { } // https://stackoverflow.com/a/11426036/15384495
		setTitle("Paintball Fed 2023");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int x = (int) (screenSize.getWidth() / 2 - 780 / 2); // Center x
		int y = (int) (screenSize.getHeight() / 2 - 450 / 2); // Center y
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

		homeTeamList = new JList();
		homeTeamList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		homeTeamList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				homeDisplayData(true);
			}
		});
		homeTeamList.setBounds(10, 119, 180, 321);
		homePanelWindow.add(homeTeamList);
		homeTeamList.setModel(homeTeamData);

		homePlayerList = new JList();
		homePlayerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		homePlayerList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				homeDisplayData(false);
			}
		});
		homePlayerList.setBounds(200, 119, 160, 321);
		homePanelWindow.add(homePlayerList);
		homePlayerList.setModel(homePlayerData);

		homeCoachTextfield = new JTextField();
		homeCoachTextfield.setEditable(false);
		homeCoachTextfield.setBounds(465, 147, 100, 20);
		homePanelWindow.add(homeCoachTextfield);
		homeCoachTextfield.setColumns(10);

		JLabel lblTeamSeparator = new JLabel("Teams");
		lblTeamSeparator.setForeground(new Color(255, 255, 255));
		lblTeamSeparator.setFont(new Font("Arial", Font.BOLD, 15));
		lblTeamSeparator.setBounds(10, 96, 174, 13);
		homePanelWindow.add(lblTeamSeparator);

		JLabel lblPlayerSeparator = new JLabel("Players");
		lblPlayerSeparator.setForeground(Color.WHITE);
		lblPlayerSeparator.setFont(new Font("Arial", Font.BOLD, 15));
		lblPlayerSeparator.setBounds(200, 96, 160, 13);
		homePanelWindow.add(lblPlayerSeparator);

		JSeparator teamSeparator = new JSeparator();
		teamSeparator.setForeground(new Color(255, 255, 255));
		teamSeparator.setBounds(10, 113, 180, 1);
		homePanelWindow.add(teamSeparator);

		JSeparator playerSeparator = new JSeparator();
		playerSeparator.setForeground(Color.WHITE);
		playerSeparator.setBounds(200, 113, 160, 1);
		homePanelWindow.add(playerSeparator);

		JLabel lblCoach = new JLabel("Coach:");
		lblCoach.setForeground(Color.WHITE);
		lblCoach.setFont(new Font("Arial", Font.BOLD, 13));
		lblCoach.setBounds(370, 146, 83, 20);
		homePanelWindow.add(lblCoach);

		homePointsTextfield = new JTextField();
		homePointsTextfield.setEditable(false);
		homePointsTextfield.setColumns(10);
		homePointsTextfield.setBounds(465, 177, 50, 20);
		homePanelWindow.add(homePointsTextfield);

		JLabel homePointsLbl = new JLabel("Points:");
		homePointsLbl.setForeground(Color.WHITE);
		homePointsLbl.setFont(new Font("Arial", Font.BOLD, 13));
		homePointsLbl.setBounds(370, 177, 83, 20);
		homePanelWindow.add(homePointsLbl);

		homeClasificationTextfield = new JTextField();
		homeClasificationTextfield.setEditable(false);
		homeClasificationTextfield.setColumns(10);
		homeClasificationTextfield.setBounds(465, 209, 50, 20);
		homePanelWindow.add(homeClasificationTextfield);

		JLabel homeClassificationLbl = new JLabel("Classification:");
		homeClassificationLbl.setForeground(Color.WHITE);
		homeClassificationLbl.setFont(new Font("Arial", Font.BOLD, 13));
		homeClassificationLbl.setBounds(370, 208, 96, 20);
		homePanelWindow.add(homeClassificationLbl);

		JLabel lblTeamData = new JLabel("Team Data");
		lblTeamData.setForeground(Color.WHITE);
		lblTeamData.setFont(new Font("Arial", Font.BOLD, 15));
		lblTeamData.setBounds(371, 119, 174, 13);
		homePanelWindow.add(lblTeamData);

		JSeparator teamDataSeparator = new JSeparator();
		teamDataSeparator.setForeground(Color.WHITE);
		teamDataSeparator.setBounds(371, 136, 180, 1);
		homePanelWindow.add(teamDataSeparator);

		homePlayerNameTextbox = new JTextField();
		homePlayerNameTextbox.setEditable(false);
		homePlayerNameTextbox.setColumns(10);
		homePlayerNameTextbox.setBounds(465, 267, 100, 20);
		homePanelWindow.add(homePlayerNameTextbox);

		JLabel homePlayerNameLbl = new JLabel("Name:");
		homePlayerNameLbl.setForeground(Color.WHITE);
		homePlayerNameLbl.setFont(new Font("Arial", Font.BOLD, 13));
		homePlayerNameLbl.setBounds(370, 266, 83, 20);
		homePanelWindow.add(homePlayerNameLbl);

		homePPointsTextbox = new JTextField();
		homePPointsTextbox.setEditable(false);
		homePPointsTextbox.setColumns(10);
		homePPointsTextbox.setBounds(465, 328, 50, 20);
		homePanelWindow.add(homePPointsTextbox);

		JLabel homePointsLbl_1 = new JLabel("Points:");
		homePointsLbl_1.setForeground(Color.WHITE);
		homePointsLbl_1.setFont(new Font("Arial", Font.BOLD, 13));
		homePointsLbl_1.setBounds(370, 328, 83, 20);
		homePanelWindow.add(homePointsLbl_1);

		homeHeadlineTexfield = new JTextField();
		homeHeadlineTexfield.setEditable(false);
		homeHeadlineTexfield.setColumns(10);
		homeHeadlineTexfield.setBounds(465, 358, 50, 20);
		homePanelWindow.add(homeHeadlineTexfield);

		JLabel homeHeadlineLbl = new JLabel("Headline:");
		homeHeadlineLbl.setForeground(Color.WHITE);
		homeHeadlineLbl.setFont(new Font("Arial", Font.BOLD, 13));
		homeHeadlineLbl.setBounds(370, 357, 96, 20);
		homePanelWindow.add(homeHeadlineLbl);

		JLabel homePlayerDataLbl = new JLabel("Player Data");
		homePlayerDataLbl.setForeground(Color.WHITE);
		homePlayerDataLbl.setFont(new Font("Arial", Font.BOLD, 15));
		homePlayerDataLbl.setBounds(371, 239, 174, 13);
		homePanelWindow.add(homePlayerDataLbl);

		JSeparator homePlayerDataSeparator = new JSeparator();
		homePlayerDataSeparator.setForeground(Color.WHITE);
		homePlayerDataSeparator.setBounds(371, 256, 180, 1);
		homePanelWindow.add(homePlayerDataSeparator);

		JLabel homeAgeLbl = new JLabel("Age:");
		homeAgeLbl.setForeground(Color.WHITE);
		homeAgeLbl.setFont(new Font("Arial", Font.BOLD, 13));
		homeAgeLbl.setBounds(370, 297, 96, 20);
		homePanelWindow.add(homeAgeLbl);

		homeAgeTextbox = new JTextField();
		homeAgeTextbox.setEditable(false);
		homeAgeTextbox.setColumns(10);
		homeAgeTextbox.setBounds(465, 298, 50, 20);
		homePanelWindow.add(homeAgeTextbox);

		JSeparator homeSeparator = new JSeparator();
		homeSeparator.setForeground(Color.WHITE);
		homeSeparator.setBounds(20, 60, 540, 1);
		homePanelWindow.add(homeSeparator);

		JLabel lblNewLabel = new JLabel("Welcome back");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(178, 10, 198, 40);
		homePanelWindow.add(lblNewLabel);

		leaguePanelWindow = new JPanel();
		layeredPane.setLayer(leaguePanelWindow, 4);
		leaguePanelWindow.setBackground(new Color(45, 45, 48));
		leaguePanelWindow.setBounds(0, 0, 580, 450);
		layeredPane.add(leaguePanelWindow);
		leaguePanelWindow.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		tabbedPane.setBackground(new Color(62, 62, 66));
		tabbedPane.setBounds(0, 0, 580, 450);
		leaguePanelWindow.add(tabbedPane);

		JPanel matchesSubPanel = new JPanel();
		matchesSubPanel.setForeground(new Color(255, 255, 255));
		matchesSubPanel.setBackground(new Color(45, 45, 48));
		tabbedPane.addTab("Matches", null, matchesSubPanel, null);
		tabbedPane.setBackgroundAt(0, new Color(0, 122, 204));
		matchesSubPanel.setLayout(null);

		JLabel leagueLocalTeamlbl = new JLabel("Local Team | L");
		leagueLocalTeamlbl.setForeground(Color.WHITE);
		leagueLocalTeamlbl.setFont(new Font("Arial", Font.BOLD, 15));
		leagueLocalTeamlbl.setBounds(10, 10, 180, 13);
		matchesSubPanel.add(leagueLocalTeamlbl);

		JSeparator legueLocalTeamSeparator = new JSeparator();
		legueLocalTeamSeparator.setForeground(Color.WHITE);
		legueLocalTeamSeparator.setBounds(10, 27, 180, 1);
		matchesSubPanel.add(legueLocalTeamSeparator);

		JLabel leagueOutsiderTeamlbl = new JLabel("Outsider Team | O");
		leagueOutsiderTeamlbl.setForeground(Color.WHITE);
		leagueOutsiderTeamlbl.setFont(new Font("Arial", Font.BOLD, 15));
		leagueOutsiderTeamlbl.setBounds(390, 10, 174, 13);
		matchesSubPanel.add(leagueOutsiderTeamlbl);

		JSeparator leagueOutsiderTeamSeparator = new JSeparator();
		leagueOutsiderTeamSeparator.setForeground(Color.WHITE);
		leagueOutsiderTeamSeparator.setBounds(390, 27, 180, 1);
		matchesSubPanel.add(leagueOutsiderTeamSeparator);

		leagueLocalTeamList = new JComboBox<Object>();
		leagueLocalTeamList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				team selectedLocalT = (team) leagueLocalTeamList.getSelectedItem();
				team selectedOutT = (team) leagueOutsiderTeamList.getSelectedItem();

				if (selectedLocalT == null)
					return;

				leagueLocalPlayerListData.clear();

				if (selectedLocalT.equals(selectedOutT)) {
					leagueLocalTeamList.setSelectedIndex(-1);
					onMatchChanged();
					NotifyUtils.warn("Teams cannot be the same, please choose another team.", "User Error!");
					FileUtils.logToFile("User Error, Teams cannot be the same, please choose another team.");
					return;
				}
				leagueLocalPlayerListData.addAll(selectedLocalT.getPlayers());
				onMatchChanged();
			}
		});
		leagueLocalTeamList.setBounds(10, 45, 180, 21);
		matchesSubPanel.add(leagueLocalTeamList);

		JLabel leagueLocalTeamSelectedPlayer = new JLabel("Select Player");
		leagueLocalTeamSelectedPlayer.setHorizontalAlignment(SwingConstants.RIGHT);
		leagueLocalTeamSelectedPlayer.setForeground(Color.WHITE);
		leagueLocalTeamSelectedPlayer.setFont(new Font("Arial", Font.BOLD, 15));
		leagueLocalTeamSelectedPlayer.setBounds(62, 328, 128, 15);
		matchesSubPanel.add(leagueLocalTeamSelectedPlayer);
		
		leagueLocalPlayerList = new JList();
		leagueLocalPlayerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		leagueLocalPlayerList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (homeTeamData.size() == 0) return;
				team selectedLocalT = (team) leagueLocalTeamList.getSelectedItem();
				player selectedLocalP = (player) leagueLocalPlayerList.getSelectedValue();
				
				if (selectedLocalT == null || selectedLocalP == null) {
					leagueLocalTeamSelectedPlayer.setText("Select Player");
					leagueLocalPlayerPoints.setText("0");
					return;
				};
				
				leagueLocalTeamSelectedPlayer.setText(selectedLocalP.getName());
				// leagueLocalPlayerPoints.setText(selectedLocalP.getPoints() + "");
				leagueLocalPlayerPoints.setText("0");	
			}
		});
		leagueLocalPlayerList.setBounds(10, 75, 180, 242);
		matchesSubPanel.add(leagueLocalPlayerList);
		leagueLocalPlayerList.setModel(leagueLocalPlayerListData);

		JLabel leagueLocalTeamSelectedlbl = new JLabel("Player:");
		leagueLocalTeamSelectedlbl.setForeground(Color.WHITE);
		leagueLocalTeamSelectedlbl.setFont(new Font("Arial", Font.BOLD, 15));
		leagueLocalTeamSelectedlbl.setBounds(10, 327, 180, 15);
		matchesSubPanel.add(leagueLocalTeamSelectedlbl);

		JLabel leagueLocalPointslbl = new JLabel("Points Made:");
		leagueLocalPointslbl.setForeground(Color.WHITE);
		leagueLocalPointslbl.setFont(new Font("Arial", Font.BOLD, 15));
		leagueLocalPointslbl.setBounds(10, 350, 180, 15);
		matchesSubPanel.add(leagueLocalPointslbl);

		leagueLocalPlayerPoints = new JTextField();
		leagueLocalPlayerPoints.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
            	String text = leagueLocalPlayerPoints.getText();
            	if (text.length() == 0) return;
            	char character = text.charAt(text.length() - 1);
            	leagueLocalPlayerPoints.setText(text.replace(" ", ""));
                
            	if (text.length() == 0) return;
            	
        		if(text.length() > 3) leagueLocalPlayerPoints.setText(text.substring(0, 3));
        		text = leagueLocalPlayerPoints.getText();
        		if (!WindowUtils.isNumberChar(character) && character != '.')
        			leagueLocalPlayerPoints.setText(text.substring(0, text.length()-1));

			}
		});
		leagueLocalPlayerPoints.setFont(new Font("Tahoma", Font.BOLD, 9));
		leagueLocalPlayerPoints.setHorizontalAlignment(SwingConstants.CENTER);
		leagueLocalPlayerPoints.setText("0");
		leagueLocalPlayerPoints.setBounds(125, 348, 65, 15);
		matchesSubPanel.add(leagueLocalPlayerPoints);
		leagueLocalPlayerPoints.setColumns(1);

		leagueLocalSetPlayerPoints = new JButton("Save Points");
		leagueLocalSetPlayerPoints.setForeground(new Color(255, 255, 255));
		leagueLocalSetPlayerPoints.setBackground(new Color(70, 70, 75));
		leagueLocalSetPlayerPoints.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				if (leagueLocalTeamList.getSelectedIndex() != -1 && leagueOutsiderTeamList.getSelectedIndex() != -1) return;
				
				String pointsStr = leagueLocalPlayerPoints.getText();
				team selectedLocalT = (team) leagueLocalTeamList.getSelectedItem();
				player selectedLocalP = (player) leagueLocalPlayerList.getSelectedValue();
				
				Double points = pointsStr.isEmpty() || !pointsStr.matches("[0-9]{1,13}(\\.[0-9]*)?") ? null : Double.parseDouble(pointsStr);
			
				if (selectedLocalT == null || selectedLocalP == null || points == null) {
					NotifyUtils.error("Invalid team/player selected or invalid points.", "User error");
					return;
				}
				
				
//				selectedLocalP.setPoints(selectedLocalP.getPoints() + points);
//				leagueMatchLogData.addElement("L | "+ selectedLocalP.getName() + " scored: " + points +" points");
				leagueMatchLogData.addElement(new score(selectedLocalP, points, true));
				onMatchChanged();
			}
		});
		leagueLocalSetPlayerPoints.setBounds(10, 375, 180, 38);
		matchesSubPanel.add(leagueLocalSetPlayerPoints);
		windowButtons.add(leagueLocalSetPlayerPoints);

		leagueOutsiderTeamList = new JComboBox<Object>();
		leagueOutsiderTeamList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				team selectedLocalT = (team) leagueLocalTeamList.getSelectedItem();
				team selectedOutT = (team) leagueOutsiderTeamList.getSelectedItem();

				leagueOutsiderPlayerListData.clear();

				if (selectedOutT == null)
					return;

				if (selectedOutT.equals(selectedLocalT)) {
					leagueOutsiderTeamList.setSelectedIndex(-1);
					onMatchChanged();
					NotifyUtils.warn("Teams cannot be the same, please choose another team.", "User Error!");
					FileUtils.logToFile("User Error, Teams cannot be the same, please choose another team.");
					return;
				}

				leagueOutsiderPlayerListData.addAll(selectedOutT.getPlayers());
				onMatchChanged();
			}
		});

		leagueOutsiderTeamList.setBounds(384, 45, 180, 21);
		matchesSubPanel.add(leagueOutsiderTeamList);
		
		JLabel leagueOutsiderTeamSelectedPlayer = new JLabel("Select Player");
		leagueOutsiderTeamSelectedPlayer.setHorizontalAlignment(SwingConstants.RIGHT);
		leagueOutsiderTeamSelectedPlayer.setForeground(Color.WHITE);
		leagueOutsiderTeamSelectedPlayer.setFont(new Font("Arial", Font.BOLD, 15));
		leagueOutsiderTeamSelectedPlayer.setBounds(436, 328, 128, 15);
		matchesSubPanel.add(leagueOutsiderTeamSelectedPlayer);

		leagueOutsiderPlayerList = new JList();
		leagueOutsiderPlayerList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (homeTeamData.size() == 0) return;
				team selectedOutsiderT = (team) leagueOutsiderTeamList.getSelectedItem();
				player selectedOutsiderP = (player) leagueOutsiderPlayerList.getSelectedValue();
				
				if (selectedOutsiderT == null || selectedOutsiderP == null) {
					leagueOutsiderTeamSelectedPlayer.setText("");
					leagueOutsiderPlayerPoints.setText("0");
					return;
				};
				
				leagueOutsiderTeamSelectedPlayer.setText(selectedOutsiderP.getName());
//				leagueOutsiderPlayerPoints.setText(selectedOutsiderP.getPoints() + "");	
				leagueOutsiderPlayerPoints.setText("0");	
			}
		});
		leagueOutsiderPlayerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		leagueOutsiderPlayerList.setBounds(384, 75, 180, 242);
		matchesSubPanel.add(leagueOutsiderPlayerList);
		leagueOutsiderPlayerList.setModel(leagueOutsiderPlayerListData);

		JLabel leagueOutsiderTeamSelectedlbl = new JLabel("Player:");
		leagueOutsiderTeamSelectedlbl.setForeground(Color.WHITE);
		leagueOutsiderTeamSelectedlbl.setFont(new Font("Arial", Font.BOLD, 15));
		leagueOutsiderTeamSelectedlbl.setBounds(384, 327, 180, 15);
		matchesSubPanel.add(leagueOutsiderTeamSelectedlbl);

		JLabel leagueOutsiderPointslbl = new JLabel("Points Made:");
		leagueOutsiderPointslbl.setForeground(Color.WHITE);
		leagueOutsiderPointslbl.setFont(new Font("Arial", Font.BOLD, 15));
		leagueOutsiderPointslbl.setBounds(384, 350, 180, 15);
		matchesSubPanel.add(leagueOutsiderPointslbl);

		leagueOutsiderPlayerPoints = new JTextField();
		leagueOutsiderPlayerPoints.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
            	String text = leagueOutsiderPlayerPoints.getText();
            	if (text.length() == 0) return;
            	char character = text.charAt(text.length() - 1);
            	leagueOutsiderPlayerPoints.setText(text.replace(" ", ""));
                
            	if (text.length() == 0) return;
            	            	
        		if(text.length() > 3) leagueOutsiderPlayerPoints.setText(text.substring(0, 3));
        		text = leagueOutsiderPlayerPoints.getText();
        		if (!WindowUtils.isNumberChar(character) && character != '.')
        			leagueOutsiderPlayerPoints.setText(text.substring(0, text.length()-1));
			}
		});
		leagueOutsiderPlayerPoints.setFont(new Font("Tahoma", Font.BOLD, 9));
		leagueOutsiderPlayerPoints.setText("0");
		leagueOutsiderPlayerPoints.setHorizontalAlignment(SwingConstants.CENTER);
		leagueOutsiderPlayerPoints.setColumns(1);
		leagueOutsiderPlayerPoints.setBounds(499, 348, 65, 15);
		matchesSubPanel.add(leagueOutsiderPlayerPoints);

		leagueOutsiderSetPlayerPoints = new JButton("Save Points");
		leagueOutsiderSetPlayerPoints.setForeground(new Color(255, 255, 255));
		leagueOutsiderSetPlayerPoints.setBackground(new Color(70, 70, 75));
		leagueOutsiderSetPlayerPoints.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				if (leagueLocalTeamList.getSelectedIndex() != -1 && leagueOutsiderTeamList.getSelectedIndex() != -1) return;

				String pointsStr = leagueOutsiderPlayerPoints.getText();
				team selectedOutsiderT = (team) leagueOutsiderTeamList.getSelectedItem();
				player selectedOutsiderP = (player) leagueOutsiderPlayerList.getSelectedValue();
				
				Double points = pointsStr.isEmpty() || !pointsStr.matches("[0-9]{1,13}(\\.[0-9]*)?") ? null : Double.parseDouble(pointsStr);
			
				if (selectedOutsiderT == null || selectedOutsiderP == null || points == null) {
					NotifyUtils.error("Invalid team/player selected or invalid points.", "User error");
					return;
				}
				
//				selectedOutsiderP.setPoints(selectedOutsiderP.getPoints() + points);
//				leagueMatchLogData.addElement("O | "+ selectedOutsiderP.getName() + " scored: " + points + "points");
				leagueMatchLogData.addElement(new score(selectedOutsiderP, points, false));
				onMatchChanged();
			}
		});
		leagueOutsiderSetPlayerPoints.setBounds(384, 375, 180, 38);
		matchesSubPanel.add(leagueOutsiderSetPlayerPoints);
		windowButtons.add(leagueOutsiderSetPlayerPoints);


		JLabel leagueLlbl = new JLabel("L");
		leagueLlbl.setForeground(Color.WHITE);
		leagueLlbl.setFont(new Font("Arial", Font.BOLD, 15));
		leagueLlbl.setBounds(200, 57, 175, 20);
		matchesSubPanel.add(leagueLlbl);

		JSeparator legueLocalTeamSeparator_1 = new JSeparator();
		legueLocalTeamSeparator_1.setForeground(Color.WHITE);
		legueLocalTeamSeparator_1.setBounds(200, 79, 175, 1);
		matchesSubPanel.add(legueLocalTeamSeparator_1);

		JLabel leagueVSlbl = new JLabel("VS");
		leagueVSlbl.setHorizontalAlignment(SwingConstants.CENTER);
		leagueVSlbl.setForeground(Color.WHITE);
		leagueVSlbl.setFont(new Font("Arial", Font.BOLD, 15));
		leagueVSlbl.setBounds(200, 58, 175, 20);
		matchesSubPanel.add(leagueVSlbl);

		JLabel leagueOlbl = new JLabel("O");
		leagueOlbl.setHorizontalAlignment(SwingConstants.RIGHT);
		leagueOlbl.setForeground(Color.WHITE);
		leagueOlbl.setFont(new Font("Arial", Font.BOLD, 15));
		leagueOlbl.setBounds(200, 58, 175, 20);
		matchesSubPanel.add(leagueOlbl);

		JList leagueMatchLog = new JList();
		leagueMatchLog.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		leagueMatchLog.setBounds(200, 90, 175, 273);
		matchesSubPanel.add(leagueMatchLog);
		leagueMatchLog.setModel(leagueMatchLogData);
		
		leagueCancelMatch = new JButton("");
		leagueCancelMatch.setEnabled(false);
		leagueCancelMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer option = JOptionPane.showConfirmDialog(null, "Are your sure you want to cancel this match? The scores will be lost!"); // 0 = yes, 1 = no, 2 = cancel
				FileUtils.logToFile("Match cancel option has been triggered, user has " + (option == 0 ? "allowed" : option == 1 ? "denied" : "canceled") + " the operation.");
				if (option != 0) return;
				
				leagueLocalTeamList.setSelectedIndex(-1);
				leagueOutsiderTeamList.setSelectedIndex(-1);
				
				leagueLocalTeamSelectedPlayer.setText("Select Player");
				leagueLocalPlayerPoints.setText("0");
				leagueLocalPlayerListData.clear();
				
				leagueOutsiderTeamSelectedPlayer.setText("Select Player");
				leagueOutsiderPlayerPoints.setText("0");
				leagueOutsiderPlayerListData.clear();
				
				leagueMatchLogData.clear();
				
				onMatchChanged();
				NotifyUtils.succeed("Match has been canecelled!", null);
				FileUtils.logToFile("User has deleted match, match has been canceled!");
			}
		});
		leagueCancelMatch.setToolTipText("Cancel Match");
		leagueCancelMatch.setIcon(new ImageIcon(MainForm.class.getResource("/assets/icons8-close-48.png")));
		leagueCancelMatch.setBounds(200, 375, 85, 38);
		matchesSubPanel.add(leagueCancelMatch);
		windowButtons.add(leagueCancelMatch);
		
		leagueSaveMatch = new JButton("");
		leagueSaveMatch.setForeground(new Color(255, 255, 255));
		leagueSaveMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Integer option = JOptionPane.showConfirmDialog(null, "Are your sure the match is finished, after playing it you wont be able to edit/remove it!"); // 0 = yes, 1 = no, 2 = cancel
				FileUtils.logToFile("Match confirm option has been triggered, user has " + (option == 0 ? "allowed" : option == 1 ? "denied" : "canceled") + " the operation.");
				if (option != 0) return;
				
				team localTeam = (team) leagueLocalTeamList.getSelectedItem();
				team outsiderTeam = (team) leagueOutsiderTeamList.getSelectedItem();
				
				Double localPoints = 0.;
				Double outsiderPoints = 0.;
				
				ArrayList<score> temp_scores = new ArrayList<score>();
				for (int i=0; i< leagueMatchLogData.size(); i++) {
					score player_score = leagueMatchLogData.get(i);
					player ply = player_score.getScorer();
					if (player_score.isLocal())
						localPoints += player_score.getPoints();
					else
						outsiderPoints += player_score.getPoints();
					
					ply.addPoints(player_score.getPoints());
					temp_scores.add(player_score);
				}
				
				localTeam.addPoints(localPoints);
				outsiderTeam.addPoints(outsiderPoints);
				
				DataUtils.matches.add(new match(localTeam, localPoints, outsiderTeam, outsiderPoints, temp_scores));
				
				NotifyUtils.succeed("Successfully created match, " + (localPoints > outsiderPoints ? localTeam.getName() : outsiderTeam.getName()) + " won the match!", null);
				
				leagueLocalTeamList.setSelectedIndex(-1);
				leagueOutsiderTeamList.setSelectedIndex(-1);
				
				leagueLocalTeamSelectedPlayer.setText("Select Player");
				leagueLocalPlayerPoints.setText("0");
				leagueLocalPlayerListData.clear();
				
				leagueOutsiderTeamSelectedPlayer.setText("Select Player");
				leagueOutsiderPlayerPoints.setText("0");
				leagueOutsiderPlayerListData.clear();
				
				leagueMatchLogData.clear();
				onTeamsChanged(); 
			}
		});	
		leagueSaveMatch.setToolTipText("Save Match");
		leagueSaveMatch.setEnabled(false);
		leagueSaveMatch.setIcon(new ImageIcon(MainForm.class.getResource("/assets/icons8-done-48.png")));
		leagueSaveMatch.setBounds(290, 375, 85, 38);
		matchesSubPanel.add(leagueSaveMatch);
		windowButtons.add(leagueSaveMatch);
		
		leagueClassification = new JPanel();
		leagueClassification.setBackground(new Color(45, 45, 48));
		tabbedPane.addTab("Classification", null, leagueClassification, null);
		tabbedPane.setBackgroundAt(1, new Color(0, 122, 204));
		leagueClassification.setLayout(null);

		JLabel leagueTeamClasificationlbl = new JLabel("Team Classification");
		leagueTeamClasificationlbl.setForeground(Color.WHITE);
		leagueTeamClasificationlbl.setFont(new Font("Arial", Font.BOLD, 15));
		leagueTeamClasificationlbl.setBounds(10, 10, 180, 13);
		leagueClassification.add(leagueTeamClasificationlbl);

		JSeparator legueLocalTeamSeparator_2 = new JSeparator();
		legueLocalTeamSeparator_2.setForeground(Color.WHITE);
		legueLocalTeamSeparator_2.setBounds(10, 27, 555, 1);
		leagueClassification.add(legueLocalTeamSeparator_2);

		JLabel leagueMatchListlbl = new JLabel("Match List");
		leagueMatchListlbl.setForeground(Color.WHITE);
		leagueMatchListlbl.setFont(new Font("Arial", Font.BOLD, 15));
		leagueMatchListlbl.setBounds(10, 216, 180, 13);
		leagueClassification.add(leagueMatchListlbl);

		JSeparator legueLocalTeamSeparator_2_1 = new JSeparator();
		legueLocalTeamSeparator_2_1.setForeground(Color.WHITE);
		legueLocalTeamSeparator_2_1.setBounds(10, 233, 555, 1);
		leagueClassification.add(legueLocalTeamSeparator_2_1);
		
		JScrollPane leagueTeamClassificationScroll = new JScrollPane();
		leagueTeamClassificationScroll.setBounds(10, 33, 555, 173);
		leagueClassification.add(leagueTeamClassificationScroll);
		
		JList leagueTeamClassification = new JList();
		leagueTeamClassification.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		leagueTeamClassificationScroll.setViewportView(leagueTeamClassification);
		leagueTeamClassification.setModel(leagueTeamClassificationData);
		
		JScrollPane leagueMatchListScroll = new JScrollPane();
		leagueMatchListScroll.setBounds(10, 240, 555, 173);
		leagueClassification.add(leagueMatchListScroll);
		
		JList leagueMatchList = new JList();
		leagueMatchListScroll.setViewportView(leagueMatchList);
		leagueMatchList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		leagueMatchList.setModel(leagueMatchListData);


		setupLeagueWindow = new JPanel();
		setupLeagueWindow.setBackground(new Color(45, 45, 48));
		layeredPane.setLayer(setupLeagueWindow, 2);
		setupLeagueWindow.setBounds(0, 0, 580, 450);
		layeredPane.add(setupLeagueWindow);
		setupLeagueWindow.setLayout(null);

		JSeparator seutpTeamSep = new JSeparator();
		seutpTeamSep.setForeground(Color.WHITE);
		seutpTeamSep.setBounds(20, 42, 540, 1);
		setupLeagueWindow.add(seutpTeamSep);

		JLabel setupTeamLbl = new JLabel("Teams");
		setupTeamLbl.setForeground(Color.WHITE);
		setupTeamLbl.setFont(new Font("Arial", Font.BOLD, 15));
		setupTeamLbl.setBounds(20, 24, 174, 13);
		setupLeagueWindow.add(setupTeamLbl);

		JSeparator seutpPlayerSep = new JSeparator();
		seutpPlayerSep.setForeground(Color.WHITE);
		seutpPlayerSep.setBounds(20, 178, 540, 1);
		setupLeagueWindow.add(seutpPlayerSep);

		JLabel setupPlayerLbl = new JLabel("Players");
		setupPlayerLbl.setForeground(Color.WHITE);
		setupPlayerLbl.setFont(new Font("Arial", Font.BOLD, 15));
		setupPlayerLbl.setBounds(20, 160, 174, 13);
		setupLeagueWindow.add(setupPlayerLbl);

		setupSelectTeam = new JComboBox<Object>();
		setupSelectTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				team selected = (team) setupSelectTeam.getSelectedItem();

				Boolean hasPlayer = selected != null ? selected.getPlayers().size() > 0 : false;
				Boolean hasCoach = selected != null ? selected.getCoach() != null : false;

				setupSelectPlayer.removeAllItems();
				setupSelectPlayer.setEnabled(hasPlayer);
				setupDeletePlayer.setEnabled(hasPlayer);
				if (selected != null && hasPlayer) {
//					setupSelectPlayer.addAll(selected.getPlayers());
					for (Object p : selected.getPlayers()) {
						setupSelectPlayer.addItem(p);
					}
					setupCreatePlayer.setEnabled(selected.getPlayers().size() < 9);
				}

				setupSelectCoach.setEnabled(hasCoach);
				setupDeleteCoach.setEnabled(hasCoach);
				if (selected != null && hasCoach) {
					setupSelectCoach.setText(selected.getCoach().getName());
				} else {
					setupSelectCoach.setText("No Coach.");
				}
			}
		});
		setupSelectTeam.setBounds(295, 53, 265, 24);
		setupLeagueWindow.add(setupSelectTeam);

		JLabel lblSelectTeam = new JLabel("Select Team");
		lblSelectTeam.setForeground(Color.WHITE);
		lblSelectTeam.setFont(new Font("Arial", Font.PLAIN, 15));
		lblSelectTeam.setBounds(20, 53, 174, 24);
		setupLeagueWindow.add(lblSelectTeam);

		JSeparator seutpCoachSep = new JSeparator();
		seutpCoachSep.setForeground(Color.WHITE);
		seutpCoachSep.setBounds(20, 314, 540, 1);
		setupLeagueWindow.add(seutpCoachSep);

		JLabel setupCoachSec = new JLabel("Coach");
		setupCoachSec.setForeground(Color.WHITE);
		setupCoachSec.setFont(new Font("Arial", Font.BOLD, 15));
		setupCoachSec.setBounds(20, 296, 174, 13);
		setupLeagueWindow.add(setupCoachSec);

		JButton setupCreateTeam = new JButton("Create");
		setupCreateTeam.setForeground(new Color(255, 255, 255));
		setupCreateTeam.setBackground(new Color(70, 70, 75));
		setupCreateTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterTeam.main(null);
				homeDisplayData(true);
				onTeamsChanged();
			}
		});
		setupCreateTeam.setBounds(295, 90, 265, 24);
		setupLeagueWindow.add(setupCreateTeam);
		windowButtons.add(setupCreateTeam);


		JLabel setupCreateTeamlbl = new JLabel("Create Team");
		setupCreateTeamlbl.setForeground(Color.WHITE);
		setupCreateTeamlbl.setFont(new Font("Arial", Font.PLAIN, 15));
		setupCreateTeamlbl.setBounds(20, 90, 174, 24);
		setupLeagueWindow.add(setupCreateTeamlbl);

		setupSelectPlayer = new JComboBox<Object>();
		setupSelectPlayer.setBounds(295, 189, 265, 24);
		setupLeagueWindow.add(setupSelectPlayer);

		JLabel setupSelectPlayerLbl = new JLabel("Select Player");
		setupSelectPlayerLbl.setForeground(Color.WHITE);
		setupSelectPlayerLbl.setFont(new Font("Arial", Font.PLAIN, 15));
		setupSelectPlayerLbl.setBounds(20, 189, 174, 24);
		setupLeagueWindow.add(setupSelectPlayerLbl);

		setupDeleteTeam = new JButton("Delete");
		setupDeleteTeam.setForeground(new Color(255, 255, 255));
		setupDeleteTeam.setBackground(new Color(70, 70, 75));
		setupDeleteTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				team selected = (team) setupSelectTeam.getSelectedItem();
				if (selected == null)
					return;
				
				Boolean sreturn = false;
				for (match m : DataUtils.matches) {
					if (selected.equals(m.getLocal()) || selected.equals(m.getOutsider())) {
						FileUtils.logToFile(
								"Blocked '" + selected.getName() + "' from been deleted cause already has played matches");
						NotifyUtils.error("You cant delete " + selected.getName() + " they already have played matches", null);
						sreturn = true;
						break;
					}
				}
				if (sreturn) return;
				
				
				if(DataUtils.teams.remove(selected)) {
					FileUtils.logToFile(
							"Deleted team '" + selected.getName() + "' with code '" + selected.getCode() + "'");
					NotifyUtils.succeed("Deleted team '" + selected.getName() + "' with code '" + selected.getCode() + "'", null);
				}

				homeDisplayData(true);
				onTeamsChanged();
			}
		});
		setupDeleteTeam.setBounds(295, 126, 265, 24);
		setupLeagueWindow.add(setupDeleteTeam);
		windowButtons.add(setupDeleteTeam);


		JLabel setupDeleteTeamlbl = new JLabel("Delete Team");
		setupDeleteTeamlbl.setForeground(Color.WHITE);
		setupDeleteTeamlbl.setFont(new Font("Arial", Font.PLAIN, 15));
		setupDeleteTeamlbl.setBounds(20, 126, 174, 24);
		setupLeagueWindow.add(setupDeleteTeamlbl);

		JLabel setupCreatePlayerLbl = new JLabel("Create Player");
		setupCreatePlayerLbl.setForeground(Color.WHITE);
		setupCreatePlayerLbl.setFont(new Font("Arial", Font.PLAIN, 15));
		setupCreatePlayerLbl.setBounds(20, 226, 174, 24);
		setupLeagueWindow.add(setupCreatePlayerLbl);

		setupCreatePlayer = new JButton("Create");
		setupCreatePlayer.setBackground(new Color(70, 70, 75));
		setupCreatePlayer.setForeground(new Color(255, 255, 255));
		setupCreatePlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				team selectedT = (team) setupSelectTeam.getSelectedItem();
				if (selectedT == null)
					return;

//				player selectedP = (player) setupSelectPlayer.getSelectedItem();
//				if (selectedP == null) return;

				RegisterPlayer.main(new String[] { selectedT.getName() });
				homeDisplayData(false);
				onTeamsChanged();
			}
		});
		setupCreatePlayer.setBounds(295, 226, 265, 24);
		setupLeagueWindow.add(setupCreatePlayer);
		windowButtons.add(setupCreatePlayer);

		JLabel setupDeletePlayerLbl = new JLabel("Delete Player");
		setupDeletePlayerLbl.setForeground(Color.WHITE);
		setupDeletePlayerLbl.setFont(new Font("Arial", Font.PLAIN, 15));
		setupDeletePlayerLbl.setBounds(20, 262, 174, 24);
		setupLeagueWindow.add(setupDeletePlayerLbl);

		setupDeletePlayer = new JButton("Delete");
		setupDeletePlayer.setForeground(new Color(255, 255, 255));
		setupDeletePlayer.setBackground(new Color(70, 70, 75));
		setupDeletePlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				team selectedT = (team) setupSelectTeam.getSelectedItem();
				if (selectedT == null)
					return;

				player selectedP = (player) setupSelectPlayer.getSelectedItem();
				if (selectedP == null)
					return;
				
				Boolean sreturn = false;
				for (match m : DataUtils.matches) {
					for (score s : m.getScoreList()) {						
						if (selectedP.equals(s.getScorer())) {
							FileUtils.logToFile(
									"Blocked player '" + selectedP.getName() + "' from been deleted cause already has played matches");
							NotifyUtils.error("You cant delete " + selectedP.getName() + " he/she has already have played matches", null);
							sreturn = true;
							break;
						}
					}
				}
				if (sreturn) return;	
				
//				for (int i=0; i < DataUtils.players.size(); i++) 
//				if (selectedP.equals(DataUtils.players.get(i)))
//				if (DataUtils.players.remove(i) != null) {					
//					FileUtils.logToFile(
//							"Deleted player " + selectedP.getName() + ", " + Arrays.toString(selectedP.getSurnames()));
//					NotifyUtils.succeed(
//							"Deleted player " + selectedP.getName() + ", " + Arrays.toString(selectedP.getSurnames()), null);
//				} else return;
				
//				for (int i = 0; i < selectedT.getPlayers().size(); i++) {
//				player iplayer = selectedT.getPlayers().get(i);
//				if (selectedP.equals(iplayer)) {
//					FileUtils.logToFile("Deleted from team " + selectedT.getName() + " player " + iplayer.getName()
//							+ ", " + Arrays.toString(iplayer.getSurnames()));
//					selectedT.removePlayerIndex(i);
//				}
//			}
				
				if (DataUtils.players.remove(selectedP)) {					
					FileUtils.logToFile(
							"Deleted player " + selectedP.getName() + ", " + Arrays.toString(selectedP.getSurnames()));
					NotifyUtils.succeed(
							"Deleted player " + selectedP.getName() + ", " + Arrays.toString(selectedP.getSurnames()), null);
				}


				if (selectedT.removePlayer(selectedP)) {
					FileUtils.logToFile("Deleted from team " + selectedT.getName() + " player " + selectedP.getName()
							+ ", " + Arrays.toString(selectedP.getSurnames()));
				}

				homeDisplayData(false);
				onTeamsChanged();
			}
		});
		setupDeletePlayer.setBounds(295, 262, 265, 24);
		setupLeagueWindow.add(setupDeletePlayer);
		windowButtons.add(setupDeletePlayer);


		JLabel setupCreateCoachlbl = new JLabel("Create Coach");
		setupCreateCoachlbl.setForeground(Color.WHITE);
		setupCreateCoachlbl.setFont(new Font("Arial", Font.PLAIN, 15));
		setupCreateCoachlbl.setBounds(20, 362, 174, 24);
		setupLeagueWindow.add(setupCreateCoachlbl);

		setupCreateCoach = new JButton("Create");
		setupCreateCoach.setBackground(new Color(70, 70, 75));
		setupCreateCoach.setForeground(new Color(255, 255, 255));
		setupCreateCoach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				team selectedT = (team) setupSelectTeam.getSelectedItem();
				if (selectedT == null)
					return;

				RegisterCoach.main(new String[] { selectedT.getName() });
				homeDisplayData(false);
				onTeamsChanged();
			}
		});
		setupCreateCoach.setBounds(295, 362, 265, 24);
		setupLeagueWindow.add(setupCreateCoach);
		windowButtons.add(setupCreateCoach);


		JLabel setupDeleteCoachlbl = new JLabel("Delete Coach");
		setupDeleteCoachlbl.setForeground(Color.WHITE);
		setupDeleteCoachlbl.setFont(new Font("Arial", Font.PLAIN, 15));
		setupDeleteCoachlbl.setBounds(20, 398, 174, 24);
		setupLeagueWindow.add(setupDeleteCoachlbl);

		setupDeleteCoach = new JButton("Delete");
		setupDeleteCoach.setBackground(new Color(70, 70, 75));
		setupDeleteCoach.setForeground(new Color(255, 255, 255));
		setupDeleteCoach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				team selectedT = (team) setupSelectTeam.getSelectedItem();
				if (selectedT == null)
					return;

				String coachName = setupSelectCoach.getText();

				for (int i = 0; i < DataUtils.coaches.size(); i++) {
					coach icoach = DataUtils.coaches.get(i);
					if (coachName.equals(icoach)) {
						FileUtils.logToFile(
								"Deleted coach " + icoach.getName() + ", " + Arrays.toString(icoach.getSurnames()));
						DataUtils.teams.remove(i);
					}
				}

				selectedT.setCoach(null);
				FileUtils.logToFile("Deleted from team " + selectedT.getName() + " player " + coachName);

				homeDisplayData(false);
				onTeamsChanged();
			}
		});
		setupDeleteCoach.setBounds(295, 398, 265, 24);
		setupLeagueWindow.add(setupDeleteCoach);
		windowButtons.add(setupDeleteCoach);

		JLabel lblCoach_1 = new JLabel("Coach");
		lblCoach_1.setForeground(Color.WHITE);
		lblCoach_1.setFont(new Font("Arial", Font.PLAIN, 15));
		lblCoach_1.setBounds(20, 325, 174, 24);
		setupLeagueWindow.add(lblCoach_1);

		setupSelectCoach = new JLabel("");
		setupSelectCoach.setForeground(Color.WHITE);
		setupSelectCoach.setFont(new Font("Arial", Font.PLAIN, 15));
		setupSelectCoach.setBounds(295, 328, 265, 24);
		setupLeagueWindow.add(setupSelectCoach);

		settingsPanelWindow = new JPanel();
		settingsPanelWindow.setBackground(new Color(45, 45, 48));
		layeredPane.setLayer(settingsPanelWindow, 3);
		settingsPanelWindow.setBounds(0, 0, 580, 450);
		layeredPane.add(settingsPanelWindow);
		settingsPanelWindow.setLayout(null);

		JSeparator settingsMidSeparator = new JSeparator();
		settingsMidSeparator.setOrientation(SwingConstants.VERTICAL);
		settingsMidSeparator.setForeground(Color.WHITE);
		settingsMidSeparator.setBounds(290, 10, 1, 425);
		settingsPanelWindow.add(settingsMidSeparator);

		JSeparator settingsLeftSeparator = new JSeparator();
		settingsLeftSeparator.setForeground(Color.WHITE);
		settingsLeftSeparator.setBounds(10, 61, 260, 2);
		settingsPanelWindow.add(settingsLeftSeparator);

		JSeparator settingsRightSeparator = new JSeparator();
		settingsRightSeparator.setForeground(Color.WHITE);
		settingsRightSeparator.setBounds(310, 61, 260, 2);
		settingsPanelWindow.add(settingsRightSeparator);

		JLabel appLbl = new JLabel("App");
		appLbl.setForeground(new Color(255, 255, 255));
		appLbl.setBackground(new Color(255, 255, 255));
		appLbl.setFont(new Font("Arial", Font.BOLD, 20));
		appLbl.setBounds(10, 10, 260, 38);
		settingsPanelWindow.add(appLbl);

		JLabel systemLbl = new JLabel("System");
		systemLbl.setForeground(Color.WHITE);
		systemLbl.setFont(new Font("Arial", Font.BOLD, 20));
		systemLbl.setBackground(Color.WHITE);
		systemLbl.setBounds(310, 10, 260, 38);
		settingsPanelWindow.add(systemLbl);

		JLabel settingsSysCLogs = new JLabel("Clear Logs");
		settingsSysCLogs.setForeground(new Color(255, 255, 255));
		settingsSysCLogs.setFont(new Font("Arial", Font.BOLD, 12));
		settingsSysCLogs.setBounds(310, 125, 145, 21);
		settingsPanelWindow.add(settingsSysCLogs);

		JButton settingsSysClearLogsBtn = new JButton("Clear");
		settingsSysClearLogsBtn.setForeground(new Color(255, 255, 255));
		settingsSysClearLogsBtn.setBackground(new Color(70, 70, 75));
		settingsSysClearLogsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean success = FileUtils.removeDirectory(new File(FileUtils.logs_root));
				FileUtils.logToFile("Cleaning logs was a " + (success ? "success" : "failure") + ".");
			}
		});
		settingsSysClearLogsBtn.setBounds(465, 125, 105, 21);
		settingsPanelWindow.add(settingsSysClearLogsBtn);
		windowButtons.add(settingsSysClearLogsBtn);


		JLabel settingsSysClearCache = new JLabel("Clear Cache");
		settingsSysClearCache.setForeground(Color.WHITE);
		settingsSysClearCache.setFont(new Font("Arial", Font.BOLD, 12));
		settingsSysClearCache.setBounds(310, 156, 145, 21);
		settingsPanelWindow.add(settingsSysClearCache);

		JButton settingsSysClearCacheBtn = new JButton("Clear");
		settingsSysClearCacheBtn.setForeground(new Color(255, 255, 255));
		settingsSysClearCacheBtn.setBackground(new Color(70, 70, 75));
		settingsSysClearCacheBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean success = FileUtils.removeDirectory(new File(FileUtils.cache_root));
				FileUtils.logToFile("Cleaning cache was a " + (success ? "success" : "failure") + ".");
			}
		});
		settingsSysClearCacheBtn.setBounds(465, 156, 105, 21);
		settingsPanelWindow.add(settingsSysClearCacheBtn);
		windowButtons.add(settingsSysClearCacheBtn);

		JLabel settingsSysFRestoreLbl = new JLabel("Full Clean");
		settingsSysFRestoreLbl.setEnabled(false);
		settingsSysFRestoreLbl.setForeground(Color.WHITE);
		settingsSysFRestoreLbl.setFont(new Font("Arial", Font.BOLD, 12));
		settingsSysFRestoreLbl.setBounds(311, 380, 145, 21);
		settingsPanelWindow.add(settingsSysFRestoreLbl);

		JButton settingsSysFullRestore = new JButton("Clean");
		settingsSysFullRestore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean success = FileUtils.removeDirectory(new File(FileUtils.root));
				FileUtils.logToFile("Full cleanning was a " + (success ? "success" : "failure") + ".");
			}
		});
		settingsSysFullRestore.setForeground(new Color(255, 255, 255));
		settingsSysFullRestore.setBackground(new Color(70, 70, 75));
		settingsSysFullRestore.setEnabled(false);
		settingsSysFullRestore.setToolTipText("This will completely remove all the data of the app");
		settingsSysFullRestore.setBounds(466, 380, 104, 21);
		settingsPanelWindow.add(settingsSysFullRestore);
		windowButtons.add(settingsSysFullRestore);

		JLabel settingsSysUninstall = new JLabel("Uninstall");
		settingsSysUninstall.setEnabled(false);
		settingsSysUninstall.setForeground(Color.WHITE);
		settingsSysUninstall.setFont(new Font("Arial", Font.BOLD, 12));
		settingsSysUninstall.setBounds(311, 414, 145, 21);
		settingsPanelWindow.add(settingsSysUninstall);

		JButton settingsSysUninstallBtn = new JButton("Uninstall");
		settingsSysUninstallBtn.setForeground(new Color(255, 255, 255));
		settingsSysUninstallBtn.setBackground(new Color(70, 70, 75));
		settingsSysUninstallBtn.setEnabled(false);
		settingsSysUninstallBtn.setToolTipText("This will completely remove the app");
		settingsSysUninstallBtn.setBounds(466, 414, 104, 21);
		settingsPanelWindow.add(settingsSysUninstallBtn);
		windowButtons.add(settingsSysUninstallBtn);


		JCheckBox chckbxNewCheckBox = new JCheckBox("Enable Risky Options");
		chckbxNewCheckBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				Boolean status = e.getStateChange() == 1 ? true : false;
				settingsSysUninstallBtn.setEnabled(status);
				settingsSysUninstall.setEnabled(status);
				settingsSysFullRestore.setEnabled(status);
				settingsSysFRestoreLbl.setEnabled(status);
			}
		});
		chckbxNewCheckBox.setBackground(new Color(45, 45, 48));
		chckbxNewCheckBox.setForeground(new Color(255, 255, 255));
		chckbxNewCheckBox.setBounds(310, 346, 260, 21);
		settingsPanelWindow.add(chckbxNewCheckBox);

		JLabel settingsAppFolderlbl = new JLabel("App  Folder");
		settingsAppFolderlbl.setForeground(Color.WHITE);
		settingsAppFolderlbl.setFont(new Font("Arial", Font.BOLD, 12));
		settingsAppFolderlbl.setBounds(310, 94, 145, 21);
		settingsPanelWindow.add(settingsAppFolderlbl);

		JButton settingsAppFolder = new JButton("Open");
		settingsAppFolder.setForeground(new Color(255, 255, 255));
		settingsAppFolder.setBackground(new Color(70, 70, 75));
		settingsAppFolder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().open(new File(FileUtils.root));
				} catch (IOException e1) {
					NotifyUtils.error("Cant open folder path.", "App error");
					FileUtils.logToFile("App error, cannot open the app folder.");
				}
			}
		});
		settingsAppFolder.setBounds(465, 94, 105, 21);
		settingsPanelWindow.add(settingsAppFolder);
		windowButtons.add(settingsAppFolder);

		
		JLabel settingsCloseWithoutSavinglbl = new JLabel("Close without saving");
		settingsCloseWithoutSavinglbl.setForeground(Color.WHITE);
		settingsCloseWithoutSavinglbl.setFont(new Font("Arial", Font.BOLD, 12));
		settingsCloseWithoutSavinglbl.setBounds(10, 94, 145, 21);
		settingsPanelWindow.add(settingsCloseWithoutSavinglbl);
		
		JButton settingsCloseWithoutSaving = new JButton("Close");
		settingsCloseWithoutSaving.setForeground(new Color(255, 255, 255));
		settingsCloseWithoutSaving.setBackground(new Color(70, 70, 75));
		settingsCloseWithoutSaving.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				should_not_save = true;
				System.exit(0);
			}
		});
		settingsCloseWithoutSaving.setBounds(165, 94, 105, 21);
		settingsPanelWindow.add(settingsCloseWithoutSaving);
		windowButtons.add(settingsCloseWithoutSaving);


		WindowUtils.setWindowButtonStyle(windowButtons);
		updateCurrentWindow(currentWindow);
		homeDisplayData(true); // True if we want to update team, false players
		onTeamsChanged();
		onMatchChanged();
	}
	
	public static void reOrderTeams() {
		leagueTeamClassificationData.clear();
		for (team t : DataUtils.teams) {
			Boolean isin = false;
			for (int i = 0; i < leagueTeamClassificationData.size(); i++) {
				if (t.compareTo(leagueTeamClassificationData.get(i)) > 0 && !isin) {
					t.setKlasifikation(i+1);
					leagueTeamClassificationData.add(i, new classification(t)); isin = true;
//					break;
				}
				leagueTeamClassificationData.get(i).setKlasifikation(i+1);
			}
			if (!isin) {
				t.setKlasifikation(leagueTeamClassificationData.size()+1);
				leagueTeamClassificationData.addElement(new classification(t));
			}
//			FileUtils.logToFile("Team clasification executed, " + t.getName() + " has been clasified with " + (isin ? "coparing" : "luck") + " in the league in position " + t.getKlasifikation());
		}
	}
	
	public static void onMatchChanged() {
//		if (frame == null) return;
		Boolean matchReady = leagueLocalTeamList.getSelectedIndex() != -1 && leagueOutsiderTeamList.getSelectedIndex() != -1;
		if (matchReady) {
			matchReady = ((team) leagueLocalTeamList.getSelectedItem()).getPlayers().size() > 1 && ((team) leagueOutsiderTeamList.getSelectedItem()).getPlayers().size() > 1;
			if (!matchReady) FileUtils.logToFile("One of the selected teams has less than 2 players, requested to chose other teams.");
			if (!matchReady) NotifyUtils.warn("One of the selected teams has less than 2 players, please add them or choose another teams.", null);
		}
		
		leagueLocalSetPlayerPoints.setEnabled(matchReady);
		leagueOutsiderSetPlayerPoints.setEnabled(matchReady);
		
		leagueSaveMatch.setEnabled(leagueMatchLogData.size() > 0);
		leagueCancelMatch.setEnabled(leagueMatchLogData.size() > 0);
		
		leagueLocalTeamList.setEnabled(leagueMatchLogData.size() == 0);
		leagueOutsiderTeamList.setEnabled(leagueMatchLogData.size() == 0);
		
		leagueMatchListData.clear(); leagueMatchListData.addAll(DataUtils.matches);
		reOrderTeams();
	}

	public static void onTeamsChanged() {
//		if (frame == null) return;
		Integer index = setupSelectTeam.getSelectedIndex();
		homeTeamData.clear();
		homeTeamData.addAll(DataUtils.teams);

		Boolean htdEnabled = homeTeamData.size() > 1;
		setupSelectTeam.setEnabled(htdEnabled);
		setupDeleteTeam.setEnabled(htdEnabled);
		setupSelectPlayer.setEnabled(htdEnabled);
		setupCreatePlayer.setEnabled(htdEnabled);
		setupDeletePlayer.setEnabled(htdEnabled);
		setupSelectCoach.setEnabled(htdEnabled);
		setupCreateCoach.setEnabled(htdEnabled);
		setupDeleteCoach.setEnabled(htdEnabled);

		leagueLocalTeamList.setEnabled(htdEnabled);
		leagueOutsiderTeamList.setEnabled(htdEnabled);
		leagueLocalPlayerPoints.setEnabled(htdEnabled);
		leagueOutsiderPlayerPoints.setEnabled(htdEnabled);
		leagueLocalSetPlayerPoints.setEnabled(htdEnabled);
		leagueOutsiderSetPlayerPoints.setEnabled(htdEnabled);
		tabbedPane.setEnabledAt(1, htdEnabled);
//		leagueClassification.set

		WindowUtils.dlmToComboBoxAdd(homeTeamData, setupSelectTeam);

		WindowUtils.dlmToComboBoxAdd(homeTeamData, leagueLocalTeamList);
		leagueLocalTeamList.setSelectedIndex(-1);
		leagueLocalPlayerListData.clear();
		WindowUtils.dlmToComboBoxAdd(homeTeamData, leagueOutsiderTeamList);
		leagueOutsiderTeamList.setSelectedIndex(-1);
		leagueOutsiderPlayerListData.clear();

		if (index != -1 && homeTeamData.size() - 1 >= index) {
			setupSelectTeam.setSelectedIndex(index);
		}
	}

	private void homeDisplayData(Boolean teamUpdated) {
		int tSelected = homeTeamList.getSelectedIndex();
		int pSelected = homePlayerList.getSelectedIndex();

		if (teamUpdated) {
			if (DataUtils.teams.size() == 0) {
				if (homeEmpty.size() == 0)
					homeEmpty.addElement("No data.");
				if (homeTeamList.getModel() != homeEmpty)
					homeTeamList.setModel(homeEmpty);
				if (homePlayerList.getModel() != homeEmpty)
					homePlayerList.setModel(homeEmpty);
				WindowUtils.clearTextFields(homeCoachTextfield, homePointsTextfield, homeClasificationTextfield,
						homePlayerNameTextbox, homeAgeTextbox, homePPointsTextbox, homeHeadlineTexfield);
				return;
			}
			
			if (homeTeamList.getModel() != homeTeamData)
				homeTeamList.setModel(homeTeamData);

			homeTeamData.clear();
//			for (int i =0; i< DataUtils.teams.size(); i++) System.out.println(DataUtils.teams.get(i));
			homeTeamData.addAll(DataUtils.teams);

//			System.out.println(tSelected + " - " + homeTeamData.size());
			if (tSelected == -1 || homeTeamData.size() - 1 < tSelected) {
				homePlayerData.clear();
				WindowUtils.clearTextFields(homeCoachTextfield, homePointsTextfield, homeClasificationTextfield,
						homePlayerNameTextbox, homeAgeTextbox, homePPointsTextbox, homeHeadlineTexfield);
				return;
			}

			homeTeamList.setSelectedIndex(tSelected);

			homePlayerData.clear();
			if (homeTeamData.get(tSelected).getPlayers().size() > 0)
				homePlayerData.addAll(homeTeamData.get(tSelected).getPlayers());

			homeCoachTextfield.setText(
					homeTeamData.get(tSelected).getCoach() != null ? homeTeamData.get(tSelected).getCoach().getName()
							: "No Coach");
			homePointsTextfield.setText(homeTeamData.get(tSelected).getPoints() + "p");
			homeClasificationTextfield.setText(homeTeamData.get(tSelected).getKlasifikation() + "");
		}

		if (homePlayerData.size() == 0 || tSelected == -1) {
			if (homeEmpty.size() == 0)
				homeEmpty.addElement("No data.");
			if (homePlayerList.getModel() != homeEmpty)
				homePlayerList.setModel(homeEmpty);
			WindowUtils.clearTextFields(homePlayerNameTextbox, homeAgeTextbox, homePPointsTextbox,
					homeHeadlineTexfield);
			return;
		}

		if (homePlayerList.getModel() != homePlayerData)
			homePlayerList.setModel(homePlayerData);
		if (pSelected == -1 || homePlayerData.size() - 1 < pSelected)
			return;

		homePlayerList.setSelectedIndex(pSelected);

		homePlayerNameTextbox.setText(homePlayerData.get(pSelected).getName());
		homeAgeTextbox.setText(homePlayerData.get(pSelected).getAge() + "yo");
		homePPointsTextbox.setText(homePlayerData.get(pSelected).getPoints() + "p");
		homeHeadlineTexfield.setText(homePlayerData.get(pSelected).getHeadline() ? "Yes" : "No");
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
