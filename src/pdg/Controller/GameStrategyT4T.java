package pdg.Controller;

import pdg.Model.GameModel;

public class GameStrategyT4T extends GameController implements GameStrategy {
	private GameModel gm;

	public GameStrategyT4T(GameModel model) {
		gm = model;
	}

	@Override
	public boolean playGame(int activeNode, int selectedNode, int turn) {
		// get last turn interaction between the two nodes:
		int lastTurnInteracted = gm.getNode(activeNode).getLastTurnInteracted(selectedNode);
		int timesInteracted = gm.getNode(activeNode).getTimesInteracted(selectedNode);
		// in case its the first time these nodes interact then always cooperate:
		if (lastTurnInteracted == 0 && timesInteracted == 0) {
			// Always cooperate on 1st contact with selected node:
			return true;
		} else {
			int interaction = gm.getNode(activeNode).getNeighbourInteractionHistory(selectedNode, lastTurnInteracted);
			// active node defected
			if (interaction == 2) {
				return true;
			}
			// selected node defected:
			if (interaction == -1) {
				return false;
			}
			// both defected:
			if (interaction == 0) {
				return false;
			}
			// both cooperated:
			if (interaction == 1) {
				return true;
			}
		}
		return false;
	}
}
