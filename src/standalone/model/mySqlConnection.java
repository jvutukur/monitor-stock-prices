package standalone.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class mySqlConnection {

	private static Connection conn = null;
	
	protected mySqlConnection(){
		
	}
	
	public static Connection getConnection(){
		
		if(conn == null){
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
			    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/?" + "user=root&password=");		       
			} 
			
			catch (Exception e) {
				System.out.println(e.getMessage());
			}	
		}
		else{
			return conn;
		}
		
		return conn;
		
	}
	
	public static boolean closeConnection(){
		return true;
	}
	
}
