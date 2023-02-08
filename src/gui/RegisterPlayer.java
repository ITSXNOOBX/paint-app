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
import java.util.Arrays;
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
import teams.team;
import utils.DataUtils;
import utils.FileUtils;
import utils.NotifyUtils;
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
			playerName.setBounds(319, 77, 149, 20);
			contentPanel.add(playerName);
			playerName.setColumns(10);
			
			JLabel playerNamelbl = new JLabel("Player Name: ");
			playerNamelbl.setVerticalAlignment(SwingConstants.CENTER);
			playerNamelbl.setForeground(Color.WHITE);
			playerNamelbl.setFont(new Font("Arial", Font.BOLD, 15));
			playerNamelbl.setBackground(new Color(45, 45, 48));
			playerNamelbl.setBounds(109, 75, 133, 20);
			contentPanel.add(playerNamelbl);
			
			JLabel playerSurnameLbl = new JLabel("Player Surnames:");
			playerSurnameLbl.setToolTipText("Separate each surname with comma.");
			playerSurnameLbl.setVerticalAlignment(SwingConstants.CENTER);
			playerSurnameLbl.setForeground(Color.WHITE);
			playerSurnameLbl.setFont(new Font("Arial", Font.BOLD, 15));
			playerSurnameLbl.setBackground(new Color(45, 45, 48));
			playerSurnameLbl.setBounds(109, 105, 133, 20);
			contentPanel.add(playerSurnameLbl);
			
			playerSurname = new JTextField();
			playerSurname.setToolTipText("Separate each surname with comma.");
			playerSurname.setForeground(new Color(255, 255, 255));
			playerSurname.setBackground(new Color(62, 62, 66));
			playerSurname.setColumns(10);
			playerSurname.setBounds(319, 107, 149, 20);
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
			playerNanlbl.setBounds(109, 136, 133, 20);
			contentPanel.add(playerNanlbl);
			
			playerNan = new JTextField();
			playerNan.setToolTipText("");
			playerNan.setForeground(Color.WHITE);
			playerNan.setColumns(10);
			playerNan.setBackground(new Color(62, 62, 66));
			playerNan.setBounds(319, 137, 149, 20);
			contentPanel.add(playerNan);
			
			JLabel playerAgelbl = new JLabel("Player Age:");
			playerAgelbl.setVerticalAlignment(SwingConstants.CENTER);
			playerAgelbl.setToolTipText("");
			playerAgelbl.setForeground(Color.WHITE);
			playerAgelbl.setFont(new Font("Arial", Font.BOLD, 15));
			playerAgelbl.setBackground(new Color(45, 45, 48));
			playerAgelbl.setBounds(109, 166, 133, 20);
			contentPanel.add(playerAgelbl);
			
			playerAge = new JTextField();
			playerAge.setToolTipText("");
			playerAge.setForeground(Color.WHITE);
			playerAge.setColumns(10);
			playerAge.setBackground(new Color(62, 62, 66));
			playerAge.setBounds(319, 168, 149, 20);
			contentPanel.add(playerAge);
			
			JLabel playerNumberlbl = new JLabel("Player Number:");
			playerNumberlbl.setVerticalAlignment(SwingConstants.CENTER);
			playerNumberlbl.setToolTipText("");
			playerNumberlbl.setForeground(Color.WHITE);
			playerNumberlbl.setFont(new Font("Arial", Font.BOLD, 15));
			playerNumberlbl.setBackground(new Color(45, 45, 48));
			playerNumberlbl.setBounds(109, 196, 133, 20);
			contentPanel.add(playerNumberlbl);
			
			playerNumber = new JTextField();
			playerNumber.setToolTipText("");
			playerNumber.setForeground(Color.WHITE);
			playerNumber.setColumns(10);
			playerNumber.setBackground(new Color(62, 62, 66));
			playerNumber.setBounds(319, 198, 149, 20);
			contentPanel.add(playerNumber);
			
			JLabel playerHeadlinelbl = new JLabel("Headline:");
			playerHeadlinelbl.setVerticalAlignment(SwingConstants.CENTER);
			playerHeadlinelbl.setToolTipText("");
			playerHeadlinelbl.setForeground(Color.WHITE);
			playerHeadlinelbl.setFont(new Font("Arial", Font.BOLD, 15));
			playerHeadlinelbl.setBackground(new Color(45, 45, 48));
			playerHeadlinelbl.setBounds(109, 226, 133, 20);
			contentPanel.add(playerHeadlinelbl);
			
			JCheckBox playerHeadline = new JCheckBox("Headline");
			playerHeadline.setForeground(new Color(255, 255, 255));
			playerHeadline.setBackground(new Color(37, 37, 38));
			playerHeadline.setBounds(319, 227, 149, 21);
			contentPanel.add(playerHeadline);
			
			playerName.addKeyListener(new KeyAdapter() {
	            @Override
				public void keyReleased(KeyEvent e) {
	                String text = playerName.getText();
	                playerName.setText(text.replace(" ", ""));
	                
	            	if (text.length() > 15) {
	            		playerName.setText(text.substring(0, 15));
	            	}
				}
	        });
			
			playerSurname.addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyReleased(KeyEvent e) {
	            	String text = playerSurname.getText();
	            	
	            	if (text.length() > 40) {
//	            		e.setKeyChar((char) 0);
	            		playerSurname.setText(text.substring(0, 40));
	            	}
	            }
	        });
			
			playerNan.addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyReleased(KeyEvent e) {
	            	String text = playerNan.getText();
	            	playerNan.setText(text.replace(" ", ""));
	                
	            	if (text.length() == 0) return;
	            	            	
	            	if (text.length() <= 8) {
	            		if (!WindowUtils.isNumberChar(text.charAt(text.length() - 1)))
//	            			e.setKeyChar((char) 0);
	            			playerNan.setText(text.substring(0, text.length()-1));
	            	} else if (text.length() == 9) {
	            		if (WindowUtils.isNumberChar(text.charAt(text.length() - 1)))
//	            			e.setKeyChar((char) 0);
	            			playerNan.setText(text.substring(0, text.length()-1));
	            	} else if(text.length() > 9) {
//		    			e.setKeyChar((char) 0);
	            		playerNan.setText(text.substring(0, 9));
					}
	            }
	        });
			
			playerAge.addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyReleased(KeyEvent e) {
	            	String text = playerAge.getText();

	            	if (text.length() == 0) return;
	            	
	            	if (!WindowUtils.isNumberChar(text.charAt(text.length() - 1))) {
//	            		e.setKeyChar((char) 0);
	            		playerAge.setText(text.substring(0, text.length()-1));
	            	}
	            	
	            	if (text.length() > 3) {
//	            		e.setKeyChar((char) 0);
	            		playerAge.setText(text.substring(0, text.length()-1));
	            	}
	            }
	        });
			
			playerNumber.addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyReleased(KeyEvent e) {
	            	String text = playerNumber.getText();
	            	
	            	if (text.length() == 0) return;

	            	
	            	if (!WindowUtils.isNumberChar(text.charAt(text.length() - 1))) {
	            		playerNumber.setText(text.substring(0, text.length()-1));
	            	}
	            	
	            	if (text.length() > 1) {
	            		playerNumber.setText(text.substring(0, 1));
	            	}
	            }
	        });
			
			playerCreate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String pName = playerName.getText().replace(" ", "-");
					String[] pSurname = playerSurname.getText().split(",");
					String pNan = playerNan.getText().replace(" ", "");
					Integer pAge = Integer.parseInt(playerAge.getText().replace(" ", ""));
					Integer pNumber = Integer.parseInt(playerNumber.getText().replace(" ", ""));
					Boolean pHeadline = playerHeadline.isSelected();
					team playerTeam = null;
					for (team t : DataUtils.teams) if (t.getName().equals(tName)) playerTeam = t;
					
					if (playerTeam == null || playerTeam.getPlayers().size() > 9) {
						NotifyUtils.error("The team has too many players, you cant add more than 9.", "User Error!");
	                    FileUtils.logToFile("User Error!, The team has too many players, you cant add more than 9.");
	                    return;
					} else if (pName.length() < 3) {
						NotifyUtils.error("The player name is invalid, please write at least 3.", "User Error!");
	                    FileUtils.logToFile("The player name is invalid, please write at least 3.");
	                    return;
					} else if (pSurname.length > 4) {
						NotifyUtils.error("The player has too many surnames", "User Error!");
	                    FileUtils.logToFile("User Error!, he player has too many surnames");
	                    return;
					} else if (pNan.length() < 9) {
						NotifyUtils.error("The player has too many surnames", "User Error!");
	                    FileUtils.logToFile("User Error!, The player has too many surnames");
	                    return;
					} else if (pAge > 100) {
						NotifyUtils.error("The player is too old to play in this league.", "User Error!");
	                    FileUtils.logToFile("User Error!, The player is too old to play in this league.");
	                    return;
					} else if (pNumber > 100) {
						NotifyUtils.error("The player is too old to play in this league.", "User Error!");
	                    FileUtils.logToFile("User Error!, The player is too old to play in this league.");
	                    return;
					}

					Boolean already_exists = false;
					for (player p : DataUtils.players) {
						if (p.getNan().equals(pNan))
							already_exists = true;
					}
					
					if (already_exists) {
						NotifyUtils.error("This player already exists, please check the name and the code again!", "User Error!");
	                    FileUtils.logToFile("User Error!, This player already exists, please check the name and the code again!");
	                    return;
					}
					
					Boolean code_exists = false;
					for (player p : playerTeam.getPlayers())
						if (p.getTeam_number() == pNumber)
							code_exists = true;
					
					if (code_exists) {
						NotifyUtils.error("This player team number: '" + pNumber +"' belongs to another player. please use another number.", "User Error!");
	                    FileUtils.logToFile("User Error!, This player team number: '" + pNumber +"' belongs to another player. please use another number.");
	                    return;
					}
					
					
					player newPlayer = new player(pName, pSurname, pNan, pAge, pNumber, pHeadline);
					
					DataUtils.players.add(newPlayer);
					
					playerTeam.addPlayer(newPlayer);
					
					MainForm.onTeamsChanged(); // Trigger player update event

			        FileUtils.logToFile("Added new player to team "+ tName + " with name: " + pName + ", " + Arrays.toString(pSurname));
					NotifyUtils.succeed("Successfully added "+ pName + " to " + tName, null);

				}
			});
			
			WindowUtils.setWindowButtonStyle(windowButtons);
		}
}
