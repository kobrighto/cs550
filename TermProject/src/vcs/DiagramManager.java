package vcs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.JDBC;
import db.Diagram;

public class DiagramManager {
		
	/** diagram */
	private Diagram diagram; //do we need?
	
	/** diagram List */
	private List<Diagram> diagramList = new ArrayList<Diagram>();
	
	//we need to keep this for one manager
	private String id;
	private String pw;
	/**
	 * constructor
	 * 
	 * @param 
	 * @return 
	 */
	public DiagramManager() {
	}
	
	/**
	 * load diagram from DB.
	 * 
	 * @param String id
	 * @param String pass
	 * @return boolean state
	 */
	public boolean loadDiaData(String i, String p) {
		
		boolean state = false;
		this.id=i;
		this.pw=p;
		//TODO: Log in into DB and get Diagrams.
		
		//this.diagramList = loadDia();
		try{
			Connection conn=JDBC.getConnection();
			PreparedStatement prestatement=conn.prepareStatement("select * from diagram where owner=?");
			prestatement.setString(1, this.id);
			ResultSet rs = prestatement.executeQuery();
			while(rs.next()){
				int ver=rs.getInt("version");
				String dia=rs.getString("matrix");
				String own=rs.getString("owner");
				Diagram diag=new Diagram(ver,dia,own);
				diagramList.add(diag);				
			}
			System.out.println("Get diagramList is completed");
			state=true;
		}catch(Exception e){
			System.out.println("Get diagramList error!");
		}
		return state;
	}
	
	/**
	 * getDiagram.
	 * 
	 * @param int version
	 * @return Diagram diagram
	 */
	public Diagram getDiagram(int v) {
		
		//TODO: get Diagrams.
		//this.diagram = this.diagramList.get(v);
			Diagram diagram=null;
		//diagram=this.diagramList.get(v);
		for(Diagram d:diagramList){
			if(d.getVersion()==v)
				diagram=d;
		}
		/*NO DB, JUST USE LIST
		 * Diagram diagram=null;
		try{
			Connection conn=JDBC.getConnection();
			PreparedStatement prestatement=conn.prepareStatement("select * from diagram where version=?");
			prestatement.setInt(1, v);
			ResultSet rs = prestatement.executeQuery();
			diagram=new Diagram();
			while(rs.next()){
				diagram.setVersion(rs.getInt("version"));
				diagram.setDiagram(rs.getString("matrix"));
				System.out.println("rs: "+rs.getString("matrix"));
			}
			System.out.println("Get diagram is completed");
		}catch(Exception e){
			System.out.println("Get diagram error!");
		}
		*/
		return diagram;
	}

	/**
	 * commit and return state.
	 * 
	 * @param Diagram diagram
	 * @return boolean state
	 */
	public boolean commit(Diagram d, int v) {
		
		boolean state = false;
		//TODO: commit
		// Insert Diagram into diagramList and DB.
		//TODO: insert diagram
		
		//db starts
		try{
			Connection conn=JDBC.getConnection();
			
			PreparedStatement prestatement=conn.prepareStatement("insert into diagram values (null,?,?,?)");
			prestatement.setInt(1,this.getDiaLastVersion()+1);
			prestatement.setString(2, d.getDiagram());
			prestatement.setString(3,this.id);
			prestatement.executeUpdate();
			state=true;
			System.out.println("Commit diagram is completed");
		}catch(Exception e){
			System.out.println("Commit diagram error!");
		}
		return state;
	}
	
	/**
	 * difference and return diagram.
	 * 
	 * @param string path
	 * @return Diagram diffDiagram
	 */
	public Diagram difference(int v1, int v2) {
		
		//TODO: difference
		//diagram = diffdiagram();
		
		return diagram;
	}	
	
	/**
	 * Delete diagram from DB.
	 * 
	 * @param int version
	 * @return boolean state
	 */
	public boolean delDiagram(int v) {
		
		boolean state = false;
		//TODO: delete
		//delete from diagramList and DB.
		//TODO: delete from diagramList
		
		//Delete from DB
		try{
			Connection conn=JDBC.getConnection();

			PreparedStatement prestatement=conn.prepareStatement("delete from diagram where version=? and owner=?");
			prestatement.setInt(1, v);
			prestatement.setString(2, this.id);
			prestatement.executeUpdate();
			
			System.out.println("Delete diagram is completed");
		}catch(Exception e){
			System.out.println("Delete diagram error!");
		}
		return state;
	}

	/**
	 * make version tree by using diagramList
	 * 
	 * @param 
	 * @return Diagram versionTree
	 */
	public Diagram makeVersionTree() {
		
		//TODO: make version tree
		//this.diagram = versionTree();
		
		return diagram;
	}
	
	/**
	 * Send last version of diagram to versionView.
	 * 
	 * @param 
	 * @return int lastVersion
	 */
	public int getDiaLastVersion() {
		
		int lastVersion = 0;
		//TODO: find last version of each user's diagram.
		//lastVersion = ~~;
		try{
			Connection conn=JDBC.getConnection();
			PreparedStatement prestatement=conn.prepareStatement("select version from diagram where owner=? order by version desc limit 1");
			prestatement.setString(1, this.id);
			ResultSet rs=prestatement.executeQuery();
			while(rs.next()){
				lastVersion=rs.getInt("version");
			}
			System.out.println("Get last version is completed");
		}catch(Exception e){
			System.out.println("Get last version error!");
		}
				
		return lastVersion;
	}
	
}
