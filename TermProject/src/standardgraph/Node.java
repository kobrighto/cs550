package standardGraph;

public class Node {
	private int number;
	private String label;
	
	public Node(int number, String label){
		this.number = number;
		this.label = label;
	}
	
	public int getNumber(){
		return number;
	}
	
	public String getLabel(){
		return label;
	}
}
