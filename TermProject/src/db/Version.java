package db;

public class Version {
		
	/** diagram */
	private String diagram;
	
	/** version */
	private String dId;
	
	/** comment of version */
	private String vcomment;

	/**
	 * constructor to set diagram and version.
	 * 
	 * @param 
	 * @return 
	 */
	public Version(){
	
	}
	
	/**
	 * constructor to set diagram and version.
	 * 
	 * @param String diagram id.
	 * @param String diagram.
	 * @param String version comment.
	 * @return 
	 */
	public Version(String did, String d, String vc) {
		
		this.dId = did;
		this.diagram =d;
		this.vcomment =vc;

	}
	
	/**
	 * Make version to String.
	 * 
	 * @param 
	 * @return String version information.
	 */
	@Override
	public String toString() {
		return "Diagram [diagram=" + diagram + ", version=" + dId + "version comments=" + vcomment + "]";
	}

	/**
	 * get diagram of version.
	 * 
	 * @param 
	 * @return String diagram of version.
	 */
	public String getDiagram() {
		return diagram;
	}

	/**
	 * get diagram id.
	 * 
	 * @param 
	 * @return String diagram id.
	 */
	public String getdID() {
		return dId;
	}
	
	/**
	 * get version comment.
	 * 
	 * @param 
	 * @return String version comment.
	 */
	public String getVcomment() {
		return vcomment;
	}

}
