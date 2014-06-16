package standardGraph;

public class Node {
	private int id; //unique identifier
	private String label;
	
	public Node(int id, String label){
		this.id = id;
		this.label = label;
	}
	
	public String getLabel(){
		return label;
	}
	
	public int getID(){
		return id;
	}
}
