package standardGraph;

public class Edge {
	private int fromNode;
	private int toNode;
	private String label;
	
	public Edge(int fromNode, int toNode, String label){
		this.fromNode=fromNode;
		this.toNode=toNode;
		this.label=label;
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
