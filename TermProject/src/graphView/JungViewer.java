package graphView;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.commons.collections15.Transformer;

import standardGraph.*;
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
 * 
 * Modified by Minh Nguyen for CS550 Term Project, KAIST, 2014
 */
public class JungViewer {
	public void display(final StandardGraph graph){
		Graph<Integer,String> basis = new DirectedSparseMultigraph<Integer, String>();;
		if (graph.isDirected()){
			basis = new DirectedSparseMultigraph<Integer, String>();
		}else{
			basis = new UndirectedSparseMultigraph<Integer, String>();
		}
		final ArrayList<Node> nodes = graph.getNodeSet();
		final ArrayList<Edge> edges = graph.getEdgeSet();
		for (int i=0; i<nodes.size(); i++){
			basis.addVertex(nodes.get(i).getID());
		}
		
		for (int i=0; i<edges.size(); i++){
			basis.addEdge(Integer.toString(i), edges.get(i).getFromNode(), edges.get(i).getToNode());
		}

		final Layout<Integer, String> layout = new CircleLayout<Integer, String>(basis);

		layout.setSize(new Dimension(500, 500));
		VisualizationViewer<Integer, String> vv = new VisualizationViewer<Integer, String>(layout);
		vv.setPreferredSize(new Dimension(1000, 1000));

		final Color Grey = new Color(0,0,0, 0.1f);

		Transformer<Integer,Paint> vertexPaint = new Transformer<Integer,Paint>() {

			@Override
			public Paint transform(Integer i) {
				return Grey;
			}
		};  

		Transformer<Context<Graph<Integer, String>, String>, Shape> edgeTransformer = new Transformer<Context<Graph<Integer,String>,String>,Shape>(){
			@Override
			public Shape transform(Context<Graph<Integer, String>, String> graphStringContext)
			{
				return (new Line2D.Double(0, 1, 1, 2));
			}
		};

		Transformer<String, Paint> edgePaint = new Transformer<String, Paint>() {
			public Paint transform(String s) {
				return Color.BLACK;
			}
		};
		
		Transformer<Integer, String> vertexLabel = new Transformer<Integer, String>(){

			@Override
			public String transform(Integer i) {
				return graph.getNode(i).getLabel();
			}
		};
		
		Transformer <String, String> edgeLabel = new Transformer<String, String>(){

			@Override
			public String transform(String s) {
				return edges.get(Integer.parseInt(s)).getLabel();
			}			
		};

		Transformer<Integer,Shape> vertexSize = new Transformer<Integer,Shape>(){
			public Shape transform(Integer i){
				Ellipse2D circle = new Ellipse2D.Double(-15, -15, 30, 30);
				// in this case, the vertex is twice as large
				return AffineTransform.getScaleInstance(1.5, 1.5).createTransformedShape(circle);
			}
		};

		vv.getRenderContext().setVertexLabelTransformer(vertexLabel);
		vv.getRenderContext().setEdgeLabelTransformer(edgeLabel);
		vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
		vv.getRenderContext().setEdgeDrawPaintTransformer(edgePaint);
		vv.getRenderContext().setVertexShapeTransformer(vertexSize);
		vv.getRenderContext().setEdgeShapeTransformer(edgeTransformer);

		JFrame frame = new JFrame("Simple Graph View 2");		
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		JPanel panel = new JPanel(new GridLayout(1,2));
		panel.add(vv);
		vv.setOpaque(false);
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
	}
}
