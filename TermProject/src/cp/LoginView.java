package cp;

import cp.HDVCS_UI.handleType;
import cp.HDVCS_UI.viewStyle;
import db.User;

public class LoginView {

	/** viewStyle */
	private viewStyle style;
		
	/** handleType */
	private handleType type;
		
	/** User */
	private User user;
	
	/** ID */
	private String id;
	
	/** password */
	private String pass;
	
	/** state of login (success or fail) */
	public boolean state;
	
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
