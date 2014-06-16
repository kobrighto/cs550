package jdbc;

import java.sql.*;

public class JDBC {
	// JDBC driver name and database url
	static final String JDBC_Driver = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://143.248.55.240:3306/termproject";
	
	// Database credentials
	static final String user = "root";
	static final String password = "digger";
	
	public Connection createConnection(){
		try{
			// Load MySQL Java driver
			Class.forName(JDBC_Driver);
			
			// Establish Java MySQL connection
			System.out.println("Connecting to database...");
			Connection conn = null;
			conn = DriverManager.getConnection(DB_URL, user, password);
			System.out.println("Connection established!");
			return conn;
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public ResultSet executeQuery(Connection conn, String statement){
		try{
			// Execute a query
			Statement stmt = null;
			System.out.println("Creating statement: " + statement);
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(statement);
			return rs;
		}catch (Exception e){
			e.printStackTrace();
		}		
		return null;
	}
}
