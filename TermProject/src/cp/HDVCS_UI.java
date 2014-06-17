package cp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Thread.State;

import graphView.JungViewer;
import standardGraph.*;
import vcs.SnapshotManager;
import vcs.UserManager;
import vcs.VersionManager;

public class HDVCS_UI {
	
	/** viewStyle */
	public enum  viewStyle {LOGIN, VERSION, SNAPSHOT};
	
	/** handleType */
	public enum  handleType {LOGIN, VERSION, SNAPSHOT};
		
	/** handler */
	static protected VersionManager verHandler =null;
	static protected SnapshotManager snapHandler = null;
	static protected UserManager userHandler = null;
	
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
		
		InputStreamReader in=new InputStreamReader(System.in);
	    BufferedReader br=new BufferedReader(in);
	    
	    verHandler = new VersionManager();
		snapHandler = new SnapshotManager();
		userHandler = new UserManager();
		
		this.running = true;
		boolean state = false;
		LoginView logView = new LoginView();
		
		if ((state = logView.runLoginView()) == false)
			this.stopProgram();
		else
		{
			System.out.println("Login completed!!");
			verHandler.setOwner(logView.getId());
			snapHandler.setOwner(logView.getId());
		}
		
		SnapshotView snapView = new SnapshotView();
		VersionView verView = new VersionView();
		
		while (running)
		{
			String select = "";
			int isfinish = -1;
			
			System.out.println("Select next step:");
			System.out.println("1. Version View.");
			System.out.println("2. Snapshot View.");
			System.out.println("3. Login View.");
			System.out.println("4. Stop Program.");
			
			System.out.print("Select : ");
			
			try {
				select = br.readLine();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("----------------------------");
			System.out.println("");
			
			switch (select)
			{
			case "1":
				isfinish = verView.runVersionView();
				if (isfinish == 0)
					break;
				else if (isfinish == 1)
					this.stopProgram();	
				break;
			
			case "2":
				isfinish = snapView.runSnapshotView();
				if (isfinish == 0)
					break;
				else if (isfinish == 1)
					this.stopProgram();
				break;
			
			case "3":
				if ((state = logView.runLoginView()) == false)
					this.stopProgram();
				else
				{
					System.out.println("Login completed!!");
					verHandler.setOwner(logView.getId());
					snapHandler.setOwner(logView.getId());
				}
				break;

			case "4":
				this.stopProgram();
				break;	
				
			default:
				System.out.println("Select correct number.");
				System.out.println("----------------------------");
				System.out.println("");
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
		
		System.out.println("Goodbye to HDVCS !\n");
		System.exit(0);
	}
	
	String readFile(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
}
