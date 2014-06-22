import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Client extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JTextField usernameField, passwordField;
	private static JButton submit;
	final String NAME = "Robert", PASSWORD = "sleepingbobbykim";

	public Client() {
		super("Happy Birthday!");
		
		usernameField = new JTextField(10);
		passwordField = new JTextField(10);
		
		submit = new JButton();
		// ImageIcon buttonImage = new
		// ImageIcon(this.getClass().getResource("resources/robert.jpg"));
		ImageIcon buttonImage = getImageIcon("resources/robert.jpg");
		submit.setIcon(buttonImage);
		submit.setPreferredSize(new Dimension(60, 70));

		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent ae) {
				if (check() == true) {
					new Present();
				}
			}
		});

		JRootPane rootPane = getRootPane();
		rootPane.setDefaultButton(submit);

		final JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(2, 2));
		inputPanel.add(new JLabel("Username:"));
		inputPanel.add(usernameField);
		inputPanel.add(new JLabel("Password:"));
		inputPanel.add(passwordField);

		final JPanel buttonPanel = new JPanel();
		buttonPanel.add(submit);

		final Container mainPanel = getContentPane();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(inputPanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);

		pack();
		// setIconImage(new ImageIcon(this.getClass().getResource(
		// "resources/rob2.PNG")).getImage());
		ImageIcon img = getImageIcon("resources/rob2.png");
		setIconImage(img.getImage());

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultUI();
		setVisible(true);
	}

	public static void setDefaultUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
			        "Error: Look and Feel not set.", "Error",
			        JOptionPane.ERROR_MESSAGE);
		}
	}
	
	 public static ImageIcon getImageIcon(final String filename) {
	        ImageIcon icon;
	        final URL url = Client.class.getResource(filename);
	        if (url != null) {
	            icon = new ImageIcon(url);
	        } else {
	            icon = new ImageIcon(filename);
	            
	            if ((icon == null) || (icon.getImageLoadStatus() != MediaTracker.COMPLETE)) {
	                try {
	                    icon = new ImageIcon(new URL(filename));
	                } catch (final MalformedURLException murle) {
	                    return null;
	                }
	            }
	        }
	        return icon;
	    }
	
	public boolean check() {
		String user = usernameField.getText().trim();
		String pass = passwordField.getText().trim();

		if (pass.equals("") || user.equals("")) {
			JOptionPane.showMessageDialog(null,
			        "Error: Please fill in all fields to continue.", "ERROR",
			        JOptionPane.ERROR_MESSAGE);
		} else {
			if (user.equalsIgnoreCase(NAME) && pass.equalsIgnoreCase(PASSWORD)) {
				return true;
			}
			JOptionPane
			        .showMessageDialog(
			                null,
			                "Error: Access Denied. Incorrect credentials have been provided.",
			                "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return false;

	}

	public static void main(String[] args) {
		new Client();
	}
}
