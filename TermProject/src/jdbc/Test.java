package jdbc;

import java.sql.*;

public class Test {
	public static void main (String[] args){
		try{
			JDBC jdbc = new JDBC();
			Connection conn = jdbc.createConnection();
			String statement = "select * from user";
			ResultSet rs = jdbc.executeQuery(conn, statement);
			while (rs.next()){
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				
				System.out.println("ID: " + id);
				System.out.println("Username: " + username);
				System.out.println("Password: " + password);
			}
		} catch(Exception e){
			e.printStackTrace();
		}		
	}
}
