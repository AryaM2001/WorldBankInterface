package httpTest;
import java.util.Arrays;
import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
/**
 * Login Proxy class that extends Jframe.
 *  First look for the user into the system interface. It runs through using the login object.
 * 
 * @author Jasmine C., Darshil Choksi, Arya Mattu, Zane Chiang
 *
 */
public class loginProxy extends JFrame {
	String Username;
	String Password;
	String Filename;
	login loginObj;
	static ObserverManager obManager;
	static DropdownObserver dropObserver;
	private JButton logButton;

	/**
	 * Constructor for loginProxy.
	 * Creates new object from loginObj class
	 */
	public loginProxy() {
		loginObj = new login();
	}

	/**
	 * Method for initializing the window for login
	 */
	public void initializer() {
		loginObj.windowInitializer();

	}
	
	/**
	 * Main Method for loginProxy. 
	 * @param args
	 * Builds Session object and updates observer methods
	 */
	public static void main(String[] args) {
		System.out.println("main method");
		Session sess = new Session(0);
		obManager = ObserverManager.getInstance();
		dropObserver = new DropdownObserver(sess);
		obManager.register(dropObserver);
		
	}

}