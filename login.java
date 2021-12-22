package httpTest;
import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
/**
 * Login Class that implements the ActionListener Class. 
 * This class is in charge of building the login class window for the user.
 * @author  Jasmine C., Darshil Choksi, Arya Mattu, Zane Chiang
 *
 */
public class login extends JFrame implements ActionListener {
	private JLabel userLabel, passwordLabel, validateLabel;
	private JTextField userText;
	private JPasswordField passWord;
	private ImageIcon valid, invalid;
	static JLabel message;
	private JButton logButton;
	private static Scanner sc;
	String userName = "";
	String password = "";
	String fileName = "credentials.txt";
	boolean found;

	/**
	 * Constructor for Login Class. Builds the frame for taking the user's login information
	 */
	public login() {
		super("Login ");
		setLayout(new FlowLayout());
		found = false;
		userLabel = new JLabel("Username: ");
		passwordLabel = new JLabel("Password: ");
		validateLabel = new JLabel();
		userText = new JTextField(15);
		passWord = new JPasswordField(15);
		logButton = new JButton("Submit!");
		logButton.addActionListener(this);
		message = new JLabel();
		add(userLabel);
		add(userText);
		add(passwordLabel);
		add(passWord);
		add(message);
		add(logButton);
		add(validateLabel);
	}
/**
 * Method for initializing the login window
*
 */
	public void windowInitializer() {
		login window = new login();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(300, 180);
		window.setVisible(true);
	}
/**
 * Returns the username
 * @return String userName
 */
	public String getUsername() {
		return userName;
	}
/**
 * Returns the username
 * @return String password
 */
	public String getPassword() {
		return password;
	}
/**
 * Returns the FileName 
 * @return
 */
	public String getFilename() {
		return fileName;
	}

/**
 * Method for verifying login credentials 
 * @param username First parameter of String username
 * @param PassWord Second parameter of String password
 * @return Boolean This returns boolean of whether login is valid
 */
	public boolean verifyLogin(String username, String PassWord) {
		String tempUsername = "";
		String tempPassword = "";

		try {
			sc = new Scanner(new File(fileName));
			sc.useDelimiter("[,\n]");

			while (sc.hasNext() && !found) {
				tempUsername = sc.next();
				tempPassword = sc.next();

				if (tempUsername.trim().equals(username.trim()) && tempPassword.trim().equals(PassWord.trim())) {
					found = true;
				}

			}
			sc.close();
			if (found == true)
			{
				message.setText(" Welcome");
				this.dispose();
				JFrame frame = MainUI.getInstance();
				frame.setSize(900, 600);
				frame.pack();
				frame.setVisible(true);
			}

			else
			{
				message.setText(" Incorrect Username or Password ");
				this.dispose();
				login.infoBox("Credentials you entered were invalid. Program will terminate", "ERROR");
			}

			return found;
		}

		catch (Exception e) {
			System.out.println("Error");
			return false;
		}
	}
	/**
	 * Method for giving information in the form of a pop up for the user
	 * @param infoMessage First Parameter is a String of the message
	 * @param titleBar Second Parameter is a String for the title of the pop up box
	 */
	public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

	/**
	 * Method for the action listener placed on the submit button. Initializes when said button is pressed. 
	 */
	//@Override
	public void actionPerformed(ActionEvent e) {
		userName = userText.getText();
		password = passWord.getText();
		verifyLogin(userName, password);


	}

}