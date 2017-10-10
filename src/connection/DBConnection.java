package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static DBConnection instance = null;
	private static Connection connection = null;
	
	private static final String DB_PASSWORD = "Plamen11*";
	private static final String DB_USER = "Redmort";
	private static final String DATABASE = "deliveries";
	private static final String DB_HOST = "localhost:3306";
	
	
	private DBConnection(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://" + 
			DB_HOST + "/" + DATABASE	
			+ "?verifyServerCertificate=false" 
					+ "&useSSL=false" + "&requireSSL=false",
			DB_USER, DB_PASSWORD);
					
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static DBConnection getInstance(){
		
		synchronized (DBConnection.class) {
			if(instance == null){
				instance = new DBConnection();
			}
		}
		
		return instance;
	}
	
	

	public Connection getConnection() {
		return connection;
	}
	
	
	

}
