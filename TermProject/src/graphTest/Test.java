package graphTest;

import javax.swing.JFrame;

import graphIO.*;
import graphView.*;
import standardGraph.*;

public class Test {
	public static void main(String[] args){
		GraphReaderTXT oldReader = new GraphReaderTXT();
		GraphReaderTXT newReader = new GraphReaderTXT();
		StandardGraph oldGraph = oldReader.readTextFile("graph.txt");
		StandardGraph newGraph = newReader.readTextFile("graph2.txt");
		
		oldGraph.print();
		newGraph.print();
		
		GraphComparison compare = new GraphComparison();
		compare.print(oldGraph, newGraph);
		compare.display(oldGraph, newGraph);
		//aGraph.addNode(1, "Tuan");
		//aGraph.addEdge(2,3,"labmate");
		
		//JungViewer viewer = new JungViewer();
		//viewer.display(aGraph);
	}
}
