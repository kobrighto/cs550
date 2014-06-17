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
			
			PreparedStatement prestatement=conn.prepareStatement("select * from user where username=?");
			prestatement.setString(1,i);
			ResultSet rs=prestatement.executeQuery();
			if(rs.next()){
				String password=rs.getString("password");
				if(p.equals(password)) 
					return true; 
				else 
					System.out.println("Pass is not correct.");
			}
			else
			{
				System.out.println("Id is not exist.");
			}
			
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
			
			PreparedStatement prestatement=conn.prepareStatement("select * from user where username=?");
			prestatement.setString(1,i);
			ResultSet rs=prestatement.executeQuery();
			if(rs.next()){
				System.out.println("Id is already exist.");
			}
			else
			{
				prestatement=conn.prepareStatement("insert into user values (null,?,?)");
				prestatement.setString(1,i);
				prestatement.setString(2,p);
				//prestatement.setInt(3,0); //number of the diagrams
				prestatement.executeUpdate();
				state=true;
				System.out.println("New User creation is completed");
			}
			
		}catch(Exception e){
			System.out.println("NEw User creation error!");
			e.printStackTrace();
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
			PreparedStatement prestatement=conn.prepareStatement("select * from user where username=? and password=?");
			prestatement.setString(1,i);
			prestatement.setString(2,p);
			ResultSet rs=prestatement.executeQuery();
			if(rs.next()){
				prestatement=conn.prepareStatement("delete from user where username=? and password=?");
				prestatement.setString(1,i);
				prestatement.setString(2,p);
				prestatement.executeUpdate();
				state=true;
				System.out.println("User deletion is completed");
			}else
				System.out.println("There is no user with id:"+i+ " and password: "+p);
		}catch(Exception e){
			System.out.println("User deletion error!");
		}
		return state;
	}
	
}
