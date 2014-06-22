package db;

public class User {
	
	/** ID */
	private String id;
	
	/** password */
	private String pass;
	
	/**
	 * constructor to set style and type.
	 * 
	 * @param String id
	 * @param String password.
	 * @return 
	 */
	public User(String i, String p) 
	{  
		
		this.id = new String(i);
		this.pass = new String(p);

	}
	
	/**
	 * get id.
	 * 
	 * @param 
	 * @return String id.
	 */
	public String getid()
	{
		return id;
	}

}
