package cp;

import java.util.List;

import vcs.DiagramManager;
import vcs.SnapshotManager;

import cp.HDVCS_UI.handleType;
import cp.HDVCS_UI.viewStyle;
import db.Diagram;
import db.Snapshot;
import db.User;

public class VersionView extends HDVCS_UI {

	/** viewStyle */
	private viewStyle style;
		
	/** handleType */
	private DiagramManager diaHandler;
	private SnapshotManager snapHandler;
		
	/** current diagram */
	private Diagram curDiagram;
	
	/** diff of Diagram */
	private Diagram diffDiagram;
	
	/** snapshot list */
	private Diagram snapshotTree;
	
	/** version tree */
	private Diagram versionTree;
	
	/** password */
	private int lastSnapVersion;

	/**
	 * constructor to set style and type and lastversion.
	 * 
	 * @param 
	 * @return 
	 */
	public VersionView() {
		
		this.style = viewStyle.LOGIN;
		
		this.lastSnapVersion = snapHandler.getSnapLastVersion();
		this.snapshotTree = snapHandler.makeSnapTree();
		this.versionTree = diaHandler.makeVersionTree();
		
		//TODO:display initial view. (version tree)
		
	}
	
	/**
	 * display Snapshots.
	 * 
	 * @param 
	 * @return 
	 */
	public void displaySnap(int sID) {
		
		//TODO: display snapshot 
		//show
		
	}
	
	/**
	 * display selected diagram.
	 * 
	 * @param int version
	 * @return 
	 */
	public void displayDiagram(int v) {
		
		//TODO: find correct diagram by using version.
		//Diagram temp = versionTree.get(v);
		//display(temp);
		
	}
	
	/**
	 * display versionTree.
	 * 
	 * @param 
	 * @return 
	 */
	public void displayVersionTree() {
		
		//TODO: show versionTree
		
	}
	
	/**
	 * display snapshotTree.
	 * 
	 * @param 
	 * @return 
	 */
	public void displaySnapTree() {
		
		//TODO: show snapTree
		
	}
	
	/**
	 * run difference.
	 * 
	 * @param int version1
	 * @param int version2
	 * @return 
	 */
	public void runDiff(int v1, int v2) {
		
		//TODO: run difference
		//this.diffDiagram = difference(v1, v2);
		
	}
	
	/**
	 * run snapshot.
	 * 
	 * @param int version
	 * @return 
	 */
	public void runSnapshot(int v) {
		
		//TODO: run snapshot.
		//boolean state = snapshot(v);
		
	}
	
	/**
	 * run delete selected version of diagram.
	 * 
	 * @param int version
	 * @return 
	 */
	public void runDeleteVersion(int v) {
		
		//TODO: delete selected version of diagram.
		//boolean state = deleteDiagram(v);
		
	}

}
