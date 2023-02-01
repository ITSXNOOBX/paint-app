package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import person.player;
import utils.DataUtils;
import utils.FileUtils;
import utils.WindowUtils;

public class RegisterPlayer extends JFrame {
	static RegisterPlayer frame;
	private ArrayList<JButton> windowButtons = new ArrayList<JButton>();
	private JPanel contentPanel;
	private JTextField playerName;
	private JTextField playerSurname;
	private JTextField playerNan;
	private JTextField playerAge;
	private JTextField playerNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new RegisterPlayer(args.length > 0 ? args[0] : "Unknown");
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
		public RegisterPlayer(String tName) {
			setResizable(false);
			setIconImage(Toolkit.getDefaultToolkit().getImage(MainForm.class.getResource("/assets/painballogo.png")));
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	        try {
//				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//	        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) { } // https://stackoverflow.com/a/11426036/15384495
	        setTitle("Paintball Fed 2023 | Register Player");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 580, 350);
	        
			contentPanel = new JPanel();
			contentPanel.setBackground(new Color(37, 37, 38));
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPanel);
			contentPanel.setLayout(null);
			
			JPanel header = new JPanel();
			header.setBackground(new Color(45, 45, 48));
			header.setBounds(0, 0, 580, 50);
			contentPanel.add(header);
			header.setLayout(null);
			
			JLabel lblCreateTeam = new JLabel("Create Player For Team: ");
			lblCreateTeam.setForeground(new Color(255, 255, 255));
			lblCreateTeam.setBackground(new Color(45, 45, 48));
			lblCreateTeam.setFont(new Font("Arial", Font.BOLD, 25));
			lblCreateTeam.setVerticalAlignment(SwingConstants.CENTER);
			lblCreateTeam.setBounds(54, 0, 290, 50);
			header.add(lblCreateTeam);
			
