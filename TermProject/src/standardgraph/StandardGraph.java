package standardGraph;

import java.util.ArrayList;

public class StandardGraph {
	private boolean isDirected;
	private ArrayList<Node> nodes;
	private ArrayList<Edge> edges;

	public StandardGraph(){
		this.nodes = new ArrayList<Node>();
		this.edges = new ArrayList<Edge>();
	}

	public StandardGraph(ArrayList<Node> nodes, ArrayList<Edge> edges, boolean isDirected){
		this.isDirected = isDirected;
		this.nodes = nodes;
		this.edges = edges;
	}

	public boolean isDirected(){
		return isDirected;
	}

	public void setDirected(boolean isDirected){
		this.isDirected = isDirected;
	}

	public ArrayList<Node> getNodeSet(){
		return nodes;
	}

	public ArrayList<Edge> getEdgeSet(){
		return edges;
	}

	public void addNode(int id, String label){
		if (nodeExists(id)){
			System.err.println("Cannot add node: ID " + Integer.toString(id) + " conflict!");
			return;
		}
		Node newNode = new Node(id, label);
		nodes.add(newNode);
	}

	public void addEdge(int fromNode, int toNode, String label){
		if (!nodeExists(fromNode)){
			System.err.println("Cannot add edge: From Node " + Integer.toString(fromNode) + " does not exist!");
			return;
		}
		if (!nodeExists(toNode)){
			System.err.println("Cannot add edge: To Node " + Integer.toString(toNode) + "does not exist!");
			return;
		}
		Edge newEdge = new Edge(fromNode, toNode, label);
		for (int i=0; i<edges.size(); i++){
			if (edges.get(i).getFromNode() == fromNode){
				if (edges.get(i).getToNode() > toNode){
					edges.add(i,newEdge);
					return;
				}else if(edges.get(i).getToNode() == toNode){
					if (edges.get(i).getLabel().compareTo(label) > 0){
						edges.add(i, newEdge);
						return;
					}
				}
			}else if (edges.get(i).getFromNode() > fromNode){
				edges.add(i, newEdge);
				return;
			}
		}

		edges.add(newEdge);
		return;
	}

	public boolean nodeExists(int id){
		for (int i=0; i<nodes.size(); i++){
			if (nodes.get(i).getID()==id){
				return true;
			}
		}
		return false;
	}

	public Node getNode(int id){
		for (int i=0; i<nodes.size(); i++){
			if (nodes.get(i).getID() == id){
				return nodes.get(i);
			}
		}
		System.err.println("Cannot get node: Node id " + Integer.toString(id) + " does not exist!");
		return null;
	}
	
	public int getNodePos(int id){
		for (int i=0; i<nodes.size(); i++){
			if (nodes.get(i).getID() == id){
				return i;
			}
		}
		return -1;
	}

	public void print(){
		if (isDirected == true){
			System.out.println("Directed Graph");
		}else{
			System.out.println("Undirected Graph");
		}
		System.out.println("Nodes list: ");
		for (int i=0; i<nodes.size(); i++){
			System.out.println("ID: " + nodes.get(i).getID() + ", Label: " + nodes.get(i).getLabel());
		}
		System.out.println();
		System.out.println("Edges list: ");
		for (int i=0; i<edges.size(); i++){
			if (isDirected()==true){
				System.out.println("Edge: " + edges.get(i).getFromNode() + "--->" + edges.get(i).getToNode() 
						+", Label: " + edges.get(i).getLabel());
			}else{
				System.out.println("Edge: " + edges.get(i).getFromNode() + "----" + edges.get(i).getToNode() 
						+", Label: " + edges.get(i).getLabel());
			}
		}
	}


}
