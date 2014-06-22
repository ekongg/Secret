import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Present extends JFrame {
	private static final long serialVersionUID = 1L;
	private static JButton openPresent;

	private static AudioInputStream stream2;
	private static AudioFormat x2;
	private static Clip clip2;

	public Present() {
		super("Gift Time");
		openPresent = new JButton();
		// ImageIcon image = new
		// ImageIcon(this.getClass().getResource("resources/present.jpg"));
		ImageIcon image = new ImageIcon("resources/present.jpg");
		openPresent.setIcon(image);
		openPresent.setPreferredSize(new Dimension(200, 200));

		openPresent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent ae) {
				
				try {
					stream2 = AudioSystem
					        .getAudioInputStream(this.getClass().getResource("resources/happybirthday.wav"));
					x2 = stream2.getFormat();
				} catch (UnsupportedAudioFileException | IOException e) {
					JOptionPane.showMessageDialog(null,
					        "Error: Music audio file cannot be played.");
				}
				DataLine.Info info = new DataLine.Info(Clip.class, x2);
				try {
					clip2 = (Clip) AudioSystem.getLine(info);
					clip2.open(stream2);
				} catch (LineUnavailableException | IOException e) {
					JOptionPane.showMessageDialog(null,
					        "Error: Music audio file cannot be played.");
				}
				clip2.loop(100);
				
				ImageIcon lastImg = Client
				        .getImageIcon("resources/steam.png");
				JOptionPane.showMessageDialog(null, "Steam Code:\nEQKWK-RY9P9-YGLTD",
				        "HAPPY BIRTHDAY!", JOptionPane.INFORMATION_MESSAGE,
				        lastImg);
			}
		});

		final Container mainPanel = getContentPane();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(new JLabel("Happy Birthday Robert!"),
		        BorderLayout.PAGE_START);
		mainPanel.add(openPresent, BorderLayout.CENTER);

		pack();
		// setIconImage(new ImageIcon(this.getClass().getResource(
		// "resources/rob2.PNG")).getImage());
		ImageIcon img = Client.getImageIcon("resources/rob2.png");
		setIconImage(img.getImage());

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		Client.setDefaultUI();
		setVisible(true);
	}
}
