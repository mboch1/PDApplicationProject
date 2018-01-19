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
	// node base cooperation value:
	private double vCoop;
	// memory span:
	private int m;
	// base credit:
	private int cr;
	// interaction history with neighbours and non neighbours and a total interaction history with the nodes
	private int[] nnHistory;
	private int[] nHistory;
	private int[] totalInteractionHistory;
	// index for the last interaction with a given node
	private int nnIndex;
	private int[] nIndex;
	// strategy id which node will use to play
	private String strategy;
	// arraylist to trace changes in strategies
	private ArrayList<String> strategyHistory = new ArrayList<>();
	// reason to change the strategy
	private ArrayList<String> reasonToChange = new ArrayList<>();
	//node neighbourhood array
	int neighbourhood[];
	
	public Node(int edgeToNode[], int n, int nodeID, double riskAversion, double viCoop, int memSpan, int credit, String strategyID) {
		setId(nodeID);
		setAlpha(riskAversion);
		setvCoop(viCoop);
		m = memSpan;
		setCr(credit);
		setStrategy(strategyID);
		//setup starting conditions for n nodes:
		setScore(0);
		setTimesCoop(0);
		setTimesDef(0);
		//total interaction history
		totalInteractionHistory = new int[n]; 
		//create an array with m fields for keeping the score
		nnHistory = new int[m];
		//create an array with n fields for keeping the score
		nHistory = new int[n];
		//create an array with n fields and fill with default 0 for the last interaction index
		nIndex = new int[n];
		//default to 0
	}
	
	//getters and setters:
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
		this.score = score;
	}

	public int getTimesCoop() {
		return timesCoop;
	}

	public void setTimesCoop(int timesCoop) {
		this.timesCoop = timesCoop;
	}

	public int getTimesDef() {
		return timesDef;
	}

	public void setTimesDef(int timesDef) {
		this.timesDef = timesDef;
	}

	public double getvCoop() {
		return vCoop;
	}

	public void setvCoop(double vCoop) {
		this.vCoop = vCoop;
	}

	public int getCr() {
		return cr;
	}

	public void setCr(int cr) {
		this.cr = cr;
	}

	public int[] getNnHistory() {
		return nnHistory;
	}

	public void setNnHistory(int result) {
		if(nnIndex<m) {
			nnHistory[nnIndex] = result;
			nnIndex++;
		}
		//start counting again once reached mem span
		if(nnIndex==m) {
			nnIndex = 0;
		}
	}

	public int getnHistory(int nodeID) {
		return nHistory[nodeID];
	}

	public void setnHistory(int result, int nodeID) {
		if(nIndex[nodeID]<m) {
			nHistory[nodeID] = result;
			nIndex[nodeID]++;
		}
		//reset index if reached mem span
		if(nIndex[nodeID]==m) {
			nIndex[nodeID] = 0;
		}
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

	public ArrayList<String> getReasonToChange() {
		return reasonToChange;
	}

	public int getTotalInteractionHistory(int nodeID) {
		return totalInteractionHistory[nodeID];
	}

	public void setTotalInteractionHistory(int result, int nodeID) {
		totalInteractionHistory[nodeID] += result;
	}
	
	
}
