/*
 * Copyright (c) 2003, the JUNG Project and the Regents of the University of
 * California All rights reserved.
 * 
 * This software is open-source under the BSD license; see either "license.txt"
 * or http://jung.sourceforge.net/license.txt for a description.
 * 
 */
package graphView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.Transformer;
import org.apache.commons.collections15.functors.ConstantTransformer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.PolarPoint;
import edu.uci.ics.jung.algorithms.layout.RadialTreeLayout;
import edu.uci.ics.jung.algorithms.layout.TreeLayout;
import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedOrderedSparseMultigraph;
import edu.uci.ics.jung.graph.DirectedSparseMultigraph;
import edu.uci.ics.jung.graph.Forest;
import edu.uci.ics.jung.graph.DelegateForest;
import edu.uci.ics.jung.graph.DelegateTree;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.MultiGraph;
import edu.uci.ics.jung.graph.SortedSparseMultigraph;
import edu.uci.ics.jung.graph.Tree;
import edu.uci.ics.jung.graph.util.Context;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.VisualizationServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import edu.uci.ics.jung.visualization.decorators.EdgeShape;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.layout.LayoutTransition;
import edu.uci.ics.jung.visualization.util.Animator;

/**
 * Demonsrates TreeLayout and RadialTreeLayout.
 * @author Tom Nelson
 * 
 */
@SuppressWarnings("serial")
public class JungTreeViewer extends JApplet {

	/**
	 * the graph
	 */
	Forest<String,Integer> graph;

	Factory<DirectedGraph<String,Integer>> graphFactory = 
			new Factory<DirectedGraph<String,Integer>>() {

		public DirectedGraph<String, Integer> create() {
			return new DirectedOrderedSparseMultigraph<String,Integer>();
		}
	};

	Factory<Tree<String,Integer>> treeFactory =
			new Factory<Tree<String,Integer>> () {

		public Tree<String, Integer> create() {
			return new DelegateTree<String,Integer>(graphFactory);
		}
	};

	Factory<Integer> edgeFactory = new Factory<Integer>() {
		int i=0;
		public Integer create() {
			return i++;
		}
	};

	Factory<String> vertexFactory = new Factory<String>() {
		int i=0;
		public String create() {
			return "V"+i++;
		}
	};

	/**
	 * the visual component and renderer for the graph
	 */
	VisualizationViewer<String,Integer> vv;

	VisualizationServer.Paintable rings;

	String root;

	TreeLayout<String,Integer> layout;
	//CircleLayout<String,Integer> layout;

	RadialTreeLayout<String,Integer> radialLayout;

