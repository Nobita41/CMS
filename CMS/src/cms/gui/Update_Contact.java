package cms.gui;

import java.awt.Color;
import java.sql.*;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import cms.dbtask.DbConnection;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Update_Contact extends JFrame implements ActionListener ,ItemListener {

	private JPanel contentPane;
	private JTextField txt_email;
	private JTextField txt_Phone1;
	private JTextField txt_Phone2;
	private JTextArea txt_address;
	private  JComboBox <String> cmb_name;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Update_Contact frame = new Update_Contact();
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
	public void filCombo(){
		PreparedStatement ps =null;
		ResultSet re = null;
		  try {
			  String select_query="select name from contact_details"; // only read name column data
			  ps=con.prepareStatement(select_query);
			  
		re= ps.executeQuery();
			  // executeQuery(); it only used to selsct query it return 

		while(re.next()== true)
			// iska use is liye kiye hai ki kitna name hai
		{
		String cname=	re.getString("name"); // fetch the value from specifed column
			System.out.println(cname);
			cmb_name.addItem(cname); // adding item in the a comboBox.
		}
		  }
		  catch(SQLException se) {
			  se.printStackTrace();
		  }
		
			  finally
				{
					try {
						if(ps!=null)
							ps.close();
						if(re!=null)
							re.close();
					}
					catch(SQLException se){
						se.printStackTrace();
					}
				}
		  
	}
	 private Connection con;
	public Update_Contact() {
		 con = DbConnection.openConnection(); // db connecction established 
		PreparedStatement ps = null;
		setIconImage(Toolkit.getDefaultToolkit().getImage(Update_Contact.class.getResource("/cms/image/icon_1.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 829, 624);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(79, 174, 176));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		 cmb_name = new JComboBox();
		 cmb_name.addItemListener(this);
		cmb_name.setModel(new DefaultComboBoxModel(new String[] {"Select Name"}));
		cmb_name.setBounds(238, 101, 312, 35);
		filCombo(); // calling methid to show item in combo 
		contentPane.add(cmb_name);
		
		
		
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 21));
		lblNewLabel.setBounds(49, 109, 111, 35);
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
		
		txt_email = new JTextField();
		txt_email.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt_email.setBounds(238, 161, 312, 36);
		contentPane.add(txt_email);
		txt_email.setColumns(10);
		
		txt_Phone1 = new JTextField();
		txt_Phone1.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt_Phone1.setBounds(238, 215, 312, 35);
		contentPane.add(txt_Phone1);
		txt_Phone1.setColumns(10);
		
		txt_Phone2 = new JTextField();
		txt_Phone2.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt_Phone2.setBounds(238, 271, 312, 35);
		contentPane.add(txt_Phone2);
		txt_Phone2.setColumns(10);
		
		 txt_address = new JTextArea();
		txt_address.setFont(new Font("Tahoma", Font.BOLD, 14));
		txt_address.setBounds(238, 339, 312, 89);
		contentPane.add(txt_address);
		
		JButton btn_update = new JButton("Update");
		btn_update.setFont(new Font("Calibri", Font.BOLD, 21));
		btn_update.addActionListener(this); // that is step 2 button ko click kiye hai
		btn_update.setBounds(337, 482, 111, 24);
		contentPane.add(btn_update);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String n =(String ) cmb_name.getSelectedItem();
		String em = txt_email.getText();
		String ph1= txt_Phone1.getText();
		String ph2 = txt_Phone2.getText();	
		String add = txt_address.getText();
		System.out.println("in button");
	
		if(n.equalsIgnoreCase("Select Name")|| em.length()==0 || ph1.length()==0 && ph2.length()==0 || add.length()==0)
			// if (name.isEmpty()
		 {
			JOptionPane.showMessageDialog(this, "please provide meaningful data for updation");
		}	
		else {
			
			PreparedStatement ps = null;
			try {
				String upsql = "update  contact_details set email =?, phone1=?, phone2=?, address=? where name =?" ;
				ps= con.prepareStatement(upsql);
				
				ps.setString(1, em);
				ps.setString(2, ph1);
				ps.setString(3, ph2);
				ps.setString(4, add);
				ps.setString(5, n);
				
				int status= ps.executeUpdate();
				if(status >0) {
					JOptionPane.showMessageDialog(this,"Contact for" + n + "Updated successfully");
					
			}}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
		finally {
			try {
				if(ps!=null)
					ps.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}}
		}
		
	}
	

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
//		String cm = cmb_name.getItemAt();
		// combo ki 2 states hoti hai ek current ek preivious to hme current wala chaiyea hota hai 
		System.out.println("Item Selected");
		int state=e.getStateChange();
//		System.out.println(state);
		if(state==1)
		{
			// fetching the value from combo box
			String cname =(String ) cmb_name.getSelectedItem(); // yha typecast kiye hai
			// .getSelectedItem(); method fetch the value form the JcomboBox aur cname combobox se fetch kiya gya hai
			System.out.println("name is : " +cname);
			
			PreparedStatement ps = null;
			ResultSet rs= null;
			try {
				String sql="Select * from contact_details where name = ?";
				ps= con.prepareStatement(sql);
				ps.setString(1,cname);
				rs=ps.executeQuery();
				rs.next(); 
				//  next() method work :-it is check the data presnt are not and second work is move the cursor 
				String em = rs.getString("email");
				String ph1= rs.getString("phone1");
				String ph2 = rs.getString("phone2");
				String add= rs.getString("address");
				txt_email.setText(em);
				txt_address.setText(add);
				txt_Phone1.setText(ph1);
				txt_Phone2.setText(ph2);
				
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			finally {
				try {
					if(ps!=null)
						ps.close();
					if(rs!=null)
						rs.close();
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
			}
			
		}
		
		
		
	}
}
