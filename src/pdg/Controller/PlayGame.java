package pdg.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import pdg.Model.GameModel;

public class PlayGame extends GameController {
	private GameStrategyClassic gsc;
	private GameStrategyT4T gst4t;
	private GameStrategyT4TRandom gst4tr;
	private GameStrategyRandom gst4to;
	private GameModel gm;
	private boolean nodeIresult = false;
	private boolean nodeJresult = false;
	private String stratI;
	private String stratJ;
	private int selectedPlayer;
	private int turns;

	// extends game controller for better readability:
	public PlayGame(GameModel model) {
		gm = model;
		turns = gm.getTurns();
		gsc = new GameStrategyClassic(gm);
		gst4t = new GameStrategyT4T(gm);
		gst4tr = new GameStrategyT4TRandom(gm);
		gst4to = new GameStrategyRandom(gm);
		playGame();
	}

	private void playGame() {

		for (int z = 0; z < turns; z++) {
			// CORRECT WEIGHTS FOR NEIGHBOURHOOD NODES AT THE TURN START:
			for (int k = 0; k < gm.getNodeNumber(); k++) {
				double weights[] = gm.getNode(k).getNeighbourhoodWeights();
				int[] neighbour = gm.getNode(k).getNeighbours();

				for (int j = 0; j < gm.getNodeNumber(); j++) {
					// reset weight to min 5.0 between nodes:
					if (neighbour[j] == 1 && weights[j] <= 0.0 && gm.isForgiving()) {
						gm.getNode(k).setCorretNeighbourhoodWeight(j, 5.0);
					}
					// correct weights so they won't breach 100%
					if (weights[j] > 100.0 && neighbour[j] == 1) {
						gm.getNode(k).setCorretNeighbourhoodWeight(j, 100.0);
					}
				}
			}

			// SELECT NODE i and play PD Game round for it:
			for (int i = 0; i < gm.getNodeNumber(); i++) {
				// SELECT PLAYER TO INVITE
				int nb[] = gm.getNode(i).getNeighbours();
				double nbw[] = gm.getNode(i).getNeighbourhoodWeights();
				selectedPlayer = selectPlayer(i, nb, nbw);

				if (selectedPlayer != -1) {
					// IF TARGET NODE IS NOT FORCED TO PLAY:
					if (gm.isForcedPlay() == false) {
						if (acceptToPlay(i, z) == true) {
							playRound(i, z);
						} else {
							// if the node was refused to play lower the chance to ask this node again in
							// future in order to promote other neighbours:
							gm.getNode(i).setNeighbourhoodWeight(selectedPlayer, calculateWeightChange("lower gain"));
						}
					} else {
						// IF GAME IS FORCED TO PLAY
						// PLAY - CHOOSE STRATEGIES BASED ON NODE'S CURRENT APPROACH
						playRound(i, z);
					}
				}
			}
		}
	}

	private boolean acceptToPlay(int i, int z) {
		String strategy = gm.getNode(selectedPlayer).getStrategy();

		double tpji = gm.getNode(selectedPlayer).getTpijForNode(i);
		double vCoop = gm.getNode(selectedPlayer).getvCoop();
		double m = gm.getM();
		double coop = gm.getNode(selectedPlayer).getTimesCoop();
		double def = gm.getNode(selectedPlayer).getTimesDef();
		double vCoopTemp = 0;
		
		if (def + coop == 0) {
			vCoopTemp = 0.05;
		} else {
			vCoopTemp = coop / (coop + def);
		}
		
		double tpm = (m + tpji) / m;

		if (strategy != "original") {
			if (tpm >= vCoopTemp) {
				return true;
			} else {
				return false;
			}
		} else {
			if (tpm >= vCoop) {
				return true;
			} else {
				return false;
			}
		}
	}

