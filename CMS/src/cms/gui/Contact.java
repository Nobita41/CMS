package cms.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardDownRightHandler;

import cms.dbtask.DbConnection;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.*;
import java.awt.Font;
import java.awt.KeyboardFocusManager;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.sql.*;
public class Contact extends JFrame implements ActionListener, KeyListener {
	// KeyListener is use to

	private JPanel contentPane;
	private JTextField txt_name;
	private JTextField txt_email;
	private JTextField txt_Phone1;
	private JTextField txt_Phone2;
	private JTextArea txt_address;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Contact frame = new Contact();
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
	public Contact() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Contact.class.getResource("/cms/image/icon_1.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 752, 558);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(79, 174, 176));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 21));
		lblNewLabel.setBounds(49, 107, 111, 35);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("email");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 21));
		lblNewLabel_1.setBounds(49, 168, 57, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Phone 1");
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 21));
		lblNewLabel_2.setBounds(49, 221, 79, 24);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Phone 2");
		lblNewLabel_3.setFont(new Font("Calibri", Font.BOLD, 21));
		lblNewLabel_3.setBounds(49, 292, 79, 24);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Address");
		lblNewLabel_4.setFont(new Font("Calibri", Font.BOLD, 21));
		lblNewLabel_4.setBounds(49, 370, 93, 35);
		contentPane.add(lblNewLabel_4);
		
		txt_name = new JTextField();
		txt_name.addKeyListener(this);
		txt_name.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt_name.setBackground(new Color(255, 255, 255));
		txt_name.setForeground(new Color(0, 0, 0));
		txt_name.setBounds(238, 106, 312, 35);
		contentPane.add(txt_name);
		txt_name.setColumns(10);
		
		txt_email = new JTextField();
		txt_email.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt_email.setBounds(238, 161, 312, 36);
		contentPane.add(txt_email);
		txt_email.setColumns(10);
		
		txt_Phone1 = new JTextField();
		txt_Phone1.addKeyListener(this);
		txt_Phone1.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt_Phone1.setBounds(238, 215, 312, 35);
		contentPane.add(txt_Phone1);
		txt_Phone1.setColumns(10);
		
		txt_Phone2 = new JTextField();
		txt_Phone2.addKeyListener(this);
		txt_Phone2.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt_Phone2.setBounds(238, 271, 312, 35);
		contentPane.add(txt_Phone2);
		txt_Phone2.setColumns(10);
		
		 txt_address = new JTextArea();
		 txt_address.addKeyListener(this);
		txt_address.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt_address.setBounds(238, 339, 312, 89);
		contentPane.add(txt_address);
		
		JButton btn_submit = new JButton("Submit");
		btn_submit.setFont(new Font("Calibri", Font.BOLD, 21));
		btn_submit.addActionListener(this); 
		btn_submit.addKeyListener(this);// that is step 2 button ko click kiye hai
		btn_submit.setBounds(337, 482, 111, 24);
		contentPane.add(btn_submit);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(Contact.class.getResource("/cms/image/login2.png")));
		lblNewLabel_5.setBounds(325, 11, 123, 84);
		contentPane.add(lblNewLabel_5);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		addData();
	}
	
	void addData() {
		String n = txt_name.getText();
		String em = txt_email.getText();
		String ph1= txt_Phone1.getText();
		String ph2 = txt_Phone2.getText();	
		String add = txt_address.getText();
		
		if(n.length()==0 || em.length()==0 || ph1.length()==0 && ph2.length()==0 || add.length()==0)
			// if (name.isEmpty()
		 {
			JOptionPane.showMessageDialog(this, "please provide the details");
		}
		
		else {
			Connection con = DbConnection.openConnection();
			PreparedStatement ps = null;
			try {
				
				String insert_query="insert into contact_details(name, email, phone1, phone2, address, date)values(?,?,?,?,?,?)";
				ps=con.prepareStatement(insert_query);   // method of connection interface
//				(Passes the queries to DBMS compiler compilse the query and store intp a buffer-> referd by ps)
				java.util.Date d= new java.util.Date(); // current date
				
				// conversion of util date in sql date
				java.sql.Date sd= new java.sql.Date(d.getTime()); // it convert the util date into sql date in long form 
				ps.setString(1, n);// value fatch from control are getting set of particular column
				ps.setString(2, em);
				ps.setString(3, ph1);
				ps.setString(4, ph2);
				ps.setString(5, add);
				ps.setDate(6, sd);
//				System.out.println(ps);
				
			int status=	ps.executeUpdate();// fire insert/update / delete query
			// agr value store ho gyi hai to status variable poistive value arises kregi
			if(status >0) {
				JOptionPane.showMessageDialog(this, "Details added successfully");
				txt_name.setText(" ");
				txt_email.setText(" ");
				txt_Phone1.setText(" ");
				txt_Phone2.setText(" ");
				txt_address.setText(" ");
//				txt_name.setText(" ");
//				isko likhne ka mtlb yeh hai ki hum jab submit button pai click kre to woh database mai 
//				 add hoke page ko clear kr dega
//				txt_email.setText(" ");
//				txt_Phone1.setText(" ");
//				txt_Phone2.setText(" ");
//				txt_address.setText(" ");
			}
			}
			catch(SQLException se) {
				JOptionPane.showMessageDialog(this, "Phone number alredy exists");
				se.printStackTrace();
			}
			finally
			{
				try {
					if(ps!=null)
						ps.close();
					if(con!=null)
						ps.close();
				}
				catch(SQLException se){
					se.printStackTrace();
				}
			}
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
		char c = e.getKeyChar();// method of key
	
//		System.out.println(c);
		if(e.getSource()== txt_name) {
			//getSource() --> is return the object that is generating event
			if(!(Character.isAlphabetic(c) || c == KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE) || c == KeyEvent.VK_SPACE )  {
				e.consume();
				JOptionPane.showMessageDialog(this, "Only alphabets are allow");
				
			}
			
			
		}
		else if(e.getSource()==txt_Phone1 || e.getSource()== txt_Phone2) {
			if(!(Character.isDigit(c))) {
				e.consume();
				JOptionPane.showMessageDialog(this, "Only digit are allow");
			}
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==10) {
			addData();
		}
//		// TODO Auto-generated method stub
//		int code = e.getKeyCode();// method of key
//		System.out.println(code);
//		tab se bhar niklne ke liye  address box se 
		if(e.getSource()==txt_address) {
		if(e.getKeyCode()==KeyEvent.VK_TAB) {
			e.consume();
			KeyboardFocusManager
			.getCurrentKeyboardFocusManager().focusNextComponent();
		}
		}
	}				

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
	}
}
