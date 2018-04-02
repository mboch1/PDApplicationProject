package pdg.Model;

import java.util.ArrayList;

public class GameModel {

	// variables for game and network settings:
	// node strategy types allowed
	private boolean orgApproach;
	private boolean tit4tat;
	private boolean random;
	private boolean tit4tatRandom;

	// other:
	private boolean forcedPlay;
	private boolean isForgiving;
	//gain settings:
	private double gIncrease;
	private double gDecrease;
	private double randomChanceValue;
	
	//small world settings:
	private int smallWorldK;
	private double smallWorldRewire;
	
	//random graph:
	private int totalEdges;

	// for generating node strategies (weights)
	private double origApproach;
	private double t4t;
	private double t4tRand;
	private double rand;

	// node strategy change allowed
	private boolean strategyChange;

	// node strategy change reasons selected
	private boolean randomChange;
	private double randomChangeValue;
	private boolean loosingNeighbour;

	// number of turns to play
	private int turns;
	// node number
	private int nodeNumber;
	// alpha factor
	private double alpha;
	// base trust credit
	private int cr;
	// memory span
	private int m;
	// fix cooperation value for all
	boolean fixedCoop;
	// fixed coop value
	double fixedVcoop;
	// nodes neighbourhood array from controller
	private int[] nodeNeighbourghood;
	// game model matrix:
	private int[][] matrix;

	// Nodes arraylist which will contain all the nodes in the structure:
	private ArrayList<Node> nodes = new ArrayList<>();
	
	private long seed;

	// default constructor to initialise on start
	public GameModel() {
	}

	// get neighbour matrix for node id
	public int[] getNeighbourMatrix(int id) {

		int[] neighbour = new int[this.getNodeNumber()];
		for (int i = 0; i < this.getNodeNumber(); i++) {
			neighbour[i] = matrix[id][i];
		}

		return neighbour;
	}

	public double getOrigApproachWeight() {
		return origApproach;
	}

	public double getT4TWeight() {
		return t4t;
	}

	public double getRandomWeight() {
		return rand;
	}

	public double getT4TRandWeight() {
		return t4tRand;
	}

	public void setOrigApproachWeight(double origWeight) {
		this.origApproach = origWeight;
	}

	public void setT4TWeight(double t4TWeight) {
		this.t4t = t4TWeight;
	}

	public void setRandomWeight(double random) {
		this.rand = random;
	}

	public void setT4TRandWeight(double t4tRand) {
		this.t4tRand = t4tRand;
	}

	public boolean isOrgApproach() {
		return orgApproach;
	}

	public void setOrgApproach(boolean orgApproach) {
		this.orgApproach = orgApproach;
	}

	public boolean isTit4tat() {
		return tit4tat;
	}

	public void setTit4tat(boolean tit4tat) {
		this.tit4tat = tit4tat;
	}

	public boolean isRandom() {
		return random;
	}

	public void setRandom(boolean random) {
		this.random = random;
	}

	public boolean isTit4TatRandom() {
		return tit4tatRandom;
	}

	public void setTit4TatRandom(boolean tit4tatRandom) {
		this.tit4tatRandom = tit4tatRandom;
	}

	public double getOriginalApproach() {
		return origApproach;
	}

	public void setOriginalApproach(double originalApproach) {
		this.origApproach = originalApproach;
	}

	public boolean isStrategyChange() {
		return strategyChange;
	}

	public void setStrategyChange(boolean strategyChange) {
		this.strategyChange = strategyChange;
	}

	public boolean isRandomChange() {
		return randomChange;
	}

	public void setRandomChange(boolean randomChange) {
		this.randomChange = randomChange;
	}

	public boolean isLoosingNeighbour() {
		return loosingNeighbour;
	}

	public void setLoosingNeighbour(boolean loosingNeighbour) {
		this.loosingNeighbour = loosingNeighbour;
	}

