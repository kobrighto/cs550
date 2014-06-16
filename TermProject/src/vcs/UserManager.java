package vcs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jdbc.JDBC;
import cp.HDVCS_UI.handleType;
import cp.HDVCS_UI.viewStyle;
import db.User;

public class UserManager {
	
	/** User */
	private User user;
	
	/** User list pre-loaded */
	private List<User> userList = new ArrayList<User>();

	/**
	 * constructor.
	 * 
	 * @param 
	 * @return 
	 */
	public UserManager() {
		
	}
	
	/**
	 * authentication.
	 * 
	 * @param string id.
	 * @param string pass.
	 * @return boolean state
	 */
	public boolean authenticate(String i, String p) {
		
	
		//TODO: make login process.
		try{
			Connection conn=JDBC.getConnection();
			
			PreparedStatement prestatement=conn.prepareStatement("select * from user where id=?");
			prestatement.setString(1,i);
			ResultSet rs=prestatement.executeQuery();
			while(rs.next()){
				String password=rs.getString("password");
				if(p.equals(password)) return true; 
			}
			
			System.out.println("authentication is completed");
		}catch(Exception e){
			System.out.println("authentication error!");
			return false;
		}
		return false;
		
	}
	
	/**
	 * new user.
	 * 
	 * @param string id.
	 * @param string pass.
	 * @return boolean state
	 */
	public boolean newUser(String i, String p) {
		
		boolean state = false;
		//TODO: make new user in DB.
		try{
			Connection conn=JDBC.getConnection();
			
			PreparedStatement prestatement=conn.prepareStatement("insert into user values (?,?,?)");
			prestatement.setString(1,i);
			prestatement.setString(2,p);
			prestatement.setInt(3,0); //number of the diagrams
			prestatement.executeUpdate();
			state=true;
			System.out.println("New User creation is completed");
		}catch(Exception e){
			System.out.println("NEw User creation error!");
		}
		return state;
	}
	
	/**
	 * delete user.
	 * 
	 * @param string id.
	 * @param string pass.
	 * @return boolean state
	 */
	public boolean delUser(String i, String p) {
		
		boolean state = false;
		//TODO: delete a user in DB.
		try{
			Connection conn=JDBC.getConnection();
			
			PreparedStatement prestatement=conn.prepareStatement("delete from user where id=? and password=?");
			prestatement.setString(1,i);
			prestatement.setString(2,p);
			prestatement.executeUpdate();
			state=true;
			System.out.println("User deletion is completed");
		}catch(Exception e){
			System.out.println("User deletion error!");
		}
		return state;
	}
	
}
