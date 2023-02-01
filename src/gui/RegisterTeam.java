package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import teams.team;
import utils.DataUtils;
import utils.FileUtils;
import utils.NotifyUtils;
import utils.WindowUtils;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class RegisterTeam extends JFrame {

	static RegisterTeam frame;
	private ArrayList<JButton> windowButtons = new ArrayList<JButton>();
	private JPanel contentPanel;
	private JTextField teamNameTxb;
	private JTextField teamCodeTxb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new RegisterTeam();
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
	public RegisterTeam() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainForm.class.getResource("/assets/painballogo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) { } // https://stackoverflow.com/a/11426036/15384495
        setTitle("Paintball Fed 2023 | Register Team");
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
		
		JLabel lblCreateTeam = new JLabel("Create Team");
		lblCreateTeam.setForeground(new Color(255, 255, 255));
		lblCreateTeam.setBackground(new Color(45, 45, 48));
		lblCreateTeam.setFont(new Font("Arial", Font.BOLD, 25));
		lblCreateTeam.setVerticalAlignment(SwingConstants.CENTER);
		lblCreateTeam.setBounds(209, 0, 149, 50);
		header.add(lblCreateTeam);
		
		JButton closeButton = new JButton("");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.exit(0);
				 close();
			}
		});
		closeButton.setIcon(new ImageIcon(RegisterTeam.class.getResource("/assets/close-24.png")));
		closeButton.setBackground(new Color(45, 45, 48));
		closeButton.setBounds(555, 0, 24, 24);
		header.add(closeButton);
		windowButtons.add(closeButton);
		
		
		teamNameTxb = new JTextField();
		teamNameTxb.setForeground(new Color(255, 255, 255));
		teamNameTxb.setBackground(new Color(62, 62, 66));
		teamNameTxb.setBounds(272, 78, 196, 30);
		contentPanel.add(teamNameTxb);
		teamNameTxb.setColumns(10);
		
		JLabel lblTeamName = new JLabel("Team Name: ");
		lblTeamName.setVerticalAlignment(SwingConstants.CENTER);
		lblTeamName.setForeground(Color.WHITE);
		lblTeamName.setFont(new Font("Arial", Font.BOLD, 20));
		lblTeamName.setBackground(new Color(45, 45, 48));
		lblTeamName.setBounds(109, 76, 149, 32);
		contentPanel.add(lblTeamName);
		
		JLabel lblTeamCode = new JLabel("Team Code: ");
		lblTeamCode.setVerticalAlignment(SwingConstants.CENTER);
		lblTeamCode.setForeground(Color.WHITE);
		lblTeamCode.setFont(new Font("Arial", Font.BOLD, 20));
		lblTeamCode.setBackground(new Color(45, 45, 48));
		lblTeamCode.setBounds(109, 126, 149, 32);
		contentPanel.add(lblTeamCode);
		
		teamCodeTxb = new JTextField();
		teamCodeTxb.setForeground(new Color(255, 255, 255));
		teamCodeTxb.setBackground(new Color(62, 62, 66));
		teamCodeTxb.setColumns(10);
		teamCodeTxb.setBounds(272, 128, 196, 30);
		teamCodeTxb.setEnabled(false);
		contentPanel.add(teamCodeTxb);
		
		JCheckBox generateCodeChckbx = new JCheckBox("Generate Code");
		generateCodeChckbx.setForeground(new Color(255, 255, 255));
		generateCodeChckbx.setBackground(new Color(37, 37, 38));
		generateCodeChckbx.setSelected(true);
		generateCodeChckbx.setBounds(272, 175, 196, 21);
		contentPanel.add(generateCodeChckbx);
		
		JButton btnNewButton = new JButton("Create Team");
		btnNewButton.setBackground(new Color(70, 70, 75));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(109, 241, 359, 38);
		contentPanel.add(btnNewButton);
		windowButtons.add(btnNewButton);
		
		teamNameTxb.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (generateCodeChckbx.isSelected()) {
                    String str = teamNameTxb.getText().replace(" ", ""); // to filter white spaces
                    String code = "";
                    if (str.length() == 0)
                        return;
                    Random random = new Random();
                    for (int i = 0; i <= 3; i++) {
                        int index = random.nextInt(str.length());
                        code += String.valueOf(str.charAt(index));
                        teamCodeTxb.setText(code);
                    }
                }
            }
        });
		
		teamCodeTxb.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                String str = teamCodeTxb.getText().replace(" ", "");
                if (str.length() >= 4) {
                    String text = "";
                    for (int i = 0; i < 3; i++) {
                        text += String.valueOf(str.charAt(i));
                    }
                    teamCodeTxb.setText(text);
                }
            }
        });
		
		generateCodeChckbx.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
            	teamCodeTxb.setEnabled(e.getStateChange() == 1 ? false : true); // 1 it's true, 2 it's false, so we reverse it to invert it
            }
        });
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String tName = teamNameTxb.getText().replace(" ", "-");
				String tCode = teamCodeTxb.getText().replace(" ", "-");
				
				if (tName.length() < 4 || tName.length() > 15) {
					NotifyUtils.error("The team name is invalid, please write at least 4 characters and less than 15 characters.", "User Error!");
                    FileUtils.logToFile("User Error!, The team name is invalid, please write at least 4 characters and less than 15 characters.");
                    return;
				} else if (tCode.length() != 4) {
					NotifyUtils.error("The team code must be 4 characters length", "User Error!");
                    FileUtils.logToFile("User Error!, The team code must be 4 characters length");
                    return;
				}

				Boolean already_exists = false;
				for (team t : DataUtils.teams) {
					if (t.getName().equals(tName) || t.getCode().equals(tCode))
						already_exists = true;
				}
				
				if (already_exists) {
					NotifyUtils.error("This team already exists, please check the name and the code again!", "User Error!");
                    FileUtils.logToFile("User Error!, This team already exists, please check the name and the code again!");
                    return;
				}
				
				DataUtils.teams.add(new team(tName, tCode));
				MainForm.onTeamsChanged(); // Trigger team update event
				
		        FileUtils.logToFile("Added new team with name: " + tName + " and code: " + tCode);
		        NotifyUtils.succeed("Successfully created team " +tName, null);
			}
		});
		
		WindowUtils.setWindowButtonStyle(windowButtons);
	}
}
