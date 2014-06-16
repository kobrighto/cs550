package cp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import vcs.UserManager;
import cp.HDVCS_UI.handleType;
import cp.HDVCS_UI.viewStyle;
import db.User;

public class LoginView extends HDVCS_UI {

	/** viewStyle */
	private viewStyle style;
	
	/** ID */
	private String id;
	
	/** password */
	private String pass;
	
	/** state of login (success or fail) */
	private boolean state;
	
	/**
	 * constructor to set style and type.
	 * 
	 * @param 
	 * @return 
	 */
	public LoginView() {
		
		this.style = viewStyle.LOGIN;
		this.state = false;

	}
	
	/**
	 * get state.
	 * 
	 * @param 
	 * @return 
	 */	
	public boolean isState() {
		return state;
	}
	
	/**
	 * run authentication.
	 * 
	 * @param string id.
	 * @param string pass.
	 * @return 
	 */
	public void runAuthenticate(String i, String p) {
		
		this.state = userHandler.authenticate(i, p);
		
	}
	
	/**
	 * run new user.
	 * 
	 * @param string id.
	 * @param string pass.
	 * @return 
	 */
	public void runNewUser(String i, String p) {
		
		userHandler.newUser(i, p);
		
	}

	/**
	 * run delete user.
	 * 
	 * @param string id.
	 * @param string pass.
	 * @return 
	 */
	public void runDelUser(String i, String p) {
		
		userHandler.delUser(i, p);
		
	}
	
	/**
	 * run LoginView.
	 * 
	 * @param 
	 * @return boolean state
	 */
	public boolean runLoginView() {
		
		InputStreamReader in=new InputStreamReader(System.in);
	    BufferedReader br=new BufferedReader(in);
		
		while (!state)
		{
			int select = -1;
			System.out.println("1. Login.");
			System.out.println("2. New User.");
			System.out.println("3. Delete User");
			
			System.out.print("Select : ");
			
			try {
				select = System.in.read();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			switch (select)
			{
			case 1:
				try {
					System.out.println("Enter ID & PASS");
					System.out.print("ID : ");
					String i = br.readLine();
					
					System.out.print("PASS : ");
					String p = br.readLine();
						
					this.runAuthenticate(i, p);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				break;
			
			case 2:
				try {
					System.out.println("Enter New ID & PASS");
					System.out.print("ID : ");
					String i = br.readLine();
					
					System.out.print("PASS : ");
					String p = br.readLine();
						
					this.runNewUser(i, p);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			
			case 3:
				try {
					System.out.println("Enter Delete ID & PASS");
					System.out.print("ID : ");
					String i = br.readLine();
					
					System.out.print("PASS : ");
					String p = br.readLine();
						
					this.runDelUser(i, p);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			default:
				System.out.println("Select correct number.");
				break;
			}	
	
		}
		
		return state;
		
	}	
	
}
