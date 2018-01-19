package pdg.Model;

public class GameModel {
	
	//variables for game and network settings:
	//node strategy types allowed
	private boolean orgApproach;
	private boolean tit4tat;
	private boolean fixedDefaultCoopChance;
	private boolean alwaysCoop;
	private boolean alwaysDef;
	private boolean tit4tatRandom;
	private boolean tit42tats;
	private boolean fixedChanceOverViCoop;
	//variable for fixed chance overriding ViCoop strategy:
	private double fixedChanceOverrideValue;
	
	//other:
	private int smallWorldK;
	private double smallWorldRewire;
	
	//node weights
	private double origApproach;
	private double t4t;
	private double fxDefCoop;
	private double fxChanceOverride;
	private double aCoop;
	private double aDef;
	private double t4tRand;
	private double t42t;
	
	//node strategy change allowed
	private boolean strategyChange;
	
	//node strategy change reasons selected
	private boolean randomChange;
	private double randomChangeValue;
	private boolean loosingXTimes;
	private int losingXTimesValue;
	private boolean loosingNeighbour;
	
	//number of turns to play
	private int turns;
	//node number
	private int nodeNumber;
	//alpha factor
	private double alpha;
	//base trust credit
	private int cr;
	//memory span
	private int m;
	//fix cooperation value for all
	boolean fixedCoop;
	//fixed coop value
	double fixedVcoop;
	//nodes neighbourhood array from controller
	private int[] nodeNeighbourghood;

	//default constructor to initialise on start
	public GameModel() {
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

	public boolean isFixedDefaultCoopChance() {
		return fixedDefaultCoopChance;
	}

	public void setFixedDefaultCoopChance(boolean fixedDefaultCoopChance) {
		this.fixedDefaultCoopChance = fixedDefaultCoopChance;
	}

	public boolean isFixedChanceOverViCoop() {
		return fixedChanceOverViCoop;
	}

	public void setFixedChanceOverViCoop(boolean fixedChanceOverViCoop) {
		this.fixedChanceOverViCoop = fixedChanceOverViCoop;
	}

	public boolean isAlwaysCoop() {
		return alwaysCoop;
	}

	public void setAlwaysCoop(boolean alwaysCoop) {
		this.alwaysCoop = alwaysCoop;
	}

	public boolean isAlwaysDef() {
		return alwaysDef;
	}

	public void setAlwaysDef(boolean alwaysDef) {
		this.alwaysDef = alwaysDef;
	}

	public boolean isTit4tatRandom() {
		return tit4tatRandom;
	}

	public void setTit4tatRandom(boolean tit4tatRandom) {
		this.tit4tatRandom = tit4tatRandom;
	}

	public boolean isTit42tats() {
		return tit42tats;
	}

	public void setTit42tats(boolean tit42tats) {
		this.tit42tats = tit42tats;
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

	public boolean isLoosingXTimes() {
		return loosingXTimes;
	}

	public void setLoosingXTimes(boolean loosingXTimes) {
		this.loosingXTimes = loosingXTimes;
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

	public double getFxDefCoop() {
		return fxDefCoop;
	}

	public void setFxDefCoop(double fxDefCoop) {
		this.fxDefCoop = fxDefCoop;
	}

	public double getFxChanceOverride() {
		return fxChanceOverride;
	}

	public void setFxChanceOverride(double fxChanceOverride) {
		this.fxChanceOverride = fxChanceOverride;
	}

	public double getaCoop() {
		return aCoop;
	}

	public void setaCoop(double aCoop) {
		this.aCoop = aCoop;
	}

	public double getaDef() {
		return aDef;
	}

	public void setaDef(double aDef) {
		this.aDef = aDef;
	}

	public double getT4tRand() {
		return t4tRand;
	}

	public void setT4tRand(double t4tRand) {
		this.t4tRand = t4tRand;
	}

	public double getT42t() {
		return t42t;
	}

	public void setT42t(double t42t) {
		this.t42t = t42t;
	}

	public double getFixedChanceOverrideValue() {
		return fixedChanceOverrideValue;
	}

	public void setFixedChanceOverrideValue(double fixedChanceOverrideValue) {
		this.fixedChanceOverrideValue = fixedChanceOverrideValue;
	}
	
	public void setFixedCoop(boolean fixedCoop) {
		this.fixedCoop = fixedCoop;
	}
	
	public boolean getFixedCoop() {
		return fixedCoop;
	}
	
	public void setFixedVcoop (double fixedVcoop) {
		this.fixedVcoop = fixedVcoop;
	}
	
	public double getFixedVcoop () {
		return fixedVcoop;
	}

	public double getRandomChangeValue() {
		return randomChangeValue;
	}

	public void setRandomChangeValue(double randomChangeValue) {
		this.randomChangeValue = randomChangeValue;
	}

	public int getLosingXTimesValue() {
		return losingXTimesValue;
	}

	public void setLosingXTimesValue(int losingXTimesValue) {
		this.losingXTimesValue = losingXTimesValue;
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
}
