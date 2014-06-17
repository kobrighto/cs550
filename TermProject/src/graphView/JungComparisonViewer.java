package graphView;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.commons.collections15.Transformer;

import standardGraph.Edge;
import standardGraph.Node;
import standardGraph.StandardGraph;
import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseMultigraph;
import edu.uci.ics.jung.graph.util.Context;
import edu.uci.ics.jung.visualization.VisualizationViewer;

/**
 * Jung example - controlling the appearance of vertices and edges.
 * 
 * See http://kahdev.wordpress.com/2010/01/30/controlling-the-appearance-of-vertices-and-edges-in-jung/ 
 * @author Kah
 */
public class JungComparisonViewer {

	/**
	 * @param args
	 * @throws IOException
	 */
	public void display(final StandardGraph oldGraph, final StandardGraph newGraph, final boolean graphComparison, final ArrayList<Integer> removedNodes, 
			final ArrayList<Integer> addedNodes, final ArrayList<Integer> removedEdges, final ArrayList<Integer> addedEdges) {	
		Graph<Integer,String> oldBasis = new DirectedSparseMultigraph<Integer, String>();;
		if (oldGraph.isDirected()){
			oldBasis = new DirectedSparseMultigraph<Integer, String>();
		}else{
			oldBasis = new UndirectedSparseMultigraph<Integer, String>();
		}
		
		Graph<Integer,String> newBasis = new DirectedSparseMultigraph<Integer, String>();;
		if (newGraph.isDirected()){
			newBasis = new DirectedSparseMultigraph<Integer, String>();
		}else{
			newBasis = new UndirectedSparseMultigraph<Integer, String>();
		}
		
		final ArrayList<Node> oldNodes = oldGraph.getNodeSet();
		final ArrayList<Edge> oldEdges = oldGraph.getEdgeSet();
		for (int i=0; i<oldNodes.size(); i++){
			oldBasis.addVertex(oldNodes.get(i).getID());
		}
		for (int i=0; i<oldEdges.size(); i++){
			oldBasis.addEdge(Integer.toString(i), oldEdges.get(i).getFromNode(), oldEdges.get(i).getToNode());
		}
		
		final ArrayList<Node> newNodes = newGraph.getNodeSet();
		final ArrayList<Edge> newEdges = newGraph.getEdgeSet();
		for (int i=0; i<newNodes.size(); i++){
			newBasis.addVertex(newNodes.get(i).getID());
		}
		for (int i=0; i<newEdges.size(); i++){
			newBasis.addEdge(Integer.toString(i), newEdges.get(i).getFromNode(), newEdges.get(i).getToNode());
		}
		
		final Layout<Integer, String> oldLayout = new CircleLayout<Integer, String>(oldBasis);
		final Layout<Integer, String> newLayout = new CircleLayout<Integer, String>(newBasis);
		
		oldLayout.setSize(new Dimension(500, 500));
		newLayout.setSize(new Dimension(500, 500));
		
		VisualizationViewer<Integer, String> oldVV = new VisualizationViewer<Integer, String>(oldLayout);
		oldVV.setPreferredSize(new Dimension(600, 600));  //jy: size		
		VisualizationViewer<Integer, String> newVV = new VisualizationViewer<Integer, String>(newLayout);
		newVV.setPreferredSize(new Dimension(600, 600));
		
		final Color Grey = new Color(0,0,0,0.1f);
		final Color Green = new Color(0,1,0,0.1f);
		final Color Red = new Color(1,0,0,0.1f);
		
		Transformer<Integer,Paint> oldVertexPaint = new Transformer<Integer,Paint>() {

			@Override
			public Paint transform(Integer id) {
				if (graphComparison==false || removedNodes.contains(oldGraph.getNodePos(id))){
					return Red;
				}else{
					return Grey;
				}	
			}
		};
		
		Transformer<Integer,Paint> newVertexPaint = new Transformer<Integer,Paint>() {

			@Override
			public Paint transform(Integer id) {
				if (graphComparison==false || addedNodes.contains(newGraph.getNodePos(id))){
					return Green;
				}else{
					return Grey;
				}	
			}
		};
		
		Transformer<String, Paint> oldEdgePaint = new Transformer<String, Paint>() {
			public Paint transform(String s) {
				if (graphComparison==false || removedEdges.contains(Integer.parseInt(s))){
					return Color.RED;
				}else{
					return Color.BLACK;
				}
			}
		};
		
		Transformer<String, Paint> newEdgePaint = new Transformer<String, Paint>() {
			public Paint transform(String s) {
				if (graphComparison==false || addedEdges.contains(Integer.parseInt(s))){
					return Color.GREEN;
				}else{
					return Color.BLACK;
				}
			}
		};
		
		Transformer<Integer, String> oldVertexLabel = new Transformer<Integer, String>(){

			@Override
			public String transform(Integer id) {
				return oldGraph.getNode(id).getLabel();
			}
		};
		
		Transformer<Integer, String> newVertexLabel = new Transformer<Integer, String>(){

			@Override
			public String transform(Integer id) {
				return newGraph.getNode(id).getLabel();
			}
		};
		
		Transformer <String, String> oldEdgeLabel = new Transformer<String, String>(){

			@Override
			public String transform(String s) {
				return oldEdges.get(Integer.parseInt(s)).getLabel();
			}			
		};
		
		Transformer <String, String> newEdgeLabel = new Transformer<String, String>(){

			@Override
			public String transform(String s) {
				return newEdges.get(Integer.parseInt(s)).getLabel();
			}			
		};
		
		Transformer<Context<Graph<Integer, String>, String>, Shape> edgeTransformer = new Transformer<Context<Graph<Integer,String>,String>,Shape>(){
			@Override
			public Shape transform(Context<Graph<Integer, String>, String> graphStringContext)
			{
				return (new Line2D.Double(0, 1, 1, 2));
			}
		};
		
		Transformer<Integer,Shape> vertexSize = new Transformer<Integer,Shape>(){
			public Shape transform(Integer i){
				Ellipse2D circle = new Ellipse2D.Double(-15, -15, 30, 30);
				// in this case, the vertex is twice as large
				return AffineTransform.getScaleInstance(1.5, 1.5).createTransformedShape(circle);
			}
		};
		
		oldVV.getRenderContext().setVertexLabelTransformer(oldVertexLabel);
		oldVV.getRenderContext().setEdgeLabelTransformer(oldEdgeLabel);
		oldVV.getRenderContext().setVertexFillPaintTransformer(oldVertexPaint);
		oldVV.getRenderContext().setEdgeDrawPaintTransformer(oldEdgePaint);
		oldVV.getRenderContext().setVertexShapeTransformer(vertexSize);
		//oldVV.getRenderContext().setEdgeShapeTransformer(edgeTransformer);
		
		newVV.getRenderContext().setVertexLabelTransformer(newVertexLabel);
		newVV.getRenderContext().setEdgeLabelTransformer(newEdgeLabel);
		newVV.getRenderContext().setVertexFillPaintTransformer(newVertexPaint);
		newVV.getRenderContext().setEdgeDrawPaintTransformer(newEdgePaint);
		newVV.getRenderContext().setVertexShapeTransformer(vertexSize);
		//newVV.getRenderContext().setEdgeShapeTransformer(edgeTransformer);
		
		JFrame frame = new JFrame("Comparison View");		
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		JPanel panel = new JPanel(new GridLayout(1,2));
		panel.add(oldVV);
		panel.add(newVV);
		oldVV.setOpaque(false);
		newVV.setOpaque(false);
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);

	}
}
