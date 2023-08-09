package cms.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import cms.dbtask.DbConnection;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.sql.*;
import javax.swing.JButton;
public class AllContact extends JFrame  implements ActionListener{

	private JPanel contentPane;
	private JTable table;
	private Connection con;
	private 	JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllContact frame = new AllContact();
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
	public AllContact() {
		con=DbConnection.openConnection();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 678, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	 scrollPane = new JScrollPane();
		scrollPane.setBounds(70, 11, 482, 261);
		contentPane.add(scrollPane);
		
		JButton btn_showContacts = new JButton("Show Contacts");
		btn_showContacts.addActionListener(this);
		
		btn_showContacts.setBounds(255, 318, 132, 31);
		contentPane.add(btn_showContacts);
		
		table = new JTable();
		 JTableHeader header=table.getTableHeader(); // this method used to find the table header
		header.setForeground(Color.BLUE); // set the text color
		header.setFont(new Font("Lucida Console", Font.BOLD,10)); // set the text formate and size 
		header.setBackground(Color.CYAN); // set the background
//		filledRecords();
//		scrollPane.setViewportView(table);
	}
	public void filledRecords() {
		PreparedStatement ps =null;
		ResultSet rs=null;
		try {
		String sql ="Select * from contact_details";
		ps= con.prepareStatement(sql);
		
		rs=ps.executeQuery();
		
		TableModel t =DbUtils.resultSetToTableModel(rs); // model create 
		table.setModel(t);// model set
		
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		filledRecords();
		scrollPane.setViewportView(table);
	}
}
