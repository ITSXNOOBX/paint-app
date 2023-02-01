package gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import utils.DataUtils;
import utils.FileUtils;
import utils.RoundedButton;
import utils.WindowUtils;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.util.ArrayList;
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
import javax.swing.event.ListSelectionListener;

import person.player;
import teams.team;

import javax.swing.event.ListSelectionEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

public class MainForm extends JFrame {

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
		if (frame == null) return;
		WindowUtils.handleMouseDrag(frame, false);
		frame.setVisible(false);
		frame.dispose();
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
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
		
		homeTeamList = new JList();
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
		layeredPane.setLayer(leaguePanelWindow, 1);
		leaguePanelWindow.setBackground(new Color(45, 45, 48));
		leaguePanelWindow.setBounds(0, 0, 580, 450);
		layeredPane.add(leaguePanelWindow);
		leaguePanelWindow.setLayout(null);
		
		setupLeagueWindow = new JPanel();
		setupLeagueWindow.setBackground(new Color(45, 45, 48));
		layeredPane.setLayer(setupLeagueWindow, 4);
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
				if (selected != null && hasPlayer) {
//					setupSelectPlayer.addAll(selected.getPlayers());
					for (Object p : selected.getPlayers()) {
						
						setupSelectPlayer.addItem(p);
					}
				} else {
					setupSelectPlayer.addItem("No Players.");
					
				}
				
