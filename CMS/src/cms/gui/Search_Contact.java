package cms.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cms.dbtask.DbConnection;

import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Search_Contact extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txt_name;
	private JTextField txt_email;
	private JTextField txt_phone;
	private JTextArea txt_address;
	private JTextField txtOr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Search_Contact frame = new Search_Contact();
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
	public Search_Contact() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Search_Contact.class.getResource("/cms/image/icon_1.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 627, 543);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(79, 174, 176));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);// to place a window in center
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 21));
		lblNewLabel.setBounds(68, 68, 84, 23);
		contentPane.add(lblNewLabel);
		
		txt_name = new JTextField();
		txt_name.setBounds(217, 67, 159, 23);
		contentPane.add(txt_name);
		txt_name.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Email");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 21));
		lblNewLabel_1.setBounds(68, 122, 72, 20);
		contentPane.add(lblNewLabel_1);
		
		txt_email = new JTextField();
		txt_email.setBounds(217, 121, 159, 21);
		contentPane.add(txt_email);
		txt_email.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Phone no");
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 21));
		lblNewLabel_2.setBounds(68, 171, 84, 20);
		contentPane.add(lblNewLabel_2);
		
		txt_phone = new JTextField();
		txt_phone.setBounds(217, 170, 159, 21);
		contentPane.add(txt_phone);
		txt_phone.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Address");
		lblNewLabel_3.setFont(new Font("Calibri", Font.BOLD, 21));
		lblNewLabel_3.setBounds(68, 225, 84, 23);
		contentPane.add(lblNewLabel_3);
		
		 txt_address = new JTextArea();
		txt_address.setBounds(217, 223, 159, 92);
		contentPane.add(txt_address);
		
		JButton btn_submit = new JButton("Search");
		btn_submit.addActionListener(this);
		btn_submit.setFont(new Font("Calibri", Font.BOLD, 21));
		btn_submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_submit.setBounds(138, 343, 112, 23);
		contentPane.add(btn_submit);
		
		txtOr = new JTextField();
		txtOr.setFont(new Font("Calibri", Font.BOLD, 19));
		txtOr.setText("OR");
		txtOr.setBounds(395, 87, 33, 23);
		contentPane.add(txtOr);
		txtOr.setColumns(10);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String name = txt_name.getText().trim();
		
		String phone=txt_phone.getText().trim();
		
		
		if(name.isEmpty()  && phone.isEmpty()  ) {
			JOptionPane.showMessageDialog(this, "Please provide the accurate details");
		}
		else {
			Connection con = DbConnection.openConnection();
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				String serach="select * from contact_details where name=? or phone1=?" ;
				
				ps=con.prepareStatement(serach);
				ps.setString(1, name);
				ps.setString(2, phone);
				
				
				rs =ps.executeQuery();
				if(rs.next()) {
					String cname=rs.getString("name");
					String ph=rs.getString("phone1");
					String ema=rs.getString("email");
					String add=rs.getString("address");
					
					txt_name.setText( cname);
					txt_phone.setText( ph);
					txt_email.setText( ema);
					
					txt_address.setText(add);
					
			
					
				}
				else {
					JOptionPane.showMessageDialog(this, "Contact are not available");
				}
			}
			catch (SQLException se) {
				se.printStackTrace();
			}
			finally {
				try {
					if(ps!=null)
						ps.close();
					if(rs!=null)
						rs.close();
				}
				catch(SQLException se){
					se.printStackTrace();
				}}
			
			}
			
		
	}
}
