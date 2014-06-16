package db;

public class User {
	
	/** ID */
	private String id;
	
	/** password */
	private String pass;
	
	private int numDiagram;
	/**
	 * constructor to set style and type.
	 * 
	 * @param .
	 * @return 
	 */
	public User(String i, String p) {  //first user
		
		this.id = i;
		this.pass = p;
		this.numDiagram=0;
	}
	public User(String i, String p,int num) {  //existing user
		
		this.id = i;
		this.pass = p;
		this.numDiagram=num;
	}

}
