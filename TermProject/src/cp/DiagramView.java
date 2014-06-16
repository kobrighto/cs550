package cp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import cp.HDVCS_UI.viewStyle;
import db.Diagram;
import standardGraph.StandardGraph;
import vcs.DiagramManager;

public class DiagramView extends HDVCS_UI {
	
	/** viewStyle */
	private viewStyle style;
		
	/** current diagram */
	private StandardGraph curDiagram;
	
	/** password */
	private int lastDiaVersion;

	/**
	 * constructor to set style and type and lastversion.
	 * 
	 * @param 
	 * @return 
	 */
	public DiagramView() {
		
		this.style = viewStyle.LOGIN;
		
		this.lastDiaVersion = diaHandler.getDiaLastVersion();
		
		//TODO:display initial view.
		
	}
	
	/**
	 * load diagram from local.
	 * 
	 * @param string path.
	 * @return 
	 */
	public void loadLocal(String p) {
		
		//TODO: load diagram from local
		//this.curDiagram = loadL(String p);
		
	}
	
	/**
	 * load diagram from DB.
	 * 
	 * @param string path.
	 * @return 
	 */
	public void loadDB(String p) {
		
		//TODO: load diagram from DB
		//this.curDiagram = loadD(String p);
		
	}
	
	/**
	 * new Diagram
	 * 
	 * @param string path.
	 * @return 
	 */
	public void newDiagram(String d, int lv) {
		
		//TODO: make new diagram
		//Diagram temp = new Diagram(d, lv);
		
	}
	
	/**
	 * run Commit.
	 * 
	 * @param 
	 * @return 
	 */
	public void runCommit() {
		
		//TODO: run commit
		//commit(Diagram curDiagram);
		
	}
	
	/**
	 * run LoginView.
	 * 
	 * @param 
	 * @return intgeger next do
	 */
	public int runDiagramView() {
		
		InputStreamReader in=new InputStreamReader(System.in);
	    BufferedReader br=new BufferedReader(in);
		
		while (true)
		{
			int select = -1;
			System.out.println("1. Load local diagram.");
			System.out.println("2. Load DB diagram.");
			System.out.println("3. New diagram");
			System.out.println("4. Delete diagram.");
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
					System.out.println("Enter alsolute path");
					System.out.print("ID : ");
					String i = br.readLine();
					
					System.out.print("PASS : ");
					String p = br.readLine();
						
					this.runAuthenticate(i, p);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				break;
			
			case 2:
				try {
					System.out.println("Enter New ID & PASS");
					System.out.print("ID : ");
					String i = br.readLine();
					
					System.out.print("PASS : ");
					String p = br.readLine();
						
					this.runNewUser(i, p);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			
			case 3:
				try {
					System.out.println("Enter Delete ID & PASS");
					System.out.print("ID : ");
					String i = br.readLine();
					
					System.out.print("PASS : ");
					String p = br.readLine();
						
					this.runDelUser(i, p);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case 4:
				try {
					System.out.println("Enter Delete ID & PASS");
					System.out.print("ID : ");
					String i = br.readLine();
					
					System.out.print("PASS : ");
					String p = br.readLine();
						
					this.runDelUser(i, p);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case 5:
				return 0;
				break;
				
			case 6:
				return 1;
				break;
				
			default:
				System.out.println("Select correct number.");
				break;
			}	
	
		}
		
	}
	
}
