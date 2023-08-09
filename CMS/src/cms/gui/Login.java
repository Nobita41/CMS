package cms.gui;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.*;
public class Login extends JFrame  implements ActionListener // ActionListner Step 1
{

	private JPanel contentPane;
	private JTextField txt_id;
	private JPasswordField txt_Password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/cms/image/icon_1.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 429, 347);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(79, 174, 176));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txt_id = new JTextField();
		txt_id.setForeground(new Color(255, 0, 0));
		txt_id.setBounds(131, 88, 166, 30);
		contentPane.add(txt_id);
		txt_id.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("id");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 19));
		lblNewLabel.setBounds(21, 93, 71, 22);
		contentPane.add(lblNewLabel);
		
		txt_Password = new JPasswordField();
		txt_Password.setForeground(new Color(255, 0, 0));
		txt_Password.setBounds(131, 140, 166, 26);
		contentPane.add(txt_Password);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 19));
		lblNewLabel_1.setBounds(10, 139, 100, 30);
		contentPane.add(lblNewLabel_1);
		
		JButton btn_Submit = new JButton("Submit");
		btn_Submit.addActionListener(this); // step 2 this keyword is a run time interface
		btn_Submit.setBounds(158, 188, 89, 23);
		contentPane.add(btn_Submit);
		
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/cms/image/icons8-login-96.png")));
		lblNewLabel_2.setBounds(158, 15, 106, 62);
		contentPane.add(lblNewLabel_2);
	}

	@Override
	public void actionPerformed(ActionEvent e) // Step 3
	{
		// TODO Auto-generated method stub
//		System.out.println("Button is being clicked");
		String id=txt_id.getText().trim(); // trim method use for removing space start aur last lka
			char[] pass = txt_Password.getPassword();
			String password=String.valueOf(pass).trim();
			
//			System.out.println(id+password);
			if(id.length()==0 || password.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please provide ID and password");
			}
			else if(id.equalsIgnoreCase("raj") || password.equals("Sharma")){
				
				User_DashBoard user = new User_DashBoard();
				user.setVisible(true);
				this.dispose();
			}
			else {
				JOptionPane.showMessageDialog(this, "Invalid Credentials", "Login Error", JOptionPane.ERROR_MESSAGE);
			}
			
			
	}
}
