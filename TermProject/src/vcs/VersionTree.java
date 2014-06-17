package vcs;

import java.util.ArrayList;

public class VersionTree {
	private ArrayList<String> versionTree;

	public VersionTree(){
		versionTree = new ArrayList<String>();
	}

	public ArrayList<String> createVersionTree(ArrayList<String> versions){		
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
		return versionTree;
	}

	public String getLastVersion(String curVersion){
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
			latestBranch = getLastVersion(builder.toString());
		}
		return latestBranch;
	}

	public void print(){
		System.out.println("Version Array List:");
		for (int i=0; i<versionTree.size(); i++){
			System.out.println(versionTree.get(i));;
		}
	}

}
