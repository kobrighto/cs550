package standardGraph;

import java.util.ArrayList;

public class StandardGraph {
	private ArrayList<Node> nodes;
	private ArrayList<Edge> edges;
	
	public StandardGraph(){
		this.nodes = new ArrayList<Node>();
		this.edges = new ArrayList<Edge>();
	}
	
	public StandardGraph(ArrayList<Node> nodes, ArrayList<Edge> edges){
		this.nodes = nodes;
		this.edges = edges;
	}
	
	public ArrayList<Node> getNodeSet(){
		return nodes;
	}
	
	public ArrayList<Edge> getEdgeSet(){
		return edges;
	}
	
	public void addNode(int number, String label){
		Node newNode = new Node(number, label);
		nodes.add(newNode);
	}
	
	public void addEdge(int fromNode, int toNode, boolean isDirected, String label){
		Edge newEdge = new Edge(fromNode, toNode, isDirected, label);
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
				System.out.println("Edge: " + edges.get(i).getFromNode() + "--->" + edges.get(i).getToNode() 
						+", Label: " + edges.get(i).getLabel() + ", Directed");
			}else{
				System.out.println("Edge: " + edges.get(i).getFromNode() + "----" + edges.get(i).getToNode() 
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
	
	public Edge getEdge(int fromNode, int toNode, boolean isDirected){
		if (isDirected==true){
			for (int i=0; i<edges.size(); i++){
				if (edges.get(i).getFromNode()==fromNode && edges.get(i).getToNode()==toNode){
					return edges.get(i);
				}
			}
		}else{
			for (int i=0; i<edges.size();i++){
				if ((edges.get(i).getFromNode()==fromNode && edges.get(i).getToNode()==toNode)
						|| (edges.get(i).getFromNode()==toNode && edges.get(i).getToNode()==fromNode)){
					return edges.get(i);
				}
			}
		}
		System.err.println("Edge cannot be found!");
		return null;
	}

}