	public int getTurns() {
		return turns;
	}

	public void setTurns(int turns) {
		this.turns = turns;
	}

	public double getAlpha() {
		return alpha;
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

	public int getCr() {
		return cr;
	}

	public void setCr(int cr) {
		this.cr = cr;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public int getNodeNumber() {
		return nodeNumber;
	}

	public void setNodeNumber(int nodeNumber) {
		this.nodeNumber = nodeNumber;
	}

	public int[] getNodeNeighbourghood() {
		return nodeNeighbourghood;
	}

	public void setNodeNeighbourghood(int[] nodeNeighbourghood) {
		this.nodeNeighbourghood = nodeNeighbourghood;
	}

	public double getT4t() {
		return t4t;
	}

	public void setT4t(double t4t) {
		this.t4t = t4t;
	}

	public double getRandom() {
		return rand;
	}

	public void setRandom(double rand) {
		this.rand = rand;
	}

	public double getT4TRand() {
		return t4tRand;
	}

	public void setT4TRand(double t4tRand) {
		this.t4tRand = t4tRand;
	}

	public void setFixedCoop(boolean fixedCoop) {
		this.fixedCoop = fixedCoop;
	}

	public boolean getFixedCoop() {
		return fixedCoop;
	}

	public void setFixedVcoop(double fixedVcoop) {
		this.fixedVcoop = fixedVcoop;
	}

	public double getFixedVcoop() {
		return fixedVcoop;
	}

	public double getRandomChangeValue() {
		return randomChangeValue;
	}

	public void setRandomChangeValue(double randomChangeValue) {
		this.randomChangeValue = randomChangeValue;
	}

	public void setSmallWorldK(int parseInt) {
		smallWorldK = parseInt;
	}

	public int getSmallWorldK() {
		return smallWorldK;
	}

	public void setSmallWorldRewire(double rewire) {
		smallWorldRewire = rewire;
	}

	public double getSmallWorldRewire() {
		return smallWorldRewire;
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	// get whole array of nodes
	public ArrayList<Node> getNodes() {
		return nodes;
	}

	// get individual node by given id
	public Node getNode(int nodeID) {
		return nodes.get(nodeID);
	}

	public void setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}
	
	public void initNodes () {
		nodes = new ArrayList<>();
	}

	public void removeAllNodes() {
		this.nodes.removeAll(nodes);
	}

	// adds a node to an array list:
	public void addNewNode(int[] neighbourhoodMatrix, double[] neighbourhoodWeights, int n, int i, double alpha2,
			double fixedCoopValue, int m2, int turns, String strategy) {
		nodes.add(new Node(neighbourhoodMatrix, neighbourhoodWeights, n, i, alpha2, fixedCoopValue, m2, turns, strategy));
	}

	public boolean isForcedPlay() {
		return forcedPlay;
	}

	public void setForcedPlay(boolean forcedPlay) {
		this.forcedPlay = forcedPlay;
	}

	//check if setting is forgiving is selected true or false:
	public boolean isForgiving() {
		return isForgiving;
	}

	public void setForgiving(boolean isForgiving) {
		this.isForgiving = isForgiving;
	}

	public long getSeed() {
		return seed;
	}

	public void setSeed(long seed) {
		this.seed = seed;
	}

	public double getGIncrease() {
		return gIncrease;
	}

	public void setGIncrease(double gIncrease) {
		this.gIncrease = gIncrease;
	}

	public double getGDecrease() {
		return gDecrease;
	}

	public void setGDecrease(double gDecrease) {
		this.gDecrease = gDecrease;
	}

	public double getRandomChanceValue() {
		return randomChanceValue;
	}

	public void setRandomChanceValue(double randomChanceValue) {
		this.randomChanceValue = randomChanceValue;
	}

	public void setTotalEdges(int edges) {
		this.totalEdges = edges;
	}
	public int getTotalEdges() {
		return totalEdges;
	}
}
