package cp;

import cp.HDVCS_UI.handleType;
import cp.HDVCS_UI.viewStyle;
import db.Diagram;

public class DiagramView {
	
	/** viewStyle */
	private viewStyle style;
		
	/** handleType */
	private handleType type;
		
	/** current diagram */
	private Diagram curDiagram;
	
	/** path of Diagram */
	private String path;
	
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
		this.type = handleType.LOGIN;
		
		this.lastDiaVersion = getLastVersion();
		
	}
	
	/**
	 * display current diagram.
	 * 
	 * @param 
	 * @return 
	 */
	public void displayCurr() {

		//TODO: connect to diagram viewer 
		//display(curDiagram);
		
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
	 * load diagram from DB.
	 * 
	 * @param string path.
	 * @return 
	 */
	public void newDiagram(String d, int lv) {
		
		//TODO: make new diagram
		//Diagram temp = new Diagram(d, lv);
		
	}
	
	/**
	 * load diagram from DB.
	 * 
	 * @param string path.
	 * @return 
	 */
	public void runCommit() {
		
		//TODO: run commit
		//commit(Diagram curDiagram);
		
	}
	
}
