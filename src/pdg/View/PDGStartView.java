package pdg.View;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.SpringLayout;

import org.jgrapht.Graph;
import org.jgrapht.ListenableGraph;
import org.jgrapht.VertexFactory;
import org.jgrapht.alg.util.IntegerVertexFactory;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.generate.GraphGenerator;
import org.jgrapht.generate.ScaleFreeGraphGenerator;
import org.jgrapht.generate.WattsStrogatzGraphGenerator;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultListenableGraph;
import org.jgrapht.graph.SimpleDirectedGraph;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxRectangle;

import pdg.Controller.GameController;
import pdg.Model.GameModel;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class PDGStartView {

	private enum Action {
		GENERATE, CONFIRM
	}

	GameController controller;
	GameModel model;

	private JFrame frmPdgApplication;
	public JPanel panel;
	public JPanel networkStructureView;

	public JRadioButton rdbtnSmallWorld;
	public JRadioButton rdbtnScalefree;
	public JRadioButton rdbtnRandom;
	public JTextField textFieldRandomChangeChance;
	public JTextField textFieldXTurnsToChange;
	public JTextField textFieldFixChanceOver;
	public JTextField textField1MInteractions;
	public JTextField textField2FixedGenOnly;
	public JTextField textField3TitTat;
	public JTextField textField4Fixed;
	public JTextField textField5AlwaysCoop;
	public JTextField textField6AlwaysDefect;
	public JTextField textField7TitTatRandom;
	public JTextField textField8TitTwoTats;
	public JTextField textFieldTurns;
	public JTextField textFieldMemSpan;
	public JTextField textFieldAlpha;
	public JTextField textFieldCredit;
	public JTextField textFieldFixedCoop;
	public JTextField textFieldNodesNumber;
	public JTextField textFieldRandomMin;
	public JTextField textFieldRandomMax;
	public JTextField textFieldSmallWorldK;
	public JTextField textFieldSmallWorldRewire;
	public JRadioButton rdbtnYes;
	public JRadioButton rdbtnNo;
	public JCheckBox checkBoxRandomChangeChance;
	public JCheckBox checkBoxXTurns;
	public JCheckBox checkBoxLostConnection;
	public JCheckBox checkBoxForce;
	public JCheckBox checkBoxAllowToPlay;
	public JCheckBox checkBoxAllowToShare;
	public JCheckBox chckbxAllowToLie;
	public JCheckBox checkBoxFixCoopForAll;
	public JCheckBox checkBoxOriginalApproach;
	public JCheckBox checkBoxFixedCoopDefaultOnly;
	public JCheckBox checkBoxTitTatAllow;
	public JCheckBox checkBoxFixedOverride;
	public JCheckBox checkBoxAlwaysCoop;
	public JCheckBox checkBoxAlwaysDefect;
	public JCheckBox checkBoxTitTatRandom;
	public JCheckBox checkBoxTitTwoTat;
	public JButton btnConfirm;
	public JButton btnGenerate;

	/**
	 * Create the application.
	 */
	public PDGStartView() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					initialize();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPdgApplication = new JFrame();
		frmPdgApplication.setResizable(false);
		frmPdgApplication.setTitle("PDG Application");
		frmPdgApplication.setBounds(100, 100, 1280, 960);
		frmPdgApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmPdgApplication.setJMenuBar(menuBar);

		JMenu mnProgram = new JMenu("Program");
		menuBar.add(mnProgram);

		JMenuItem mntmSettings = new JMenuItem("General Settings");
		mntmSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout) (panel.getLayout());
				cl.show(panel, "generalSettingsPanel");
			}
		});
		mntmSettings.setEnabled(false);
		mnProgram.add(mntmSettings);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnProgram.add(mntmExit);

		JMenu mnGameSettings = new JMenu("Game");
		menuBar.add(mnGameSettings);

		JMenuItem mntmEditGameSettings = new JMenuItem("Edit Settings");
		mntmEditGameSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout) (panel.getLayout());
				cl.show(panel, "gameSettingsPanel");
			}
		});
		mnGameSettings.add(mntmEditGameSettings);

		JMenuItem mntmLoadGameSettings = new JMenuItem("Load Settings");
		mntmLoadGameSettings.setEnabled(false);
		mnGameSettings.add(mntmLoadGameSettings);

		JMenu mnNetworkSettings = new JMenu("Network");
		menuBar.add(mnNetworkSettings);

		JMenuItem mntmEditNetworkSettings = new JMenuItem("Edit Settings");
		mntmEditNetworkSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout) (panel.getLayout());
				cl.show(panel, "networkSettingsPanel");
			}
		});
		mnNetworkSettings.add(mntmEditNetworkSettings);

		JMenuItem mntmLoadNetwork = new JMenuItem("Load Previous Network");
		mntmLoadNetwork.setEnabled(false);
		mnNetworkSettings.add(mntmLoadNetwork);

		JMenu mnViewer = new JMenu("Viewer");
		menuBar.add(mnViewer);

		JMenuItem mntmViewNetwork = new JMenuItem("Network Structure");
		mntmViewNetwork.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout) (panel.getLayout());
				cl.show(panel, "networkStructurePanel");
			}
		});
		mnViewer.add(mntmViewNetwork);

		JMenuItem mntmViewNetworkStatistics = new JMenuItem("Network Statistics");
		mntmViewNetworkStatistics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout) (panel.getLayout());
				cl.show(panel, "networkStatisticsPanel");
			}
		});
		mnViewer.add(mntmViewNetworkStatistics);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO SHOW ABOUT WINDOW
			}
		});
		mntmAbout.setEnabled(false);
		mnHelp.add(mntmAbout);

		JMenuItem mntmManual = new JMenuItem("Manual");
		mntmManual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO OPEN MANUAL FILE
			}
		});
		mntmManual.setEnabled(false);
		mnHelp.add(mntmManual);

		panel = new JPanel();
		frmPdgApplication.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new CardLayout(0, 0));

		JPanel mainMenuPanel = new JPanel();
		panel.add(mainMenuPanel, "mainMenuPanel");
		SpringLayout sl_mainMenuPanel = new SpringLayout();
		mainMenuPanel.setLayout(sl_mainMenuPanel);

		JLabel lblThisIsNetwork = new JLabel("This is network structure modelling and quality analysis program.  ");
		sl_mainMenuPanel.putConstraint(SpringLayout.NORTH, lblThisIsNetwork, 10, SpringLayout.NORTH, mainMenuPanel);
		sl_mainMenuPanel.putConstraint(SpringLayout.WEST, lblThisIsNetwork, 10, SpringLayout.WEST, mainMenuPanel);
		sl_mainMenuPanel.putConstraint(SpringLayout.EAST, lblThisIsNetwork, 460, SpringLayout.WEST, mainMenuPanel);
		mainMenuPanel.add(lblThisIsNetwork);

		JPanel generalSettings = new JPanel();
		panel.add(generalSettings, "generalSettingsPanel");

		JPanel networkSettings = new JPanel();
		panel.add(networkSettings, "networkSettingsPanel");
		SpringLayout sl_networkSettings = new SpringLayout();
		networkSettings.setLayout(sl_networkSettings);

		JLabel lblAdjustNetworkOptions = new JLabel(
				"Adjust network options and settings, generate a new network to play the game.");
		sl_networkSettings.putConstraint(SpringLayout.NORTH, lblAdjustNetworkOptions, 10, SpringLayout.NORTH,
				networkSettings);
		sl_networkSettings.putConstraint(SpringLayout.WEST, lblAdjustNetworkOptions, 10, SpringLayout.WEST,
				networkSettings);
		lblAdjustNetworkOptions.setFont(new Font("SansSerif", Font.BOLD, 16));
		networkSettings.add(lblAdjustNetworkOptions);

		JLabel lblNodesNumber = new JLabel("Nodes number:");
		lblNodesNumber.setFont(new Font("SansSerif", Font.PLAIN, 14));
		sl_networkSettings.putConstraint(SpringLayout.NORTH, lblNodesNumber, 41, SpringLayout.SOUTH,
				lblAdjustNetworkOptions);
		sl_networkSettings.putConstraint(SpringLayout.WEST, lblNodesNumber, 10, SpringLayout.WEST, networkSettings);
		networkSettings.add(lblNodesNumber);

		textFieldNodesNumber = new JTextField();
		sl_networkSettings.putConstraint(SpringLayout.NORTH, textFieldNodesNumber, -6, SpringLayout.NORTH,
				lblNodesNumber);
		textFieldNodesNumber.setFont(new Font("SansSerif", Font.PLAIN, 14));
		textFieldNodesNumber.setText("50");
		networkSettings.add(textFieldNodesNumber);
		textFieldNodesNumber.setColumns(10);

		JLabel lblTypeOfNetwork = new JLabel("Type of network to generate:");
		sl_networkSettings.putConstraint(SpringLayout.NORTH, lblTypeOfNetwork, 8, SpringLayout.SOUTH,
				textFieldNodesNumber);
		sl_networkSettings.putConstraint(SpringLayout.WEST, lblTypeOfNetwork, 0, SpringLayout.WEST,
				lblAdjustNetworkOptions);
		lblTypeOfNetwork.setFont(new Font("SansSerif", Font.BOLD, 14));
		networkSettings.add(lblTypeOfNetwork);

		rdbtnSmallWorld = new JRadioButton("Small World");
		rdbtnSmallWorld.setSelected(true);
		sl_networkSettings.putConstraint(SpringLayout.NORTH, rdbtnSmallWorld, 7, SpringLayout.SOUTH, lblTypeOfNetwork);
		sl_networkSettings.putConstraint(SpringLayout.WEST, rdbtnSmallWorld, 10, SpringLayout.WEST, lblTypeOfNetwork);
		networkSettings.add(rdbtnSmallWorld);

		rdbtnScalefree = new JRadioButton("Scale-Free");
		sl_networkSettings.putConstraint(SpringLayout.NORTH, rdbtnScalefree, 16, SpringLayout.SOUTH, rdbtnSmallWorld);
		sl_networkSettings.putConstraint(SpringLayout.WEST, rdbtnScalefree, 20, SpringLayout.WEST, networkSettings);
		networkSettings.add(rdbtnScalefree);

		rdbtnRandom = new JRadioButton("Random");
		sl_networkSettings.putConstraint(SpringLayout.WEST, rdbtnRandom, 0, SpringLayout.WEST, rdbtnSmallWorld);
		networkSettings.add(rdbtnRandom);

		// set button group for type of network:
		ButtonGroup networkType = new ButtonGroup();
		networkType.add(rdbtnSmallWorld);
		networkType.add(rdbtnScalefree);
		networkType.add(rdbtnRandom);

		JLabel lblMinNeighbours = new JLabel("Min Neighbours:");
		sl_networkSettings.putConstraint(SpringLayout.WEST, lblMinNeighbours, 32, SpringLayout.EAST, rdbtnRandom);
		sl_networkSettings.putConstraint(SpringLayout.NORTH, rdbtnRandom, 1, SpringLayout.NORTH, lblMinNeighbours);
		sl_networkSettings.putConstraint(SpringLayout.EAST, textFieldNodesNumber, 80, SpringLayout.WEST,
				lblMinNeighbours);
		sl_networkSettings.putConstraint(SpringLayout.WEST, textFieldNodesNumber, 0, SpringLayout.WEST,
				lblMinNeighbours);
		lblMinNeighbours.setFont(new Font("SansSerif", Font.PLAIN, 14));
		networkSettings.add(lblMinNeighbours);

		textFieldRandomMin = new JTextField();
		sl_networkSettings.putConstraint(SpringLayout.NORTH, textFieldRandomMin, -7, SpringLayout.NORTH, rdbtnRandom);
		sl_networkSettings.putConstraint(SpringLayout.WEST, textFieldRandomMin, 6, SpringLayout.EAST, lblMinNeighbours);
		sl_networkSettings.putConstraint(SpringLayout.EAST, textFieldRandomMin, 44, SpringLayout.EAST,
				lblMinNeighbours);
		textFieldRandomMin.setFont(new Font("SansSerif", Font.PLAIN, 14));
		textFieldRandomMin.setText("1");
		textFieldRandomMin.setColumns(10);
		networkSettings.add(textFieldRandomMin);

		JLabel lblMaxNeighbours = new JLabel("Max Neighbours:");
		sl_networkSettings.putConstraint(SpringLayout.NORTH, lblMaxNeighbours, -1, SpringLayout.NORTH, rdbtnRandom);
		sl_networkSettings.putConstraint(SpringLayout.WEST, lblMaxNeighbours, 21, SpringLayout.EAST,
				textFieldRandomMin);
		sl_networkSettings.putConstraint(SpringLayout.EAST, lblMaxNeighbours, 126, SpringLayout.EAST,
				textFieldRandomMin);
		lblMaxNeighbours.setFont(new Font("SansSerif", Font.PLAIN, 14));
		networkSettings.add(lblMaxNeighbours);

		textFieldRandomMax = new JTextField();
		sl_networkSettings.putConstraint(SpringLayout.NORTH, textFieldRandomMax, -7, SpringLayout.NORTH, rdbtnRandom);
		sl_networkSettings.putConstraint(SpringLayout.WEST, textFieldRandomMax, 6, SpringLayout.EAST, lblMaxNeighbours);
		sl_networkSettings.putConstraint(SpringLayout.EAST, textFieldRandomMax, 438, SpringLayout.WEST,
				networkSettings);
		textFieldRandomMax.setFont(new Font("SansSerif", Font.PLAIN, 14));
		textFieldRandomMax.setText("1");
		textFieldRandomMax.setColumns(10);
		networkSettings.add(textFieldRandomMax);

		JLabel lblKneighboursNumber = new JLabel("Node has K neighbours number:");
		sl_networkSettings.putConstraint(SpringLayout.NORTH, lblMinNeighbours, 51, SpringLayout.SOUTH,
				lblKneighboursNumber);
		sl_networkSettings.putConstraint(SpringLayout.WEST, lblKneighboursNumber, 13, SpringLayout.EAST,
				rdbtnSmallWorld);
		lblKneighboursNumber.setFont(new Font("SansSerif", Font.PLAIN, 14));
		sl_networkSettings.putConstraint(SpringLayout.NORTH, lblKneighboursNumber, 6, SpringLayout.SOUTH,
				lblTypeOfNetwork);
		lblKneighboursNumber.setToolTipText("Must be even number, and larger than ln(number of nodes)");
		networkSettings.add(lblKneighboursNumber);

		textFieldSmallWorldK = new JTextField();
		sl_networkSettings.putConstraint(SpringLayout.NORTH, textFieldSmallWorldK, 95, SpringLayout.SOUTH,
				lblAdjustNetworkOptions);
		sl_networkSettings.putConstraint(SpringLayout.WEST, textFieldSmallWorldK, 367, SpringLayout.WEST,
				networkSettings);
		sl_networkSettings.putConstraint(SpringLayout.EAST, lblKneighboursNumber, -6, SpringLayout.WEST,
				textFieldSmallWorldK);
		textFieldSmallWorldK.setFont(new Font("SansSerif", Font.PLAIN, 14));
		textFieldSmallWorldK.setText("4");
		textFieldSmallWorldK.setColumns(10);
		networkSettings.add(textFieldSmallWorldK);

		JLabel lblRewireChance = new JLabel("Rewire chance[0.00 - 1.00]:");
		sl_networkSettings.putConstraint(SpringLayout.NORTH, lblRewireChance, 99, SpringLayout.SOUTH,
				lblAdjustNetworkOptions);
		sl_networkSettings.putConstraint(SpringLayout.EAST, textFieldSmallWorldK, -22, SpringLayout.WEST,
				lblRewireChance);
		sl_networkSettings.putConstraint(SpringLayout.WEST, lblRewireChance, 427, SpringLayout.WEST, networkSettings);
		sl_networkSettings.putConstraint(SpringLayout.EAST, lblRewireChance, 608, SpringLayout.WEST, networkSettings);
		lblRewireChance.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblRewireChance.setToolTipText(
				"Influences the graph final structure by adding or removin connections at given chance");
		networkSettings.add(lblRewireChance);

		textFieldSmallWorldRewire = new JTextField();
		sl_networkSettings.putConstraint(SpringLayout.NORTH, textFieldSmallWorldRewire, 126, SpringLayout.NORTH,
				networkSettings);
		sl_networkSettings.putConstraint(SpringLayout.WEST, textFieldSmallWorldRewire, 6, SpringLayout.EAST,
				lblRewireChance);
		sl_networkSettings.putConstraint(SpringLayout.EAST, textFieldSmallWorldRewire, -590, SpringLayout.EAST,
				networkSettings);
		textFieldSmallWorldRewire.setFont(new Font("SansSerif", Font.PLAIN, 14));
		textFieldSmallWorldRewire.setText("0.75");
		textFieldSmallWorldRewire.setColumns(10);
		networkSettings.add(textFieldSmallWorldRewire);

		btnGenerate = new JButton("Generate");
		btnGenerate.setActionCommand(Action.GENERATE.name());
		btnGenerate.setEnabled(false);
		btnGenerate.addActionListener(controller);

		sl_networkSettings.putConstraint(SpringLayout.SOUTH, btnGenerate, -10, SpringLayout.SOUTH, networkSettings);
		sl_networkSettings.putConstraint(SpringLayout.EAST, btnGenerate, -185, SpringLayout.EAST, networkSettings);
		networkSettings.add(btnGenerate);

		JButton btnDefault_1 = new JButton("Default");
		sl_networkSettings.putConstraint(SpringLayout.NORTH, btnDefault_1, 0, SpringLayout.NORTH, btnGenerate);
		sl_networkSettings.putConstraint(SpringLayout.WEST, btnDefault_1, 6, SpringLayout.EAST, btnGenerate);
		networkSettings.add(btnDefault_1);

		JButton btnSave_1 = new JButton("Save");
		sl_networkSettings.putConstraint(SpringLayout.NORTH, btnSave_1, 0, SpringLayout.NORTH, btnGenerate);
		sl_networkSettings.putConstraint(SpringLayout.WEST, btnSave_1, 6, SpringLayout.EAST, btnDefault_1);
		networkSettings.add(btnSave_1);

		JPanel gameSettings = new JPanel();
		panel.add(gameSettings, "gameSettingsPanel");
		SpringLayout sl_gameSettings = new SpringLayout();
		gameSettings.setLayout(sl_gameSettings);

		JLabel lblAdjustGameOptions = new JLabel("Adjust game options and settings.");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblAdjustGameOptions, 10, SpringLayout.NORTH, gameSettings);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblAdjustGameOptions, 10, SpringLayout.WEST, gameSettings);
		sl_gameSettings.putConstraint(SpringLayout.EAST, lblAdjustGameOptions, 350, SpringLayout.WEST, gameSettings);
		lblAdjustGameOptions.setFont(new Font("SansSerif", Font.BOLD, 16));
		gameSettings.add(lblAdjustGameOptions);

		JLabel lblEnableNodeStrategy = new JLabel("Enable node strategy change:");
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblEnableNodeStrategy, 10, SpringLayout.WEST, gameSettings);
		sl_gameSettings.putConstraint(SpringLayout.EAST, lblEnableNodeStrategy, -1021, SpringLayout.EAST, gameSettings);
		lblEnableNodeStrategy.setFont(new Font("SansSerif", Font.BOLD, 14));
		gameSettings.add(lblEnableNodeStrategy);

		rdbtnYes = new JRadioButton("Yes");
		sl_gameSettings.putConstraint(SpringLayout.SOUTH, lblEnableNodeStrategy, -6, SpringLayout.NORTH, rdbtnYes);
		sl_gameSettings.putConstraint(SpringLayout.NORTH, rdbtnYes, 87, SpringLayout.NORTH, gameSettings);
		sl_gameSettings.putConstraint(SpringLayout.WEST, rdbtnYes, 10, SpringLayout.WEST, gameSettings);
		gameSettings.add(rdbtnYes);

		rdbtnNo = new JRadioButton("No");
		rdbtnNo.setSelected(true);
		sl_gameSettings.putConstraint(SpringLayout.WEST, rdbtnNo, 6, SpringLayout.EAST, rdbtnYes);
		gameSettings.add(rdbtnNo);

		ButtonGroup changeStrategy = new ButtonGroup();
		changeStrategy.add(rdbtnYes);
		changeStrategy.add(rdbtnNo);

		JLabel lblStrategyChangeReason = new JLabel("Strategy change reasons:");
		lblStrategyChangeReason.setFont(new Font("SansSerif", Font.BOLD, 14));
		sl_gameSettings.putConstraint(SpringLayout.SOUTH, rdbtnNo, -6, SpringLayout.NORTH, lblStrategyChangeReason);
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblStrategyChangeReason, 6, SpringLayout.SOUTH, rdbtnYes);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblStrategyChangeReason, 10, SpringLayout.WEST, gameSettings);
		gameSettings.add(lblStrategyChangeReason);

		JLabel lblRandomChance = new JLabel("1. Random chance on each turn");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblRandomChance, 6, SpringLayout.SOUTH,
				lblStrategyChangeReason);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblRandomChance, 0, SpringLayout.WEST, lblAdjustGameOptions);
		lblRandomChance.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lblRandomChance);

		checkBoxRandomChangeChance = new JCheckBox("Allow");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, checkBoxRandomChangeChance, 6, SpringLayout.SOUTH,
				lblRandomChance);
		checkBoxRandomChangeChance.setFont(new Font("SansSerif", Font.PLAIN, 14));
		sl_gameSettings.putConstraint(SpringLayout.WEST, checkBoxRandomChangeChance, 10, SpringLayout.WEST,
				gameSettings);
		checkBoxRandomChangeChance.setEnabled(false);
		gameSettings.add(checkBoxRandomChangeChance);

		JLabel lblChance = new JLabel("Chance for random change");
		lblChance.setToolTipText("Random chance of changing strategy each turn, enter a value between 0.001 and 1.000");
		lblChance.setFont(new Font("SansSerif", Font.PLAIN, 14));
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblChance, 6, SpringLayout.SOUTH, checkBoxRandomChangeChance);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblChance, 10, SpringLayout.WEST, gameSettings);
		gameSettings.add(lblChance);

		textFieldRandomChangeChance = new JTextField();
		sl_gameSettings.putConstraint(SpringLayout.NORTH, textFieldRandomChangeChance, -4, SpringLayout.NORTH,
				lblChance);
		sl_gameSettings.putConstraint(SpringLayout.WEST, textFieldRandomChangeChance, 17, SpringLayout.EAST, lblChance);
		sl_gameSettings.putConstraint(SpringLayout.EAST, textFieldRandomChangeChance, 60, SpringLayout.EAST, lblChance);
		textFieldRandomChangeChance.setEnabled(false);
		textFieldRandomChangeChance.setText("0.01");
		gameSettings.add(textFieldRandomChangeChance);
		textFieldRandomChangeChance.setColumns(10);

		JLabel lblLosingOverX = new JLabel("2. Losing over X number of turns");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblLosingOverX, 6, SpringLayout.SOUTH, lblChance);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblLosingOverX, 0, SpringLayout.WEST, lblAdjustGameOptions);
		sl_gameSettings.putConstraint(SpringLayout.EAST, lblLosingOverX, 206, SpringLayout.WEST, lblAdjustGameOptions);
		lblLosingOverX.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lblLosingOverX);

		checkBoxXTurns = new JCheckBox("Allow");
		sl_gameSettings.putConstraint(SpringLayout.WEST, checkBoxXTurns, 0, SpringLayout.WEST, lblAdjustGameOptions);
		checkBoxXTurns.setFont(new Font("SansSerif", Font.PLAIN, 14));
		checkBoxXTurns.setEnabled(false);
		gameSettings.add(checkBoxXTurns);

		JLabel lblTurnsToChange = new JLabel("Turns to change strategy");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblTurnsToChange, 261, SpringLayout.NORTH, gameSettings);
		sl_gameSettings.putConstraint(SpringLayout.SOUTH, checkBoxXTurns, -6, SpringLayout.NORTH, lblTurnsToChange);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblTurnsToChange, 0, SpringLayout.WEST, lblAdjustGameOptions);
		lblTurnsToChange.setToolTipText("Lost number of turns after which a new strategy will be considered");
		lblTurnsToChange.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lblTurnsToChange);

		textFieldXTurnsToChange = new JTextField();
		sl_gameSettings.putConstraint(SpringLayout.NORTH, textFieldXTurnsToChange, -4, SpringLayout.NORTH,
				lblTurnsToChange);
		sl_gameSettings.putConstraint(SpringLayout.WEST, textFieldXTurnsToChange, 31, SpringLayout.EAST,
				lblTurnsToChange);
		sl_gameSettings.putConstraint(SpringLayout.EAST, textFieldXTurnsToChange, 74, SpringLayout.EAST,
				lblTurnsToChange);
		textFieldXTurnsToChange.setText("10");
		textFieldXTurnsToChange.setEnabled(false);
		textFieldXTurnsToChange.setColumns(10);
		gameSettings.add(textFieldXTurnsToChange);

		JLabel lblLostConnection = new JLabel("3. Lost connection with a neighbour node");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblLostConnection, 14, SpringLayout.SOUTH,
				textFieldXTurnsToChange);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblLostConnection, 0, SpringLayout.WEST, lblAdjustGameOptions);
		lblLostConnection.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lblLostConnection);

		checkBoxLostConnection = new JCheckBox("Allow");
		checkBoxLostConnection.setToolTipText(
				"If trust value between 2 nodes will drop to 0 the active node will change its strategy played.");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, checkBoxLostConnection, 6, SpringLayout.SOUTH,
				lblLostConnection);
		sl_gameSettings.putConstraint(SpringLayout.WEST, checkBoxLostConnection, 0, SpringLayout.WEST,
				lblAdjustGameOptions);
		checkBoxLostConnection.setFont(new Font("SansSerif", Font.PLAIN, 14));
		checkBoxLostConnection.setEnabled(false);
		gameSettings.add(checkBoxLostConnection);

		JLabel lblNodePlayStrategy = new JLabel("Node play strategy settings:");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblNodePlayStrategy, 0, SpringLayout.NORTH,
				lblEnableNodeStrategy);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblNodePlayStrategy, 122, SpringLayout.EAST,
				lblEnableNodeStrategy);
		lblNodePlayStrategy.setFont(new Font("SansSerif", Font.BOLD, 14));
		gameSettings.add(lblNodePlayStrategy);

		JLabel lblForceNodes = new JLabel("1. Force nodes to play each turn");
		lblForceNodes
				.setToolTipText("It will cause nodes to continue playing within neighbourhood no matter on results.");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblForceNodes, -1, SpringLayout.NORTH, rdbtnYes);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblForceNodes, 0, SpringLayout.WEST, lblNodePlayStrategy);
		lblForceNodes.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lblForceNodes);

		checkBoxForce = new JCheckBox("Force");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, checkBoxForce, 0, SpringLayout.NORTH,
				lblStrategyChangeReason);
		sl_gameSettings.putConstraint(SpringLayout.WEST, checkBoxForce, 0, SpringLayout.WEST, lblNodePlayStrategy);
		checkBoxForce.setSelected(true);
		checkBoxForce
				.setToolTipText("It will cause nodes to continue playing within neighbourhood no matter on results");
		checkBoxForce.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(checkBoxForce);

		JLabel lblNodesCan = new JLabel("2. Nodes can play outside their neighbourhood");
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblNodesCan, 0, SpringLayout.WEST, lblNodePlayStrategy);
		sl_gameSettings.putConstraint(SpringLayout.SOUTH, lblNodesCan, 0, SpringLayout.SOUTH, lblRandomChance);
		lblNodesCan.setToolTipText(
				"If first option was also selected it will cause nodes to force them to play with someone outside the neighbourhood with a chance proportional to the missing weights from within their current neighbourhood.");
		lblNodesCan.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lblNodesCan);

		checkBoxAllowToPlay = new JCheckBox("Allow to play");
		checkBoxAllowToPlay.setEnabled(false);
		checkBoxAllowToPlay.setToolTipText(
				"If first option was also selected it will cause nodes to force them to play with someone outside the neighbourhood with a chance proportional to the missing weights from within their current neighbourhood. Otherwise the node will have an increasing chance to select non-neighbour given memory span and number of losses.");
		sl_gameSettings.putConstraint(SpringLayout.WEST, checkBoxAllowToPlay, 0, SpringLayout.WEST,
				lblNodePlayStrategy);
		sl_gameSettings.putConstraint(SpringLayout.SOUTH, checkBoxAllowToPlay, 0, SpringLayout.SOUTH,
				checkBoxRandomChangeChance);
		checkBoxAllowToPlay.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(checkBoxAllowToPlay);

		JSeparator separator = new JSeparator();
		sl_gameSettings.putConstraint(SpringLayout.NORTH, separator, 6, SpringLayout.SOUTH, checkBoxLostConnection);
		sl_gameSettings.putConstraint(SpringLayout.WEST, separator, 10, SpringLayout.WEST, gameSettings);
		sl_gameSettings.putConstraint(SpringLayout.SOUTH, separator, 8, SpringLayout.SOUTH, checkBoxLostConnection);
		sl_gameSettings.putConstraint(SpringLayout.EAST, separator, 754, SpringLayout.WEST, gameSettings);
		gameSettings.add(separator);

		JLabel lblNodePlayableStrategies = new JLabel("Node playable strategies and distribution:");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblNodePlayableStrategies, 6, SpringLayout.SOUTH, separator);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblNodePlayableStrategies, 0, SpringLayout.WEST,
				lblAdjustGameOptions);
		lblNodePlayableStrategies.setFont(new Font("SansSerif", Font.BOLD, 14));
		gameSettings.add(lblNodePlayableStrategies);

		JLabel lblTotalRandom = new JLabel("1. Based on m previous interactions and default cooperation chance");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblTotalRandom, 6, SpringLayout.SOUTH,
				lblNodePlayableStrategies);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblTotalRandom, 0, SpringLayout.WEST, lblAdjustGameOptions);
		lblTotalRandom.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lblTotalRandom);

		checkBoxOriginalApproach = new JCheckBox("Allow");
		checkBoxOriginalApproach.setSelected(true);
		checkBoxOriginalApproach.setFont(new Font("SansSerif", Font.PLAIN, 14));
		sl_gameSettings.putConstraint(SpringLayout.NORTH, checkBoxOriginalApproach, 6, SpringLayout.SOUTH,
				lblTotalRandom);
		sl_gameSettings.putConstraint(SpringLayout.WEST, checkBoxOriginalApproach, 10, SpringLayout.WEST, gameSettings);
		gameSettings.add(checkBoxOriginalApproach);

		JLabel lblFixedCoop = new JLabel("2. Fixed generated default cooperation likehood chance");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblFixedCoop, 6, SpringLayout.SOUTH,
				checkBoxOriginalApproach);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblFixedCoop, 0, SpringLayout.WEST, lblAdjustGameOptions);
		lblFixedCoop.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lblFixedCoop);

		checkBoxFixedCoopDefaultOnly = new JCheckBox("Allow");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, checkBoxFixedCoopDefaultOnly, 6, SpringLayout.SOUTH,
				lblFixedCoop);
		sl_gameSettings.putConstraint(SpringLayout.WEST, checkBoxFixedCoopDefaultOnly, 0, SpringLayout.WEST,
				lblAdjustGameOptions);
		checkBoxFixedCoopDefaultOnly.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(checkBoxFixedCoopDefaultOnly);

		JLabel lbltitFor = new JLabel("3. Pure \"Tit for tat\" strategy");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lbltitFor, 6, SpringLayout.SOUTH,
				checkBoxFixedCoopDefaultOnly);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lbltitFor, 0, SpringLayout.WEST, lblAdjustGameOptions);
		lbltitFor.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lbltitFor);

		textFieldFixChanceOver = new JTextField();
		textFieldFixChanceOver.setEnabled(false);
		gameSettings.add(textFieldFixChanceOver);
		textFieldFixChanceOver.setColumns(10);

		checkBoxTitTatAllow = new JCheckBox("Allow");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, checkBoxTitTatAllow, 6, SpringLayout.SOUTH, lbltitFor);
		sl_gameSettings.putConstraint(SpringLayout.WEST, checkBoxTitTatAllow, 0, SpringLayout.WEST,
				lblAdjustGameOptions);
		checkBoxTitTatAllow.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(checkBoxTitTatAllow);

		JLabel label = new JLabel("Fixed chance [0.000 - 1.000]");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, textFieldFixChanceOver, -4, SpringLayout.NORTH, label);
		sl_gameSettings.putConstraint(SpringLayout.WEST, textFieldFixChanceOver, 9, SpringLayout.EAST, label);
		sl_gameSettings.putConstraint(SpringLayout.WEST, label, 0, SpringLayout.WEST, lblAdjustGameOptions);
		label.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(label);

		JLabel lblFixedChance = new JLabel("4. Fixed chance (overrides node default cooperation chance)");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblFixedChance, 6, SpringLayout.SOUTH, checkBoxTitTatAllow);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblFixedChance, 0, SpringLayout.WEST, lblAdjustGameOptions);
		lblFixedChance.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lblFixedChance);

		checkBoxFixedOverride = new JCheckBox("Allow");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, label, 6, SpringLayout.SOUTH, checkBoxFixedOverride);
		sl_gameSettings.putConstraint(SpringLayout.NORTH, checkBoxFixedOverride, 6, SpringLayout.SOUTH, lblFixedChance);
		sl_gameSettings.putConstraint(SpringLayout.WEST, checkBoxFixedOverride, 0, SpringLayout.WEST,
				lblAdjustGameOptions);
		checkBoxFixedOverride.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(checkBoxFixedOverride);

		JLabel lblAlwaysCooperate = new JLabel("5. Always cooperate");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblAlwaysCooperate, 11, SpringLayout.SOUTH, label);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblAlwaysCooperate, 0, SpringLayout.WEST,
				lblAdjustGameOptions);
		lblAlwaysCooperate.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lblAlwaysCooperate);

		checkBoxAlwaysCoop = new JCheckBox("Allow");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, checkBoxAlwaysCoop, 6, SpringLayout.SOUTH,
				lblAlwaysCooperate);
		sl_gameSettings.putConstraint(SpringLayout.WEST, checkBoxAlwaysCoop, 0, SpringLayout.WEST,
				lblAdjustGameOptions);
		checkBoxAlwaysCoop.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(checkBoxAlwaysCoop);

		JLabel lblAlwaysDefect = new JLabel("6. Always Defect");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblAlwaysDefect, 6, SpringLayout.SOUTH, checkBoxAlwaysCoop);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblAlwaysDefect, 0, SpringLayout.WEST, lblAdjustGameOptions);
		lblAlwaysDefect.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lblAlwaysDefect);

		checkBoxAlwaysDefect = new JCheckBox("Allow");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, checkBoxAlwaysDefect, 6, SpringLayout.SOUTH, lblAlwaysDefect);
		sl_gameSettings.putConstraint(SpringLayout.WEST, checkBoxAlwaysDefect, 0, SpringLayout.WEST,
				lblAdjustGameOptions);
		checkBoxAlwaysDefect.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(checkBoxAlwaysDefect);

		JLabel lblNodesShare = new JLabel("3. Nodes share neighbours if similar trust and positive interactions");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblNodesShare, 0, SpringLayout.NORTH, lblChance);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblNodesShare, 0, SpringLayout.WEST, lblNodePlayStrategy);
		lblNodesShare.setToolTipText(
				"If first option was also selected it will cause nodes to force them to play with someone outside the neighbourhood with a chance proportional to the missing weights from within their current neighbourhood.");
		lblNodesShare.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lblNodesShare);

		checkBoxAllowToShare = new JCheckBox("Allow to share neighbours ");
		checkBoxAllowToShare.setEnabled(false);
		sl_gameSettings.putConstraint(SpringLayout.NORTH, checkBoxAllowToShare, 6, SpringLayout.SOUTH, lblNodesShare);
		sl_gameSettings.putConstraint(SpringLayout.WEST, checkBoxAllowToShare, 0, SpringLayout.WEST,
				lblNodePlayStrategy);
		checkBoxAllowToShare.setToolTipText("");
		checkBoxAllowToShare.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(checkBoxAllowToShare);

		JLabel lblNodesCan_1 = new JLabel("4. Nodes can lie about neighbours (based on previous m interactions)");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblNodesCan_1, 6, SpringLayout.SOUTH, checkBoxAllowToShare);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblNodesCan_1, 0, SpringLayout.WEST, lblNodePlayStrategy);
		lblNodesCan_1.setToolTipText(
				"If first option was also selected it will cause nodes to force them to play with someone outside the neighbourhood with a chance proportional to the missing weights from within their current neighbourhood.");
		lblNodesCan_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lblNodesCan_1);

		chckbxAllowToLie = new JCheckBox("Allow to lie");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, chckbxAllowToLie, 7, SpringLayout.SOUTH, lblNodesCan_1);
		sl_gameSettings.putConstraint(SpringLayout.WEST, chckbxAllowToLie, 0, SpringLayout.WEST, lblNodePlayStrategy);
		chckbxAllowToLie.setToolTipText("");
		chckbxAllowToLie.setFont(new Font("SansSerif", Font.PLAIN, 14));
		chckbxAllowToLie.setEnabled(false);
		gameSettings.add(chckbxAllowToLie);

		JLabel lbltitFor_1 = new JLabel("7. \"Tit for tat\" and Random");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lbltitFor_1, 6, SpringLayout.SOUTH, checkBoxAlwaysDefect);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lbltitFor_1, 0, SpringLayout.WEST, lblAdjustGameOptions);
		lbltitFor_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lbltitFor_1);

		checkBoxTitTatRandom = new JCheckBox("Allow");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, checkBoxTitTatRandom, 6, SpringLayout.SOUTH, lbltitFor_1);
		sl_gameSettings.putConstraint(SpringLayout.WEST, checkBoxTitTatRandom, 0, SpringLayout.WEST,
				lblAdjustGameOptions);
		checkBoxTitTatRandom.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(checkBoxTitTatRandom);

		JLabel lbltitFor_2 = new JLabel("8. \"Tit for Two Tats\"");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lbltitFor_2, 6, SpringLayout.SOUTH, checkBoxTitTatRandom);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lbltitFor_2, 0, SpringLayout.WEST, lblAdjustGameOptions);
		lbltitFor_2.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lbltitFor_2);

		checkBoxTitTwoTat = new JCheckBox("Allow");
		checkBoxTitTwoTat.setEnabled(false);
		sl_gameSettings.putConstraint(SpringLayout.NORTH, checkBoxTitTwoTat, 6, SpringLayout.SOUTH, lbltitFor_2);
		sl_gameSettings.putConstraint(SpringLayout.WEST, checkBoxTitTwoTat, 0, SpringLayout.WEST, lblAdjustGameOptions);
		checkBoxTitTwoTat.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(checkBoxTitTwoTat);

		JSeparator separator_1 = new JSeparator();
		sl_gameSettings.putConstraint(SpringLayout.EAST, textFieldFixChanceOver, -203, SpringLayout.WEST, separator_1);
		sl_gameSettings.putConstraint(SpringLayout.NORTH, separator_1, 31, SpringLayout.SOUTH, separator);
		sl_gameSettings.putConstraint(SpringLayout.WEST, separator_1, 6, SpringLayout.EAST, lblTotalRandom);
		sl_gameSettings.putConstraint(SpringLayout.SOUTH, separator_1, 471, SpringLayout.SOUTH, separator);
		sl_gameSettings.putConstraint(SpringLayout.EAST, separator_1, 8, SpringLayout.EAST, lblTotalRandom);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		gameSettings.add(separator_1);

		JLabel lblNewLabel = new JLabel("Distribution (weights)");
		lblNewLabel.setToolTipText(
				"Allowed strategies will be distributed among the nodes on generation. Type 0 to not include the strategy initially, it can show up during game still.");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblNewLabel, 6, SpringLayout.SOUTH, separator);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblNewLabel, 153, SpringLayout.EAST,
				lblNodePlayableStrategies);
		gameSettings.add(lblNewLabel);

		textField1MInteractions = new JTextField();
		sl_gameSettings.putConstraint(SpringLayout.NORTH, textField1MInteractions, -4, SpringLayout.NORTH,
				checkBoxOriginalApproach);
		sl_gameSettings.putConstraint(SpringLayout.WEST, textField1MInteractions, 28, SpringLayout.EAST, separator_1);
		sl_gameSettings.putConstraint(SpringLayout.EAST, textField1MInteractions, 95, SpringLayout.EAST, separator_1);
		textField1MInteractions.setText("1");
		gameSettings.add(textField1MInteractions);
		textField1MInteractions.setColumns(10);

		textField2FixedGenOnly = new JTextField();
		sl_gameSettings.putConstraint(SpringLayout.NORTH, textField2FixedGenOnly, -4, SpringLayout.NORTH,
				checkBoxFixedCoopDefaultOnly);
		sl_gameSettings.putConstraint(SpringLayout.WEST, textField2FixedGenOnly, 28, SpringLayout.EAST, separator_1);
		sl_gameSettings.putConstraint(SpringLayout.EAST, textField2FixedGenOnly, 95, SpringLayout.EAST, separator_1);
		textField2FixedGenOnly.setEnabled(false);
		textField2FixedGenOnly.setText("0");
		textField2FixedGenOnly.setColumns(10);
		gameSettings.add(textField2FixedGenOnly);

		textField3TitTat = new JTextField();
		sl_gameSettings.putConstraint(SpringLayout.NORTH, textField3TitTat, -4, SpringLayout.NORTH,
				checkBoxTitTatAllow);
		sl_gameSettings.putConstraint(SpringLayout.WEST, textField3TitTat, 28, SpringLayout.EAST, separator_1);
		sl_gameSettings.putConstraint(SpringLayout.EAST, textField3TitTat, 95, SpringLayout.EAST, separator_1);
		textField3TitTat.setText("0");
		textField3TitTat.setEnabled(false);
		textField3TitTat.setColumns(10);
		gameSettings.add(textField3TitTat);

		textField4Fixed = new JTextField();
		sl_gameSettings.putConstraint(SpringLayout.NORTH, textField4Fixed, -4, SpringLayout.NORTH,
				checkBoxFixedOverride);
		sl_gameSettings.putConstraint(SpringLayout.WEST, textField4Fixed, 28, SpringLayout.EAST, separator_1);
		sl_gameSettings.putConstraint(SpringLayout.EAST, textField4Fixed, 95, SpringLayout.EAST, separator_1);
		textField4Fixed.setText("0");
		textField4Fixed.setEnabled(false);
		textField4Fixed.setColumns(10);
		gameSettings.add(textField4Fixed);

		textField5AlwaysCoop = new JTextField();
		sl_gameSettings.putConstraint(SpringLayout.NORTH, textField5AlwaysCoop, -4, SpringLayout.NORTH,
				checkBoxAlwaysCoop);
		sl_gameSettings.putConstraint(SpringLayout.WEST, textField5AlwaysCoop, 28, SpringLayout.EAST, separator_1);
		sl_gameSettings.putConstraint(SpringLayout.EAST, textField5AlwaysCoop, 95, SpringLayout.EAST, separator_1);
		textField5AlwaysCoop.setText("0");
		textField5AlwaysCoop.setEnabled(false);
		textField5AlwaysCoop.setColumns(10);
		gameSettings.add(textField5AlwaysCoop);

		textField6AlwaysDefect = new JTextField();
		sl_gameSettings.putConstraint(SpringLayout.NORTH, textField6AlwaysDefect, -4, SpringLayout.NORTH,
				checkBoxAlwaysDefect);
		sl_gameSettings.putConstraint(SpringLayout.WEST, textField6AlwaysDefect, 28, SpringLayout.EAST, separator_1);
		sl_gameSettings.putConstraint(SpringLayout.EAST, textField6AlwaysDefect, 95, SpringLayout.EAST, separator_1);
		textField6AlwaysDefect.setText("0");
		textField6AlwaysDefect.setEnabled(false);
		textField6AlwaysDefect.setColumns(10);
		gameSettings.add(textField6AlwaysDefect);

		textField7TitTatRandom = new JTextField();
		sl_gameSettings.putConstraint(SpringLayout.NORTH, textField7TitTatRandom, -4, SpringLayout.NORTH,
				checkBoxTitTatRandom);
		sl_gameSettings.putConstraint(SpringLayout.WEST, textField7TitTatRandom, 28, SpringLayout.EAST, separator_1);
		sl_gameSettings.putConstraint(SpringLayout.EAST, textField7TitTatRandom, 95, SpringLayout.EAST, separator_1);
		textField7TitTatRandom.setText("0");
		textField7TitTatRandom.setEnabled(false);
		textField7TitTatRandom.setColumns(10);
		gameSettings.add(textField7TitTatRandom);

		textField8TitTwoTats = new JTextField();
		sl_gameSettings.putConstraint(SpringLayout.NORTH, textField8TitTwoTats, -4, SpringLayout.NORTH,
				checkBoxTitTwoTat);
		sl_gameSettings.putConstraint(SpringLayout.WEST, textField8TitTwoTats, 28, SpringLayout.EAST, separator_1);
		sl_gameSettings.putConstraint(SpringLayout.EAST, textField8TitTwoTats, 95, SpringLayout.EAST, separator_1);
		textField8TitTwoTats.setText("0");
		textField8TitTwoTats.setEnabled(false);
		textField8TitTwoTats.setColumns(10);
		gameSettings.add(textField8TitTwoTats);

		btnConfirm = new JButton("Confirm");
		sl_gameSettings.putConstraint(SpringLayout.EAST, btnConfirm, 1072, SpringLayout.WEST, gameSettings);
		btnConfirm.setActionCommand(Action.CONFIRM.name());
		btnConfirm.addActionListener(controller);
		gameSettings.add(btnConfirm);

		JButton btnSave = new JButton("Save");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, btnConfirm, 0, SpringLayout.NORTH, btnSave);
		sl_gameSettings.putConstraint(SpringLayout.SOUTH, btnSave, -10, SpringLayout.SOUTH, gameSettings);
		btnSave.setEnabled(false);
		gameSettings.add(btnSave);

		JButton btnDefault = new JButton("Default");
		sl_gameSettings.putConstraint(SpringLayout.WEST, btnSave, 6, SpringLayout.EAST, btnDefault);
		sl_gameSettings.putConstraint(SpringLayout.SOUTH, btnDefault, -10, SpringLayout.SOUTH, gameSettings);
		btnDefault.setEnabled(false);
		sl_gameSettings.putConstraint(SpringLayout.WEST, btnDefault, 1076, SpringLayout.WEST, gameSettings);
		gameSettings.add(btnDefault);

		JLabel lblGameSettings = new JLabel("Game settings:");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblGameSettings, 0, SpringLayout.NORTH,
				lblNodePlayableStrategies);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblGameSettings, 149, SpringLayout.EAST, lblNewLabel);
		lblGameSettings.setFont(new Font("SansSerif", Font.BOLD, 14));
		gameSettings.add(lblGameSettings);

		JLabel lblNumberOfTurns = new JLabel("Number of turns:");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblNumberOfTurns, 0, SpringLayout.NORTH, lblTotalRandom);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblNumberOfTurns, 0, SpringLayout.WEST, lblGameSettings);
		lblNumberOfTurns.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lblNumberOfTurns);

		JLabel lblNodeMemorySpan = new JLabel("Node memory span:");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblNodeMemorySpan, 0, SpringLayout.NORTH, lblFixedCoop);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblNodeMemorySpan, 0, SpringLayout.WEST, lblGameSettings);
		lblNodeMemorySpan.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lblNodeMemorySpan);

		JLabel lblDefaultTrustFactor = new JLabel("Default trust factor (alpha):");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblDefaultTrustFactor, 0, SpringLayout.NORTH, lbltitFor);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblDefaultTrustFactor, 0, SpringLayout.WEST, lblGameSettings);
		lblDefaultTrustFactor.setToolTipText(
				"the higher this factor is set, the less likely the node will cooperate with less trustworthy nodes.");
		lblDefaultTrustFactor.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lblDefaultTrustFactor);

		JLabel lblBaseTrustCredit = new JLabel("Base trust credit:");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblBaseTrustCredit, 0, SpringLayout.NORTH, lblFixedChance);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblBaseTrustCredit, 0, SpringLayout.WEST, lblGameSettings);
		lblBaseTrustCredit.setToolTipText("Experimental, should be at least equal to memory span.");
		lblBaseTrustCredit.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lblBaseTrustCredit);

		JLabel lblCooperationValue = new JLabel("Fix cooperation value:");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblCooperationValue, 31, SpringLayout.SOUTH,
				lblBaseTrustCredit);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblCooperationValue, 0, SpringLayout.WEST, lblGameSettings);
		lblCooperationValue.setToolTipText("Random value assigned on generation otherwise.");
		lblCooperationValue.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lblCooperationValue);

		checkBoxFixCoopForAll = new JCheckBox("Yes");
		sl_gameSettings.putConstraint(SpringLayout.WEST, btnConfirm, 0, SpringLayout.WEST, checkBoxFixCoopForAll);
		sl_gameSettings.putConstraint(SpringLayout.NORTH, checkBoxFixCoopForAll, 4, SpringLayout.NORTH,
				textFieldFixChanceOver);
		sl_gameSettings.putConstraint(SpringLayout.WEST, checkBoxFixCoopForAll, 105, SpringLayout.EAST,
				lblCooperationValue);
		checkBoxFixCoopForAll.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(checkBoxFixCoopForAll);

		JLabel lblFixedValue = new JLabel("Fixed value:");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblFixedValue, 31, SpringLayout.SOUTH, lblCooperationValue);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblFixedValue, 0, SpringLayout.WEST, lblGameSettings);
		lblFixedValue.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lblFixedValue);

		textFieldTurns = new JTextField();
		sl_gameSettings.putConstraint(SpringLayout.NORTH, textFieldTurns, -4, SpringLayout.NORTH, lblTotalRandom);
		sl_gameSettings.putConstraint(SpringLayout.WEST, textFieldTurns, 139, SpringLayout.EAST, lblNumberOfTurns);
		sl_gameSettings.putConstraint(SpringLayout.EAST, textFieldTurns, -192, SpringLayout.EAST, gameSettings);
		textFieldTurns.setText("1000");
		gameSettings.add(textFieldTurns);
		textFieldTurns.setColumns(10);

		textFieldMemSpan = new JTextField();
		sl_gameSettings.putConstraint(SpringLayout.NORTH, textFieldMemSpan, -4, SpringLayout.NORTH, lblFixedCoop);
		sl_gameSettings.putConstraint(SpringLayout.WEST, textFieldMemSpan, 118, SpringLayout.EAST, lblNodeMemorySpan);
		sl_gameSettings.putConstraint(SpringLayout.EAST, textFieldMemSpan, -192, SpringLayout.EAST, gameSettings);
		textFieldMemSpan.setText("10");
		textFieldMemSpan.setColumns(10);
		gameSettings.add(textFieldMemSpan);

		textFieldAlpha = new JTextField();
		sl_gameSettings.putConstraint(SpringLayout.NORTH, textFieldAlpha, -4, SpringLayout.NORTH, lbltitFor);
		sl_gameSettings.putConstraint(SpringLayout.WEST, textFieldAlpha, 74, SpringLayout.EAST, lblDefaultTrustFactor);
		sl_gameSettings.putConstraint(SpringLayout.EAST, textFieldAlpha, -192, SpringLayout.EAST, gameSettings);
		textFieldAlpha.setText("0.5");
		textFieldAlpha.setColumns(10);
		gameSettings.add(textFieldAlpha);

		textFieldCredit = new JTextField();
		sl_gameSettings.putConstraint(SpringLayout.NORTH, textFieldCredit, -4, SpringLayout.NORTH, lblFixedChance);
		sl_gameSettings.putConstraint(SpringLayout.WEST, textFieldCredit, 137, SpringLayout.EAST, lblBaseTrustCredit);
		sl_gameSettings.putConstraint(SpringLayout.EAST, textFieldCredit, -192, SpringLayout.EAST, gameSettings);
		textFieldCredit.setText("10");
		textFieldCredit.setColumns(10);
		gameSettings.add(textFieldCredit);

		textFieldFixedCoop = new JTextField();
		sl_gameSettings.putConstraint(SpringLayout.NORTH, textFieldFixedCoop, -4, SpringLayout.NORTH,
				checkBoxAlwaysCoop);
		sl_gameSettings.putConstraint(SpringLayout.WEST, textFieldFixedCoop, 168, SpringLayout.EAST, lblFixedValue);
		sl_gameSettings.putConstraint(SpringLayout.EAST, textFieldFixedCoop, -192, SpringLayout.EAST, gameSettings);
		textFieldFixedCoop.setEnabled(false);
		textFieldFixedCoop.setColumns(10);
		gameSettings.add(textFieldFixedCoop);

		JPanel networkStructure = new JPanel();
		panel.add(networkStructure, "networkStructurePanel");
		networkStructure.setLayout(null);

		// TODO add buttons with zoom in/out and swapping the layout for the network
		// view:
		JPanel networkStructureMenu = new JPanel();
		networkStructureMenu.setBounds(0, 0, 256, 892);
		networkStructure.add(networkStructureMenu);
		networkStructureMenu.setLayout(null);

		networkStructureView = new JPanel();
		networkStructureView.setLayout(new BorderLayout());
		networkStructureView.setBounds(257, 0, 1001, 892);
		networkStructureView.setPreferredSize(frmPdgApplication.getContentPane().getSize());
		networkStructure.add(networkStructureView);

		JPanel networkStatistics = new JPanel();
		panel.add(networkStatistics, "networkStatisticsPanel");
		networkStatistics.setLayout(new SpringLayout());

		frmPdgApplication.setVisible(true);

	}

	public void addController(GameController controller) {
		// add controller to all buttons etc. button.addActionListener(controller);
		this.controller = controller;
	}

	public void setNetworkView(mxGraphComponent graph) {
		networkStructureView.removeAll();
		networkStructureView.add(graph);
		networkStructureView.revalidate();
		networkStructureView.repaint();
	}

	public void addModel(GameModel model) {
		this.model = model;
	}
}
