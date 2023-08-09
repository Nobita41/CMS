package cms.dbtask;
import java.sql.*;
import java.util.Iterator;
public class DbConnection 
{
	private static Connection con;
	
	public static Connection openConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // that is factory method--> factory method is used
//			the object of the class implicty (Step-1)
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/contact_db","root","root");
			
//			 "jdbc:mysql://locahost:3306/contact_db","root","root" --> that is connection string
//			 jdbc:mysql: --> it is subprotocol ,
//			locahost:3306--> name or IP address of the machine db has been installed.
//			locahost:3306 --> port no of RDBMS where it listens all the sql request
//			contact_db --> name if the database through I connection. 
//			"root" --> user id of the RDBMS
//			"root" --> password of the RDBMS
		}
		catch(ClassNotFoundException|SQLException cse){
			System.out.println(cse);
			
			
		} 
		return con;
		
	}
//	public static void main(String[] args) {
//		Connection c =openConnection();
//		System.out.println(c);
//	}
	

}
