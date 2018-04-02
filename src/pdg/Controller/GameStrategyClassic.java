package pdg.Controller;

import java.util.Random;

import pdg.Model.GameModel;

//This is strategy based on classic approach where node considers who to play with on previous interactions with the nodes
public class GameStrategyClassic extends GameController implements GameStrategy {

	private GameModel gm;

	public GameStrategyClassic(GameModel model) {
		gm = model;
	}

	@Override
	public boolean playGame(int id, int target, int turn) {
		boolean result = false;
		double idCoop = gm.getNode(id).getvCoop();
		double m = gm.getM();
		double motivator = 0;
		double sumTpij = 0;
		int[][] tpij = gm.getNode(id).getTpij();

		// sum total points from interaction with target node over m last turns
		for (int i = 0; i < gm.getM(); i++) {
			sumTpij += tpij[target][i];
		}
		// bias is added as it was suggested in the research paper as tpij / m which
		// adds from -0.1 to 0.2 to the default cooperation
		double bias = sumTpij / m;
		// target node or current tends to defect from the game, motivate negatively
		if ((bias <= 2 && bias > 1.5) || bias <= -0.5) {
			motivator = -0.1;
		}
		// motivate positively for cooperation emergence
		else if (bias <= 1.25 && bias > -0.75) {
			motivator = 0.1;
		}
		// motivate neutrally for remaining cases
		else {
			motivator = 0;
		}

		// might be higher than 1 or lesser than 0 in this case
		double determineResultThreshold = idCoop + motivator;

		Random rd = new Random();
		int roll = rd.nextInt(10000000);
		// should produce number between 0.0 to 1.0 i.e. 0.94213433
		double rollD = (double) roll / 10000000;

		if (rollD <= determineResultThreshold) {
			result = true;
		} else {
			result = false;
		}
		
		return result;
	}
}
