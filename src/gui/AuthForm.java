package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import auth.AuthUtils;
import auth.user;
import utils.FileUtils;
import utils.NotifyUtils;
import utils.WindowUtils;
import javax.swing.JLayeredPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AuthForm extends JFrame {

	public static boolean success = false;
	public static String username = "";
	
	private JPanel contentPane;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static AuthForm frame;
	private ArrayList<JButton> windowButtons = new ArrayList<JButton>();
	private JTextField loginUsername;
	private JTextField loginPassword;
	private JTextField registerUsername;
	private JTextField registerPassword;
	private JTextField registerPasswordrepeat;
	private JTextField registerEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new AuthForm();
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
	
	/*
	 * 		setFont(new Font("Arial", Font.PLAIN, 12));
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
	 */
	public AuthForm() {
		setTitle("Paintball Fed 2023 | Auth");
		setFont(new Font("Arial", Font.BOLD, 15));
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainForm.class.getResource("/assets/painballogo.png")));
		int x = (int) (screenSize.getWidth() / 2 - 780 / 2); // Center x
		int y = (int) (screenSize.getHeight() / 2 - 450 / 2); // Center y
		setBounds(100, 100, 350, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0, 0, 350, 450);
		contentPane.add(layeredPane);
		
		JPanel loginPanel = new JPanel();
		layeredPane.setLayer(loginPanel, 1);
		loginPanel.setBackground(new Color(37, 37, 38));
		loginPanel.setBounds(0, 0, 350, 450);
		layeredPane.add(loginPanel);
		loginPanel.setLayout(null);
		
		JLabel loginTitleLbl = new JLabel("Login");
		loginTitleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		loginTitleLbl.setForeground(new Color(255, 255, 255));
		loginTitleLbl.setFont(new Font("Arial", Font.BOLD, 25));
		loginTitleLbl.setBounds(0, 0, 350, 55);
		loginPanel.add(loginTitleLbl);
		
		JLabel loginUsernamelbl = new JLabel("Erabiltzailea:"); // Username:
		loginUsernamelbl.setForeground(new Color(255, 255, 255));
		loginUsernamelbl.setFont(new Font("Arial", Font.BOLD, 15));
		loginUsernamelbl.setBounds(36, 223, 122, 25);
		loginPanel.add(loginUsernamelbl);
		
		loginUsername = new JTextField();
		loginUsername.setForeground(new Color(255, 255, 255));
		loginUsername.setFont(new Font("Consolas", Font.BOLD, 12));
		loginUsername.setBackground(new Color(62, 62, 62));
		loginUsername.setBounds(136, 226, 186, 25);
		loginPanel.add(loginUsername);
		loginUsername.setColumns(10);
		
		JLabel loginPasswordlbl = new JLabel("Pasahitza:"); // Password:
		loginPasswordlbl.setForeground(Color.WHITE);
		loginPasswordlbl.setFont(new Font("Arial", Font.BOLD, 15));
		loginPasswordlbl.setBounds(36, 268, 90, 25);
		loginPanel.add(loginPasswordlbl);
		
		loginPassword = new JTextField();
		loginPassword.setFont(new Font("Consolas", Font.BOLD, 12));
		loginPassword.setForeground(new Color(255, 255, 255));
		loginPassword.setColumns(10);
		loginPassword.setBackground(new Color(62, 62, 62));
		loginPassword.setBounds(135, 271, 186, 25);
		loginPanel.add(loginPassword);
		
		JCheckBox rememberLoginCredentials = new JCheckBox("Gogoratu"); // Remember Login
		rememberLoginCredentials.setSelected(true);
		rememberLoginCredentials.setToolTipText("Remember login credentials for the next  time");
		rememberLoginCredentials.setFont(new Font("Arial", Font.BOLD, 15));
		rememberLoginCredentials.setBackground(new Color(37, 37, 38));
		rememberLoginCredentials.setForeground(new Color(255, 255, 255));
		rememberLoginCredentials.setBounds(36, 315, 286, 21);
		loginPanel.add(rememberLoginCredentials);
		
		JButton loginAuthenticate = new JButton("Sartu"); // Authenticate
		loginAuthenticate.setFont(new Font("Arial", Font.BOLD, 16));
		loginAuthenticate.setBackground(new Color(62, 62, 62));
		loginAuthenticate.setForeground(new Color(255, 255, 255));
		loginAuthenticate.setBounds(102, 370, 220, 55);
		loginPanel.add(loginAuthenticate);
		windowButtons.add(loginAuthenticate);
		
		JButton loginBannerButton = new JButton("");
		loginBannerButton.setIcon(new ImageIcon(AuthForm.class.getResource("/assets/painballogo.png")));
		loginBannerButton.setBackground(new Color(37, 37, 38));
		loginBannerButton.setBounds(0, 55, 350, 111);
		loginPanel.add(loginBannerButton);
		windowButtons.add(loginBannerButton);
		
		JButton closeButtonLogin = new JButton("");
		closeButtonLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		closeButtonLogin.setIcon(new ImageIcon(AuthForm.class.getResource("/assets/close-24.png")));
		closeButtonLogin.setBackground(new Color(45, 45, 48));
		closeButtonLogin.setBounds(320, 5, 24, 24);
		loginPanel.add(closeButtonLogin);
		windowButtons.add(closeButtonLogin);
		
		JPanel registerPanel = new JPanel();
		layeredPane.setLayer(registerPanel, 0);
		registerPanel.setBackground(new Color(37, 37, 38));
		registerPanel.setBounds(0, 0, 350, 450);
		layeredPane.add(registerPanel);
		registerPanel.setLayout(null);
		
		JButton gotoRegister = new JButton("");
		gotoRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginPanel.setVisible(false);
				registerPanel.setVisible(true);
			}
		});
		gotoRegister.setIcon(new ImageIcon(AuthForm.class.getResource("/assets/icons8-writer-male-24.png")));
		gotoRegister.setToolTipText("register");
		gotoRegister.setBackground(new Color(62, 62, 62));
		gotoRegister.setBounds(36, 370, 69, 55);
		loginPanel.add(gotoRegister);
		windowButtons.add(gotoRegister);
		
		JLabel registerTitlelbl = new JLabel("Sortu"); // Register
		registerTitlelbl.setHorizontalAlignment(SwingConstants.CENTER);
		registerTitlelbl.setForeground(Color.WHITE);
		registerTitlelbl.setFont(new Font("Arial", Font.BOLD, 25));
		registerTitlelbl.setBounds(0, 0, 350, 55);
		registerPanel.add(registerTitlelbl);
		
		JLabel registerUsernamelbl = new JLabel("Erabiltzailea:"); // Username:
		registerUsernamelbl.setForeground(Color.WHITE);
		registerUsernamelbl.setFont(new Font("Arial", Font.BOLD, 15));
		registerUsernamelbl.setBounds(36, 182, 102, 25);
		registerPanel.add(registerUsernamelbl);
		
		registerUsername = new JTextField();
		registerUsername.setForeground(Color.WHITE);
		registerUsername.setFont(new Font("Consolas", Font.BOLD, 12));
		registerUsername.setColumns(10);
		registerUsername.setBackground(new Color(62, 62, 62));
		registerUsername.setBounds(136, 182, 186, 25);
		registerPanel.add(registerUsername);
		
		JLabel registerPasswordlbl = new JLabel("Pasahitza:"); //Password:
		registerPasswordlbl.setForeground(Color.WHITE);
		registerPasswordlbl.setFont(new Font("Arial", Font.BOLD, 15));
		registerPasswordlbl.setBounds(37, 252, 90, 25);
		registerPanel.add(registerPasswordlbl);
		
		registerPassword = new JTextField();
		registerPassword.setForeground(Color.WHITE);
		registerPassword.setFont(new Font("Consolas", Font.BOLD, 12));
		registerPassword.setColumns(10);
		registerPassword.setBackground(new Color(62, 62, 62));
		registerPassword.setBounds(136, 252, 186, 25);
		registerPanel.add(registerPassword);
		
		JButton registerCreateAccount = new JButton("Sortu"); // Sing Up
		registerCreateAccount.setForeground(Color.WHITE);
		registerCreateAccount.setFont(new Font("Arial", Font.BOLD, 16));
		registerCreateAccount.setBackground(new Color(62, 62, 62));
		registerCreateAccount.setBounds(36, 370, 220, 55);
		registerPanel.add(registerCreateAccount);
		windowButtons.add(registerCreateAccount);
		
		JButton registerBannerButton = new JButton("");
		registerBannerButton.setIcon(new ImageIcon(AuthForm.class.getResource("/assets/painballogo.png")));
		registerBannerButton.setBackground(new Color(37, 37, 38));
		registerBannerButton.setBounds(0, 55, 350, 111);
		registerPanel.add(registerBannerButton);
		windowButtons.add(registerBannerButton);
		
		JLabel registerPasswordRepeatlbl = new JLabel("Pasahitza:"); // Password: 
		registerPasswordRepeatlbl.setToolTipText("Repeat password");
		registerPasswordRepeatlbl.setForeground(Color.WHITE);
		registerPasswordRepeatlbl.setFont(new Font("Arial", Font.BOLD, 15));
		registerPasswordRepeatlbl.setBounds(37, 287, 90, 25);
		registerPanel.add(registerPasswordRepeatlbl);
		
		registerPasswordrepeat = new JTextField();
		registerPasswordrepeat.setForeground(Color.WHITE);
		registerPasswordrepeat.setFont(new Font("Consolas", Font.BOLD, 12));
		registerPasswordrepeat.setColumns(10);
		registerPasswordrepeat.setBackground(new Color(62, 62, 62));
		registerPasswordrepeat.setBounds(136, 287, 186, 25);
		registerPanel.add(registerPasswordrepeat);
		
		JButton closeButtonRegister = new JButton("");
		closeButtonRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		closeButtonRegister.setIcon(new ImageIcon(AuthForm.class.getResource("/assets/close-24.png")));
		closeButtonRegister.setBackground(new Color(45, 45, 48));
		closeButtonRegister.setBounds(320, 5, 24, 24);
		registerPanel.add(closeButtonRegister);
		windowButtons.add(closeButtonRegister);
		
		JButton gotoLogin = new JButton("");
		gotoLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginPanel.setVisible(true);
				registerPanel.setVisible(false);
			}
		});
		gotoLogin.setToolTipText("login");
		gotoLogin.setBackground(new Color(37, 37, 38));
		gotoLogin.setIcon(new ImageIcon(AuthForm.class.getResource("/assets/icons8-enter-24.png")));
		gotoLogin.setBounds(253, 370, 69, 55);
		registerPanel.add(gotoLogin);
		windowButtons.add(gotoLogin);
		
		JLabel registerEmaillbl = new JLabel("Emaila:"); // Email:
		registerEmaillbl.setForeground(Color.WHITE);
		registerEmaillbl.setFont(new Font("Arial", Font.BOLD, 15));
		registerEmaillbl.setBounds(37, 217, 90, 25);
		registerPanel.add(registerEmaillbl);
		
		registerEmail = new JTextField();
		registerEmail.setForeground(Color.WHITE);
		registerEmail.setFont(new Font("Consolas", Font.BOLD, 12));
		registerEmail.setColumns(10);
		registerEmail.setBackground(new Color(62, 62, 62));
		registerEmail.setBounds(136, 217, 186, 25);
		registerPanel.add(registerEmail);
		
		
		registerCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = registerUsername.getText();
				String email = registerEmail.getText();
				String password = registerPassword.getText();
				String passwordRepeat = registerPasswordrepeat.getText();
				
				if (username.length() < 3 || username.length() > 15) {
					NotifyUtils.error("izen baliogabea. 3-15 arteko izen bat erabili.", "User Error!"); // invalid username, use between 3 and 15 characters.
                    FileUtils.logToFile("User Error!, invalid username, use between 3 and 15 characters.");
                    return;
				} else if (email.length() < 3 || !email.contains("@") || !email.contains(".")) {
					NotifyUtils.error("email txarra.", "User Error!"); // invalid email.
                    FileUtils.logToFile("User Error!, invalid email.");
                    return;
				} else if (password.length() < 3) {
					NotifyUtils.error("Pasahitza oso motxa da.", "User Error!"); // invalid password, please use a longer one.
                    FileUtils.logToFile("User Error!, invalid password, please use a longer one.");
                    return;
				} else if (!password.equals(passwordRepeat)) {
					NotifyUtils.error("Pasahitza ez dago ondo ipinita.", "User Error!"); // invalid password check, password dont match.
                    FileUtils.logToFile("User Error!, invalid password check, password dont match.");
                    return;
				}
				
				user usr = new user(username, email, password);
				
				AuthUtils.users.add(usr);
		        FileUtils.logToFile("Created new user: " + username + " with mail: " + email);
		        NotifyUtils.succeed("Successfully created user " +username, null);
		        
				loginPanel.setVisible(true);
				registerPanel.setVisible(false);
				AuthUtils.save();
			}
		});
		
		loginAuthenticate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = loginUsername.getText();
				String password = loginPassword.getText();
				
				Boolean success_login = false;
				for (user u : AuthUtils.users) {
					if (
							u.getUsername().equals(username) && 
							u.getHaxPassword().equals(password)
						) {
				        AuthUtils.logged_user = u;
						success_login = true;
					}
				}
				
				AuthUtils.writeCred(rememberLoginCredentials.isSelected(), username, password);
				
				if (success_login) {
//					MainForm.main(null);
					success = true;
			        FileUtils.logToFile("Successfully logged as " + username + ", welcome!");
			        NotifyUtils.succeed("Successfully logged as " + username + ", welcome!", null);
			        frame.close();
				} else {
			        FileUtils.logToFile("Invalid username or password!");
			        NotifyUtils.succeed("Invalid username or password!", null);
				}
			}
		});
		
		String cred = AuthUtils.readCred();
		
		if (cred != null) {
			String[] usr = cred.split(":");
			loginUsername.setText(usr[0]);
			loginPassword.setText(usr[1]);
		}
		
		
		WindowUtils.setWindowButtonStyle(windowButtons);
	}
}
