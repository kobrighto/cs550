package cp;

import graphIO.GraphReaderTXT;
import graphView.JungViewer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import standardGraph.GraphComparison;
import standardGraph.StandardGraph;
import vcs.SnapshotManager;

import cp.HDVCS_UI.handleType;
import cp.HDVCS_UI.viewStyle;
import db.Snapshot;
import db.User;

public class SnapshotView extends HDVCS_UI {

	/** viewStyle */
	private viewStyle style;
		
	/** current snapshot */
	private Snapshot snapshot;
	
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
	 * display Snapshots.
	 * 
	 * @param 
	 * @return 
	 */
	public void displaySnap(int sID) {
		
		GraphReaderTXT Reader = new GraphReaderTXT();
		Snapshot temp = snapHandler.getSnapshot(sID);
		
		StandardGraph newGraph = Reader.readString(temp.getSnapshot().getDiagram());
		
		this.display(newGraph);
		
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
	 * run snapshot.
	 * 
	 * @param int version
	 * @return 
	 */
	public void runSnapshot(int v) {
		
		Diagram temp = diaHandler.getDiagram(v);
		snapHandler.snapshot(temp);
		
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
		
		this.lastSnapVersion = snapHandler.getSnapLastVersion();
		InputStreamReader in=new InputStreamReader(System.in);
	    BufferedReader br=new BufferedReader(in);
		
		while (true)
		{
			int select = -1;
			System.out.println("1. Display version tree.");
			System.out.println("2. Display snapshot tree.");
			System.out.println("3. Display specific version of diagram");
			System.out.println("4. Display specific ID of snapshot.");
			System.out.println("5. Run diff between two version.");
			System.out.println("6. Run snapshot.");
			System.out.println("7. Delete specific version of diagram.");
			System.out.println("8. Change View.");
			System.out.println("9. Stop program.");
			
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
				this.displayVersionTree();
				break;
			
			case 2:
				this.displaySnapTree();
				break;
			
			case 3:
				try {
					System.out.println("Enter diagram version: ");
					System.out.print("Version : ");
					int v = Integer.parseInt(br.readLine());
					
					this.displayDiagram(v);
					System.out.println("----------------------------");
					System.out.println("");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case 4:
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
				
			case 5:
				try {
					System.out.println("Enter two versions which will be differed: ");
					System.out.print("Version 1 : ");
					int v1 = Integer.parseInt(br.readLine());
					System.out.print("Version 2 : ");
					int v2 = Integer.parseInt(br.readLine());
					
					this.runDiff(v1, v2);
					System.out.println("----------------------------");
					System.out.println("");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case 6:
				try {
					System.out.println("Enter diagram version which will be snapshoted: ");
					System.out.print("Version : ");
					int v = Integer.parseInt(br.readLine());
					
					this.runSnapshot(v);
					System.out.println("----------------------------");
					System.out.println("");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case 7:
				try {
					System.out.println("Enter diagram version which will be deleted: ");
					System.out.print("Version : ");
					int v = Integer.parseInt(br.readLine());
					
					this.runDeleteVersion(v);
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
