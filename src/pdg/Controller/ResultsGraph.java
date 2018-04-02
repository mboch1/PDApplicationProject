package pdg.Controller;

import java.util.ArrayList;
import org.jgrapht.Graph;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxOrganicLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraphView;

import pdg.Model.GameModel;

public class ResultsGraph extends GameController {

	private GameModel gm;
	private int[][] matrix;
	private JGraphXAdapter<Integer, DefaultEdge> jgxAdapter;
	private mxGraphComponent mxGraphComp;
	private mxCircleLayout layout;
	private mxOrganicLayout layout1;
	private mxHierarchicalLayout layout2;
	private Graph<Integer, DefaultEdge> g;
	private ArrayList<Object> vertices;
	private ArrayList<Object> edges;
	private Object parent;

	public ResultsGraph(GameModel model) {
		this.gm = model;
	}

	// reverse the data saved in model and construct new graph:
	private void modelToStructure() {
		// get matrix
		matrix = gm.getMatrix();
		// initialize arraylists:
		vertices = new ArrayList<>();
		edges = new ArrayList<>();
		// set parent:
		parent = jgxAdapter.getDefaultParent();
		// construct nodes:
		for (int l = 0; l < gm.getNodeNumber(); l++) {
			String strategy = gm.getNode(l).getStrategy();
			int k = 0;
			double jScore = 0;
			int[] neighbourhood = gm.getNode(l).getNeighbours();

			double interacted = 0;
			double sumPerformance = 0;
			double performance = 0;

			for (int m = 0; m < gm.getNodeNumber(); m++) {
				if (neighbourhood[m] == 1 && m != l) {
					k++;
					interacted = gm.getNode(l).getTimesInteracted(m);
					jScore = gm.getNode(l).getTotalInteractionHistory(m);
					// divide jScore by times interacted to get Performance between node i and j:
					if (interacted != 0) {
						sumPerformance += jScore / interacted;
					}
				}
			}

			// calculate average node performance factor / number of neighbours:
			performance = sumPerformance / k;
			// set colour based on node total performance
			if (performance > 0.750 && performance < 1.250) {
				vertices.add(jgxAdapter.insertVertex(parent, null, strategy + " " + Integer.toString(l), 20, 20, 80, 30,
						"strokeColor=black;fillColor=#b3d9ff"));
			}
			// average performance:
			if ((performance > 0.250 && performance <= 0.750) || (performance < 1.500 && performance >= 1.250)) {
				vertices.add(jgxAdapter.insertVertex(parent, null, strategy + " " + Integer.toString(l), 20, 20, 80, 30,
						"strokeColor=blue;fillColor=#b3ffb3"));
			}
			// low performance:
			if ((performance > -0.250 && performance <= 0.250) || (performance < 1.750 && performance >= 1.500)) {
				vertices.add(jgxAdapter.insertVertex(parent, null, strategy + " " + Integer.toString(l), 20, 20, 80, 30,
						"strokeColor=blue;fillColor=#ffecb3"));
			}
			// extreme performance:
			if (performance <= -0.250) {
				vertices.add(jgxAdapter.insertVertex(parent, null, strategy + " " + Integer.toString(l), 20, 20, 80, 30,
						"strokeColor=black;fillColor=#ffb3b3"));
			}
			// extreme performance:
			if (performance >= 1.750) {
				vertices.add(jgxAdapter.insertVertex(parent, null, strategy + " " + Integer.toString(l), 20, 20, 80, 30,
						"strokeColor=black;fillColor=#ffb3ff"));
			}
		}
		// get weights matrix and construct vertices between nodes, o < k ensures that
		// edge is made just once
		for (int o = 0; o < gm.getNodeNumber(); o++) {
			int[] nn = gm.getNode(o).getNeighbours();
			ArrayList<Integer> nnids = new ArrayList<>();

			for (int l = 0; l < gm.getNodeNumber(); l++) {
				if (nn[l] == 1) {
					nnids.add(l);
				}
			}

			for (int k = 0; k < nnids.size(); k++) {
				// Draw average connection value between 2 nodes (weights/2)

				double avgWeight = (gm.getNode(o).getNeighbourWeight(nnids.get(k))
						+ gm.getNode(nnids.get(k)).getNeighbourWeight(o)) / 2;
				// connection value 100 or higher
				try {
					if (avgWeight >= 90) {
						edges.add(jgxAdapter.insertEdge(parent, null,
								Integer.toString(o) + " : " + Integer.toString(nnids.get(k)), vertices.get(o),
								vertices.get(nnids.get(k)),
								"startArrow=none;endArrow=none;strokeWidth=2;strokeColor=#64dcf0"));
					}
					// connection value 80 - 100:
					if (avgWeight >= 70 && avgWeight < 90) {
						edges.add(jgxAdapter.insertEdge(parent, null,
								Integer.toString(o) + " : " + Integer.toString(nnids.get(k)), vertices.get(o),
								vertices.get(nnids.get(k)),
								"startArrow=none;endArrow=none;strokeWidth=2;strokeColor=#64a0f0"));
					}

					// connection value 60 - 80:
					if (avgWeight >= 50 && avgWeight < 70) {
						edges.add(jgxAdapter.insertEdge(parent, null,
								Integer.toString(o) + " : " + Integer.toString(nnids.get(k)), vertices.get(o),
								vertices.get(nnids.get(k)),
								"startArrow=none;endArrow=none;strokeWidth=2;strokeColor=#50a050"));
					}
					// connection value 40 - 60:
					if (avgWeight >= 30 && avgWeight < 50) {
						edges.add(jgxAdapter.insertEdge(parent, null,
								Integer.toString(o) + " : " + Integer.toString(nnids.get(k)), vertices.get(o),
								vertices.get(nnids.get(k)),
								"startArrow=none;endArrow=none;strokeWidth=2;strokeColor=#50dc50"));
					}
					// connection value 20 - 40:
					if (avgWeight >= 20 && avgWeight < 30) {
						edges.add(jgxAdapter.insertEdge(parent, null,
								Integer.toString(o) + " : " + Integer.toString(nnids.get(k)), vertices.get(o),
								vertices.get(nnids.get(k)),
								"startArrow=none;endArrow=none;strokeWidth=2;strokeColor=#dcdc64"));
					}
					// connection value 0 - 20:
					if (avgWeight >= 0 && avgWeight < 20) {
						edges.add(jgxAdapter.insertEdge(parent, null,
								Integer.toString(o) + " : " + Integer.toString(nnids.get(k)), vertices.get(o),
								vertices.get(nnids.get(k)),
								"startArrow=none;endArrow=none;strokeWidth=2;strokeColor=#dcb464"));
					}
					// connection value negative:
					if (avgWeight < 0) {
						edges.add(jgxAdapter.insertEdge(parent, null,
								Integer.toString(o) + " : " + Integer.toString(nnids.get(k)), vertices.get(o),
								vertices.get(nnids.get(k)),
								"startArrow=none;endArrow=none;strokeWidth=2;strokeColor=#dc6450"));
					}
				} catch (IndexOutOfBoundsException e) {
					System.err.println("IndexOutOfBoundsException: " + e.getMessage());
				}
			}
		}
	}

