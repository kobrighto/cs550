package db;

public class Snapshot {

	/** snapshot */
	private Diagram snapshot;
	
	/** version */
	private int version;
	
	/**
	 * constructor to set snapshot and version.
	 * 
	 * @param .
	 * @return 
	 */
	public Snapshot(Diagram s, int v) {
		
		this.snapshot = s;
		this.version = v;
		
	}
}
