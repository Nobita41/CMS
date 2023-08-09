package cms.gui;

import java.awt.EventQueue;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Frame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class User_DashBoard extends JFrame implements WindowListener, ActionListener {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					User_DashBoard frame = new User_DashBoard();
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
	public User_DashBoard() {
		this.addWindowListener(this);// First this is source and second for class which implements listner
		
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setIconImage(Toolkit.getDefaultToolkit().getImage(User_DashBoard.class.getResource("/cms/image/icon_1.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// DISPOSE_ON_CLOSE only can close that frame
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMyContacts = new JMenu("My Contacts");
		menuBar.add(mnMyContacts);
		
		JMenuItem mi_add = new JMenuItem("add");
		mi_add.addActionListener(this);
		mnMyContacts.add(mi_add);
		
		JMenuItem mi_update = new JMenuItem("Update");
		mi_update.addActionListener(this);
		mnMyContacts.add(mi_update);
		
		JMenuItem mi_delete = new JMenuItem("Delete");
		mi_delete.addActionListener(this);
		mnMyContacts.add(mi_delete);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Search");
		mntmNewMenuItem.addActionListener(this); 
		mnMyContacts.add(mntmNewMenuItem);
		
		JMenuItem mi_viewall = new JMenuItem("View All");
		mi_viewall.addActionListener(this);
		mnMyContacts.add(mi_viewall);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		Login login = new Login();
		login.setVisible(true);
		JOptionPane.showMessageDialog(this, "Thank you for using me!!");
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String caption =e.getActionCommand();
		//e.getActionCommand(); --> it returns the text written on a button or menuItem.
		
		if(caption.equalsIgnoreCase("add")) {
			Contact c= new Contact(); // contact ka object iska use frame ko open krne ke liye kiya jata hai
			c.setVisible(true);
		}
		else	if(caption.equalsIgnoreCase("Update")) {
			Update_Contact uc= new Update_Contact(); // contact ka object iska use frame ko open krne ke liye kiya jata hai
			uc.setVisible(true);
		}
		else	if(caption.equalsIgnoreCase("Delete")) {
		Delete_Contact dc= new Delete_Contact(); // contact ka object iska use frame ko open krne ke liye kiya jata hai
			dc.setVisible(true);
		}
		else	if(caption.equalsIgnoreCase("Search")) {
			Search_Contact sc= new Search_Contact(); // contact ka object iska use frame ko open krne ke liye kiya jata hai
			sc.setVisible(true);
		}
		else if(caption.equalsIgnoreCase("View ALl")) {
			AllContact ac = new AllContact();
			ac.setVisible(true);
		}
		
	}
}
