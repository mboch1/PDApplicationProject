package pdg.Controller;

//This strategy is based on tit 4 tat strategy but adds a fixed random chance that the node will play opposite to what it did last time with selected node
import java.util.Random;

import pdg.Model.GameModel;

public class GameStrategyT4TRandom extends GameController implements GameStrategy {

	private GameModel gm;

	public GameStrategyT4TRandom(GameModel model) {
		gm = model;
	}

	@Override
	public boolean playGame(int id, int selectedPlayer, int turn) {
		Random rd = new Random();
		int value = rd.nextInt(10000);
		double check = (double) value / 10000.0;

		// get last turn interaction between two nodes:
		int lastTurnInteracted = gm.getNode(id).getLastTurnInteracted(selectedPlayer);
		int timesInteracted = gm.getNode(id).getTimesInteracted(selectedPlayer);
		// in case its the first time these nodes interact then always cooperate:
		if (lastTurnInteracted == 0 && timesInteracted == 0) {
			// Always cooperate on 1st contact with selected node:
			return true;
		} else {
			int interaction = gm.getNode(id).getNeighbourInteractionHistory(selectedPlayer, lastTurnInteracted);
			// increments counter for number of interactions with selected node:
			// I defected last turn so interaction score will be equal to 2:
			if (interaction == 2) {
				if (check <= gm.getRandomChanceValue()) {
					return false;
				}
				return true;
			}
			// He defected last turn:
			if (interaction == -1) {
				if (check <= gm.getRandomChanceValue()) {
					return true;
				}
				return false;
			}
			// we draw:
			if (interaction == 0) {
				if (check <= gm.getRandomChanceValue()) {
					return true;
				}
				return false;
			}
			// both cooperated:
			if (interaction == 1) {
				if (check <= gm.getRandomChanceValue()) {
					return false;
				}
				return true;
			}
		}

		return false;
	}

}
