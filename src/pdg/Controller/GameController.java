package pdg.Controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pdg.Model.GameModel;
import pdg.View.PDGStartView;

public class GameController implements ActionListener {

	// initiate model once user sends data from GUI
	public GameModel gm;
	public PDGStartView sv;
	public GenerateSmallWorldStructure smNetwork;
	public GenerateScaleFreeStructure sfNetwork;
	public GenerateRandomStructure rdNetwork;
	public ResultsGraph rg;
	public PlayGame game;
	public NodeFactory nf;
	public PrintToFile pf;

	// make controller aware of view its attatched to
	public void addView(PDGStartView view) {
		this.sv = view;
	}

	// make controller aware of the model it is referencing to
	public void addModel(GameModel model) {
		gm = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// if the user will press "CONFIRM" game settings set these values in game
		// model:
		if (e.getActionCommand() == Action.GAME_SETTINGS.name()) {
			CardLayout cl = (CardLayout) (sv.panel.getLayout());
			cl.show(sv.panel, "gameSettingsPanel");
		}

		if (e.getActionCommand() == Action.CONFIRM.name()) {
			// set game turns:
			gm.setTurns(Integer.parseInt(sv.textFieldTurns.getText()));
			// selected variable values
			gm.setAlpha(Double.parseDouble(sv.textFieldAlpha.getText()));
			gm.setNodeNumber(Integer.parseInt(sv.textFieldNodesNumber.getText()));
			gm.setM(Integer.parseInt(sv.textFieldMemSpan.getText()));
			gm.setCr(Integer.parseInt(sv.textFieldCredit.getText()));
			gm.setNodeNumber(Integer.parseInt(sv.textFieldNodesNumber.getText()));

			// selected strategies:
			gm.setOrgApproach(sv.checkBoxOriginalApproach.isSelected());
			gm.setTit4tat(sv.checkBoxTitTatAllow.isSelected());
			gm.setTit4TatRandom(sv.checkBoxTitTwoTat.isSelected());
			gm.setRandom(sv.checkBoxRandom.isSelected());
			// set strategy weights:
			gm.setOriginalApproach(Double.parseDouble(sv.textField1MInteractions.getText()));
			gm.setT4t(Double.parseDouble(sv.textField3TitTat.getText()));
			gm.setRandomWeight(Double.parseDouble(sv.textField7Random.getText()));
			gm.setT4TRand(Double.parseDouble(sv.textField8TitTwoTats.getText()));
			// reasons to change strategy:
			gm.setLoosingNeighbour(sv.checkBoxLostConnection.isSelected());
			gm.setRandomChange(sv.checkBoxRandomChangeChance.isSelected());
			if (sv.checkBoxRandomChangeChance.isSelected()) {
				gm.setRandomChangeValue(Double.parseDouble(sv.textFieldRandomChangeChance.getText()));
			} else {
				gm.setRandomChangeValue(0.0);
			}
			// other settings:
			gm.setFixedCoop(sv.checkBoxFixCoopForAll.isSelected());
			if (sv.checkBoxFixCoopForAll.isSelected()) {
				gm.setFixedVcoop(Double.parseDouble(sv.textFieldFixedCoop.getText()));
			} else {
				gm.setFixedVcoop(0.0);
			}

			gm.setGDecrease(Double.parseDouble(sv.textFieldGainDecrease.getText()));
			gm.setGIncrease(Double.parseDouble(sv.textFieldGainIncrease.getText()));

			gm.setForgiving(sv.chckbxAllowForgive.isSelected());
			gm.setSmallWorldK(Integer.parseInt(sv.textFieldSmallWorldK.getText()));
			gm.setSmallWorldRewire(Double.parseDouble(sv.textFieldSmallWorldRewire.getText()));
			gm.setForcedPlay(sv.checkBoxForce.isSelected());

			
			gm.initNodes();
			// once the game settings are set, enable the generate button
			sv.btnGenerate.setEnabled(true);
			sv.btnResults.setEnabled(false);
			sv.btnPlay.setEnabled(false);
			
			CardLayout cl = (CardLayout) (sv.panel.getLayout());
			cl.show(sv.panel, "networkSettingsPanel");
		}
		
		// if user will press GENERATE button, generate respective network and extract
		// its data to game model
		if (e.getActionCommand() == Action.GENERATE.name()) {
			if (sv.rdbtnRandom.isSelected()) {
				gm.setSeed(Long.parseLong(sv.textFieldSeed.getText()));
				gm.setNodeNumber(Integer.parseInt(sv.textFieldNodesNumber.getText()));
				gm.setTotalEdges(Integer.parseInt(sv.textFieldTotalEdges.getText()));
				rdNetwork = new GenerateRandomStructure(gm);
				sv.setNetworkView(rdNetwork.generateGraph(gm.getNodeNumber(), gm.getTotalEdges(), gm.getSeed()));
				// now that we have game model matrix ready we have to create the remainder:
				nf = new NodeFactory(gm, gm.getNodeNumber(), sv.checkBoxFixCoopForAll.isSelected());
				sv.btnPlay.setEnabled(true);

			} else if (sv.rdbtnScalefree.isSelected()) {
				// generate scale free network:
				gm.setSeed(Long.parseLong(sv.textFieldSeed.getText()));
				gm.setNodeNumber(Integer.parseInt(sv.textFieldNodesNumber.getText()));
				sfNetwork = new GenerateScaleFreeStructure(gm);
				sv.setNetworkView(sfNetwork.generateGraph(gm.getNodeNumber(), gm.getSeed()));
				// now that we have game model matrix ready we have to create the remainder:
				nf = new NodeFactory(gm, gm.getNodeNumber(), sv.checkBoxFixCoopForAll.isSelected());
				sv.btnPlay.setEnabled(true);

			} else if (sv.rdbtnSmallWorld.isSelected()) {
				// generate small world network:
				gm.setSeed(Long.parseLong(sv.textFieldSeed.getText()));
				gm.setNodeNumber(Integer.parseInt(sv.textFieldNodesNumber.getText()));
				gm.setSmallWorldK(Integer.parseInt(sv.textFieldSmallWorldK.getText()));
				gm.setSmallWorldRewire(Double.parseDouble(sv.textFieldSmallWorldRewire.getText()));
				smNetwork = new GenerateSmallWorldStructure(gm);
				sv.setNetworkView(smNetwork.generateGraph(gm.getNodeNumber(), gm.getSmallWorldK(),
						gm.getSmallWorldRewire(), gm.getSeed()));
				// now that we have game model matrix ready we have to create the remainder:
				nf = new NodeFactory(gm, gm.getNodeNumber(), sv.checkBoxFixCoopForAll.isSelected());
				sv.btnPlay.setEnabled(true);

			}
			// Moves to the network view panel once generated:
			CardLayout cl = (CardLayout) (sv.panel.getLayout());
			cl.show(sv.panel, "networkStructurePanel");

			// DISABLE CONTROLS FOR RESULT VIEW:
			sv.btnApplyCircle.setEnabled(false);
			sv.btnApplyHierarchical.setEnabled(false);
			sv.btnApplyOrganic.setEnabled(false);
			sv.btnInResults.setEnabled(false);
			sv.btnOutResults.setEnabled(false);
			sv.btnBackToView.setEnabled(false);
			sv.btnResults.setEnabled(false);
		}

		// STARTS THE GAME OVER SELECTED NETWORK
		if (e.getActionCommand() == Action.GAME_START.name()) {
			// START PLAYING PD GAME:
			game = new PlayGame(gm);
			rg = new ResultsGraph(gm);

			pf = new PrintToFile(gm);
			pf.printFile();

			// GENERATE SECOND GRAPH WITH THE RESULTS:
			sv.setResultsView(rg.drawGraph());
			// ENABLE CONTROLS FOR RESULT VIEW:
			sv.btnApplyCircle.setEnabled(true);
			sv.btnApplyHierarchical.setEnabled(true);
			sv.btnApplyOrganic.setEnabled(true);
			sv.btnInResults.setEnabled(true);
			sv.btnOutResults.setEnabled(true);
			sv.btnBackToView.setEnabled(true);
			sv.btnResults.setEnabled(true);
		}

		// ZOOM IN AND OUT THE VIEW OF THE GRAPH
		if (e.getActionCommand() == Action.ZOOM_IN.name()) {
			if (sv.rdbtnRandom.isSelected()) {
				rdNetwork.zoomIn();
			} else if (sv.rdbtnScalefree.isSelected()) {
				sfNetwork.zoomIn();
			} else if (sv.rdbtnSmallWorld.isSelected()) {
				smNetwork.zoomIn();
			}
		}

		if (e.getActionCommand() == Action.ZOOM_OUT.name()) {
			if (sv.rdbtnRandom.isSelected()) {
				rdNetwork.zoomOut();
			} else if (sv.rdbtnScalefree.isSelected()) {
				sfNetwork.zoomOut();
			} else if (sv.rdbtnSmallWorld.isSelected()) {
				smNetwork.zoomOut();
			}
		}

		// ZOOM IN AND OUT THE VIEW OF THE RESULTS GRAPH
		if (e.getActionCommand() == Action.RESULTS_ZOOM_IN.name()) {
			rg.zoomIn();
		}

		if (e.getActionCommand() == Action.RESULTS_ZOOM_OUT.name()) {
			rg.zoomOut();
		}

		// BACK TO BASIC NETWORK VIEW
		if (e.getActionCommand() == Action.BACK_TO_VIEW.name()) {
			CardLayout cl = (CardLayout) (sv.panel.getLayout());
			cl.show(sv.panel, "networkStructurePanel");
		}
		
		// VIEW THE NETWORK AFTER GAME
		if (e.getActionCommand() == Action.VIEW_RESULT_NETWORK.name()) {
			CardLayout cl = (CardLayout) (sv.panel.getLayout());
			cl.show(sv.panel, "resultsPanel");
		}
		
		// SETTING DIFFERENT LAYOUTS
		if (e.getActionCommand() == Action.HIERARCHICAL.name()) {
			rg.setHierarchical();
		}
		if (e.getActionCommand() == Action.CIRCLE.name()) {
			rg.setCircle();
		}
		if (e.getActionCommand() == Action.ORGANIC.name()) {
			rg.setOrganic();
		}
	}
}