	public mxGraphComponent drawGraph() {
		g = new SimpleGraph<>(DefaultEdge.class);
		// create a visualisation using JGraphX, via an adapter
		jgxAdapter = new JGraphXAdapter<>(g);
		mxGraphComp = new mxGraphComponent(jgxAdapter);

		jgxAdapter.getModel().beginUpdate();
		try {
			// create a JGraphT graph
			modelToStructure();
			// positioning via jgraphx layouts
			layout = new mxCircleLayout(jgxAdapter);
			layout.execute(jgxAdapter.getDefaultParent());
			jgxAdapter.setCellsEditable(false);

		} finally {
			jgxAdapter.getModel().endUpdate();
		}

		return mxGraphComp;
	}

	public void zoomIn() {
		jgxAdapter.getModel().beginUpdate();
		try {

			mxGraphView view = jgxAdapter.getView();
			double scale = view.getScale();

			view.setScale(scale + 0.10);

		} finally {
			jgxAdapter.getModel().endUpdate();
		}
	}

	public void zoomOut() {
		jgxAdapter.getModel().beginUpdate();
		try {

			mxGraphView view = jgxAdapter.getView();
			double scale = view.getScale();

			view.setScale(scale - 0.10);

		} finally {
			jgxAdapter.getModel().endUpdate();
		}
	}

	public void setCircle() {
		jgxAdapter.getModel().beginUpdate();
		try {
			layout = new mxCircleLayout(jgxAdapter);
			layout.setResetEdges(true);
			layout.setRadius(250);
			layout.execute(jgxAdapter.getDefaultParent());
			jgxAdapter.setCellsEditable(false);

		} finally {
			jgxAdapter.getModel().endUpdate();
		}
	}

	public void setOrganic() {
		jgxAdapter.getModel().beginUpdate();
		try {
			layout1 = new mxOrganicLayout(jgxAdapter);
			layout1.setResetEdges(true);
			layout1.setMinDistanceLimit(2.0);
			layout1.execute(jgxAdapter.getDefaultParent());
			jgxAdapter.setCellsEditable(false);

		} finally {
			jgxAdapter.getModel().endUpdate();
		}
	}

	public void setHierarchical() {
		jgxAdapter.getModel().beginUpdate();
		try {
			layout2 = new mxHierarchicalLayout(jgxAdapter);
			layout2.execute(jgxAdapter.getDefaultParent());
			jgxAdapter.setCellsEditable(false);

		} finally {
			jgxAdapter.getModel().endUpdate();
		}
	}
}
