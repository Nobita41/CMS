package cms.gui;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cms.dbtask.DbConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class Course extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txt_name;
	private JTextField txt_fees;
	private JTextField txt_duration;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Course frame = new Course();
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
	public Course() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 751, 523);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CourseName");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 21));
		lblNewLabel.setBounds(68, 79, 139, 28);
		contentPane.add(lblNewLabel);
		
		txt_name = new JTextField();
		txt_name.setBounds(275, 79, 197, 23);
		contentPane.add(txt_name);
		txt_name.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("CourseFee");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 21));
		lblNewLabel_1.setBounds(68, 156, 120, 23);
		contentPane.add(lblNewLabel_1);
		
		txt_fees = new JTextField();
		txt_fees.setBounds(274, 156, 198, 23);
		contentPane.add(txt_fees);
		txt_fees.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("CourseDuration");
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 21));
		lblNewLabel_2.setBounds(68, 229, 139, 23);
		contentPane.add(lblNewLabel_2);
		
		txt_duration = new JTextField();
		txt_duration.setBounds(275, 229, 197, 23);
		contentPane.add(txt_duration);
		txt_duration.setColumns(10);
		
		JButton btn_submit = new JButton("Submit");
		btn_submit.addActionListener(this);
		btn_submit.setFont(new Font("Calibri", Font.BOLD, 21));
		btn_submit.setBounds(241, 295, 131, 39);
		contentPane.add(btn_submit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String name =txt_name.getText();
		String fees=txt_fees.getText();
		String duration = txt_duration.getText();
		if(name.isEmpty() || fees.isEmpty() || duration.isEmpty()) {
			JOptionPane.showMessageDialog(this, "please provide the details");
		}
		else {
			int cfees= Integer.parseInt(fees);
			Connection con = DbConnection .openConnection();
			PreparedStatement ps = null;
			try {
				
				String insert_query="insert into course_details( course_name, course_duration, course_fees)values(?,?,?)";
				ps=con.prepareStatement(insert_query); 
				ps.setString(1, name);
				ps.setString(2,duration);
				ps.setInt(3, cfees);
				
//				System.out.println(ps);
				
				int status = ps.executeUpdate();
				
				if(status >0) {
					JOptionPane.showMessageDialog(this, "Details added successfully");
					txt_name.setText(" ");
					txt_duration.setText(" ");
					txt_fees.setText(" ");
					
				}
		}
			catch(SQLException se) {
				JOptionPane.showMessageDialog(this, "Details alredy exists");
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
				 catch(SQLException se) {
					 se.printStackTrace();
				 }
			 }
			
			}
	}
}
