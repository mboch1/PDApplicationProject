package pdg.Controller;

import java.util.ArrayList;
import java.util.Set;
import org.jgrapht.Graph;
import org.jgrapht.alg.util.IntegerVertexFactory;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.generate.GraphGenerator;
import org.jgrapht.generate.WattsStrogatzGraphGenerator;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraphView;

import pdg.Model.GameModel;

public class GenerateSmallWorldStructure extends Generator {

	private GameModel model;
	private int[][] matrix;
	public JGraphXAdapter<Integer, DefaultEdge> jgxAdapter;
	public mxGraphComponent mxGraphComp;
	public mxCircleLayout layout;
	public GraphGenerator<Integer, DefaultEdge, Integer> gen;
	public Graph<Integer, DefaultEdge> g;

	public GenerateSmallWorldStructure(GameModel gm) {
		model = gm;
	}

	public mxGraphComponent generateGraph(int nodes, int k, double factor, long seed) {

		// create a JGraphT graph
		gen = new WattsStrogatzGraphGenerator<>(nodes, k, factor, seed);
		g = new SimpleGraph<>(DefaultEdge.class);
		gen.generateGraph(g, new IntegerVertexFactory(), null);

		// create a visualisation using JGraphX, via an adapter
		jgxAdapter = new JGraphXAdapter<>(g);

		mxGraphComp = new mxGraphComponent(jgxAdapter);
		// read existing structure and transfer it to the game model:
		structureToModel(g, nodes);
		model.setMatrix(matrix);
		// positioning via jgraphx layouts
		layout = new mxCircleLayout(jgxAdapter);
		// set graph size to be more visible:
		layout.setRadius(450);
		layout.execute(jgxAdapter.getDefaultParent());
		jgxAdapter.setCellsEditable(false);

		return mxGraphComp;
	}

	public void structureToModel(Graph g, int nodes) {
		// to store temporary neighbours:
		ArrayList<Integer> listOfNeighbours;
		// creates a game matrix
		matrix = new int[nodes][nodes];

		// iterate through each node:
		for (int e = 0; e < nodes; e++) {
			listOfNeighbours = new ArrayList<>();
			// get node edge set:
			Set<DefaultEdge> edge = g.edgesOf(e);
			// transfer set to array:
			Object[] o = edge.toArray();

			for (int i = 0; i < o.length; i++) {
				// get individual edge:
				String s = o[i].toString();
				String[] splitted = s.split(" ");
				// now we have an array of (x,:,y), if split will occur at ( it will add an
				// empty string in place ""
				String[] x = splitted[0].split("\\(");
				String[] y = splitted[2].split("\\)");
				// result is x[1] and y[0]:
				String finalX = x[1];
				String finalY = y[0];
				// for testing:
				// System.out.println(finalX+","+finalY);
				int x1 = Integer.parseInt(finalX);
				int y1 = Integer.parseInt(finalY);

				if (x1 != e) {
					listOfNeighbours.add(x1);
				}
				if (y1 != e) {
					listOfNeighbours.add(y1);
				}
			}
			// create an array of neighbours from the list and send to model:
			int[] send = new int[listOfNeighbours.size()];
			for (int k = 0; k < send.length; k++) {
				send[k] = listOfNeighbours.get(k);
			}

			for (int j = 0; j < send.length; j++) {
				// sets 2 nodes as friends on matrix:
				matrix[e][send[j]] = 1;
				matrix[send[j]][e] = 1;
			}
		}
	}

	public void zoomIn() {
		jgxAdapter.getModel().beginUpdate();
		try {

			mxGraphView view = jgxAdapter.getView();
			double scale = view.getScale();

			view.setScale(scale + 0.25);

		} finally {
			jgxAdapter.getModel().endUpdate();
		}
	}

	public void zoomOut() {
		jgxAdapter.getModel().beginUpdate();
		try {

			mxGraphView view = jgxAdapter.getView();
			double scale = view.getScale();

			view.setScale(scale - 0.25);

		} finally {
			jgxAdapter.getModel().endUpdate();
		}
	}
}
