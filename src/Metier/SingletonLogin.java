package Metier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonLogin {
 
	private static Connection connection;
	static {
		
	//	System.out.println("singleton !!! ");
		String url= "jdbc:mysql://localhost:3306/BachMek?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user="root";
		String password="";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection=DriverManager.getConnection(url, user, password);
		//	System.out.println("connexion !!! ");
		}catch (ClassNotFoundException e) {

			e.printStackTrace();
			
		} catch (SQLException e) {

			e.printStackTrace(); 
		}
		
	}
	public static Connection getConnection() {
		return connection;
	}
	
}