	public JungTreeViewer(ArrayList<String> versions) {
		
		// Added by Minh Nguyen for CS550 Term Project, KAIST, 2014
		Transformer<String,Shape> vertexSize = new Transformer<String,Shape>(){
			public Shape transform(String str){
				Ellipse2D circle = new Ellipse2D.Double(-15, -15, 30, 30);
				// in this case, the vertex is twice as large
				return AffineTransform.getScaleInstance(1.5, 1.5).createTransformedShape(circle);
			}
		};
		
		Transformer<String,Paint> vertexPaint = new Transformer<String,Paint>() {
			@Override
			public Paint transform(String str) {
				Color green = new Color(0,1,0, 0.1f);
				String[] chars = str.split("\\.");
				if (chars.length == 1){
					return Color.blue;
				}else{
					return green;
				}
			}
		};

		// create a simple graph for the demo
		graph = new DelegateForest<String,Integer>();

		createTree(versions);

		layout = new TreeLayout<String,Integer>(graph);
		radialLayout = new RadialTreeLayout<String,Integer>(graph);
		radialLayout.setSize(new Dimension(700,700));
		vv =  new VisualizationViewer<String,Integer>(layout, new Dimension(700,700));  //jy: size
		vv.setBackground(Color.white);
		vv.getRenderContext().setEdgeShapeTransformer(new EdgeShape.Line());
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		// add a listener for ToolTips
		vv.setVertexToolTipTransformer(new ToStringLabeller());
		//vv.getRenderContext().setArrowFillPaintTransformer(new ConstantTransformer(Color.lightGray));
		vv.getRenderContext().setArrowFillPaintTransformer(new ConstantTransformer(Color.BLACK));
		vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
		//vv.getRenderContext().setVertexShapeTransformer(vertexSize);		
		
		rings = new Rings();

		Container content = getContentPane();
		final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
		content.add(panel);

		final DefaultModalGraphMouse graphMouse = new DefaultModalGraphMouse();

		vv.setGraphMouse(graphMouse);

		JComboBox<String> modeBox = graphMouse.getModeComboBox();
		modeBox.addItemListener(graphMouse.getModeListener());
		graphMouse.setMode(ModalGraphMouse.Mode.TRANSFORMING);

		final ScalingControl scaler = new CrossoverScalingControl();

		JButton plus = new JButton("+");
		plus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scaler.scale(vv, 1.1f, vv.getCenter());
			}
		});
		JButton minus = new JButton("-");
		minus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scaler.scale(vv, 1/1.1f, vv.getCenter());
			}
		});

		JToggleButton radial = new JToggleButton("Radial");
		radial.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {

					LayoutTransition<String,Integer> lt =
							new LayoutTransition<String,Integer>(vv, layout, radialLayout);
					Animator animator = new Animator(lt);
					animator.start();
					vv.getRenderContext().getMultiLayerTransformer().setToIdentity();
					vv.addPreRenderPaintable(rings);
				} else {
					LayoutTransition<String,Integer> lt =
							new LayoutTransition<String,Integer>(vv, radialLayout, layout);
					Animator animator = new Animator(lt);
					animator.start();
					vv.getRenderContext().getMultiLayerTransformer().setToIdentity();
					vv.removePreRenderPaintable(rings);
				}
				vv.repaint();
			}});

		JPanel scaleGrid = new JPanel(new GridLayout(1,0));
		scaleGrid.setBorder(BorderFactory.createTitledBorder("Zoom"));

		JPanel controls = new JPanel();
		scaleGrid.add(plus);
		scaleGrid.add(minus);
		controls.add(radial);
		controls.add(scaleGrid);
		controls.add(modeBox);

		content.add(controls, BorderLayout.SOUTH);
	}

	class Rings implements VisualizationServer.Paintable {

		Collection<Double> depths;

		public Rings() {
			depths = getDepths();
		}

		private Collection<Double> getDepths() {
			Set<Double> depths = new HashSet<Double>();
			Map<String,PolarPoint> polarLocations = radialLayout.getPolarLocations();
			for(String v : graph.getVertices()) {
				PolarPoint pp = polarLocations.get(v);
				depths.add(pp.getRadius());
			}
			return depths;
		}

		public void paint(Graphics g) {
			g.setColor(Color.lightGray);

			Graphics2D g2d = (Graphics2D)g;
			Point2D center = radialLayout.getCenter();

			Ellipse2D ellipse = new Ellipse2D.Double();
			for(double d : depths) {
				ellipse.setFrameFromDiagonal(center.getX()-d, center.getY()-d, 
						center.getX()+d, center.getY()+d);
				Shape shape = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).transform(ellipse);
				g2d.draw(shape);
			}
		}

		public boolean useTransform() {
			return true;
		}
	}

	/**
	 * 
	 */
	private void createTree(ArrayList<String> versions) {
		if (versions.size()>0){
			graph.addVertex(versions.get(0));
		}
		for (int i=1; i<versions.size(); i++){
			String[] versionNumbers = versions.get(i).split("\\.");
			StringBuilder builder = new StringBuilder();
			if (versionNumbers.length == 1){
				graph.addEdge(edgeFactory.create(), Integer.toString(Integer.parseInt(versions.get(i)) -1 ), versions.get(i));
			}else if (versionNumbers[versionNumbers.length-1].equals("1")){
				builder.append(versionNumbers[0]);
				for (int j=1; j<versionNumbers.length-1; j++){
					builder.append(".");
					builder.append(versionNumbers[j]);    				
				}
				graph.addEdge(edgeFactory.create(), builder.toString(), versions.get(i));
			}else {
				builder.append(versionNumbers[0]);
				for (int j=1; j<versionNumbers.length; j++){
					if (j == versionNumbers.length-1){
						builder.append(".");
						builder.append(Integer.toString(Integer.parseInt(versionNumbers[j]) - 1));
					}else{
						builder.append(".");
						builder.append(versionNumbers[j]);
					}
				}
				graph.addEdge(edgeFactory.create(), builder.toString(), versions.get(i));
			}
		}
	}
}