package db;

import standardGraph.StandardGraph;

public class Snapshot {

	/** snapshot */
	private StandardGraph snapshot;
	
	/** version */
	private int version;
	
	/**
	 * constructor to set snapshot and version.
	 * 
	 * @param .
	 * @return 
	 */
	public Snapshot(StandardGraph s, int v) {
		
		this.snapshot = s;
		this.version = v;
		
	}
}