	// returns ID of selected node to play with
	private int selectPlayer(int id, int nb[], double weights[]) {
		double totalSumWeights = 0.0;
		double roulette[];
		double summedWeights[];

		ArrayList<Integer> myNeighbours = new ArrayList<>();
		ArrayList<Double> myNWeight = new ArrayList<>();

		// we need dynamic way to create list of only neighbours with whom we might want
		// to play in given turn for each node
		for (int i = 0; i < nb.length; i++) {
			// if node is a neighbour:
			if (nb[i] == 1) {
				// only add weight if it is positive and greater than 0, otherwise you won't
				// want to play with this node:
				if (gm.getNode(id).getNeighbourhoodWeight(i) > 0.0) {
					myNeighbours.add(i);
					myNWeight.add(weights[i]);
				}
			}
		}
		// initiate roulette and its sum for later use:
		roulette = new double[myNWeight.size()];
		summedWeights = new double[myNWeight.size()];

		// get total sum of weights:
		for (int j = 0; j < myNWeight.size(); j++) {
			totalSumWeights += myNWeight.get(j);
		}

		// calculate actual weight for each neighbour:
		for (int k = 0; k < myNeighbours.size(); k++) {
			double calc = myNWeight.get(k) / totalSumWeights;
			roulette[k] = calc;
		}

		int counter = 0;
		double sum = 0.0;

		// sum the weights
		for (int l = 0; l < roulette.length; l++) {
			if (l > 0) {
				sum += roulette[l - 1];
				summedWeights[l] = roulette[l] + sum;
			} else {
				summedWeights[l] = roulette[l];
			}

			// write current sum of weights to the sum array:

		}

		// choose random number cast it to double and select the node based on the
		// value:
		Random rd = new Random();
		int randomNumber = rd.nextInt(1000000000);
		double check = randomNumber / 1000000000.0;
		// check for sum of weights matching check value, if no value was matched it
		// means it is the last one in the array or unknown error has happened
		for (int m = 0; m < summedWeights.length; m++) {
			if (check <= summedWeights[m]) {
				return myNeighbours.get(m);
			}
		}
		// means that unknown error has happened or the node doesn't have anyone to play
		return -1;
	}

	private double calculateWeightChange(String result) {

		if (result == "increase gain") {
			return gm.getGIncrease();
		}
		if (result == "lower gain") {
			return gm.getGDecrease();
		}

		// if error happens:
		return 0;
	}

