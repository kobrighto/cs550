package vcs;

import java.util.ArrayList;
import java.util.List;

import db.Diagram;
import db.Snapshot;

public class SnapshotManager {
	
	/** diagram */
	private Snapshot snapshot;
	
	/** diagram List */
	private List<Snapshot> snapshotList = new ArrayList<Snapshot>();

	/** snapshot tree */
	private Diagram snapTree;
	
	/**
	 * constructor
	 * 
	 * @param 
	 * @return 
	 */
	public SnapshotManager() {
	
	}
	
	/**
	 * load snapshot from DB.
	 * 
	 * @param String id
	 * @param String pass
	 * @return boolean state
	 */
	public boolean loadSnapData(String i, String p) {
		
		boolean state = false;
		//TODO: Log into DB and get Snapshot.
		
		//this.diagramList = loadDia();
		
		return state;
	}
	
	/**
	 * getSnapshot.
	 * 
	 * @param int version
	 * @return Snapshot snapshot
	 */
	public Snapshot getSnapshot(int v) {
		
		//TODO: get Snapshot.
		//this.snapshot = this.snapshotList.get(v);

		return snapshot;
	}

	/**
	 * set snapshot and return state.
	 * 
	 * @param Diagram diagram
	 * @param int version
	 * @return boolean state
	 */
	public boolean snapshot(Diagram d, int v) {
		
		boolean state = false;
		//TODO: snapshot
		// Insert Diagram into snapshotList and DB.
			
		return state;
	}

	/**
	 * make version tree by using diagramList
	 * 
	 * @param 
	 * @return Diagram versionTree
	 */
	public Diagram makeSnapTree() {
		
		//TODO: make snapshot tree
		//this.snapTree = snapshotTree();
		
		return snapTree;
	}
	
	/**
	 * Send last version of snapshot to versionView.
	 * 
	 * @param 
	 * @return int lastVersion
	 */
	public int getSnapLastVersion() {
		
		int lastVersion = 0;
		//TODO: find last version of snapshot.
		//lastVersion = ~~;
		
		return lastVersion;
	}
	
}
