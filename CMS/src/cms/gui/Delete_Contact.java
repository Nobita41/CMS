package cms.gui;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cms.dbtask.DbConnection;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;

public class Delete_Contact extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txt_Phone_No;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Delete_Contact frame = new Delete_Contact();
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
	public Delete_Contact() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Delete_Contact.class.getResource("/cms/image/icon_1.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 689, 523);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(79, 174, 176));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Phone_No ");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 19));
		lblNewLabel.setBounds(94, 83, 144, 29);
		contentPane.add(lblNewLabel);
		
		txt_Phone_No = new JTextField();
		txt_Phone_No.setFont(new Font("Calibri", Font.BOLD, 18));
		txt_Phone_No.setBounds(271, 80, 211, 32);
		contentPane.add(txt_Phone_No);
		txt_Phone_No.setColumns(10);
		
		JButton btn_Delete = new JButton("Delete");
		btn_Delete.addActionListener(this);
		btn_Delete.setFont(new Font("Calibri", Font.BOLD, 19));
		btn_Delete.setBounds(230, 154, 119, 32);
		contentPane.add(btn_Delete);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String ph= txt_Phone_No.getText();
		if(ph.length()==0) {
			JOptionPane.showMessageDialog(this, "please provide the number");
		}
		else 
		{
			int choice =JOptionPane.showConfirmDialog(this, "are you sure delete this"  + ph +"number");
//			System.out.println(choice);
			if(choice==0) {
				// deletion code
				Connection con = DbConnection.openConnection(); // db connecction established 
				PreparedStatement ps = null;
				
						
				
				try {
					String delete_query ="delete from contact_details where phone1=?";
					ps= con.prepareStatement(delete_query);
					ps.setString(1, ph);
//					System.out.println(ps);
					int status=	ps.executeUpdate();
					// agr value store ho gyi hai to status variable poistive value arises kregi
					if(status >0) {
						JOptionPane.showMessageDialog(this, ph + "Contact delete successfully" );
					}
					else {
						JOptionPane.showMessageDialog(this, "No such" + ph + "Number exists" );
					}
				}

				catch(SQLException se)
				
				{
					se.printStackTrace();
				}
				
			}
			
		}
	}
}
