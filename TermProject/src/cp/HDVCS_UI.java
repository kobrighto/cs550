package cp;

import vcs.DiagramManager;
import vcs.SnapshotManager;
import vcs.UserManager;
import db.Diagram;

public class HDVCS_UI {
	
	/** viewStyle */
	public enum  viewStyle {LOGIN, DIAGRAM, VERSION};
	
	/** handleType */
	public enum  handleType {LOGIN, DIAGRAM, VERSION};
	
	/** handler */
	protected UserManager logHandler;
	protected DiagramManager diaHandler;
	protected SnapshotManager snapHandler;
		
	/**
	 * shift style of UI view.
	 * 
	 * @param enum view sytle.
	 * @return 
	 */
	public void shiftViewStyle(viewStyle style) {
		
	}
	
	
	/**
	 * shift type of handler.
	 * 
	 * @param enum handle type.
	 * @return 
	 */
	public void settHandleType(handleType type) {
	
	}
	
	/**
	 * display message.
	 * 
	 * @param string message.
	 * @return 
	 */
	public void display(String msg) {
		
		//TODO: display msg.
		
	}
	
	/**
	 * display diagram.
	 * 
	 * @param Diagram diagram.
	 * @return 
	 */
	public void display(Diagram dia) {
		
		//TODO: display diagram.
		
	}
	
	public static void main(String[] args) {
		
	}
}
