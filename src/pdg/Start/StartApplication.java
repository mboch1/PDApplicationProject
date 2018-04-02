package pdg.Start;

import pdg.Controller.GameController;
import pdg.Model.GameModel;
import pdg.View.PDGStartView;

public class StartApplication {
	
	public GameModel model = new GameModel();
	public PDGStartView view = new PDGStartView();
	public GameController controller = new GameController();
	
	public StartApplication() {
		//connect controller to model and the view
		controller.addModel(model);
		controller.addView(view);
		//connect view to controller so that it can use the action listener
		view.addController(controller);
	}

}
