package kaist.cs550.termproject.standardgraph;

import java.util.ArrayList;

public class StandardGraph {
	private ArrayList<Node> nodes;
	private ArrayList<Edge> edges;
	
	public void addNode(int number, String label){
		Node newNode = new Node(number, label);
		nodes.add(newNode);
	}
	
	public void addEdge(int fromEdge, int toEdge, boolean isDirected, String label){
		Edge newEdge = new Edge(fromEdge, toEdge, isDirected, label);
		edges.add(newEdge);
	}
	
	public void print(){
		System.out.println("Nodes list: ");
		for (int i=0; i<nodes.size(); i++){
			System.out.println("Number: " + nodes.get(i).getNumber() + ", Label: " + nodes.get(i).getLabel());
		}
		System.out.println();
		System.out.println("Edges list: ");
		for (int i=0; i<edges.size(); i++){
			if (edges.get(i).isDirected()==true){
				System.out.println("Edge: " + edges.get(i).getFromEdge() + "--->" + edges.get(i).getToEdge() 
						+", Label: " + edges.get(i).getLabel() + ", Directed");
			}else{
				System.out.println("Edge: " + edges.get(i).getFromEdge() + "----" + edges.get(i).getToEdge() 
						+", Label: " + edges.get(i).getLabel() + ", Undirected");
			}
		}
	}
	
	public Node getNode(int number){
		for(int i=0; i<nodes.size(); i++){
			if (nodes.get(i).getNumber()==number){
				return nodes.get(i);
			}
		}
		System.err.println("Node cannot be found!");
		return null;
	}
	
	public Edge getEdge(int fromEdge, int toEdge, boolean isDirected){
		if (isDirected==true){
			for (int i=0; i<edges.size(); i++){
				if (edges.get(i).getFromEdge()==fromEdge && edges.get(i).getToEdge()==toEdge){
					return edges.get(i);
				}
			}
		}else{
			for (int i=0; i<edges.size();i++){
				if ((edges.get(i).getFromEdge()==fromEdge && edges.get(i).getToEdge()==toEdge)
						|| (edges.get(i).getFromEdge()==toEdge && edges.get(i).getToEdge()==fromEdge)){
					return edges.get(i);
				}
			}
		}
		System.err.println("Edge cannot be found!");
		return null;
	}

}
