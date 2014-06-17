package cp;

import graphIO.GraphReaderTXT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import standardGraph.StandardGraph;
import vcs.SnapshotManager;

import db.Snapshot;
import db.Version;

public class SnapshotView extends HDVCS_UI {

	/** viewStyle */
	private viewStyle style;
	
	/** snapshot list */
	private StandardGraph snapshotTree;

	/**
	 * constructor to set style and type and lastversion.
	 * 
	 * @param 
	 * @return 
	 */
	public SnapshotView() {
		
		this.style = viewStyle.SNAPSHOT;
		
		//TODO:display initial view. (version tree)
		
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
	 * display Snapshots.
	 * 
	 * @param 
	 * @return 
	 */
	public void displaySnap(int sID) {
		
		GraphReaderTXT Reader = new GraphReaderTXT();
		Snapshot temp = snapHandler.getSnapshot(sID);
		
		StandardGraph newGraph = Reader.readString(temp.getVersion().getDiagram());
		
		this.display(newGraph);
		
	}
	
	/**
	 * run snapshot.
	 * 
	 * @param String did
	 * @return 
	 */
	public void runSnapshot(String did, String sc) {
		
		Version temp = verHandler.getVersion(did);
		snapHandler.snapshot(temp, sc);
		
		GraphReaderTXT Reader = new GraphReaderTXT();
		StandardGraph newGraph = Reader.readString(temp.getDiagram());
		
		this.display(newGraph);

	}
	
	/**
	 * run SnapshotView.
	 * 
	 * @param 
	 * @return integer next do
	 */
	public int runSnapshotView() {
		
		InputStreamReader in=new InputStreamReader(System.in);
	    BufferedReader br=new BufferedReader(in);
		
		while (true)
		{
			int select = -1;
			System.out.println("1. Display snapshot tree.");
			System.out.println("2. Display specific ID of snapshot.");
			System.out.println("3. Run snapshot.");
			System.out.println("4. Change View.");
			System.out.println("5. Stop program.");
			
			System.out.print("Select : ");
			
			try {
				select = Integer.parseInt(br.readLine());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("----------------------------");
			System.out.println("");
			
			switch (select)
			{
			case 1:
				this.displaySnapTree();
				break;
			
			case 2:
				try {
					System.out.println("Enter snapshot ID: ");
					System.out.print("ID : ");
					int s = Integer.parseInt(br.readLine());
					
					this.displaySnap(s);
					System.out.println("----------------------------");
					System.out.println("");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case 3:
				try {
					System.out.println("Enter diagram version, and snapshot comment which will be snapshoted: ");
					System.out.print("Version : ");
					String did = br.readLine();
					System.out.print("snapshot comment : ");
					String sc = br.readLine();
					
					this.runSnapshot(did, sc);
					System.out.println("----------------------------");
					System.out.println("");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case 8:
				return 0;
				
			case 9:
				return 1;
				
			default:
				System.out.println("Select correct number.");
				break;
			}	
	
		}
		
	}

}
