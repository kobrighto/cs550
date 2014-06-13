package kaist.cs550.termproject.standardgraph;

public class Edge {
	private boolean isDirected;
	private int fromEdge;
	private int toEdge;
	private String label;
	
	public Edge(int fromEdge, int toEdge, boolean isDirected, String label){
		this.isDirected=isDirected;
		this.fromEdge=fromEdge;
		this.toEdge=toEdge;
		this.label=label;
	}
	
	public boolean isDirected(){
		return isDirected;
	}
	
	public int getFromEdge(){
		return fromEdge;
	}
	
	public int getToEdge(){
		return toEdge;
	}
	
	public String getLabel(){
		return label;
	}
}
