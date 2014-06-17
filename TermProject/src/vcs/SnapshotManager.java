package vcs;

import graphView.JungTreeViewer;

import java.awt.Container;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import standardGraph.StandardGraph;

import jdbc.JDBC;
import db.Snapshot;
import db.Version;

public class SnapshotManager {
	
	/** version List */
	private ArrayList<String> snapshotTree;
	
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
	
		snapshotTree = new ArrayList<String>();
		id = "";
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
			
			if (rs.next())
			{
				version=new Version(rs.getString("did"), rs.getString("diagram"), null);
				snapshot=new Snapshot(version, rs.getInt("sid"), rs.getString("scomment"));
				System.out.println("Get snapshot is completed");
			}
			else
			{
				System.out.println("There is no snapshot "+ sid);
			}
		}catch(Exception e){
			System.out.println("Get snapshot error!");
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
	 * sort snapshot tree by using VersionTree.
	 * 
	 * @param 
	 * @return StandardGraph versionTree
	 */
	public void sortSnapshotTree(ArrayList<String> versions){		
		for (int i=0; i<versions.size(); i++){
			//print();
			String[] versionToAdd = versions.get(i).split("\\.");
			boolean added = false;			

			for (int j=0; j<snapshotTree.size(); j++){
				String[] version = snapshotTree.get(j).split("\\.");
				if (version.length > versionToAdd.length){
					snapshotTree.add(j, versions.get(i));
					added = true;
					break;
				}else if (version.length == versionToAdd.length){
					for (int k=0; k<version.length; k++){
						if (Integer.parseInt(version[k]) > Integer.parseInt(versionToAdd[k])){
							snapshotTree.add(j, versions.get(i));
							added = true;
							break;
						}else if (Integer.parseInt(version[k]) < Integer.parseInt(versionToAdd[k])){
							break;
						}
					}
					if (added == true){
						break;
					}
				}
			}
			if (added == false){
				snapshotTree.add(versions.get(i));
			}
		}		

	}

	/**
	 * make snapshot tree by using diagramList
	 * 
	 * @param 
	 * @return 
	 */
	public void makeSnapTree() {
		
		ArrayList<String> temp = new ArrayList<String>();
		
		try{
			Connection conn=JDBC.getConnection();
			PreparedStatement prestatement=conn.prepareStatement("select * from snapshot where owner=?");
			prestatement.setString(1, this.id);
			ResultSet rs = prestatement.executeQuery();
			while(rs.next()){
				String sid=rs.getString("sid");
				temp.add(sid);	
			}
			
			this.sortSnapshotTree(temp);
				
			System.out.println("Make snapshotTree is completed.");
			
			JFrame frame = new JFrame("Snapshot Tree");
	        Container content = frame.getContentPane();
	        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        content.add(new JungTreeViewer(snapshotTree));
	        frame.pack();
	        frame.setVisible(true);
			
		}catch(Exception e){
			System.out.println("Make snapshotTree error!");
		}
				
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
			PreparedStatement prestatement=conn.prepareStatement("select sid from snapshot where owner=? order by sid desc limit 1");
			prestatement.setString(1, this.id);
			ResultSet rs=prestatement.executeQuery();
			while(rs.next()){
				lastSnapshot=rs.getInt("sid");
			}
			System.out.println("Get last snap id is completed");
		}catch(Exception e){
			System.out.println("Get last snap id error!");
		}
		return lastSnapshot;

	}
	
}
