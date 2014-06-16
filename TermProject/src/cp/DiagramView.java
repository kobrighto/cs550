package cp;

import graphIO.GraphReaderTXT;

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
	private Diagram curDiagram;
	
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
		
		//TODO:display initial view.
		
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
		
		this.display(newGraph);
		
	}
	
	/**
	 * load diagram from DB.
	 * 
	 * @param integer version.
	 * @return 
	 */
	public void loadDB(int v) {
		
		GraphReaderTXT Reader = new GraphReaderTXT();
		this.curDiagram = diaHandler.getDiagram(v);
		
		StandardGraph newGraph = Reader.readString(this.curDiagram.getDiagram());
		
		this.display(newGraph);
		
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
		
		diaHandler.commit(curDiagram);
		
		GraphReaderTXT Reader = new GraphReaderTXT();
		
		StandardGraph newGraph = Reader.readString(this.curDiagram.getDiagram());
		
		this.display(newGraph);
		
	}
	
	/**
	 * run LoginView.
	 * 
	 * @param 
	 * @return integer next do
	 */
	public int runDiagramView() {
		
		this.lastDiaVersion = diaHandler.getDiaLastVersion();
		InputStreamReader in=new InputStreamReader(System.in);
	    BufferedReader br=new BufferedReader(in);
		
		while (true)
		{
			int select = -1;
			System.out.println("1. Load local diagram.");
			System.out.println("2. Load DB diagram.");
			System.out.println("3. New diagram");
			System.out.println("4. Commit current diagram.");
			System.out.println("5. Change View.");
			System.out.println("6. Stop program.");
			
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
				try {
					System.out.println("Enter alsolute path: ");
					String p = br.readLine();
				
					this.loadLocal(p);
					
					System.out.println("----------------------------");
					System.out.println("");
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				break;
			
			case 2:
				try {
					System.out.println("Enter load diagram version: ");
					System.out.print("Version : ");
					int v = Integer.parseInt(br.readLine());
					
					this.loadDB(v);
					
					System.out.println("----------------------------");
					System.out.println("");
						
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			
			case 3:

					System.out.println("Implemented exception.");
					System.out.println("----------------------------");
					System.out.println("");

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
				System.out.println("----------------------------");
				System.out.println("");
				break;
			}	
	
		}
		
	}
	
}
