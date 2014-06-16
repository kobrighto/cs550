package vcs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import standardGraph.StandardGraph;

import jdbc.JDBC;
import db.Snapshot;
import db.Version;

public class SnapshotManager {
	
	/** diagram List */
	private List<Snapshot> snapshotList;
	
	/** owner */
	private String id;

	/** lastId of snapshot */
	private int lastSnapshot;
	
	/**
	 * constructor
	 * 
	 * @param 
	 * @return 
	 */
	public SnapshotManager() {
	
		snapshotList = new ArrayList<Snapshot>();
		id = null;
		lastSnapshot = 0;
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
	 * getSnapshot.
	 * 
	 * @param int version
	 * @return Snapshot snapshot
	 */
	public Snapshot getSnapshot(int sid) {
		
		Snapshot snapshot = null;
		Version version = null;

		try{
			Connection conn=JDBC.getConnection();
			PreparedStatement prestatement=conn.prepareStatement("select * from snapshot where sid=?");
			prestatement.setInt(1, sid);
			ResultSet rs = prestatement.executeQuery();
			
			rs.next();
		
			version=new Version(rs.getString("did"), rs.getString("diagram"), null);
			snapshot=new Snapshot(version, rs.getInt("sid"), rs.getString("scomment"));

			System.out.println("Get diagram is completed");
		}catch(Exception e){
			System.out.println("Get diagram error!");
		}
		return snapshot;
	}

	/**
	 * set snapshot and return state.
	 * 
	 * @param Diagram diagram
	 * @param int version
	 * @return boolean state
	 */
	public boolean snapshot(Version d, String sc) {
		
		boolean state = false;
		
		//db starts
		try{
			Connection conn=JDBC.getConnection();
			
			PreparedStatement prestatement=conn.prepareStatement("insert into snapshot (sid, did, diagram, owner, scomment) values (?,?,?,?,?)");
			prestatement.setInt(1,this.getSnapLastVersion() + 1);
			prestatement.setString(2,d.getdID());
			prestatement.setString(3,d.getDiagram());
			prestatement.setString(4,this.id);
			prestatement.setString(5,sc);
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
	public StandardGraph makeSnapTree() {
		
		StandardGraph snapTree = null;
		
		try{
			Connection conn=JDBC.getConnection();
			PreparedStatement prestatement=conn.prepareStatement("select * from snapshot where owner=?");
			prestatement.setString(1, this.id);
			ResultSet rs = prestatement.executeQuery();
			while(rs.next()){
				int sid=rs.getInt("sid");
				String did=rs.getString("did");
				String dia=rs.getString("version");
				String own=rs.getString("owner");
				String sc=rs.getString("scomment");
				Version ver=new Version(did,dia,null);
				Snapshot snap=new Snapshot(ver,sid,sc);
				snapshotList.add(snap);	
			}
			
			
			//TODO: make snapshot tree
			System.out.println("Make snapshotTree is completed");
		}catch(Exception e){
			System.out.println("Make snapshotTree error!");
		}
		
		return snapTree;
				
	}
	
	/**
	 * Send last version of snapshot to versionView.
	 * 
	 * @param 
	 * @return 
	 */
	public int getSnapLastVersion() {
		
		try{
			Connection conn=JDBC.getConnection();
			PreparedStatement prestatement=conn.prepareStatement("select snapid from diagram where owner=? order by version desc limit 1");
			prestatement.setString(1, this.id);
			ResultSet rs=prestatement.executeQuery();
			while(rs.next()){
				lastSnapshot=rs.getInt("snapid");
			}
			System.out.println("Get last snap id is completed");
		}catch(Exception e){
			System.out.println("Get last snap id error!");
		}
		return lastSnapshot;

	}
	
}
