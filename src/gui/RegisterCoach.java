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

import person.coach;
import teams.team;
import utils.DataUtils;
import utils.FileUtils;
import utils.NotifyUtils;
import utils.WindowUtils;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class RegisterCoach extends JFrame {
	static RegisterCoach frame;
	private ArrayList<JButton> windowButtons = new ArrayList<JButton>();
	private JPanel contentPanel;
	private JTextField playerName;
	private JTextField playerSurname;
	private JTextField playerNan;
	private JTextField playerAge;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new RegisterCoach(args.length > 0 ? args[0] : "Unknown");
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
		public RegisterCoach(String tName) {
			setResizable(false);
			setIconImage(Toolkit.getDefaultToolkit().getImage(MainForm.class.getResource("/assets/painballogo.png")));
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	        try {
//				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//	        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) { } // https://stackoverflow.com/a/11426036/15384495
	        setTitle("Paintball Fed 2023 | Register Coach");
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
			
			JLabel lblCreateTeam = new JLabel("Create Coach For Team: ");
			lblCreateTeam.setForeground(new Color(255, 255, 255));
			lblCreateTeam.setBackground(new Color(45, 45, 48));
			lblCreateTeam.setFont(new Font("Arial", Font.BOLD, 25));
			lblCreateTeam.setVerticalAlignment(SwingConstants.CENTER);
			lblCreateTeam.setBounds(54, 0, 308, 50);
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
			playerName.setBounds(319, 102, 149, 20);
			contentPanel.add(playerName);
			playerName.setColumns(10);
			
			JLabel playerNamelbl = new JLabel("Coach Name: ");
			playerNamelbl.setVerticalAlignment(SwingConstants.CENTER);
			playerNamelbl.setForeground(Color.WHITE);
			playerNamelbl.setFont(new Font("Arial", Font.BOLD, 15));
			playerNamelbl.setBackground(new Color(45, 45, 48));
			playerNamelbl.setBounds(109, 100, 155, 20);
			contentPanel.add(playerNamelbl);
			
			JLabel playerSurnameLbl = new JLabel("Coach Surnames:");
			playerSurnameLbl.setToolTipText("Separate each surname with comma.");
			playerSurnameLbl.setVerticalAlignment(SwingConstants.CENTER);
			playerSurnameLbl.setForeground(Color.WHITE);
			playerSurnameLbl.setFont(new Font("Arial", Font.BOLD, 15));
			playerSurnameLbl.setBackground(new Color(45, 45, 48));
			playerSurnameLbl.setBounds(109, 130, 155, 20);
			contentPanel.add(playerSurnameLbl);
			
			playerSurname = new JTextField();
			playerSurname.setToolTipText("Separate each surname with comma.");
			playerSurname.setForeground(new Color(255, 255, 255));
			playerSurname.setBackground(new Color(62, 62, 66));
			playerSurname.setColumns(10);
			playerSurname.setBounds(319, 132, 149, 20);
			contentPanel.add(playerSurname);
			
			JButton playerCreate = new JButton("Create Coach");
			playerCreate.setBackground(new Color(70, 70, 75));
			playerCreate.setForeground(new Color(255, 255, 255));
			playerCreate.setBounds(109, 275, 359, 38);
			contentPanel.add(playerCreate);
			windowButtons.add(playerCreate);
			
			JLabel playerNanlbl = new JLabel("Coach NAN:");
			playerNanlbl.setVerticalAlignment(SwingConstants.CENTER);
			playerNanlbl.setToolTipText("");
			playerNanlbl.setForeground(Color.WHITE);
			playerNanlbl.setFont(new Font("Arial", Font.BOLD, 15));
			playerNanlbl.setBackground(new Color(45, 45, 48));
			playerNanlbl.setBounds(109, 161, 155, 20);
			contentPanel.add(playerNanlbl);
			
			playerNan = new JTextField();
			playerNan.setToolTipText("");
			playerNan.setForeground(Color.WHITE);
			playerNan.setColumns(10);
			playerNan.setBackground(new Color(62, 62, 66));
			playerNan.setBounds(319, 162, 149, 20);
			contentPanel.add(playerNan);
			
			JLabel playerAgelbl = new JLabel("Coach Age:");
			playerAgelbl.setVerticalAlignment(SwingConstants.CENTER);
			playerAgelbl.setToolTipText("");
			playerAgelbl.setForeground(Color.WHITE);
			playerAgelbl.setFont(new Font("Arial", Font.BOLD, 15));
			playerAgelbl.setBackground(new Color(45, 45, 48));
			playerAgelbl.setBounds(109, 191, 155, 20);
			contentPanel.add(playerAgelbl);
			
			playerAge = new JTextField();
			playerAge.setToolTipText("");
			playerAge.setForeground(Color.WHITE);
			playerAge.setColumns(10);
			playerAge.setBackground(new Color(62, 62, 66));
			playerAge.setBounds(319, 193, 149, 20);
			contentPanel.add(playerAge);
			
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
			
			playerCreate.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String pName = playerName.getText().replace(" ", "-");
					String[] pSurname = playerSurname.getText().split(",");
					String pNan = playerNan.getText().replace(" ", "");
					Integer pAge = Integer.parseInt(playerAge.getText().replace(" ", ""));
					
					team coachTeam = null;
					for (team t : DataUtils.teams) if (t.getName().equals(tName)) coachTeam = t;
					
					if (coachTeam == null || coachTeam.getCoach() != null) {
						NotifyUtils.error("This team already has a coach, please delete it before making it again.", "User Error!");
	                    FileUtils.logToFile("User Error!, This team already has a coach, please delete it before making it again.");
	                    return;
					} else if (pName.length() < 3) {
						NotifyUtils.error("The coach name is invalid, please write at least 3.", "User Error!");
	                    FileUtils.logToFile("User Error!, The coach name is invalid, please write at least 3.");
	                    return;
					} else if (pSurname.length > 4) {
						NotifyUtils.error("The coach has too many surnames.", "User Error!");
	                    FileUtils.logToFile("User Error!, he coach has too many surnames");
	                    return;
					} else if (pNan.length() < 9) {
						NotifyUtils.error("The coach has too many surnames", "User Error!");
	                    FileUtils.logToFile("User Error!, The coach has too many surnames");
	                    return;
					} else if (pAge > 100) {
						NotifyUtils.error("The coach is too old/joung to play in this league.", "User Error!");
	                    FileUtils.logToFile("User Error!, The coach is too old/joung to play in this league.");
	                    return;
					}

					Boolean already_exists = false;
					for (coach p : DataUtils.coaches) {
						if (p.getNan().equals(pNan))
							already_exists = true;
					}
					
					if (already_exists) {
						NotifyUtils.error("This coach already exists, please check the name and the code again!", "User Error!");
	                    FileUtils.logToFile("User Error!, This coach already exists, please check the name and the code again!");
	                    return;
					}	
					
					coach newCoach = new coach(pName, pSurname, pNan, pAge);
					
					DataUtils.coaches.add(newCoach);
					
					coachTeam.setCoach(newCoach);
					
					MainForm.onTeamsChanged(); // Trigger coach update event

			        FileUtils.logToFile("Added new coach to team "+ tName + " with name: " + pName + ", " + Arrays.toString(pSurname));
					NotifyUtils.succeed("Successfully added "+ pName + " to " + tName, null);
					close();
				}
			});
			
			WindowUtils.setWindowButtonStyle(windowButtons);
		}
}
