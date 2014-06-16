package vcs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import standardGraph.StandardGraph;

import jdbc.JDBC;
import db.Snapshot;
import db.Version;

public class VersionManager {
			
	/** diagram List */
	private List<Version> versionList;
	
	/** owner */
	private String id;
	
	/** lastVersion of Version */
	private int lastVersion;
	
	/**
	 * constructor
	 * 
	 * @param 
	 * @return 
	 */
	public VersionManager() {
		
		versionList = new ArrayList<Version>();
		id = null;
		lastVersion = 0;
		
	}
	
	/**
	 * set Owner
	 * 
	 * @param String id
	 * @return 
	 */
	public void setOwner(String i) {
		this.id = i;
		
	}
	
	/**
	 * load diagram from DB.
	 * 
	 * @param String id
	 * @return boolean state
	 */
	public boolean loadDiaData(String i) {
		
		boolean state = false;
		this.id=i;

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
				Version diag=new Version(ver,dia);
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
	 * load diagram from DB.
	 * 
	 * @param String id
	 * @param String pass
	 * @return boolean state
	 */
	public boolean refresh(String i, String p) {
		
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
				Version diag=new Version(ver,dia);
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
	public Version getVersion(int v) {
		
		//TODO: get Diagrams.
		//this.diagram = this.diagramList.get(v);
		Version version=null;
		//diagram=this.diagramList.get(v);
		for(Version d:diagramList){
			if(d.getdID()==v)
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
	public boolean commit(Version v) {
		
		boolean state = false;
		//TODO: commit
		// Insert Diagram into diagramList and DB.
		//TODO: insert diagram
		
		//db starts
		try{
			Connection conn=JDBC.getConnection();
			
			PreparedStatement prestatement=conn.prepareStatement("insert into diagram (version, diagram, owner) values (?,?,?)");
			prestatement.setInt(1,this.getDiaLastVersion()+1);
			prestatement.setString(2, v.getDiagram());
			prestatement.setString(3,this.id);
			prestatement.executeUpdate();
			
			state=true;
			System.out.println("Commit diagram is completed");
		}catch(Exception e){
			e.printStackTrace();
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
	public Version difference(int v1, int v2) {
		
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
	public boolean delVersion(int v) {
		
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
			
			System.out.println("Delete version is completed");
		}catch(Exception e){
			System.out.println("Delete version error!");
		}
		return state;
	}

	/**
	 * make version tree by using diagramList
	 * 
	 * @param 
	 * @return StandardGraph versionTree
	 */
	public StandardGraph makeVersionTree() {
		
		StandardGraph snapTree = null;
		
		try{
			Connection conn=JDBC.getConnection();
			PreparedStatement prestatement=conn.prepareStatement("select * from version where owner=?");
			prestatement.setString(1, this.id);
			ResultSet rs = prestatement.executeQuery();
			while(rs.next()){
				String did=rs.getString("did");
				String dia=rs.getString("version");
				String own=rs.getString("owner");
				String vc=rs.getString("vcomment");
				Version ver=new Version(did,dia,vc);
				versionList.add(ver);	
			}
			
			
			//TODO: make version tree
			System.out.println("Make snapshotTree is completed");
		}catch(Exception e){
			System.out.println("Make snapshotTree error!");
		}
		
		return snapTree;
	}
	
	/**
	 * Send last version of diagram to versionView.
	 * 
	 * @param 
	 * @return int lastVersion
	 */
	public int getDiaLastVersion() {
		
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