			JButton closeButton = new JButton("");
			closeButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
//					System.exit(0);
					 close();
				}
			});
			closeButton.setIcon(new ImageIcon(RegisterTeam.class.getResource("/assets/close-24.png")));
			closeButton.setBackground(new Color(45, 45, 48));
			closeButton.setBounds(555, 0, 24, 24);
			header.add(closeButton);
			windowButtons.add(closeButton);
			
			JLabel teamName = new JLabel(tName);
			teamName.setVerticalAlignment(SwingConstants.CENTER);
			teamName.setForeground(Color.WHITE);
			teamName.setFont(new Font("Arial", Font.BOLD, 25));
			teamName.setBackground(new Color(45, 45, 48));
			teamName.setBounds(354, 0, 191, 50);
			header.add(teamName);
			
			
			playerName = new JTextField();
			playerName.setForeground(new Color(255, 255, 255));
			playerName.setBackground(new Color(62, 62, 66));
			playerName.setBounds(319, 94, 149, 20);
			contentPanel.add(playerName);
			playerName.setColumns(10);
			
			JLabel playerNamelbl = new JLabel("Player Name: ");
			playerNamelbl.setVerticalAlignment(SwingConstants.CENTER);
			playerNamelbl.setForeground(Color.WHITE);
			playerNamelbl.setFont(new Font("Arial", Font.BOLD, 15));
			playerNamelbl.setBackground(new Color(45, 45, 48));
			playerNamelbl.setBounds(109, 92, 133, 20);
			contentPanel.add(playerNamelbl);
			
			JLabel playerSurnameLbl = new JLabel("Player Surnames:");
			playerSurnameLbl.setToolTipText("Separate each surname with comma.");
			playerSurnameLbl.setVerticalAlignment(SwingConstants.CENTER);
			playerSurnameLbl.setForeground(Color.WHITE);
			playerSurnameLbl.setFont(new Font("Arial", Font.BOLD, 15));
			playerSurnameLbl.setBackground(new Color(45, 45, 48));
			playerSurnameLbl.setBounds(109, 122, 133, 20);
			contentPanel.add(playerSurnameLbl);
			
			playerSurname = new JTextField();
			playerSurname.setToolTipText("Separate each surname with comma.");
			playerSurname.setForeground(new Color(255, 255, 255));
			playerSurname.setBackground(new Color(62, 62, 66));
			playerSurname.setColumns(10);
			playerSurname.setBounds(319, 124, 149, 20);
			playerSurname.setEnabled(false);
			contentPanel.add(playerSurname);
			
			JButton playerCreate = new JButton("Create Player");
			playerCreate.setBackground(new Color(70, 70, 75));
			playerCreate.setForeground(new Color(255, 255, 255));
			playerCreate.setBounds(109, 275, 359, 38);
			contentPanel.add(playerCreate);
			windowButtons.add(playerCreate);
			
			JLabel playerNanlbl = new JLabel("Player NAN:");
			playerNanlbl.setVerticalAlignment(SwingConstants.CENTER);
			playerNanlbl.setToolTipText("");
			playerNanlbl.setForeground(Color.WHITE);
			playerNanlbl.setFont(new Font("Arial", Font.BOLD, 15));
			playerNanlbl.setBackground(new Color(45, 45, 48));
			playerNanlbl.setBounds(109, 153, 133, 20);
			contentPanel.add(playerNanlbl);
			
			playerNan = new JTextField();
			playerNan.setToolTipText("");
			playerNan.setForeground(Color.WHITE);
			playerNan.setEnabled(false);
			playerNan.setColumns(10);
			playerNan.setBackground(new Color(62, 62, 66));
			playerNan.setBounds(319, 154, 149, 20);
			contentPanel.add(playerNan);
			
			JLabel playerAgelbl = new JLabel("Player Age:");
			playerAgelbl.setVerticalAlignment(SwingConstants.CENTER);
			playerAgelbl.setToolTipText("");
			playerAgelbl.setForeground(Color.WHITE);
			playerAgelbl.setFont(new Font("Arial", Font.BOLD, 15));
			playerAgelbl.setBackground(new Color(45, 45, 48));
			playerAgelbl.setBounds(109, 183, 133, 20);
			contentPanel.add(playerAgelbl);
			
			playerAge = new JTextField();
			playerAge.setToolTipText("");
			playerAge.setForeground(Color.WHITE);
			playerAge.setEnabled(false);
			playerAge.setColumns(10);
			playerAge.setBackground(new Color(62, 62, 66));
			playerAge.setBounds(319, 185, 149, 20);
			contentPanel.add(playerAge);
			
			JLabel playerNumberlbl = new JLabel("Player Number:");
			playerNumberlbl.setVerticalAlignment(SwingConstants.CENTER);
			playerNumberlbl.setToolTipText("");
			playerNumberlbl.setForeground(Color.WHITE);
			playerNumberlbl.setFont(new Font("Arial", Font.BOLD, 15));
			playerNumberlbl.setBackground(new Color(45, 45, 48));
			playerNumberlbl.setBounds(109, 213, 133, 20);
			contentPanel.add(playerNumberlbl);
			
			playerNumber = new JTextField();
			playerNumber.setToolTipText("");
			playerNumber.setForeground(Color.WHITE);
			playerNumber.setEnabled(false);
			playerNumber.setColumns(10);
			playerNumber.setBackground(new Color(62, 62, 66));
			playerNumber.setBounds(319, 215, 149, 20);
			contentPanel.add(playerNumber);
			
			playerName.addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyTyped(KeyEvent e) {
	                super.keyTyped(e);

	            }
	        });
			
			playerSurname.addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyTyped(KeyEvent e) {
	                super.keyTyped(e);
	            }
	        });
			
			playerCreate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String tName = playerName.getText().replace(" ", "-");
					String tCode = playerSurname.getText().replace(" ", "-");
					
					if (tName.length() < 4 || tName.length() > 15) {
	                    JOptionPane.showMessageDialog(null, "The player name is invalid, please write at least 4 characters and less than 15 characters.", "User Error!", JOptionPane.ERROR_MESSAGE);
	                    FileUtils.logToFile("User Error!, The player name is invalid, please write at least 4 characters and less than 15 characters.");
	                    return;
					} else if (tCode.length() != 4) {
	                    JOptionPane.showMessageDialog(null, "The player code must be 4 characters length", "User Error!", JOptionPane.ERROR_MESSAGE);
	                    FileUtils.logToFile("User Error!, The player code must be 4 characters length");
	                    return;
					}

					Boolean already_exists = false;
					for (player t : DataUtils.players) {
//						if (t.getName().equals(tName) || t.getCode().equals(tCode))
//							already_exists = true;
					}
					
					if (already_exists) {
	                    JOptionPane.showMessageDialog(null, "This player already exists, please check the name and the code again!", "User Error!", JOptionPane.ERROR_MESSAGE);
	                    FileUtils.logToFile("User Error!, This player already exists, please check the name and the code again!");
	                    return;
					}
					
//					DataUtils.players.add(new player(tName, tCode));
					MainForm.onTeamsChanged(); // Trigger player update event
					
			        FileUtils.logToFile("Added new player with name: " + tName + " and code: " + tCode);
				}
			});
			
			WindowUtils.setWindowButtonStyle(windowButtons);
		}
}
