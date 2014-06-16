package vcs;

import java.util.ArrayList;
import java.util.List;

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
		
		boolean state = false;
		//TODO: make login process.
		
		return state;
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
		//TODO: make new user in DB.
		
		return state;
	}
	
}
