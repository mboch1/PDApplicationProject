package pdg.Controller;

import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.generate.GraphGenerator;
import org.jgrapht.graph.DefaultEdge;

import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;

public abstract class Generator {
	public JGraphXAdapter<Integer, DefaultEdge> jgxAdapter;
	public mxGraphComponent mxGraphComp;
	public mxHierarchicalLayout layout;
	public GraphGenerator<Integer, DefaultEdge, Integer> gen;
	public Graph<? , ?> g;

	public mxGraphComponent generateGraph() {
		return mxGraphComp;
	}

	public void structureToModel(Graph g, int nodes) {
	}

	public void zoomIn() {
	}

	public void zoomOut() {
	}
}
