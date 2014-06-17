package vcs;

import graphIO.GraphReaderTXT;
import graphView.JungTreeViewer;
import graphView.JungViewer;

import java.awt.Container;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

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
	private String lastVersion;
	
	/**
	 * constructor
	 * 
	 * @param 
	 * @return 
	 */
	public VersionManager() {
		
		versionTree = new ArrayList<String>();
		id = "";
		curBranch = new String("1");
		lastVersion = "";
		
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
			String last = "";
			String temp = "";
			
			PreparedStatement prestatement=conn.prepareStatement("insert into version (id, owner, did, diagram, vcomment) values (null,?,?,?,?)");
			
			System.out.println("Now curBranch : " + curBranch); //debug
			System.out.println("Now last Branch : " + this.getLatestBranch(curBranch)); //debug
			if (this.getLatestBranch(curBranch).length() == 0)
			{
				temp = curBranch;
				versionTree.add(temp);
			}
			else
			{
				last = this.getLatestBranch(curBranch).substring((this.getLatestBranch(curBranch).length()-1));
				int lastPlus = Integer.parseInt(last) + 1;	
				temp = (this.getLatestBranch(curBranch).substring(0,this.getLatestBranch(curBranch).length()-1)) + String.valueOf(lastPlus);
				versionTree.add(temp);
				
				System.out.println("Now version : " + last +"    "+lastPlus+"    "+temp); //debug
			}
			
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
		//	System.out.println(prestatement.toString()); //debug
			ResultSet rs=prestatement.executeQuery();
			if(rs.next()){
				prestatement=conn.prepareStatement("delete from version where did=? and owner=?");
				prestatement.setString(1, d);
				prestatement.setString(2, this.id);
				prestatement.executeUpdate();
			
				int i = 0;
				for (String s:versionTree)
				{
					if(s.equals(d))
						versionTree.remove(i);
					i++;
				}		
				
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
	 * sort version tree by using VersionTree.
	 * 
	 * @param ArrayList<String> versions
	 * @return 
	 */
	public void sortVersionTree(ArrayList<String> versions){		
		
		versionTree.clear();
		for (int i=0; i<versions.size(); i++){
			//print();
			String[] versionToAdd = versions.get(i).split("\\.");
			boolean added = false;			

			for (int j=0; j<versionTree.size(); j++){
				String[] version = versionTree.get(j).split("\\.");
				if (version.length > versionToAdd.length){
					versionTree.add(j, versions.get(i));
					added = true;
					break;
				}else if (version.length == versionToAdd.length){
					for (int k=0; k<version.length; k++){
						if (Integer.parseInt(version[k]) > Integer.parseInt(versionToAdd[k])){
							versionTree.add(j, versions.get(i));
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
				versionTree.add(versions.get(i));
			}
		}
		System.out.println(versionTree.toString()); //debug

	}

	/**
	 * make version tree by using diagramList
	 * 
	 * @param 
	 * @return 
	 */
	public void makeVersionTree() {
		
		ArrayList<String> temp = new ArrayList<String>();
		
		try{
			Connection conn=JDBC.getConnection();
			PreparedStatement prestatement=conn.prepareStatement("select * from version where owner=?");
			prestatement.setString(1, this.id);
			ResultSet rs = prestatement.executeQuery();
			while(rs.next()){
				String did=rs.getString("did");
				temp.add(did);
				System.out.println("Exist did : " + did);
			}
			
			this.sortVersionTree(temp);
				
			System.out.println("Make snapshotTree is completed.");
			
			JFrame frame = new JFrame();
	        Container content = frame.getContentPane();
	        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        content.add(new JungTreeViewer(versionTree));
	        frame.pack();
	        frame.setVisible(true);
			
		}catch(Exception e){
			System.out.println("Make snapshotTree error!");
		}
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
			System.out.println("Builder: " + builder.toString()); //debug
			latestBranch = getDiaLastVersion(builder.toString());
		}
		
		if (latestBranch.length() != 0)
			this.curBranch = latestBranch;
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
			prestatement.setString(1, d+".1");
			ResultSet rs = prestatement.executeQuery();
			
			if(rs.next())
			{
				System.out.println("branch is already exist.");
			}
			else
			{
				this.curBranch = new String(d + ".1");
				System.out.println("Make branch is completed.\n @@@@@@ if you don't commit in new branch, branch will be goen. @@@@@");
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
