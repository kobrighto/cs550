package vcs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jdbc.JDBC;
import db.Diagram;
import db.Snapshot;

public class SnapshotManager {
	
	/** diagram */
	private Snapshot snapshot; //do we need?
	
	
	/** diagram List */
	private List<Snapshot> snapshotList = new ArrayList<Snapshot>();

	/** snapshot tree */
	private Diagram snapTree;
	
	//we need to keep this for one manager
	private String id;
	private String pw;
	/**
	 * constructor
	 * 
	 * @param 
	 * @return 
	 */
	public SnapshotManager() {
	
	}
	
	/**
	 * load snapshot from DB.
	 * 
	 * @param String id
	 * @param String pass
	 * @return boolean state
	 */
	public boolean loadSnapData(String i, String p) {
		
		boolean state = false;
		//TODO: Log into DB and get Snapshot.
		
		try{
			Connection conn=JDBC.getConnection();
			PreparedStatement prestatement=conn.prepareStatement("select * from snapshot where owner=?");
			prestatement.setString(1, this.id);
			ResultSet rs = prestatement.executeQuery();
			while(rs.next()){
				int sid=rs.getInt("snapid");
				int ver=rs.getInt("version");
				String dia=rs.getString("matrix");
				String own=rs.getString("owner");
				Diagram diag=new Diagram(ver,dia,own);
				Snapshot snap=new Snapshot(diag,sid);
				snapshotList.add(snap);				
			}
			System.out.println("Get SnapshotList is completed");
			state=true;
		}catch(Exception e){
			System.out.println("Get SnapshotList error!");
		}
		return state;
	}
	
	/**
	 * getSnapshot.
	 * 
	 * @param int version
	 * @return Snapshot snapshot
	 */
	public Snapshot getSnapshot(int v) {
		
		//TODO: get Snapshot.
		//this.snapshot = this.snapshotList.get(v);
		Snapshot snapshot = null;
		
		for(Snapshot s:snapshotList){
			if(s.getVersion()==v)
				snapshot=s;
		}
		/*NO DB, JUST USE ARRAYLIST
		 * try{
			Connection conn=JDBC.getConnection();
			PreparedStatement prestatement=conn.prepareStatement("select * from diagram where snapid=?");
			prestatement.setInt(1, v);
			ResultSet rs = prestatement.executeQuery();
			
			while(rs.next()){
				diagram=new Diagram();
				diagram.setVersion(rs.getInt("version"));
				diagram.setDiagram(rs.getString("matrix"));
				snapshot=new Snapshot();
				snapshot.setSnapshot(diagram);
				snapshot.setVersion(rs.getInt("snapid"));
			}
			System.out.println("Get diagram is completed");
		}catch(Exception e){
			System.out.println("Get diagram error!");
		}
		*/
		return snapshot;
	}

	/**
	 * set snapshot and return state.
	 * 
	 * @param Diagram diagram
	 * @param int version
	 * @return boolean state
	 */
	public boolean snapshot(Diagram d, int v) {
		
		boolean state = false;
		//TODO: snapshot
		// Insert Diagram into snapshotList and DB.
		//TODO: insert diagram
		
		//db starts
		try{
			Connection conn=JDBC.getConnection();
			
			PreparedStatement prestatement=conn.prepareStatement("insert into snapshot values (null,?,?,?,?)");
			prestatement.setInt(1,this.getSnapLastVersion()+1);
			prestatement.setInt(2,d.getVersion());
			prestatement.setString(3,d.getDiagram());
			prestatement.setString(4,this.id);
			prestatement.executeUpdate();
			
			System.out.println("store snapshot is completed");
			state=true;
		}catch(Exception e){
			System.out.println("store snapshot diagram error!");
		}
		return state;
		
	}

	/**
	 * make version tree by using diagramList
	 * 
	 * @param 
	 * @return Diagram versionTree
	 */
	public Diagram makeSnapTree() {
		
		//TODO: make snapshot tree
		//this.snapTree = snapshotTree();
		
		return snapTree;
	}
	
	/**
	 * Send last version of snapshot to versionView.
	 * 
	 * @param 
	 * @return int lastVersion
	 */
	public int getSnapLastVersion() {
		
		int lastVersion = 0;
		//TODO: find last version of snapshot.
		//lastVersion = ~~;
		try{
			Connection conn=JDBC.getConnection();
			PreparedStatement prestatement=conn.prepareStatement("select snapid from diagram where owner=? order by version desc limit 1");
			prestatement.setString(1, this.id);
			ResultSet rs=prestatement.executeQuery();
			while(rs.next()){
				lastVersion=rs.getInt("snapid");
			}
			System.out.println("Get last version is completed");
		}catch(Exception e){
			System.out.println("Get last version error!");
		}
				
		return lastVersion;
	}
	
}
