package cp;

import graphIO.GraphReaderTXT;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import standardGraph.StandardGraph;
import vcs.VersionManager;

import db.Version;

public class VersionView extends HDVCS_UI {

	/** viewStyle */
	private viewStyle style;
		
	/** current diagram */
	private Version curDiagram;
	
	/** version tree */
	private Version versionTree;

	/**
	 * constructor to set style and type and lastversion.
	 * 
	 * @param 
	 * @return 
	 */
	public VersionView() {
		
		this.style = viewStyle.VERSION;
		
		//TODO:display initial view. (version tree)
		
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
	 * display selected diagram.
	 * 
	 * @param String did
	 * @return 
	 */
	public void displayVersion(String d) {
		
		GraphReaderTXT Reader = new GraphReaderTXT();
		Version temp = verHandler.getVersion(d);
		
		StandardGraph newGraph = Reader.readString(temp.getDiagram());
		
		this.display(newGraph);
		
	}
	
	/**
	 * run difference.
	 * 
	 * @param int version1
	 * @param int version2
	 * @return 
	 */
	public void runDiff(String d1, String d2) {
		
		verHandler.difference(d1, d2);
		
	}
	
	/**
	 * load diagram from local.
	 * 
	 * @param string path.
	 * @return 
	 */
	public void loadLocal(String p) {
		
		GraphReaderTXT Reader = new GraphReaderTXT();

		StandardGraph newGraph = Reader.readTextFile(p);
		
		try {
			this.curDiagram = new Version(null, readFile(p), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.display(newGraph);
		
	}
	
	/**
	 * run delete selected version of diagram.
	 * 
	 * @param String did
	 * @return 
	 */
	public void runDeleteVersion(String d) {
		
		verHandler.delVersion(d);
		
	}
	
	/**
	 * run Commit.
	 * 
	 * @param String version comment.
	 * @return 
	 */
	public void runCommit(String vc) {
		
		verHandler.commit(curDiagram, vc);
		
	}
	
	/**
	 * make branch.
	 * 
	 * @param String version of diagram.
	 * @param String branch name.
	 * @return 
	 */
	public void runMakeBranch(String d) {
		
		verHandler.makeBranch(d);
		
	}
	
	/**
	 * change branch.
	 * 
	 * @param String branch name.
	 * @return 
	 */
	public void runChangeBranch(String vn) {
		
		verHandler.changeBranch(vn);
		
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
			System.out.println("2. Display specific version of diagram.");
			System.out.println("3. Delete specific version of diagram.");			
			System.out.println("4. Run diff between two version.");
			System.out.println("5. Load local diagram.");
			System.out.println("6. Make branch.");
			System.out.println("7. Change branch.");
			System.out.println("8. Commit loaded diagram.");
			System.out.println("9. Change View.");
			System.out.println("10. Stop program.");
			
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
				try {
					System.out.println("Enter diagram version: ");
					System.out.print("Version : ");
					String v = br.readLine();
					
					this.displayVersion(v);
					System.out.println("----------------------------");
					System.out.println("");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case 3:
				try {
					System.out.println("Enter diagram version which will be deleted: ");
					System.out.print("Version : ");
					String v = br.readLine();
					
					this.runDeleteVersion(v);
					System.out.println("----------------------------");
					System.out.println("");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			
			case 4:
				try {
					System.out.println("Enter two versions which will be differed: ");
					System.out.print("Version 1 : ");
					String v1 = br.readLine();
					System.out.print("Version 2 : ");
					String v2 = br.readLine();
					
					this.runDiff(v1, v2);
					System.out.println("----------------------------");
					System.out.println("");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case 5:
				try {
					System.out.println("Enter alsolute path of diagram: ");
					String p = br.readLine();
				
					this.loadLocal(p);
					
					System.out.println("----------------------------");
					System.out.println("");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				break;
				
			case 6:
				try {
					System.out.println("Enter junction version which you want to make branch\n"
							+ "(only support 1 brach for parent branch): ");
					System.out.print("Juction version: ");
					String d = br.readLine();					
					
					this.runMakeBranch(d);
					System.out.println("----------------------------");
					System.out.println("");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case 7:
				try {
					System.out.println("Enter branch name which you want to change: ");
					System.out.print("Branched version (e.g: 1, 1.1, X.1, X.X.1 ...): ");
					String bn = br.readLine();
					
					this.runChangeBranch(bn);
					System.out.println("----------------------------");
					System.out.println("");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case 8:
				try {
					System.out.println("Enter version comment for commit: ");
					System.out.print("version comment: ");
					String vc = br.readLine();
					
					this.runCommit(vc);
					System.out.println("----------------------------");
					System.out.println("");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case 9:
				return 1;
				
			case 10:
				return 0;

			default:
				System.out.println("Select correct number.");
				break;
			}	
	
		}
		
	}

}
