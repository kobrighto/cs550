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
	 * @param .
	 * @return 
	 */
	
	public Version(){
	
	}
	
	public Version(String did, String d, String vc) {
		
		this.dId = new String(did);
		this.diagram = new String(d);
		this.vcomment = new String(vc);

	}
	
	@Override
	public String toString() {
		return "Diagram [diagram=" + diagram + ", version=" + dId + "version comments=" + vcomment + "]";
	}
	
	public String getVcomment() {
		return vcomment;
	}

	public void setVcomment(String vcomment) {
		this.vcomment = vcomment;
	}
	
	public String getDiagram() {
		return diagram;
	}

	public String getdID() {
		return dId;
	}
	
	public void setDiagram(String diagram) {
		this.diagram = diagram;
	}

	public void setdID(String did) {
		this.dId = did;
	}

}
