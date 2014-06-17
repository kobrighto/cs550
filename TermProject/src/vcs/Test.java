package vcs;

import graphView.JungTreeViewer;

import java.awt.Container;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Test {
	public static void main (String[] args){		
		ArrayList<String> versions = new ArrayList<String>();
		versions.add("4");
		versions.add("1.1.1");				
		versions.add("2.1");
		versions.add("2.1.1");
		versions.add("2");
		versions.add("2.1.2");
		versions.add("2.2");
		versions.add("2.3");
		versions.add("3");
		versions.add("1.1");
		versions.add("3.1");
		versions.add("1");

		VersionTree versionTree = new VersionTree();
		ArrayList<String> tree = versionTree.createVersionTree(versions);
		versionTree.print();

		String curVersion = "2";
		System.out.println("Last Version Seen: " + versionTree.getLastVersion(curVersion));

		String curBranch = "2";
		System.out.println("Latest Branch: " + versionTree.getLatestBranch(curBranch));

		JFrame frame = new JFrame();
        Container content = frame.getContentPane();
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        content.add(new JungTreeViewer(tree));
        frame.pack();
        frame.setVisible(true);
	}

}
