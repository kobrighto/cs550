package graphIO;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import standardGraph.*;

/*
 * File format to read graph:
 * - Text: nodes & edges, each in one line
 * 		<Graph>
 * 		<directed|undirected>
 * 		</Graph>
 * 		<Node> 
 * 		<number> <label>
 * 		</Node>
 * 		<Edge> 
 * 		<label> <fromNode> <toNode>
 * 		</Edge>
 */

public class GraphReaderTXT {
	StandardGraph newGraph = new StandardGraph();
	
	public StandardGraph readString(String diagram) {
			
		boolean isDirected = false;		
		BufferedReader br = null;
		String dataType = null;
		
		// convert String into InputStream
		InputStream is = new ByteArrayInputStream(diagram.getBytes());
	 
		// read it with BufferedReader
		br = new BufferedReader(new InputStreamReader(is));
		
		try{
			String sCurrentLine;
			
			while ((sCurrentLine = br.readLine()) != null){
				if (sCurrentLine.trim().equalsIgnoreCase("<Graph>")){
					dataType = "graph";
				}else if (sCurrentLine.trim().equalsIgnoreCase("</Graph>")){
					dataType = null;
				}else if (sCurrentLine.trim().equalsIgnoreCase("<Node>")){
					dataType = "node";
				}else if (sCurrentLine.trim().equalsIgnoreCase("</Node>")){
					dataType = null;
				}else if (sCurrentLine.trim().equalsIgnoreCase("<Edge>")){
					dataType = "edge";
				}else if (sCurrentLine.trim().equalsIgnoreCase("</Edge>")){
					dataType = null;
				}else{
					if (dataType.equalsIgnoreCase("graph")){
						readGraph(sCurrentLine);
					}else if (dataType.equalsIgnoreCase("node")){
						readNode(sCurrentLine);
					}else if(dataType.equalsIgnoreCase("edge")){
						readEdge(sCurrentLine);
					}else{
						System.err.println("Graph Input file format error!");
						br.close();
						return null;
					}
				}
			}
			br.close();
			
			return newGraph;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	public StandardGraph readTextFile(String path){
		boolean isDirected = false;		
		BufferedReader br = null;
		String dataType = null;
		
		try{
			String sCurrentLine;
			
			br = new BufferedReader(new FileReader(path));
			
			while ((sCurrentLine = br.readLine()) != null){
				if (sCurrentLine.trim().equalsIgnoreCase("<Graph>")){
					dataType = "graph";
				}else if (sCurrentLine.trim().equalsIgnoreCase("</Graph>")){
					dataType = null;
				}else if (sCurrentLine.trim().equalsIgnoreCase("<Node>")){
					dataType = "node";
				}else if (sCurrentLine.trim().equalsIgnoreCase("</Node>")){
					dataType = null;
				}else if (sCurrentLine.trim().equalsIgnoreCase("<Edge>")){
					dataType = "edge";
				}else if (sCurrentLine.trim().equalsIgnoreCase("</Edge>")){
					dataType = null;
				}else{
					if (dataType.equalsIgnoreCase("graph")){
						readGraph(sCurrentLine);
					}else if (dataType.equalsIgnoreCase("node")){
						readNode(sCurrentLine);
					}else if(dataType.equalsIgnoreCase("edge")){
						readEdge(sCurrentLine);
					}else{
						System.err.println("Graph Input file format error!");
						br.close();
						return null;
					}
				}
			}
			br.close();
			
			return newGraph;
		}catch(Exception e){
			//e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean readGraph(String graphInfo){
		if (graphInfo.trim().equalsIgnoreCase("undirected")){
			newGraph.setDirected(false);
			return true;
		}else if (graphInfo.trim().equalsIgnoreCase("directed")){
			newGraph.setDirected(true);
			return true;
		}else {
			System.out.println("Graph Type format error!");
			return false;
		}
	}
	
	public boolean readNode(String nodeInfo){
		String[] data = nodeInfo.split(" ");

		if (data.length != 2){
			System.err.println("Node format error!");
			return false;
		}else{
			int id = Integer.parseInt(data[0]);
			String label = data[1];
			newGraph.addNode(id, label);
			return true;
		}
	}
	
	public boolean readEdge(String edgeInfo){
		String[] data = edgeInfo.split(" ");
		
		if (data.length != 3){
			System.err.println("Edge format error!");
			return false;
		}else{			
			int fromNode = Integer.parseInt(data[0]);
			int toNode = Integer.parseInt(data[1]);
			String label = data[2];
			if (newGraph.isDirected() == false && (fromNode > toNode)){
				int temp;
				temp = fromNode;
				fromNode = toNode;
				toNode = temp;
			}
			newGraph.addEdge(fromNode, toNode, label);
			return true;			
		}
	}

	
}
