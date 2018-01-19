package pdg.Controller;

import org.jgrapht.Graph;
import org.jgrapht.alg.util.IntegerVertexFactory;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.generate.GraphGenerator;
import org.jgrapht.generate.WattsStrogatzGraphGenerator;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;

public class GenerateSmallWorldStructure {

	public JGraphXAdapter<Integer, DefaultEdge> jgxAdapter;
	public mxGraphComponent mxGraphComp;
	
	public mxGraphComponent generateGraph(int nodes, int k, double factor) {
		
		// create a JGraphT graph
		GraphGenerator<Integer, DefaultEdge, Integer> gen = new WattsStrogatzGraphGenerator<>(nodes, k, factor);
		Graph<Integer, DefaultEdge> g = new SimpleDirectedGraph<>(DefaultEdge.class);
		gen.generateGraph(g, new IntegerVertexFactory(), null);
		
		// create a visualisation using JGraphX, via an adapter
		jgxAdapter = new JGraphXAdapter<>(g);       

        System.out.println(g.edgeSet());

		// double w = jgxAdapter.getGraphBounds().getWidth();
		// double h = jgxAdapter.getGraphBounds().getHeight();

		// ScaleFreeGraphGenerator<String, DefaultEdge> sf = new
		// ScaleFreeGraphGenerator<String, DefaultEdge>(50);
		// sf.generateGraph(g, vertexFactory, null);
		
		// double w1 = jgxAdapter.getGraphBounds().getWidth();
		// double h1 = jgxAdapter.getGraphBounds().getHeight();

		// jgxAdapter.getModel().setGeometry(jgxAdapter.getDefaultParent(), new
		// mxGeometry((w - w1)+50, (h - h1)+50, w, h));
		// jgxAdapter.getModel().setStyle(arg0, arg1)

		mxGraphComp = new mxGraphComponent(jgxAdapter);

		// positioning via jgraphx layouts
		mxCircleLayout layout = new mxCircleLayout(jgxAdapter);
		// mxHierarchicalLayout layout = new mxHierarchicalLayout(jgxAdapter);
		// ALLELUJA:
		layout.setRadius(700);
		layout.execute(jgxAdapter.getDefaultParent());
		jgxAdapter.setCellsEditable(false);

		return mxGraphComp;
	}

}
