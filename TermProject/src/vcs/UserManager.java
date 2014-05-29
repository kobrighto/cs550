package vcs;

import java.util.ArrayList;
import java.util.List;

import cp.HDVCS_UI.handleType;
import cp.HDVCS_UI.viewStyle;
import db.User;

public class UserManager {
	
	/** User */
	private User user;
	
	/** User list pre-loaded */
	private List<User> userList = new ArrayList<User>();
	
	/** user count */
	private int userCount;

	/**
	 * constructor to set style and type.
	 * 
	 * @param 
	 * @return 
	 */
	public LoginView() {
		
		this.style = viewStyle.LOGIN;
		this.type = handleType.LOGIN;
		
	}
	
	
	/**
	 * run authentication.
	 * 
	 * @param string id.
	 * @param string pass.
	 * @return boolean state
	 */
	public boolean runAuthenticate(String i, String p) {
		
		//TODO: make login process.
		
		return state;
	}
	
	/**
	 * run new user.
	 * 
	 * @param string id.
	 * @param string pass.
	 * @return boolean state
	 */
	public boolean runNewUser(String i, String p) {
		
		//TODO: make new user in DB.
		
		return state;
	}
}
