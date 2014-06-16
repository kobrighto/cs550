package cp;

import java.io.IOException;
import java.lang.Thread.State;

import graphView.JungViewer;
import db.Diagram;
import standardGraph.*;
import vcs.DiagramManager;
import vcs.SnapshotManager;
import vcs.UserManager;

public class HDVCS_UI {
	
	/** viewStyle */
	public enum  viewStyle {LOGIN, DIAGRAM, VERSION};
	
	/** handleType */
	public enum  handleType {LOGIN, DIAGRAM, VERSION};
		
	/** handler */
	protected DiagramManager diaHandler = new DiagramManager();
	protected SnapshotManager snapHandler = new SnapshotManager();
	protected UserManager userHandler = new UserManager();
	
	/** state of login */
	private boolean running = false;
	
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
		
		System.out.println(msg);
		
	}
	
	/**
	 * display diagram.
	 * 
	 * @param Diagram diagram.
	 * @return 
	 */
	public void display(StandardGraph dia) {
		
		JungViewer viewer = new JungViewer();
		viewer.display(dia);
		
	}
	
	/**
	 * run program.
	 * 
	 * @param 
	 * @return 
	 */
	public void runProgram() {
		
		this.running = true;
		boolean state = false;
		LoginView logView = new LoginView();
		DiagramView diaView = new DiagramView();
		VersionView verView = new VersionView();
		
		if ((state = logView.runLoginView()) == false)
			this.stopProgram();
		else
			System.out.println("Login completed!!");
				
		while (running)
		{
			int select = -1;
			int isfinish = -1;
			
			System.out.println("Select next step:");
			System.out.println("1. Diagram View.");
			System.out.println("2. Version View.");
			System.out.println("3. Stop Program.");
			
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
				isfinish = diaView.runDiagramView();
				if (isfinish == 0)
					break;
				else if (isfinish == 1)
					this.stopProgram();	
				break;
			
			case 2:
				isfinish = verView.runVersionView();
				if (isfinish == 0)
					break;
				else if (isfinish == 1)
					this.stopProgram();
				break;
			
			case 3:
				this.stopProgram();
				break;

			default:
				System.out.println("Select correct number.");
				break;
			}
			
		}
		
	}
	
	/**
	 * stop program.
	 * 
	 * @param 
	 * @return 
	 */
	public void stopProgram() {
		
		this.running = false;
		
	}
	
	public static void main(String[] args) {
	
		HDVCS_UI ui = new HDVCS_UI();
		System.out.println("Welcome to HDVCS !\n");
		
		ui.runProgram();
		
	}
}
