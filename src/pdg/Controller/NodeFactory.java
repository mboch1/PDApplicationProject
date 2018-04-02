package pdg.Controller;

import java.util.Random;
import pdg.Model.GameModel;

public class NodeFactory {

	private GameModel gm;
	private int n;
	private int[] gStratNodeID;
	private double weightSum;
	private double fixedCoopValue;
	private boolean fixedCoop;
	private double[] neighbourhoodWeights;

    /*
     * This class is used to assemble the nodes,
     * it fills the node with data and returns the result.
     * The result is an arraylist of generated nodes saved to game model.
     */
    public NodeFactory(GameModel model, int nodeNumber, boolean fixedCoop) {
		gm = model;
		n = nodeNumber;
		this.fixedCoop = fixedCoop;
		double orig = 0;
		double t4t = 0;
		double random = 0;
		double t4trand = 0;

		// if fixed Vcoop is selected
		if (fixedCoop == true) {
			fixedCoopValue = gm.getFixedVcoop();
		}
		// by default this node strategy array will have 0 which means no strategy is
		// selected yet, 1 is origianl startegy, 2 is tit for tat, 3 is tit for tat with
		// original approach, 4 is tit for 2 tats
		gStratNodeID = new int[n];

		weightSum = gm.getOrigApproachWeight() + gm.getT4TWeight() + gm.getRandomWeight() + gm.getT4TRandWeight();
		// get individual weights and multiply weights by number of nodes to get a total
		// number of this type of node to distribute:

		if (gm.isOrgApproach()) {
			orig = (gm.getOrigApproachWeight() / weightSum) * n;
		}
		if (gm.isTit4tat()) {
			t4t = (gm.getT4TWeight() / weightSum) * n;
		}
		if (gm.isTit4TatRandom()) {
			t4trand = (gm.getT4TRandWeight() / weightSum) * n;
		}
		if (gm.isRandom()) {
			random = (gm.getRandomWeight() / weightSum) * n;
		}

		boolean arrayFilled = false;
		Random rd = new Random();

		// create counters for later use:
		int orgCounter = (int) orig;
		int t4tCounter = (int) t4t;
		int randCounter = (int) random;
		int t4tRandomCounter = (int) t4trand;
		int sumStrategies = orgCounter + t4tCounter + randCounter + t4tRandomCounter;

		// if sum of strategies is different from total number of nodes we will have to
		// correct it in favour of one strategy
		if (sumStrategies < n) {
			int counter = n - sumStrategies;

			while (counter > 0) {
				int selected = rd.nextInt(n);

				if (orig != 0.0 && gStratNodeID[selected] == 0) {
					gStratNodeID[selected] = 1;
					counter--;
				} else if (t4t != 0.0 && gStratNodeID[selected] == 0) {
					gStratNodeID[selected] = 2;
					counter--;
				} else if (randCounter != 0.0 && gStratNodeID[selected] == 0) {
					gStratNodeID[selected] = 3;
					counter--;
				} else if (t4trand != 0.0 && gStratNodeID[selected] == 0) {
					gStratNodeID[selected] = 4;
					counter--;
				}
			}
		}
		// fill in the strategies
		while (arrayFilled != true) {
			// select random id:
			int selected = rd.nextInt(n);

			if (orig > 0 && gStratNodeID[selected] == 0 && orgCounter > 0) {
				gStratNodeID[selected] = 1;
				orgCounter--;
			} else if (t4t > 0 && gStratNodeID[selected] == 0 && t4tCounter > 0) {
				gStratNodeID[selected] = 2;
				t4tCounter--;
			} else if (random > 0 && gStratNodeID[selected] == 0 && randCounter > 0) {
				gStratNodeID[selected] = 3;
				randCounter--;
			} else if (t4trand > 0 && gStratNodeID[selected] == 0 && t4tRandomCounter > 0) {
				gStratNodeID[selected] = 4;
				t4tRandomCounter--;
			}

			// check if the array has no 0 values:
			int j = 0;

			for (int i = 0; i < gStratNodeID.length; i++) {
				if (gStratNodeID[i] == 0) {
					j++;
				}
			}
			// if after checking there are no 0 values left set arrayFilled to true and end
			// this
			if (j == 0) {
				arrayFilled = true;
			}
		}

		// run construct node n times while checking the node strategy set
		for (int k = 0; k < n; k++) {
			if (gStratNodeID[k] == 1) {
				construct(k, "original");
			} else if (gStratNodeID[k] == 2) {
				construct(k, "tit4tat");
			} else if (gStratNodeID[k] == 3) {
				construct(k, "random");
			} else if (gStratNodeID[k] == 4) {
				construct(k, "tit4tatRand");
			}
		}

	}

	private void construct(int id, String strategy) {
		Random random = new Random();
		// reset array:
		neighbourhoodWeights = new double[n];
		// int edgeToNode[], int n, int nodeID, double riskAversion, double viCoop, int
		// memSpan, int credit, String strategyID
		// get neighbourhood matrix for node id
		int[] neighbourhoodMatrix = gm.getNeighbourMatrix(id);
		// set default node neighbourhood weights values to 100.0 for each valid
		// neighbour of the node id
		for (int i = 0; i < n; i++) {
			if (neighbourhoodMatrix[i] == 1) {
				neighbourhoodWeights[i] = 100.0;
			}
		}
		double alpha = (double) random.nextInt(10000) / 10000.0;
		int m = gm.getM();
		// if fixed value is selected create a node else randomly pick value and then
		// create a node:
		if (fixedCoop == true) {
			gm.addNewNode(neighbourhoodMatrix, neighbourhoodWeights, n, id, alpha, fixedCoopValue, m, gm.getTurns(),
					strategy);
		} else {
			double coop = (double) random.nextInt(10000) / 10000.0;
			gm.addNewNode(neighbourhoodMatrix, neighbourhoodWeights, n, id, alpha, coop, m, gm.getTurns(), strategy);
		}

	}

}
