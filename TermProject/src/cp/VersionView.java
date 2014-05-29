package cp;

import java.util.List;

import cp.HDVCS_UI.handleType;
import cp.HDVCS_UI.viewStyle;
import db.Diagram;
import db.Snapshot;
import db.User;

public class VersionView {

	/** viewStyle */
	private viewStyle style;
		
	/** handleType */
	private handleType type;
		
	/** current diagram */
	private Diagram curDiagram;
	
	/** diff of Diagram */
	private Diagram diffDiagram;
	
	/** snapshot list */
	private List<Snapshot> snapshotList;
	
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
		this.type = handleType.LOGIN;
		
		this.lastSnapVersion = getSnapCount();
		this.snapshotList = getSnapshotList();
		this.versionTree = makeVersionTree();
		
	}
	
	
	/**
	 * display Tree.
	 * 
	 * @param  
	 * @return 
	 */
	public void displayTree() {
		
		//TODO: make graph by using list of diagram and show
		//makeTree
		
	}
	
	/**
	 * display Snapshots.
	 * 
	 * @param 
	 * @return 
	 */
	public void displaySnapshots() {
		
		//TODO: display snapshot version list
		//show
		
	}
	
	/**
	 * display diff diagram.
	 * 
	 * @param 
	 * @return 
	 */
	public void displayDiff() {
		
		//TODO: display diff diagram
		//display(diffDiagram);
		
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
