package db;

public class Diagram {
		
	/** diagram */
	private String diagram;
	
	/** version */
	private int version;
	//private String owner;
	

	/**
	 * constructor to set diagram and version.
	 * 
	 * @param .
	 * @return 
	 */
	
	public Diagram(){
	
	}
	public Diagram(int v,String d, String owner) {
		
		this.version = v;
		this.diagram = d;
	//	this.owner =owner;
	}

		
	
	
	@Override
	public String toString() {
		return "Diagram [diagram=" + diagram + ", version=" + version + "]";
	}
	public String getDiagram() {
		return diagram;
	}

	public int getVersion() {
		return version;
	}
	
	public void setDiagram(String diagram) {
		this.diagram = diagram;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
//	public void setOwner(String owner) {
//		this.owner = owner;
//	}
//	public String getOwner() {
//		return owner;
//	}
//	

}
