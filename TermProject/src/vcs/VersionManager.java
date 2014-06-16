package vcs;

import graphIO.GraphReaderTXT;
import graphView.JungViewer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import standardGraph.GraphComparison;
import standardGraph.StandardGraph;

import jdbc.JDBC;
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
	 * getDiagram.
	 * 
	 * @param int version
	 * @return Diagram diagram
	 */
	public Version getVersion(String did) {
		
		Version version = null;

		try{
			Connection conn=JDBC.getConnection();
			PreparedStatement prestatement=conn.prepareStatement("select * from version where did=?");
			prestatement.setString(1, did);
			ResultSet rs = prestatement.executeQuery();
			
			rs.next();
			
			version=new Version(rs.getString("did"), rs.getString("diagram"), rs.getString("vcomment"));
			
			System.out.println("Get diagram is completed");
		}catch(Exception e){
			System.out.println("Get diagram error!");
		}
		return version;
	}

	/**
	 * commit and return state.
	 * 
	 * @param Diagram diagram
	 * @return boolean state
	 */
	public boolean commit(Version v, String vc) {
		
		boolean state = false;
		
		//db starts
		try{
			Connection conn=JDBC.getConnection();
			
			PreparedStatement prestatement=conn.prepareStatement("insert into version (did, diagram, owner, vcomment) values (?,?,?,?)");
			//TODO:Set valid did
			//prestatement.setInt(1,this.getDiaLastVersion()+1);
			prestatement.setString(2, v.getDiagram());
			prestatement.setString(3,this.id);
			prestatement.setString(4,vc);
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
	 * @param string d1
	 * @param string d2
	 * @return 
	 */
	public void difference(String d1, String d2) {
		
		GraphReaderTXT oldReader = new GraphReaderTXT();
		GraphReaderTXT newReader = new GraphReaderTXT();
		StandardGraph oldGraph = oldReader.readString(this.getVersion(d1).getDiagram());
		StandardGraph newGraph = newReader.readString(this.getVersion(d2).getDiagram());
		
		GraphComparison compare = new GraphComparison();
		compare.print(oldGraph, newGraph);
		compare.display(oldGraph, newGraph);

	}	
	
	/**
	 * Delete diagram from DB.
	 * 
	 * @param String did
	 * @return boolean state
	 */
	public boolean delVersion(String d) {
		
		boolean state = false;
		
		//Delete from DB
		try{
			Connection conn=JDBC.getConnection();

			PreparedStatement prestatement=conn.prepareStatement("delete from version where did=? and owner=?");
			prestatement.setString(1, d);
			prestatement.setString(2, this.id);
			prestatement.executeUpdate();
			
			state = true;
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
	 * @param String branch header
	 * @return String lastVersion
	 */
	public int getDiaLastVersion(String bh) {
		
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

	/**
	 * make branch and commit branch header.
	 * 
	 * @param String did
	 * @param String branch name
	 * @return boolean state
	 */
	public boolean makeBranch(String d, String bn) {
		String branchHeaderId = d + ".1";
		Version v = this.getVersion(d);
		
		boolean state = false;
		
		//db starts
		try{
			Connection conn=JDBC.getConnection();
			
			PreparedStatement prestatement=conn.prepareStatement("insert into version (did, diagram, owner, vcomment) values (?,?,?,?)");
			prestatement.setString(1,branchHeaderId);
			prestatement.setString(2,v.getDiagram());
			prestatement.setString(3,this.id);
			prestatement.setString(4,bn);
			prestatement.executeUpdate();
			
			state=true;
			System.out.println("Make branch is completed");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Make branch error!");
		}
		
		return state;
		
	}

	public void changeBranch(String vn) {
		
		
	}
	
}
