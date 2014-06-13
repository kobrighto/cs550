package standardGraph;

public class Edge {
	private boolean isDirected;
	private int fromNode;
	private int toNode;
	private String label;
	
	public Edge(int fromNode, int toNode, boolean isDirected, String label){
		this.isDirected=isDirected;
		this.fromNode=fromNode;
		this.toNode=toNode;
		this.label=label;
	}
	
	public boolean isDirected(){
		return isDirected;
	}
	
	public int getFromNode(){
		return fromNode;
	}
	
	public int getToNode(){
		return toNode;
	}
	
	public String getLabel(){
		return label;
	}
}
