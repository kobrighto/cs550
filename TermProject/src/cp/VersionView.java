package cp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
	
	/**
	 * run LoginView.
	 * 
	 * @param 
	 * @return integer next do
	 */
	public int runVersionView() {
		
		InputStreamReader in=new InputStreamReader(System.in);
	    BufferedReader br=new BufferedReader(in);
		
		while (true)
		{
			int select = -1;
			System.out.println("1. Display version tree.");
			System.out.println("2. Display snapshot tree.");
			System.out.println("3. Display specific version of diagram");
			System.out.println("4. Display specific version of snapshot.");
			System.out.println("5. Change View.");
			System.out.println("6. Stop program.");
			
			System.out.print("Select : ");
			
			try {
				select = System.in.read();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			switch (select)
			{
			case 1:
				try {
					System.out.println("Enter alsolute path: ");
					String p = br.readLine();
					
					this.loadLocal(p);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				break;
			
			case 2:
				try {
					System.out.println("Enter load diagram version: ");
					System.out.print("ID : ");
					int v = br.read();
						
					this.loadDB(v);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			
			case 3:

					System.out.println("Implemented exception.");

				break;
				
			case 4:
				this.runCommit();
				break;
				
			case 5:
				return 0;
				
			case 6:
				return 1;
				
			default:
				System.out.println("Select correct number.");
				break;
			}	
	
		}
		
	}

}
