package vcs;

import java.util.ArrayList;
import java.util.List;

import db.Diagram;

public class DiagramManager {
		
	/** diagram */
	private Diagram diagram;
	
	/** diagram List */
	private List<Diagram> diagramList = new ArrayList<Diagram>();

	/**
	 * constructor
	 * 
	 * @param 
	 * @return 
	 */
	public DiagramManager() {
	
	}
	
	/**
	 * load diagram from DB.
	 * 
	 * @param String id
	 * @param String pass
	 * @return boolean state
	 */
	public boolean loadDiaData(String i, String p) {
		
		boolean state = false;
		//TODO: Log into DB and get Diagrams.
		
		//this.diagramList = loadDia();
		
		return state;
	}
	
	/**
	 * getDiagram.
	 * 
	 * @param int version
	 * @return Diagram diagram
	 */
	public Diagram getDiagram(int v) {
		
		//TODO: get Diagrams.
		//this.diagram = this.diagramList.get(v);

		return diagram;
	}

	/**
	 * commit and return state.
	 * 
	 * @param Diagram diagram
	 * @return boolean state
	 */
	public boolean commmit(Diagram d) {
		
		boolean state = false;
		//TODO: commit
		// Insert Diagram into diagramList and DB.
			
		return state;
	}
	
	/**
	 * difference and return diagram.
	 * 
	 * @param string path
	 * @return Diagram diffDiagram
	 */
	public Diagram difference(int v1, int v2) {
		
		//TODO: difference
		//diagram = diffdiagram();
		
		return diagram;
	}	
	
	/**
	 * Delete diagram from DB.
	 * 
	 * @param int version
	 * @return boolean state
	 */
	public boolean delDiagram(int v) {
		
		boolean state = false;
		//TODO: delete
		//delete from diagramList and DB.
		
		return state;
	}

	/**
	 * make version tree by using diagramList
	 * 
	 * @param 
	 * @return Diagram versionTree
	 */
	public Diagram makeVersionTree() {
		
		//TODO: make version tree
		//this.diagram = versionTree();
		
		return diagram;
	}
	
	/**
	 * Send last version of diagram to versionView.
	 * 
	 * @param 
	 * @return int lastVersion
	 */
	public int getDiaLastVersion() {
		
		int lastVersion = 0;
		//TODO: find last version of diagram.
		//lastVersion = ~~;
		
		return lastVersion;
	}
	
}
