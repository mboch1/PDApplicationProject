package pdg.Model;

import java.util.ArrayList;

public class Node {
	// node ID
	private int id;
	// node base risk aversion factor
	private double alpha;
	// total score gained by this node
	private int score;
	// count how many times the node coop/def in game
	private int timesCoop;
	private int timesDef;
	private int[] timesInteracted;
	private int r = 0;
	private int t = 0;
	private int s = 0;
	private int p = 0;
	// node base cooperation value:
	private double vCoop;
	// memory span:
	private int m;
	// interaction history with neighbours and a total
	// interaction history with all the nodes
	private int[][] neighbourInteractionHistory;
	private int[] totalInteractionHistory;
	// for strategies:
	private boolean[] lastStrategyPlayed;
	private int[] lastTurnInteracted;
	private int[][] tpij;
	// index for the last interaction with a given node
	private int[] nIndex;
	// strategy id which node will use to play
	private String strategy;
	// arraylist to trace changes in strategies
	private ArrayList<String> strategyHistory = new ArrayList<>();
	// reason to change the strategy
	private ArrayList<String> hasNoPlayer = new ArrayList<>();
	// node neighbourhood array
	int neighbourhood[];
	// node neighbourhood weights array
	double neighbourhoodWeights[];

	public Node(int edgeToNode[], double neighbourhoodWeights[], int n, int nodeID, double riskAversion, double viCoop,
			int memSpan, int turns, String strategyID) {
		setId(nodeID);
		setAlpha(riskAversion);
		setvCoop(viCoop);
		m = memSpan;
		setStrategy(strategyID);
		// setup starting conditions for node:
		setScore(0);
		setTimesCoop(0);
		setTimesDef(0);
		setNeighbourhoodWeights(neighbourhoodWeights);
		// total interaction history
		totalInteractionHistory = new int[n];
		// counter for last interaction with this node:
		lastTurnInteracted = new int[n];
		// counter for total number of interaction with selected node:
		timesInteracted = new int[n];
		// create an array with n fields for keeping the score
		neighbourInteractionHistory = new int[n][turns];
		// create an array with n fields and fill with default 0 for the last
		// interaction index
		nIndex = new int[n];
		// default to 0
		neighbourhood = edgeToNode;
		// default total points between node i and j over last M turns
		tpij = new int[n][m];
		lastStrategyPlayed = new boolean[n];
	}

	// getters and setters:

	// get an array of neighbourhood weights
	public double[] getNeighbourhoodWeights() {
		return neighbourhoodWeights;
	}

	public double getNeighbourWeight(int nodeID) {
		return neighbourhoodWeights[nodeID];
	}

	public void setNeighbourhoodWeights(double[] neighbourhoodWeights) {
		this.neighbourhoodWeights = neighbourhoodWeights;
	}

	// adds or subtracts individual weight to by given value
	public void setNeighbourhoodWeight(int nodeID, double g) {
		neighbourhoodWeights[nodeID] = neighbourhoodWeights[nodeID] + g;
	}

	public int getTimesInteracted(int nodeID) {
		return timesInteracted[nodeID];
	}

	public void countInteracted(int nodeID) {
		timesInteracted[nodeID] += 1;
	}

	public int[] getNeighbours() {
		return neighbourhood;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAlpha() {
		return alpha;
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score += score;
	}

	public int getTimesCoop() {
		return timesCoop;
	}

	public void setTimesCoop(int timesCoop) {
		this.timesCoop += timesCoop;
	}

	public int getTimesDef() {
		return timesDef;
	}

	public void setTimesDef(int timesDef) {
		this.timesDef += timesDef;
	}

	public double getvCoop() {
		return vCoop;
	}

	public void setvCoop(double vCoop) {
		this.vCoop = vCoop;
	}

	// return value at given turn interaction history:
	public int getNeighbourInteractionHistory(int nodeID, int turn) {
		return neighbourInteractionHistory[nodeID][turn];
	}

	// return value at given turn interaction history:
	public int[][] getAllInteractionHistory() {
		return neighbourInteractionHistory;
	}

	// remembers how our neighbour played against us last turn:
	public void setNeighbourInteractionHistory(int nodeID, int turn, int result) {
		neighbourInteractionHistory[nodeID][turn] = result;
	}

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	public ArrayList<String> getStrategyHistory() {
		return strategyHistory;
	}

	// use it before invoking total interaction history between these nodes
	public void setTpijResult(int target, int result) {

		this.tpij[target][nIndex[target]] = result;
		// increase memory index by 1
		nIndex[target] += 1;
		// reset memory index
		if (nIndex[target] == m) {
			nIndex[target] = 0;
		}
	}

	// get and set total interaction history value with given node
	public int getTotalInteractionHistory(int nodeID) {
		return totalInteractionHistory[nodeID];
	}

	public int[] getTotalInteractionHistory() {
		return totalInteractionHistory;
	}

	public void setTotalInteractionHistory(int nodeID, int value) {
		totalInteractionHistory[nodeID] += value;
	}

	// get and set total score between node i and j over m turns
	public int[][] getTpij() {
		return tpij;
	}

	public int getTpijForNode(int target) {
		int sum = 0;

		for (int i = 0; i < m; i++) {
			sum += tpij[target][i];
		}

		return sum;
	}

	public boolean[] getLastStrategyPlayed() {
		return lastStrategyPlayed;
	}

	public void setLastStrategyPlayed(int id, boolean result) {
		lastStrategyPlayed[id] = result;
	}

	// for correcting weights:
	public void setCorretNeighbourhoodWeight(int j, double d) {
		neighbourhoodWeights[j] = d;
	}

	// set interaction between two nodes to current turn
	public void setLastTurnInteracted(int selectedPlayer, int turn) {
		lastTurnInteracted[selectedPlayer] = turn;
	}

	// get last turn when these two nodes interacted (except current turn)
	public int getLastTurnInteracted(int selectedPlayer) {
		return lastTurnInteracted[selectedPlayer];
	}

	// get individual weight value with the selected node
	public double getNeighbourhoodWeight(int selectedPlayer) {
		return neighbourhoodWeights[selectedPlayer];
	}

	public void setCantPlay(String string) {
		hasNoPlayer.add(string);
	}

	public ArrayList<String> getNoPlayerList() {
		return hasNoPlayer;
	}

	public int getR() {
		return r;
	}

	public void addR() {
		this.r += 1;
	}

	public int getT() {
		return t;
	}

	public void addT() {
		this.t += 1;
	}

	public int getS() {
		return s;
	}

	public void addS() {
		this.s += 1;
	}

	public int getP() {
		return p;
	}

	public void addP() {
		this.p += 1;
	}

}
