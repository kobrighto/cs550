/* This program and the accompanying materials are dual-licensed under
 * either
 *
 * (a) the terms of the GNU Lesser General Public License version 2.1
 * as published by the Free Software Foundation, or (at your option) any
 * later version.
 *
 * or (per the licensee's choosing)
 *
 * (b) the terms of the Eclipse Public License v1.0 as published by
 * the Eclipse Foundation.
 */
/*
 * Modified by Minh H. Nguyen for CS550 Term Project, 2014
 * Support:
 * - Undirected Graphs
 * - Directed Graphs
 * - Loops (node to itself)
 * - Multiple Edges
 * 
 * Not support:
 * - Mixed Graphs (both undirected & directed edges in one graph)
 * 
 */
package graphView;

import com.mxgraph.layout.*;
import com.mxgraph.swing.*;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import org.jgrapht.*;
import org.jgrapht.ext.*;
import org.jgrapht.graph.*;

import standardGraph.*;


/**
 * A demo applet that shows how to use JGraphX to visualize JGraphT graphs.
 * Applet based on JGraphAdapterDemo.
 *
 * @since July 9, 2013
 */
public class Display
extends JApplet
{    
	private static final long serialVersionUID = 2202072534703043194L;
	private static final Dimension DEFAULT_SIZE = new Dimension(530, 320);

	private JGraphXAdapter<String, DefaultEdge> jgxAdapter;
	
	private static StandardGraph source;
	private static boolean isDirected;
	
	public Display(){
		source = new StandardGraph();
	}
	
	public Display(StandardGraph source, boolean isDirected){
		this.source = source;
		this.isDirected = isDirected;
	}
	
	private static class ListenableDirectedPseudograph<V, E> extends DefaultListenableGraph<V, E> implements DirectedGraph<V, E>
	{
		private static final long serialVersionUID = 1L;

		ListenableDirectedPseudograph(Class<E> edgeClass)
		{
			super(new DirectedPseudograph<V, E>(edgeClass));
		}
	}

	private static class ListenableUndirectedPseudograph<V, E> extends DefaultListenableGraph<V, E> implements UndirectedGraph<V, E>
	{
		private static final long serialVersionUID = 1L;

		ListenableUndirectedPseudograph(Class<E> edgeClass)
		{
			super(new Pseudograph<V, E>(edgeClass));
		}
	}

	/**
	 * An alternative starting point for this demo, to also allow running this
	 * applet as an application.
	 *
	 * @param args ignored.
	 */

	/*
	 * Added by Minh H. Nguyen for graph viewing
	 */
	private static ListenableGraph<String,DefaultEdge> getListenableGraph(){
		//create a JGraphT graph
		if (isDirected == true){
			ListenableGraph<String, DefaultEdge> g = new ListenableDirectedPseudograph<String, DefaultEdge>(DefaultEdge.class);
			return g;
		}else{
			ListenableGraph<String, DefaultEdge> g = new ListenableUndirectedPseudograph<String, DefaultEdge>(DefaultEdge.class);
			return g;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void init()
	{
		// create a JGraphT graph
		ListenableGraph<String, DefaultEdge> g = getListenableGraph();

		// create a visualization using JGraph, via an adapter
		jgxAdapter = new JGraphXAdapter<String, DefaultEdge>(g);

		getContentPane().add(new mxGraphComponent(jgxAdapter));
		resize(DEFAULT_SIZE);
		
		ArrayList<Node> nodes = source.getNodeSet();
		ArrayList<Edge> edges = source.getEdgeSet();
		String[] nodeString = new String[nodes.size()];
		
		//add nodes to graph
		for (int i=0; i<nodes.size(); i++){
			nodeString[i] = nodes.get(i).getLabel();
			g.addVertex(nodeString[i]);
		}		
		
		//add edges to graph
		for (int i=0; i<edges.size(); i++){
			g.addEdge(nodeString[edges.get(i).getFromNode()-1], nodeString[edges.get(i).getToNode()-1]);
		}

		// positioning via jgraphx layouts
		mxCircleLayout layout = new mxCircleLayout(jgxAdapter);
		layout.execute(jgxAdapter.getDefaultParent());
	}
}
