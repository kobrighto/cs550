package standardGraph;

import graphView.JungComparisonViewer;

import java.util.ArrayList;

public class GraphComparison {
	private ArrayList<Integer> removedNodes;
	private ArrayList<Integer> addedNodes;
	private ArrayList<Integer> removedEdges;
	private ArrayList<Integer> addedEdges;

	public GraphComparison(){
		removedNodes = new ArrayList<Integer>();
		addedNodes = new ArrayList<Integer>();
		removedEdges = new ArrayList<Integer>();
		addedEdges = new ArrayList<Integer>();
	}

	private boolean compareGraphs(StandardGraph oldGraph, StandardGraph newGraph){
		if (oldGraph.isDirected() != newGraph.isDirected()){
			return false;
		}
		return true;
	}

	private void compareNodes(ArrayList<Node> oldNodes, ArrayList<Node> newNodes){
		int oldCount = 0;
		int newCount = 0;
		while(oldCount < oldNodes.size() && newCount < newNodes.size()){
			if(oldNodes.get(oldCount).getID() == newNodes.get(newCount).getID()){
				if (oldNodes.get(oldCount).getLabel().compareTo(newNodes.get(newCount).getLabel()) >0){
					//a new node was added
					addedNodes.add(newCount);
					newCount++;
				}else if (oldNodes.get(oldCount).getLabel().compareTo(newNodes.get(newCount).getLabel()) <0){
					//an old node was removed
					removedNodes.add(oldCount);
					oldCount++;
				}else{
					//do nothing, same nodes
					oldCount++;
					newCount++;				
				}
			}else if (oldNodes.get(oldCount).getID() > newNodes.get(newCount).getID()){
				//a new node was added
				addedNodes.add(newCount);
				newCount++;
			}else {
				//an old node was removed
				removedNodes.add(oldCount);
				oldCount++;
			}
		}

		while (oldCount < oldNodes.size()){
			//all remaining old nodes here were removed
			removedNodes.add(oldCount);
			oldCount++;
		}

		while (newCount < newNodes.size()){
			//all remaining new nodes here were added
			addedNodes.add(newCount);
			newCount++;
		}		
	}

	private void compareEdges(ArrayList<Edge> oldEdges, ArrayList<Edge> newEdges){
		int oldCount = 0;
		int newCount = 0;
		while (oldCount < oldEdges.size() && newCount < newEdges.size()){
			if (oldEdges.get(oldCount).getFromNode() < newEdges.get(newCount).getFromNode()){
				//an old edge was removed
				removedEdges.add(oldCount);
				oldCount++;
			}else if (oldEdges.get(oldCount).getFromNode() > newEdges.get(newCount).getFromNode()){
				//a new edge was added
				addedEdges.add(newCount);
				newCount++;
			}else{
				if (oldEdges.get(oldCount).getToNode() < newEdges.get(newCount).getToNode()){
					//an old edge was removed
					removedEdges.add(oldCount);
					oldCount++;
				}else if(oldEdges.get(oldCount).getToNode() > newEdges.get(newCount).getToNode()){
					//a new edge was added
					addedEdges.add(newCount);
					newCount++;
				}else{
					if (oldEdges.get(oldCount).getLabel().compareTo(newEdges.get(newCount).getLabel()) <0 ){
						//an old edge was removed
						removedEdges.add(oldCount);
						oldCount++;
					}else if(oldEdges.get(oldCount).getLabel().compareTo(newEdges.get(newCount).getLabel()) >0 ){
						//a new edge was added
						addedEdges.add(newCount);
						newCount++;
					}else{
						//two edges are the same
						oldCount++;
						newCount++;
					}
				}
			}
		}

		while (oldCount < oldEdges.size()){
			//all remaining old edges here were removed
			removedEdges.add(oldCount);
			oldCount++;
		}

		while (newCount < newEdges.size()){
			// all remaining new edges here were added
			addedEdges.add(newCount);
			newCount++;
		}
	}

	public void print(StandardGraph oldGraph, StandardGraph newGraph){
		boolean graphComparison = compareGraphs(oldGraph, newGraph);
		if (graphComparison == false){
			//2 completely different graphs (1 undirected, 1 directed)
			System.out.println("Compare Graph: Different Graph Type...");
			return;
		}else{
			ArrayList<Node> oldNodes = oldGraph.getNodeSet();
			ArrayList<Node> newNodes = newGraph.getNodeSet();
			ArrayList<Edge> oldEdges = oldGraph.getEdgeSet();
			ArrayList<Edge> newEdges = newGraph.getEdgeSet();

			compareNodes(oldNodes,newNodes);
			compareEdges(oldEdges,newEdges);

			for (int i=0; i<removedNodes.size(); i++){
				Node oldNode = oldNodes.get(removedNodes.get(i));
				System.out.println("Removed Nodes: ");
				System.out.println("ID: " + Integer.toString(oldNode.getID()) + ", Label: " + oldNode.getLabel());
			}

			for (int i=0; i<addedNodes.size(); i++){
				Node newNode = newNodes.get(addedNodes.get(i));
				System.out.println("Added Nodes: ");
				System.out.println("ID: " + Integer.toString(newNode.getID()) + ", Label: " + newNode.getLabel());
			}

			for (int i=0; i<removedEdges.size(); i++){
				Edge oldEdge = oldEdges.get(removedEdges.get(i));
				System.out.println("Removed Edges: ");
				System.out.println("Edge: " + Integer.toString(oldEdge.getFromNode())+ "--->" + Integer.toString(oldEdge.getToNode()) + ", Label: " + oldEdge.getLabel());

			}

			for (int i=0; i<addedEdges.size(); i++){
				Edge newEdge = newEdges.get(addedEdges.get(i));
				System.out.println("Added Edges: ");
				System.out.println("Edge: " + Integer.toString(newEdge.getFromNode())+ "--->" + Integer.toString(newEdge.getToNode()) + ", Label: " + newEdge.getLabel());
			}
		}		
	}
	
	public void display(StandardGraph oldGraph, StandardGraph newGraph){
		JungComparisonViewer viewer = new JungComparisonViewer();
		boolean graphComparison = compareGraphs(oldGraph, newGraph);
		if (graphComparison == false){
			viewer.display(oldGraph, newGraph, false, removedNodes, addedNodes, removedEdges, addedEdges);
		}else{
			ArrayList<Node> oldNodes = oldGraph.getNodeSet();
			ArrayList<Node> newNodes = newGraph.getNodeSet();
			ArrayList<Edge> oldEdges = oldGraph.getEdgeSet();
			ArrayList<Edge> newEdges = newGraph.getEdgeSet();
			
			compareNodes(oldNodes,newNodes);
			compareEdges(oldEdges,newEdges);
			
			viewer.display(oldGraph, newGraph, true, removedNodes, addedNodes, removedEdges, addedEdges);
		}
	}
}
