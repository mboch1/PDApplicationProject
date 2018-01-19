package pdg.Start;

import pdg.Controller.GameController;
import pdg.Model.GameModel;
import pdg.View.PDGStartView;

public class StartApplication {
	
	public GameModel model = new GameModel();
	public PDGStartView view = new PDGStartView();
	public GameController controller = new GameController();
	
	public StartApplication() {
		
		controller.addModel(model);
		controller.addView(view);
		//tell View about the Controller
		view.addController(controller);
		view.addModel(model);
	}

}