				setupSelectCoach.setEnabled(hasCoach);
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
		setupCreateTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterTeam.main(null);
				onTeamsChanged();
			}
		});
		setupCreateTeam.setBounds(295, 90, 265, 24);
		setupLeagueWindow.add(setupCreateTeam);
		
		JLabel setupCreateTeamlbl = new JLabel("Create Team");
		setupCreateTeamlbl.setForeground(Color.WHITE);
		setupCreateTeamlbl.setFont(new Font("Arial", Font.PLAIN, 15));
		setupCreateTeamlbl.setBounds(20, 90, 174, 24);
		setupLeagueWindow.add(setupCreateTeamlbl);
		
		setupSelectPlayer = new JComboBox();
		setupSelectPlayer.setBounds(295, 189, 265, 24);
		setupLeagueWindow.add(setupSelectPlayer);
		
		JLabel setupSelectPlayerLbl = new JLabel("Select Player");
		setupSelectPlayerLbl.setForeground(Color.WHITE);
		setupSelectPlayerLbl.setFont(new Font("Arial", Font.PLAIN, 15));
		setupSelectPlayerLbl.setBounds(20, 189, 174, 24);
		setupLeagueWindow.add(setupSelectPlayerLbl);
		
		JButton setupDeleteTeam = new JButton("Delete");
		setupDeleteTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				team selected = (team) setupSelectTeam.getSelectedItem();
				if (selected == null) return;
					
				for (int i = 0; i < DataUtils.teams.size(); i++) {
					team iteam = DataUtils.teams.get(i);
					if (selected.equals(iteam)) {
						FileUtils.logToFile("Deleted team '" + iteam.getName() + "' with code '" + iteam.getCode() + "'");
						DataUtils.teams.remove(i);
					}
				}
				
				onTeamsChanged();
			}
		});
		setupDeleteTeam.setBounds(295, 126, 265, 24);
		setupLeagueWindow.add(setupDeleteTeam);
		
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
		
		JButton setupCreatePlayer = new JButton("Create");
		setupCreatePlayer.setBounds(295, 226, 265, 24);
		setupLeagueWindow.add(setupCreatePlayer);
		
		JLabel setupDeletePlayerLbl = new JLabel("Delete Player");
		setupDeletePlayerLbl.setForeground(Color.WHITE);
		setupDeletePlayerLbl.setFont(new Font("Arial", Font.PLAIN, 15));
		setupDeletePlayerLbl.setBounds(20, 262, 174, 24);
		setupLeagueWindow.add(setupDeletePlayerLbl);
		
		JButton setupDeletePlayer = new JButton("Delete");
		setupDeletePlayer.setBounds(295, 262, 265, 24);
		setupLeagueWindow.add(setupDeletePlayer);
		
		JLabel setupCreateCoachlbl = new JLabel("Create Coach");
		setupCreateCoachlbl.setForeground(Color.WHITE);
		setupCreateCoachlbl.setFont(new Font("Arial", Font.PLAIN, 15));
		setupCreateCoachlbl.setBounds(20, 362, 174, 24);
		setupLeagueWindow.add(setupCreateCoachlbl);
		
		JButton setupCreateCoach = new JButton("Create");
		setupCreateCoach.setBounds(295, 362, 265, 24);
		setupLeagueWindow.add(setupCreateCoach);
		
		JLabel setupDeleteCoachlbl = new JLabel("Delete Coach");
		setupDeleteCoachlbl.setForeground(Color.WHITE);
		setupDeleteCoachlbl.setFont(new Font("Arial", Font.PLAIN, 15));
		setupDeleteCoachlbl.setBounds(20, 398, 174, 24);
		setupLeagueWindow.add(setupDeleteCoachlbl);
		
		JButton setupDeleteCoach = new JButton("Delete");
		setupDeleteCoach.setBounds(295, 398, 265, 24);
		setupLeagueWindow.add(setupDeleteCoach);
		
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
		layeredPane.setLayer(settingsPanelWindow, 4);
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
		settingsSysCLogs.setBounds(320, 73, 145, 21);
		settingsPanelWindow.add(settingsSysCLogs);
		
		JButton settingsSysClearLogsBtn = new JButton("Clear");
		settingsSysClearLogsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean success = FileUtils.clearDirectory(new File(FileUtils.logs_root));
				FileUtils.logToFile("Cleaning logs was a " + (success ? "success" : "failure") + ".");
			}
		});
		settingsSysClearLogsBtn.setBounds(475, 73, 85, 21);
		settingsPanelWindow.add(settingsSysClearLogsBtn);
		
		JLabel settingsSysClearCache = new JLabel("Clear Cache");
		settingsSysClearCache.setForeground(Color.WHITE);
		settingsSysClearCache.setFont(new Font("Arial", Font.BOLD, 12));
		settingsSysClearCache.setBounds(320, 104, 145, 21);
		settingsPanelWindow.add(settingsSysClearCache);
		
		JButton settingsSysClearCacheBtn = new JButton("Clear");
		settingsSysClearCacheBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean success = FileUtils.clearDirectory(new File(FileUtils.cache_root));
				FileUtils.logToFile("Cleaning cache was a " + (success ? "success" : "failure") + ".");
			}
		});
		settingsSysClearCacheBtn.setBounds(475, 104, 85, 21);
		settingsPanelWindow.add(settingsSysClearCacheBtn);
		
		JLabel settingsSysFRestoreLbl = new JLabel("Full Clean");
		settingsSysFRestoreLbl.setEnabled(false);
		settingsSysFRestoreLbl.setForeground(Color.WHITE);
		settingsSysFRestoreLbl.setFont(new Font("Arial", Font.BOLD, 12));
		settingsSysFRestoreLbl.setBounds(330, 380, 145, 21);
		settingsPanelWindow.add(settingsSysFRestoreLbl);
		
		JButton settingsSysFullRestore = new JButton("Clean");
		settingsSysFullRestore.setEnabled(false);
		settingsSysFullRestore.setToolTipText("This will completely remove all the data of the app");
		settingsSysFullRestore.setBounds(485, 380, 85, 21);
		settingsPanelWindow.add(settingsSysFullRestore);
		
		JLabel settingsSysUninstall = new JLabel("Uninstall");
		settingsSysUninstall.setEnabled(false);
		settingsSysUninstall.setForeground(Color.WHITE);
		settingsSysUninstall.setFont(new Font("Arial", Font.BOLD, 12));
		settingsSysUninstall.setBounds(330, 414, 145, 21);
		settingsPanelWindow.add(settingsSysUninstall);
		
		JButton settingsSysUninstallBtn = new JButton("Uninstall");
		settingsSysUninstallBtn.setEnabled(false);
		settingsSysUninstallBtn.setToolTipText("This will completely remove the app");
		settingsSysUninstallBtn.setBounds(485, 414, 85, 21);
		settingsPanelWindow.add(settingsSysUninstallBtn);
		
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
		chckbxNewCheckBox.setBounds(329, 346, 241, 21);
		settingsPanelWindow.add(chckbxNewCheckBox);
		
		
		WindowUtils.setWindowButtonStyle(windowButtons);
		updateCurrentWindow(currentWindow);
		homeDisplayData(true); // True if we want to update team, false players
		onTeamsChanged();
	}
	
	public static void onTeamsChanged() {
		if (frame == null) return;
		homeTeamData.clear(); homeTeamData.addAll(DataUtils.teams);
		
		int htdSize = homeTeamData.size();
		setupSelectTeam.setEnabled(htdSize > 0);
		WindowUtils.dlmToComboBoxAdd(homeTeamData, setupSelectTeam);	
	}
	
	private void homeDisplayData(Boolean teamUpdated) {
		int tSelected = homeTeamList.getSelectedIndex();
		int pSelected = homePlayerList.getSelectedIndex();
		
		if (teamUpdated) { 
			if (DataUtils.teams.size() == 0) {
				if (homeEmpty.size() == 0) homeEmpty.addElement("No data.");
				if(homeTeamList.getModel() != homeEmpty) homeTeamList.setModel(homeEmpty);
				return;
			}
			
			if(homeTeamList.getModel() != homeTeamData) homeTeamList.setModel(homeTeamData);
			
			homeTeamData.clear();

//			for (int i =0; i< DataUtils.teams.size(); i++) System.out.println(DataUtils.teams.get(i));
			homeTeamData.addAll(DataUtils.teams);
			
//			System.out.println(tSelected + " - " + homeTeamData.size());
			if (tSelected == -1 || homeTeamData.size() < tSelected) return;
		
			homeTeamList.setSelectedIndex(tSelected);
			
			homePlayerData.clear();
			if (homeTeamData.get(tSelected).getPlayers().size() > 0) homePlayerData.addAll(homeTeamData.get(tSelected).getPlayers());
		
			homeCoachTextfield.setText(homeTeamData.get(tSelected).getCoach() != null ? homeTeamData.get(tSelected).getCoach().getName() : "No Coach");
			homePointsTextfield.setText(homeTeamData.get(tSelected).getPoints() + "p");
			homeClasificationTextfield.setText(homeTeamData.get(tSelected).getKlasifikation() + "");
		} 
		
		if (homePlayerData.size() == 0) {
			if (homeEmpty.size() == 0) homeEmpty.addElement("No data.");
			if (homePlayerList.getModel() != homeEmpty) homePlayerList.setModel(homeEmpty);
			return;
		}
		
		if (homePlayerList.getModel() != homePlayerData) homeTeamList.setModel(homePlayerData);
		if (pSelected == -1 || homePlayerData.size() < pSelected) return;
		
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
