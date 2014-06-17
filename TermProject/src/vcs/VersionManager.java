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
	private ArrayList<String> versionTree;
	
	/** owner */
	private String id;
	
	/** curBranch */
	private String curBranch;
	
	/** lastVersion of Version */
	private int lastVersion;
	
	/**
	 * constructor
	 * 
	 * @param 
	 * @return 
	 */
	public VersionManager() {
		
		versionTree = new ArrayList<String>();
		id = null;
		curBranch = new String("1");
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
			if(rs.first()){
				//rs.first();
				
				version=new Version(rs.getString("did"), rs.getString("diagram"), rs.getString("vcomment"));
				
				System.out.println("Get diagram is completed");
			}
			else{
				System.out.println("There is no version "+did);
			}
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
			
			PreparedStatement prestatement=conn.prepareStatement("insert into version (id, owner, did, diagram, vcomment) values (null,?,?,?,?)");
			
			String last = this.getLatestBranch(vc).substring(this.getLatestBranch(vc).length()-1);
			int lastPlus = Integer.parseInt(last) + 1;
			String temp = (this.getLatestBranch(vc).substring(0,this.getLatestBranch(vc).length()-1)) + String.valueOf(lastPlus);
				
			prestatement.setString(1,this.id);
			prestatement.setString(2,temp);
			prestatement.setString(3, v.getDiagram());
			prestatement.setString(4,vc);
			System.out.println(prestatement.toString());
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
			PreparedStatement prestatement=conn.prepareStatement("select * from version where did=? and owner=?");
			prestatement.setString(1,d);
			prestatement.setString(2,this.id);
		//	System.out.println(prestatement.toString());
			ResultSet rs=prestatement.executeQuery();
			if(rs.next()){
				prestatement=conn.prepareStatement("delete from version where did=? and owner=?");
				prestatement.setString(1, d);
				prestatement.setString(2, this.id);
				prestatement.executeUpdate();
			
				state = true;
				System.out.println("Delete version is completed");
			}else{
				System.out.println("There is no version "+d);
			}
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
				versionTree.add(did);	
			}
			
			
			
			System.out.println("Make snapshotTree is completed");
		}catch(Exception e){
			System.out.println("Make snapshotTree error!");
		}
		
		return snapTree;
	}
	
	/**
	 * Calculate last version of diagram.
	 * 
	 * @param String curVersion
	 * @return String lastVersion
	 */
	public String getDiaLastVersion(String curVersion) {
		
		String[] versionToCheck = curVersion.split("\\.");
		int lastChildSeen = -1;
		boolean childSeen = false;
		boolean passed = false;
		for (int i=0; i<versionTree.size(); i++){
			String[] versions = versionTree.get(i).split("\\.");
			if (versions.length <= versionToCheck.length){
				// do nothing
			}else if (versions.length == versionToCheck.length+1){
				// check to see if it's a child
				for (int j=0; j<versions.length-1; j++){
					if (Integer.parseInt(versions[j]) > Integer.parseInt(versionToCheck[j])){
						// not a child, already passed
						passed = true;
						break;
					}else if (Integer.parseInt(versions[j]) < Integer.parseInt(versionToCheck[j])){
						// not a child, did not pass
						break;
					}
					childSeen = true;
				}
				if (passed == true){
					// already pass the children 'area'
					break;
				}else if (childSeen == true){
					// this is a child, return
					lastChildSeen = i;
				}
			}else{
				break;
			}
		}
		if (lastChildSeen == -1){
			return "-1"; // no child has been found
		}
		return versionTree.get(lastChildSeen); //latest child = most recent version
	}
	
	/**
	 * Calculate last version of diagram in curbranch.
	 * 
	 * @param String curVersion
	 * @return String lastVersion
	 */
	public String getLatestBranch(String curBranch){
		String[] curBranchToCheck = curBranch.split("\\.");
		String latestBranch = "";
		if (curBranchToCheck.length == 1){
			for (int i=0; i<versionTree.size(); i++){
				String[] version = versionTree.get(i).split("\\.");
				if (version.length == 1){
					latestBranch = versionTree.get(i);
				}else {
					break;
				}
			}
		}else{			
			StringBuilder builder = new StringBuilder();
			builder.append(curBranchToCheck[0]);
			for (int i=1; i<curBranchToCheck.length-1; i++){
				builder.append(".");
				builder.append(curBranchToCheck[i]);				
			}
			//System.out.println("Builder: " + builder.toString());
			latestBranch = getDiaLastVersion(builder.toString());
		}
		return latestBranch;
	}

	/**
	 * make branch and commit branch header.
	 * 
	 * @param String did
	 * @return 
	 */
	public void makeBranch(String d) {
		
		try{
			Connection conn=JDBC.getConnection();
			PreparedStatement prestatement=conn.prepareStatement("select * from version where did=?");
			prestatement.setString(1, d);
			ResultSet rs = prestatement.executeQuery();
			
			if(rs.next())
			{
				System.out.println("branch is already exist.");
			}
			else
			{
				this.curBranch = new String(d + ".1");
				System.out.println("Make branch is completed.");
			}
			
		}catch(Exception e){
			System.out.println("Make branch error!");
		}

	}

	public void changeBranch(String vn) {
		try{
			Connection conn=JDBC.getConnection();
			PreparedStatement prestatement=conn.prepareStatement("select * from version where did=?");
			prestatement.setString(1, vn);
			ResultSet rs = prestatement.executeQuery();
			
			if(rs.next())
			{
				this.curBranch = new String(vn);
				System.out.println("branch change is completed.");
			}
			else
			{
				System.out.println(vn + " branch is not exist.");
			}
			
		}catch(Exception e){
			System.out.println("Make branch error!");
		}
		
		
		
	}
	
}
