package pdg.Controller;

import java.util.Random;

import pdg.Model.GameModel;

//This is a strategy based on original tit 4 tat strategy but adds a chance that the node will play opposite due to the history of m last interactions
public class GameStrategyRandom extends GameController implements GameStrategy {

	private GameModel gm;

	public GameStrategyRandom(GameModel model) {
		this.gm = model;
	}

	@Override
	public boolean playGame(int id, int selectedPlayer, int turn) {

		// play randomly either coop or defect:
		Random rd = new Random();
		int value = rd.nextInt(100);
		
		if(value > 50) {
			return false;
		} else {
			return true;
		}

	}

}
