package graphIO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import standardGraph.*;

/*
 * File format to read graph:
 * - Text: nodes & edges, each in one line
 * 		<Node> 
 * 		<number> <label>
 * 		</Node>
 * 		<Edge> 
 * 		<label> <directed|undirected> <fromNode> <toNode>
 * 		</Edge>
 */

public class GraphReader {
	public StandardGraph readTextFile(String path){
		ArrayList<Node> nodes = new ArrayList<Node>();
		ArrayList<Edge> edges = new ArrayList<Edge>();
		BufferedReader br = null;
		String dataType = null;
		
		try{
			String sCurrentLine;
			
			br = new BufferedReader(new FileReader(path));
			
			while ((sCurrentLine = br.readLine()) != null){
				if (sCurrentLine.trim().equals("<Node>")){
					dataType = "node";
				}else if (sCurrentLine.trim().equals("</Node>")){
					dataType = null;
				}else if (sCurrentLine.trim().equals("<Edge>")){
					dataType = "edge";
				}else if (sCurrentLine.trim().equals("</Edge>")){
					dataType = null;
				}else{
					if (dataType.equals("node")){
						readNode(nodes, sCurrentLine);
					}else if(dataType.equals("edge")){
						readEdge(edges, sCurrentLine);
					}else{
						System.err.println("Graph Input file format error!");
						br.close();
						return null;
					}
				}
			}
			br.close();
			
			StandardGraph newGraph = new StandardGraph(nodes,edges);
			return newGraph;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean readNode(ArrayList<Node> nodes, String nodeInfo){
		String[] data = nodeInfo.split(" ");
		if (data.length != 2){
			System.err.println("Node format error!");
			return false;
		}else{
			int number = Integer.parseInt(data[0]);
			String label = data[1];
			Node newNode = new Node(number, label);
			for (int i=0; i<nodes.size(); i++){
				if (nodes.get(i).getNumber() == number){
					System.err.println("Node already added!");
					return false;
				}else if(nodes.get(i).getNumber() > number){
					nodes.add(i, newNode);
					return true;
				}
			}
			nodes.add(newNode);
			return true;
		}
	}
	
	public boolean readEdge(ArrayList<Edge> edges, String edgeInfo){
		String[] data = edgeInfo.split(" ");
		if (data.length != 4){
			System.err.println("Edge format error!");
			return false;
		}else{
			String label = data[0];
			String nodeType = data[1];
			boolean isDirected;
			int fromNode = Integer.parseInt(data[2]);
			int toNode = Integer.parseInt(data[3]);
			if (nodeType.equals("undirected") && (fromNode > toNode)){
				int temp;
				temp = fromNode;
				fromNode = toNode;
				toNode = temp;
			}
			
			if (nodeType.equals("undirected")){
				isDirected = false;
			}else if (nodeType.equals("directed")){
				isDirected = true;
			}else {
				System.err.println("Edge direction format error!");
				return false;
			}
			
			Edge newEdge = new Edge(fromNode, toNode, isDirected, label);
			
			for (int i=0; i<edges.size(); i++){
				if (edges.get(i).getFromNode() == fromNode){
					if (edges.get(i).getToNode() == toNode){
						System.err.println("Edge already added!");
						return false;
					}else if (edges.get(i).getToNode() > toNode){
						edges.add(i, newEdge);
						return true;
					}
				}else if (edges.get(i).getFromNode() > fromNode){
					edges.add(i,newEdge);
					return true;
				}
			}
			edges.add(newEdge);
			return true;
		}
	}
}