	private void playRound(int i, int z) {
		// play game or end round with no gain
		// PLAY - CHOOSE STRATEGIES BASED ON NODE'S CURRENT APPROACH
		stratI = gm.getNode(i).getStrategy();
		stratJ = gm.getNode(selectedPlayer).getStrategy();

		if (stratI == "original") {
			nodeIresult = gsc.playGame(i, selectedPlayer, z);
		} else if (stratI == "tit4tat") {
			nodeIresult = gst4t.playGame(i, selectedPlayer, z);
		} else if (stratI == "random") {
			nodeIresult = gst4to.playGame(i, selectedPlayer, z);
		} else if (stratI == "tit4tatRand") {
			nodeIresult = gst4tr.playGame(i, selectedPlayer, z);
		}

		if (stratJ == "original") {
			nodeJresult = gsc.playGame(selectedPlayer, i, z);
		} else if (stratJ == "tit4tat") {
			nodeJresult = gst4t.playGame(selectedPlayer, i, z);
		} else if (stratJ == "random") {
			nodeJresult = gst4to.playGame(selectedPlayer, i, z);
		} else if (stratJ == "tit4tatRand") {
			nodeJresult = gst4tr.playGame(selectedPlayer, i, z);
		}
		// UPDATE: total node score, weight change, update memory arrays, turn
		// cooperated or defected counters:
		if (nodeIresult == true && nodeJresult == true) {
			// set last time interaction happened - this turn
			gm.getNode(i).setLastTurnInteracted(selectedPlayer, z);
			gm.getNode(selectedPlayer).setLastTurnInteracted(i, z);
			// for total history:
			gm.getNode(i).setNeighbourInteractionHistory(selectedPlayer, z, 1);
			gm.getNode(selectedPlayer).setNeighbourInteractionHistory(i, z, 1);

			// count interactions:
			gm.getNode(i).countInteracted(selectedPlayer);
			gm.getNode(selectedPlayer).countInteracted(i);

			// for tpij history [depending on memory span]:
			gm.getNode(i).setTpijResult(selectedPlayer, 1);
			gm.getNode(selectedPlayer).setTpijResult(i, 1);

			// add value to total interaction history:
			gm.getNode(i).setTotalInteractionHistory(selectedPlayer, 1);
			gm.getNode(selectedPlayer).setTotalInteractionHistory(i, 1);

			// for total score:
			gm.getNode(i).setScore(1);
			gm.getNode(selectedPlayer).setScore(1);

			// for defection / coop counter:
			gm.getNode(i).setTimesCoop(1);
			gm.getNode(selectedPlayer).setTimesCoop(1);

			// Reward:
			gm.getNode(i).addR();

			// calculate and set the weight change:
			gm.getNode(i).setNeighbourhoodWeight(selectedPlayer, calculateWeightChange("increase gain"));
			gm.getNode(selectedPlayer).setNeighbourhoodWeight(i, calculateWeightChange("increase gain"));
		}
		// I defected:
		else if (nodeIresult == false && nodeJresult == true) {
			// set last time interaction happened - this turn
			gm.getNode(i).setLastTurnInteracted(selectedPlayer, z);
			gm.getNode(selectedPlayer).setLastTurnInteracted(i, z);

			gm.getNode(i).setNeighbourInteractionHistory(selectedPlayer, z, 2);
			gm.getNode(selectedPlayer).setNeighbourInteractionHistory(i, z, -1);

			// count interactions:
			gm.getNode(i).countInteracted(selectedPlayer);
			gm.getNode(selectedPlayer).countInteracted(i);

			gm.getNode(i).setTpijResult(selectedPlayer, 2);
			gm.getNode(selectedPlayer).setTpijResult(i, -1);

			// add value to total interaction history:
			gm.getNode(i).setTotalInteractionHistory(selectedPlayer, 2);
			gm.getNode(selectedPlayer).setTotalInteractionHistory(i, -1);

			gm.getNode(i).setScore(2);
			gm.getNode(selectedPlayer).setScore(-1);

			// for defection / coop counter:
			gm.getNode(i).setTimesDef(1);
			gm.getNode(selectedPlayer).setTimesCoop(1);

			// Temptation:
			gm.getNode(i).addT();

			// calculate and set the weight change:
			gm.getNode(selectedPlayer).setNeighbourhoodWeight(i, calculateWeightChange("lower gain"));

		}
		// He defected:
		else if (nodeIresult == true && nodeJresult == false) {
			// set last time interaction happened - this turn
			gm.getNode(i).setLastTurnInteracted(selectedPlayer, z);
			gm.getNode(selectedPlayer).setLastTurnInteracted(i, z);

			gm.getNode(i).setNeighbourInteractionHistory(selectedPlayer, z, -1);
			gm.getNode(selectedPlayer).setNeighbourInteractionHistory(i, z, 2);

			// count interactions:
			gm.getNode(i).countInteracted(selectedPlayer);
			gm.getNode(selectedPlayer).countInteracted(i);

			gm.getNode(i).setTpijResult(selectedPlayer, -1);
			gm.getNode(selectedPlayer).setTpijResult(i, 2);

			// add value to total interaction history:
			gm.getNode(i).setTotalInteractionHistory(selectedPlayer, -1);
			gm.getNode(selectedPlayer).setTotalInteractionHistory(i, 2);

			gm.getNode(i).setScore(-1);
			gm.getNode(selectedPlayer).setScore(2);

			// for defection / coop counter:
			gm.getNode(i).setTimesCoop(1);
			gm.getNode(selectedPlayer).setTimesDef(1);

			// Sucker's payoff:
			gm.getNode(i).addS();

			// calculate and set the weight change:
			gm.getNode(i).setNeighbourhoodWeight(selectedPlayer, calculateWeightChange("lower gain"));

		}
		// both defected:
		else if (nodeIresult == false && nodeJresult == false) {
			// set last time interaction happened - this turn
			gm.getNode(i).setLastTurnInteracted(selectedPlayer, z);
			gm.getNode(selectedPlayer).setLastTurnInteracted(i, z);

			gm.getNode(i).setNeighbourInteractionHistory(selectedPlayer, z, 0);
			gm.getNode(selectedPlayer).setNeighbourInteractionHistory(i, z, 0);

			// count interactions:
			gm.getNode(i).countInteracted(selectedPlayer);
			gm.getNode(selectedPlayer).countInteracted(i);

			// add value to total interaction history:
			gm.getNode(i).setTotalInteractionHistory(selectedPlayer, 0);
			gm.getNode(selectedPlayer).setTotalInteractionHistory(i, 0);

			gm.getNode(i).setTpijResult(selectedPlayer, 0);
			gm.getNode(selectedPlayer).setTpijResult(i, 0);

			gm.getNode(i).setScore(0);
			gm.getNode(selectedPlayer).setScore(0);

			// for defection / coop counter:
			gm.getNode(i).setTimesDef(1);
			gm.getNode(selectedPlayer).setTimesDef(1);

			// Punishment:
			gm.getNode(i).addP();
		}
		// END
	}
}
