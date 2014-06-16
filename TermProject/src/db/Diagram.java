package db;

import standardGraph.StandardGraph;

public class Diagram {
		
	/** diagram */
	private StandardGraph diagram;
	
	/** version */
	private int version;
	
	/**
	 * constructor to set diagram and version.
	 * 
	 * @param .
	 * @return 
	 */
	public Diagram(StandardGraph d, int v) {
		
		this.diagram = d;
		this.version = v;
		
	}

}
