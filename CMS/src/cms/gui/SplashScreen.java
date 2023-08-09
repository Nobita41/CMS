package cms.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;

public class SplashScreen extends JFrame {

	private JPanel contentPane;
	private JTextField txtHelloBuddy;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SplashScreen frame = new SplashScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void loadFrame() {
	// we wish to create a thread using anonymous local inner class
		// new is refers to the Runnable interface where  runnable make under the class
		Thread t = new Thread(
				
				new Runnable() { // anonymous class object
					
						// anonymous class body
						
					

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(3000);
							Login login= new Login();
							login.setVisible(true);
							SplashScreen.this.dispose(); // outer class object
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
					
					
				
				});// Thread class constructor close
		t.start();
		
	}
	public SplashScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 733, 561);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(79, 174, 176));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtHelloBuddy = new JTextField();
		txtHelloBuddy.setBackground(new Color(79, 174, 176));
		txtHelloBuddy.setForeground(new Color(0, 0, 0));
		txtHelloBuddy.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 27));
		txtHelloBuddy.setText("Hello Buddy !!!!\r\n");
		txtHelloBuddy.setBounds(199, 198, 290, 83);
		contentPane.add(txtHelloBuddy);
		txtHelloBuddy.setColumns(10);
		loadFrame();
	}

}
