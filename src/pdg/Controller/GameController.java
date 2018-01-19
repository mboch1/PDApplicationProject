package pdg.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pdg.Model.GameModel;
import pdg.View.PDGStartView;

public class GameController implements ActionListener {

	// initiate model once user sends data from GUI
	GameModel gm;
	PDGStartView sv;
	GenerateSmallWorldStructure smNetwork;
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
		//if the user will press "CONFIRM" game settings set these values in game model:
		if (e.getActionCommand() == Action.CONFIRM.name()) {
			// selected variable values
			System.out.println(sv.textFieldAlpha.toString());
			gm.setAlpha(Double.parseDouble(sv.textFieldAlpha.getText()));
			gm.setNodeNumber(Integer.parseInt(sv.textFieldNodesNumber.getText()));
			gm.setM(Integer.parseInt(sv.textFieldMemSpan.getText()));
			gm.setCr(Integer.parseInt(sv.textFieldCredit.getText()));
			gm.setNodeNumber(Integer.parseInt(sv.textFieldNodesNumber.getText()));

			// selected strategies:
			gm.setOrgApproach(sv.checkBoxOriginalApproach.isSelected());
			gm.setAlwaysCoop(sv.checkBoxAlwaysCoop.isSelected());
			gm.setAlwaysDef(sv.checkBoxAlwaysDefect.isSelected());
			gm.setFixedChanceOverViCoop(sv.checkBoxFixedOverride.isSelected());
			if (sv.checkBoxFixedOverride.isSelected()) {
				gm.setFixedChanceOverrideValue(Double.parseDouble(sv.textFieldFixChanceOver.getText()));
			} else {
				gm.setFixedChanceOverrideValue(0.0);
			}
			gm.setFixedDefaultCoopChance(sv.checkBoxFixedCoopDefaultOnly.isSelected());
			gm.setTit4tat(sv.checkBoxTitTatAllow.isSelected());
			gm.setTit42tats(sv.checkBoxTitTwoTat.isSelected());
			gm.setTit4tatRandom(sv.checkBoxTitTatRandom.isSelected());
			// set strategy weights:
			gm.setOriginalApproach(Double.parseDouble(sv.textField1MInteractions.getText()));
			gm.setFxDefCoop(Double.parseDouble(sv.textField2FixedGenOnly.getText()));
			gm.setT4t(Double.parseDouble(sv.textField3TitTat.getText()));
			gm.setFxChanceOverride(Double.parseDouble(sv.textField4Fixed.getText()));
			gm.setaCoop(Double.parseDouble(sv.textField5AlwaysCoop.getText()));
			gm.setaDef(Double.parseDouble(sv.textField6AlwaysDefect.getText()));
			gm.setT4tRand(Double.parseDouble(sv.textField7TitTatRandom.getText()));
			gm.setT42t(Double.parseDouble(sv.textField8TitTwoTats.getText()));
			// reasons to change strategy:
			gm.setLoosingNeighbour(sv.checkBoxLostConnection.isSelected());
			gm.setLoosingXTimes(sv.checkBoxXTurns.isSelected());
			if (sv.checkBoxXTurns.isSelected()) {
				gm.setLosingXTimesValue(Integer.parseInt(sv.textFieldXTurnsToChange.getText()));
			} else {
				gm.setLosingXTimesValue(0);
			}
			gm.setRandomChange(sv.checkBoxRandomChangeChance.isSelected());
			if(sv.checkBoxRandomChangeChance.isSelected()) {
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
			gm.setSmallWorldK(Integer.parseInt(sv.textFieldSmallWorldK.getText()));
			gm.setSmallWorldRewire(Double.parseDouble(sv.textFieldSmallWorldRewire.getText()));
			
			//once the game settings are set, enable the generate button
			sv.btnGenerate.setEnabled(true);
		}
		//if user will press GENERATE button, generate respective network and extract its data to game model
		if (e.getActionCommand() == Action.GENERATE.name()) {
			if (sv.rdbtnRandom.isSelected()) {
				GenerateRandomStructure rdNetwork = new GenerateRandomStructure();
				
			} else if (sv.rdbtnScalefree.isSelected()) {
				GenerateScaleFreeStructure sfNetwork = new GenerateScaleFreeStructure();

			} else if (sv.rdbtnSmallWorld.isSelected()) {
				smNetwork = new GenerateSmallWorldStructure();
				//check for value update first
				gm.setNodeNumber(Integer.parseInt(sv.textFieldNodesNumber.getText()));
				gm.setSmallWorldK(Integer.parseInt(sv.textFieldSmallWorldK.getText()));
				gm.setSmallWorldRewire(Double.parseDouble(sv.textFieldSmallWorldRewire.getText()));
				
				sv.setNetworkView(smNetwork.generateGraph(gm.getNodeNumber(), gm.getSmallWorldK(), gm.getSmallWorldRewire()));
			}
		}
	}

}
