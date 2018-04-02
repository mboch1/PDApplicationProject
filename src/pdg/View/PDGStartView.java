package pdg.View;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.SpringLayout;
import com.mxgraph.swing.mxGraphComponent;
import pdg.Controller.GameController;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Dimension;

public class PDGStartView {

	private enum Action {
		GENERATE, CONFIRM, GAME_START, ZOOM_IN, ZOOM_OUT, GAME_SETTINGS, RESULTS_ZOOM_OUT, RESULTS_ZOOM_IN, BACK_TO_VIEW, CIRCLE, HIERARCHICAL, ORGANIC, VIEW_RESULT_NETWORK
	}

	private GameController controller;
	private mxGraphComponent graph;
	private mxGraphComponent results;
	private JFrame frmPdgApplication;

	public JPanel panel;
	public JPanel networkStructureView;
	public JPanel resultsStructureView;

	public JRadioButton rdbtnSmallWorld;
	public JRadioButton rdbtnScalefree;
	public JRadioButton rdbtnRandom;
	public JRadioButton rdbtnYes;
	public JRadioButton rdbtnNo;

	public JTextField textFieldRandomChangeChance;
	public JTextField textField1MInteractions;
	public JTextField textField3TitTat;
	public JTextField textField7Random;
	public JTextField textField8TitTwoTats;
	public JTextField textFieldTurns;
	public JTextField textFieldMemSpan;
	public JTextField textFieldAlpha;
	public JTextField textFieldCredit;
	public JTextField textFieldFixedCoop;
	public JTextField textFieldNodesNumber;
	public JTextField textFieldTotalEdges;
	public JTextField textFieldSmallWorldK;
	public JTextField textFieldSmallWorldRewire;
	public JTextField textFieldSeed;
	public JTextField textFieldGainIncrease;
	public JTextField textFieldGainDecrease;
	public JTextField textFieldRandomChance;

	public JCheckBox checkBoxRandomChangeChance;
	public JCheckBox checkBoxLostConnection;
	public JCheckBox checkBoxForce;
	public JCheckBox checkBoxFixCoopForAll;
	public JCheckBox checkBoxOriginalApproach;
	public JCheckBox checkBoxTitTatAllow;
	public JCheckBox checkBoxRandom;
	public JCheckBox checkBoxTitTwoTat;
	public JCheckBox chckbxAllowForgive;

	public JButton btnPlay;
	public JButton btnConfirm;
	public JButton btnGenerate;
	public JButton btnIn;
	public JButton btnOut;
	public JButton btnNext;
	public JButton btnInResults;
	public JButton btnOutResults;
	public JButton btnApplyHierarchical;
	public JButton btnApplyOrganic;
	public JButton btnApplyCircle;
	public JButton btnBackToView;
	public JButton btnResults;

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

		JMenuItem mntmResultsNetwork = new JMenuItem("Results Network");
		mntmResultsNetwork.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (panel.getLayout());
				cl.show(panel, "resultsPanel");
			}
		});
		mnViewer.add(mntmResultsNetwork);

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

		panel = new JPanel();
		frmPdgApplication.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new CardLayout(0, 0));

		JPanel mainMenuPanel = new JPanel();
		panel.add(mainMenuPanel, "mainMenuPanel");
		SpringLayout sl_mainMenuPanel = new SpringLayout();
		mainMenuPanel.setLayout(sl_mainMenuPanel);

		JLabel lblIteratedPrisonersDillema = new JLabel("Iterated Prisoner's Dillema Game Application");
		lblIteratedPrisonersDillema.setFont(new Font("Arial", Font.BOLD, 18));
		sl_mainMenuPanel.putConstraint(SpringLayout.NORTH, lblIteratedPrisonersDillema, 10, SpringLayout.NORTH,
				mainMenuPanel);
		sl_mainMenuPanel.putConstraint(SpringLayout.WEST, lblIteratedPrisonersDillema, 10, SpringLayout.WEST,
				mainMenuPanel);
		mainMenuPanel.add(lblIteratedPrisonersDillema);

		JTextArea txtrSdf = new JTextArea();
		sl_mainMenuPanel.putConstraint(SpringLayout.SOUTH, txtrSdf, 438, SpringLayout.SOUTH,
				lblIteratedPrisonersDillema);
		txtrSdf.setFont(new Font("Arial", Font.PLAIN, 14));
		txtrSdf.setBackground(Color.WHITE);
		sl_mainMenuPanel.putConstraint(SpringLayout.NORTH, txtrSdf, 38, SpringLayout.SOUTH,
				lblIteratedPrisonersDillema);
		sl_mainMenuPanel.putConstraint(SpringLayout.WEST, txtrSdf, 10, SpringLayout.WEST, mainMenuPanel);
		sl_mainMenuPanel.putConstraint(SpringLayout.EAST, txtrSdf, 910, SpringLayout.WEST, mainMenuPanel);
		txtrSdf.setWrapStyleWord(true);
		txtrSdf.setLineWrap(true);
		txtrSdf.setText(
				"Welcome to PDG Application\r\n\r\nThe instruction below will explain you shortly how to use this program in order to generate a network structure and play the Iterated PD game over it.\r\nThe results are always saved to the text file inside the root folder.\r\n\r\n1. To start press Next button in the bottom-right corner of this window, it will take you to the Game panel where you can set the rules that will apply to the PD game run.\r\n\r\n2. Once in Game panel press Configure button to confirm the rules and to go to the Network panel.\r\n\r\n3. Inside Network panel you can set the network type and its settings, node number and seed for the given network in order to be able to repeat the experiment multiple times.\r\nOnce you are happy with settings press Generate to proceed to the Viewer panel.\r\n\r\n4. After Generate button was pressed the network should be visible inside Viewer panel and Play button should be now enabled, press Play button to proceed.\r\n\r\n5. Finally the game was played, text files containing the results should be generated inside the root folder of PDG Application and a final Results panel will be displayed where you can see graphically what has happened.");
		mainMenuPanel.add(txtrSdf);

		btnNext = new JButton("Next");
		sl_mainMenuPanel.putConstraint(SpringLayout.NORTH, btnNext, -33, SpringLayout.SOUTH, mainMenuPanel);
		sl_mainMenuPanel.putConstraint(SpringLayout.WEST, btnNext, -263, SpringLayout.EAST, mainMenuPanel);
		sl_mainMenuPanel.putConstraint(SpringLayout.SOUTH, btnNext, -10, SpringLayout.SOUTH, mainMenuPanel);
		sl_mainMenuPanel.putConstraint(SpringLayout.EAST, btnNext, -163, SpringLayout.EAST, mainMenuPanel);
		btnNext.setPreferredSize(new Dimension(100, 23));
		btnNext.setMinimumSize(new Dimension(77, 23));
		btnNext.setMaximumSize(new Dimension(100, 23));
		btnNext.setActionCommand(Action.GAME_SETTINGS.name());
		btnNext.addActionListener(controller);
		btnNext.setFont(new Font("SansSerif", Font.PLAIN, 14));
		mainMenuPanel.add(btnNext);

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
		sl_networkSettings.putConstraint(SpringLayout.NORTH, lblNodesNumber, 315, SpringLayout.NORTH, networkSettings);
		sl_networkSettings.putConstraint(SpringLayout.WEST, lblNodesNumber, 0, SpringLayout.WEST,
				lblAdjustNetworkOptions);
		lblNodesNumber.setFont(new Font("SansSerif", Font.PLAIN, 14));
		networkSettings.add(lblNodesNumber);

		textFieldNodesNumber = new JTextField();
		sl_networkSettings.putConstraint(SpringLayout.NORTH, textFieldNodesNumber, 6, SpringLayout.SOUTH,
				lblNodesNumber);
		sl_networkSettings.putConstraint(SpringLayout.WEST, textFieldNodesNumber, 10, SpringLayout.WEST,
				networkSettings);
		sl_networkSettings.putConstraint(SpringLayout.EAST, textFieldNodesNumber, -1194, SpringLayout.EAST,
				networkSettings);
		textFieldNodesNumber.setFont(new Font("SansSerif", Font.PLAIN, 14));
		textFieldNodesNumber.setText("50");
		networkSettings.add(textFieldNodesNumber);
		textFieldNodesNumber.setColumns(10);

		JLabel lblTypeOfNetwork = new JLabel("Type of network to generate:");
		sl_networkSettings.putConstraint(SpringLayout.NORTH, lblTypeOfNetwork, 74, SpringLayout.SOUTH,
				lblAdjustNetworkOptions);
		sl_networkSettings.putConstraint(SpringLayout.WEST, lblTypeOfNetwork, 0, SpringLayout.WEST,
				lblAdjustNetworkOptions);
		lblTypeOfNetwork.setFont(new Font("SansSerif", Font.BOLD, 14));
		networkSettings.add(lblTypeOfNetwork);

		rdbtnSmallWorld = new JRadioButton("Small World");
		rdbtnSmallWorld.setFont(new Font("SansSerif", Font.PLAIN, 14));
		rdbtnSmallWorld.setSelected(true);
		sl_networkSettings.putConstraint(SpringLayout.NORTH, rdbtnSmallWorld, 7, SpringLayout.SOUTH, lblTypeOfNetwork);
		sl_networkSettings.putConstraint(SpringLayout.WEST, rdbtnSmallWorld, 10, SpringLayout.WEST, lblTypeOfNetwork);
		networkSettings.add(rdbtnSmallWorld);

		rdbtnScalefree = new JRadioButton("Scale-Free");
		rdbtnScalefree.setFont(new Font("SansSerif", Font.PLAIN, 14));
		sl_networkSettings.putConstraint(SpringLayout.NORTH, rdbtnScalefree, 16, SpringLayout.SOUTH, rdbtnSmallWorld);
		sl_networkSettings.putConstraint(SpringLayout.WEST, rdbtnScalefree, 20, SpringLayout.WEST, networkSettings);
		networkSettings.add(rdbtnScalefree);

		rdbtnRandom = new JRadioButton("Random");
		rdbtnRandom.setFont(new Font("SansSerif", Font.PLAIN, 14));
		sl_networkSettings.putConstraint(SpringLayout.WEST, rdbtnRandom, 0, SpringLayout.WEST, rdbtnSmallWorld);
		networkSettings.add(rdbtnRandom);

		// set button group for type of network:
		ButtonGroup networkType = new ButtonGroup();
		networkType.add(rdbtnSmallWorld);
		networkType.add(rdbtnScalefree);
		networkType.add(rdbtnRandom);

		JLabel lblMinNeighbours = new JLabel("Total Edges");
		lblMinNeighbours.setToolTipText(
				"Use this value to make the algorithm guess how many connections should it generate for the graph.");
		sl_networkSettings.putConstraint(SpringLayout.WEST, lblMinNeighbours, 32, SpringLayout.EAST, rdbtnRandom);
		sl_networkSettings.putConstraint(SpringLayout.NORTH, rdbtnRandom, 1, SpringLayout.NORTH, lblMinNeighbours);
		lblMinNeighbours.setFont(new Font("SansSerif", Font.PLAIN, 14));
		networkSettings.add(lblMinNeighbours);

		textFieldTotalEdges = new JTextField();
		sl_networkSettings.putConstraint(SpringLayout.NORTH, textFieldTotalEdges, -7, SpringLayout.NORTH, rdbtnRandom);
		sl_networkSettings.putConstraint(SpringLayout.WEST, textFieldTotalEdges, 6, SpringLayout.EAST,
				lblMinNeighbours);
		sl_networkSettings.putConstraint(SpringLayout.EAST, textFieldTotalEdges, 66, SpringLayout.EAST,
				lblMinNeighbours);
		textFieldTotalEdges.setFont(new Font("SansSerif", Font.PLAIN, 14));
		textFieldTotalEdges.setText("150");
		textFieldTotalEdges.setColumns(10);
		networkSettings.add(textFieldTotalEdges);

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
		sl_networkSettings.putConstraint(SpringLayout.NORTH, btnGenerate, -35, SpringLayout.SOUTH, networkSettings);
		sl_networkSettings.putConstraint(SpringLayout.WEST, btnGenerate, -264, SpringLayout.EAST, networkSettings);
		sl_networkSettings.putConstraint(SpringLayout.SOUTH, btnGenerate, -10, SpringLayout.SOUTH, networkSettings);
		sl_networkSettings.putConstraint(SpringLayout.EAST, btnGenerate, -168, SpringLayout.EAST, networkSettings);
		btnGenerate.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnGenerate.setActionCommand(Action.GENERATE.name());
		btnGenerate.setEnabled(false);
		btnGenerate.addActionListener(controller);
		networkSettings.add(btnGenerate);

		JLabel lblSeed = new JLabel("Seed:");
		sl_networkSettings.putConstraint(SpringLayout.WEST, lblSeed, 0, SpringLayout.WEST, lblAdjustNetworkOptions);
		lblSeed.setFont(new Font("SansSerif", Font.PLAIN, 14));
		networkSettings.add(lblSeed);

		textFieldSeed = new JTextField();
		sl_networkSettings.putConstraint(SpringLayout.NORTH, textFieldSeed, 278, SpringLayout.NORTH, networkSettings);
		sl_networkSettings.putConstraint(SpringLayout.SOUTH, lblSeed, -6, SpringLayout.NORTH, textFieldSeed);
		sl_networkSettings.putConstraint(SpringLayout.WEST, textFieldSeed, 10, SpringLayout.WEST, networkSettings);
		sl_networkSettings.putConstraint(SpringLayout.EAST, textFieldSeed, -1094, SpringLayout.EAST, networkSettings);
		lblSeed.setLabelFor(textFieldSeed);
		textFieldSeed.setToolTipText("Provide seed value to get the same graph structure.");
		textFieldSeed.setText("0");
		textFieldSeed.setFont(new Font("SansSerif", Font.PLAIN, 14));
		networkSettings.add(textFieldSeed);
		textFieldSeed.setColumns(10);

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
		rdbtnYes.setEnabled(false);
		sl_gameSettings.putConstraint(SpringLayout.SOUTH, lblEnableNodeStrategy, -6, SpringLayout.NORTH, rdbtnYes);
		sl_gameSettings.putConstraint(SpringLayout.NORTH, rdbtnYes, 87, SpringLayout.NORTH, gameSettings);
		sl_gameSettings.putConstraint(SpringLayout.WEST, rdbtnYes, 10, SpringLayout.WEST, gameSettings);
		gameSettings.add(rdbtnYes);

		rdbtnNo = new JRadioButton("No");
		rdbtnNo.setEnabled(false);
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
		checkBoxRandomChangeChance.setEnabled(false);
		sl_gameSettings.putConstraint(SpringLayout.NORTH, checkBoxRandomChangeChance, 6, SpringLayout.SOUTH,
				lblRandomChance);
		checkBoxRandomChangeChance.setFont(new Font("SansSerif", Font.PLAIN, 14));
		sl_gameSettings.putConstraint(SpringLayout.WEST, checkBoxRandomChangeChance, 10, SpringLayout.WEST,
				gameSettings);
		gameSettings.add(checkBoxRandomChangeChance);

		JLabel lblChance = new JLabel("Chance for random change");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblChance, 6, SpringLayout.SOUTH, lblRandomChance);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblChance, 6, SpringLayout.EAST, checkBoxRandomChangeChance);
		lblChance.setToolTipText("Random chance of changing strategy each turn, enter a value between 0.001 and 1.000");
		lblChance.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lblChance);

		textFieldRandomChangeChance = new JTextField();
		textFieldRandomChangeChance.setEnabled(false);
		sl_gameSettings.putConstraint(SpringLayout.NORTH, textFieldRandomChangeChance, -4, SpringLayout.NORTH,
				checkBoxRandomChangeChance);
		sl_gameSettings.putConstraint(SpringLayout.WEST, textFieldRandomChangeChance, 6, SpringLayout.EAST, lblChance);
		sl_gameSettings.putConstraint(SpringLayout.EAST, textFieldRandomChangeChance, -972, SpringLayout.EAST,
				gameSettings);
		textFieldRandomChangeChance.setText("0.01");
		gameSettings.add(textFieldRandomChangeChance);
		textFieldRandomChangeChance.setColumns(10);

		JLabel lblLostConnection = new JLabel("2. Lost connection with a neighbour node");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblLostConnection, 26, SpringLayout.SOUTH,
				textFieldRandomChangeChance);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblLostConnection, 10, SpringLayout.WEST, gameSettings);
		lblLostConnection.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lblLostConnection);

		checkBoxLostConnection = new JCheckBox("Allow");
		checkBoxLostConnection.setEnabled(false);
		sl_gameSettings.putConstraint(SpringLayout.NORTH, checkBoxLostConnection, 6, SpringLayout.SOUTH,
				lblLostConnection);
		sl_gameSettings.putConstraint(SpringLayout.WEST, checkBoxLostConnection, 0, SpringLayout.WEST,
				lblAdjustGameOptions);
		checkBoxLostConnection.setToolTipText(
				"If trust value between 2 nodes will drop to 0 the active node will change its strategy played.");
		checkBoxLostConnection.setFont(new Font("SansSerif", Font.PLAIN, 14));
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
		lblForceNodes.setLabelFor(checkBoxForce);
		sl_gameSettings.putConstraint(SpringLayout.NORTH, checkBoxForce, 0, SpringLayout.NORTH,
				lblStrategyChangeReason);
		sl_gameSettings.putConstraint(SpringLayout.WEST, checkBoxForce, 0, SpringLayout.WEST, lblNodePlayStrategy);
		checkBoxForce.setToolTipText("It will cause nodes to continue playing within their neighbourhood");
		checkBoxForce.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(checkBoxForce);

		JSeparator separator = new JSeparator();
		sl_gameSettings.putConstraint(SpringLayout.NORTH, separator, 94, SpringLayout.SOUTH, checkBoxLostConnection);
		sl_gameSettings.putConstraint(SpringLayout.WEST, separator, 10, SpringLayout.WEST, gameSettings);
		sl_gameSettings.putConstraint(SpringLayout.EAST, separator, 754, SpringLayout.WEST, gameSettings);
		gameSettings.add(separator);

		JLabel lblNodePlayableStrategies = new JLabel("Node playable strategies and distribution:");
		sl_gameSettings.putConstraint(SpringLayout.SOUTH, separator, -6, SpringLayout.NORTH, lblNodePlayableStrategies);
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblNodePlayableStrategies, 357, SpringLayout.NORTH,
				gameSettings);
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

		JLabel lbltitFor = new JLabel("2. Pure \"Tit for tat\" strategy");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lbltitFor, 6, SpringLayout.SOUTH, checkBoxOriginalApproach);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lbltitFor, 0, SpringLayout.WEST, lblAdjustGameOptions);
		lbltitFor.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lbltitFor);

		checkBoxTitTatAllow = new JCheckBox("Allow");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, checkBoxTitTatAllow, 6, SpringLayout.SOUTH, lbltitFor);
		sl_gameSettings.putConstraint(SpringLayout.EAST, checkBoxTitTatAllow, 0, SpringLayout.EAST,
				checkBoxRandomChangeChance);
		checkBoxTitTatAllow.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(checkBoxTitTatAllow);

		JLabel lbltitFor_1 = new JLabel("3. Random");
		lbltitFor_1.setToolTipText(
				"Totally random choice for the next play.");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lbltitFor_1, 6, SpringLayout.SOUTH, checkBoxTitTatAllow);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lbltitFor_1, 0, SpringLayout.WEST, lblAdjustGameOptions);
		lbltitFor_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lbltitFor_1);

		checkBoxRandom = new JCheckBox("Allow");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, checkBoxRandom, 6, SpringLayout.SOUTH, lbltitFor_1);
		sl_gameSettings.putConstraint(SpringLayout.WEST, checkBoxRandom, 0, SpringLayout.WEST,
				lblAdjustGameOptions);
		checkBoxRandom.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(checkBoxRandom);

		JLabel lbltitFor_2 = new JLabel("4. \"Tit for Tat\" and Random");
		lbltitFor_2.setToolTipText(
				"Plays standard Tit for Tat but adds a random chance to act on the contrary to the last played strategy.");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lbltitFor_2, 6, SpringLayout.SOUTH, checkBoxRandom);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lbltitFor_2, 0, SpringLayout.WEST, lblAdjustGameOptions);
		lbltitFor_2.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lbltitFor_2);

		checkBoxTitTwoTat = new JCheckBox("Allow");
		checkBoxTitTwoTat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (checkBoxTitTwoTat.isSelected()) {
					textFieldRandomChance.setEnabled(true);
				} else {
					textFieldRandomChance.setEnabled(false);
				}
			}
		});
		sl_gameSettings.putConstraint(SpringLayout.NORTH, checkBoxTitTwoTat, 6, SpringLayout.SOUTH, lbltitFor_2);
		sl_gameSettings.putConstraint(SpringLayout.WEST, checkBoxTitTwoTat, 0, SpringLayout.WEST, lblAdjustGameOptions);
		checkBoxTitTwoTat.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(checkBoxTitTwoTat);

		JSeparator separator_1 = new JSeparator();
		sl_gameSettings.putConstraint(SpringLayout.EAST, lbltitFor_1, -176, SpringLayout.WEST, separator_1);
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
		textField1MInteractions.setText("1");
		gameSettings.add(textField1MInteractions);
		textField1MInteractions.setColumns(10);

		textField3TitTat = new JTextField();
		sl_gameSettings.putConstraint(SpringLayout.NORTH, textField3TitTat, -4, SpringLayout.NORTH,
				checkBoxTitTatAllow);
		sl_gameSettings.putConstraint(SpringLayout.WEST, textField3TitTat, 0, SpringLayout.WEST,
				textField1MInteractions);
		textField3TitTat.setText("0");
		textField3TitTat.setColumns(10);
		gameSettings.add(textField3TitTat);

		textField7Random = new JTextField();
		sl_gameSettings.putConstraint(SpringLayout.NORTH, textField7Random, -4, SpringLayout.NORTH,
				checkBoxRandom);
		sl_gameSettings.putConstraint(SpringLayout.WEST, textField7Random, 0, SpringLayout.WEST,
				textField1MInteractions);
		textField7Random.setText("0");
		textField7Random.setColumns(10);
		gameSettings.add(textField7Random);

		textField8TitTwoTats = new JTextField();
		sl_gameSettings.putConstraint(SpringLayout.NORTH, textField8TitTwoTats, -4, SpringLayout.NORTH,
				checkBoxTitTwoTat);
		sl_gameSettings.putConstraint(SpringLayout.WEST, textField8TitTwoTats, 0, SpringLayout.WEST,
				textField1MInteractions);
		textField8TitTwoTats.setText("0");
		textField8TitTwoTats.setColumns(10);
		gameSettings.add(textField8TitTwoTats);

		btnConfirm = new JButton("Confirm");
		sl_gameSettings.putConstraint(SpringLayout.EAST, btnConfirm, -165, SpringLayout.EAST, gameSettings);
		btnConfirm.setMinimumSize(new Dimension(77, 23));
		btnConfirm.setMaximumSize(new Dimension(100, 23));
		btnConfirm.setPreferredSize(new Dimension(100, 23));
		sl_gameSettings.putConstraint(SpringLayout.WEST, btnConfirm, 1000, SpringLayout.WEST, gameSettings);
		sl_gameSettings.putConstraint(SpringLayout.SOUTH, btnConfirm, -10, SpringLayout.SOUTH, gameSettings);
		btnConfirm.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnConfirm.setActionCommand(Action.CONFIRM.name());
		btnConfirm.addActionListener(controller);
		gameSettings.add(btnConfirm);

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
		sl_gameSettings.putConstraint(SpringLayout.EAST, textField3TitTat, -213, SpringLayout.WEST, lblNodeMemorySpan);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblNodeMemorySpan, 0, SpringLayout.WEST, lblGameSettings);
		lblNodeMemorySpan.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lblNodeMemorySpan);

		JLabel lblDefaultTrustFactor = new JLabel("Default trust factor (alpha):");
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblDefaultTrustFactor, 0, SpringLayout.WEST, lblGameSettings);
		lblDefaultTrustFactor.setToolTipText(
				"the higher this factor is set, the less likely the node will cooperate with less trustworthy nodes.");
		lblDefaultTrustFactor.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lblDefaultTrustFactor);

		JLabel lblBaseTrustCredit = new JLabel("Base trust credit:");
		sl_gameSettings.putConstraint(SpringLayout.EAST, textField8TitTwoTats, -213, SpringLayout.WEST,
				lblBaseTrustCredit);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblBaseTrustCredit, 0, SpringLayout.WEST, lblGameSettings);
		lblBaseTrustCredit.setToolTipText("Experimental, should be at least equal to memory span.");
		lblBaseTrustCredit.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lblBaseTrustCredit);

		JLabel lblCooperationValue = new JLabel("Fix cooperation value:");
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblCooperationValue, 0, SpringLayout.WEST, lblGameSettings);
		lblCooperationValue.setToolTipText("Random value assigned on generation otherwise.");
		lblCooperationValue.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lblCooperationValue);

		checkBoxFixCoopForAll = new JCheckBox("Yes");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, checkBoxFixCoopForAll, 6, SpringLayout.SOUTH,
				lblCooperationValue);
		sl_gameSettings.putConstraint(SpringLayout.WEST, checkBoxFixCoopForAll, 0, SpringLayout.WEST, lblGameSettings);
		checkBoxFixCoopForAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (checkBoxFixCoopForAll.isSelected()) {
					textFieldFixedCoop.setEnabled(true);
				} else {
					textFieldFixedCoop.setEnabled(false);
				}
			}
		});
		lblCooperationValue.setLabelFor(checkBoxFixCoopForAll);
		checkBoxFixCoopForAll.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(checkBoxFixCoopForAll);

		JLabel lblFixedValue = new JLabel("Fixed value:");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblFixedValue, 6, SpringLayout.SOUTH, checkBoxFixCoopForAll);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblFixedValue, 0, SpringLayout.WEST, lblGameSettings);
		lblFixedValue.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lblFixedValue);

		textFieldTurns = new JTextField();
		sl_gameSettings.putConstraint(SpringLayout.EAST, textField1MInteractions, -213, SpringLayout.WEST,
				textFieldTurns);
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblNodeMemorySpan, 6, SpringLayout.SOUTH, textFieldTurns);
		sl_gameSettings.putConstraint(SpringLayout.NORTH, textFieldTurns, -4, SpringLayout.NORTH,
				checkBoxOriginalApproach);
		sl_gameSettings.putConstraint(SpringLayout.WEST, textFieldTurns, 0, SpringLayout.WEST, lblGameSettings);
		sl_gameSettings.putConstraint(SpringLayout.EAST, textFieldTurns, -436, SpringLayout.EAST, gameSettings);
		lblNumberOfTurns.setLabelFor(textFieldTurns);
		textFieldTurns.setText("1000");
		gameSettings.add(textFieldTurns);
		textFieldTurns.setColumns(10);

		textFieldMemSpan = new JTextField();
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblDefaultTrustFactor, 2, SpringLayout.SOUTH,
				textFieldMemSpan);
		sl_gameSettings.putConstraint(SpringLayout.NORTH, textFieldMemSpan, 0, SpringLayout.NORTH, checkBoxTitTatAllow);
		sl_gameSettings.putConstraint(SpringLayout.WEST, textFieldMemSpan, 0, SpringLayout.WEST, lblGameSettings);
		sl_gameSettings.putConstraint(SpringLayout.EAST, textFieldMemSpan, -436, SpringLayout.EAST, gameSettings);
		lblNodeMemorySpan.setLabelFor(textFieldMemSpan);
		textFieldMemSpan.setText("10");
		textFieldMemSpan.setColumns(10);
		gameSettings.add(textFieldMemSpan);

		textFieldAlpha = new JTextField();
		textFieldAlpha.setEnabled(false);
		sl_gameSettings.putConstraint(SpringLayout.EAST, textField7Random, -213, SpringLayout.WEST,
				textFieldAlpha);
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblBaseTrustCredit, 6, SpringLayout.SOUTH, textFieldAlpha);
		sl_gameSettings.putConstraint(SpringLayout.NORTH, textFieldAlpha, 0, SpringLayout.NORTH, checkBoxRandom);
		sl_gameSettings.putConstraint(SpringLayout.WEST, textFieldAlpha, 0, SpringLayout.WEST, lblGameSettings);
		sl_gameSettings.putConstraint(SpringLayout.EAST, textFieldAlpha, -436, SpringLayout.EAST, gameSettings);
		lblDefaultTrustFactor.setLabelFor(textFieldAlpha);
		textFieldAlpha.setText("0.5");
		textFieldAlpha.setColumns(10);
		gameSettings.add(textFieldAlpha);

		textFieldCredit = new JTextField();
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblCooperationValue, 6, SpringLayout.SOUTH, textFieldCredit);
		sl_gameSettings.putConstraint(SpringLayout.NORTH, textFieldCredit, 6, SpringLayout.SOUTH, lblBaseTrustCredit);
		sl_gameSettings.putConstraint(SpringLayout.WEST, textFieldCredit, 0, SpringLayout.WEST, lblGameSettings);
		sl_gameSettings.putConstraint(SpringLayout.EAST, textFieldCredit, -436, SpringLayout.EAST, gameSettings);
		textFieldCredit.setEnabled(false);
		lblBaseTrustCredit.setLabelFor(textFieldCredit);
		textFieldCredit.setText("10");
		textFieldCredit.setColumns(10);
		gameSettings.add(textFieldCredit);

		textFieldFixedCoop = new JTextField();
		textFieldFixedCoop.setToolTipText("Use value between 0 and 1");
		textFieldFixedCoop.setText("0.45");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, textFieldFixedCoop, 6, SpringLayout.SOUTH, lblFixedValue);
		sl_gameSettings.putConstraint(SpringLayout.WEST, textFieldFixedCoop, 0, SpringLayout.WEST, lblGameSettings);
		sl_gameSettings.putConstraint(SpringLayout.EAST, textFieldFixedCoop, -436, SpringLayout.EAST, gameSettings);
		lblFixedValue.setLabelFor(textFieldFixedCoop);
		textFieldFixedCoop.setEnabled(false);
		textFieldFixedCoop.setColumns(10);
		gameSettings.add(textFieldFixedCoop);

		JLabel lblIsForgiving = new JLabel("2. Is forgiving");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblIsForgiving, 0, SpringLayout.NORTH, lblRandomChance);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblIsForgiving, 0, SpringLayout.WEST, lblNodePlayStrategy);
		lblIsForgiving
				.setToolTipText("It will cause nodes to continue playing within neighbourhood no matter on results.");
		lblIsForgiving.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(lblIsForgiving);

		chckbxAllowForgive = new JCheckBox("Allow");
		lblIsForgiving.setLabelFor(chckbxAllowForgive);
		sl_gameSettings.putConstraint(SpringLayout.NORTH, chckbxAllowForgive, 0, SpringLayout.NORTH,
				checkBoxRandomChangeChance);
		sl_gameSettings.putConstraint(SpringLayout.WEST, chckbxAllowForgive, 0, SpringLayout.WEST, lblNodePlayStrategy);
		chckbxAllowForgive.setToolTipText("It will cause nodes to continue playing within their neighbourhood");
		chckbxAllowForgive.setFont(new Font("SansSerif", Font.PLAIN, 14));
		gameSettings.add(chckbxAllowForgive);

		textFieldGainIncrease = new JTextField();
		textFieldGainIncrease.setToolTipText("Cooperation");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, textFieldGainIncrease, -4, SpringLayout.NORTH,
				lblStrategyChangeReason);
		sl_gameSettings.putConstraint(SpringLayout.EAST, textFieldGainIncrease, -337, SpringLayout.EAST, gameSettings);
		textFieldGainIncrease.setText("1.0");
		gameSettings.add(textFieldGainIncrease);
		textFieldGainIncrease.setColumns(10);

		JLabel lblGainDecrease = new JLabel("Gain Change (defection/cooperation)");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, lblGainDecrease, -1, SpringLayout.NORTH, rdbtnYes);
		sl_gameSettings.putConstraint(SpringLayout.WEST, lblGainDecrease, 0, SpringLayout.WEST, lblGameSettings);
		lblGainDecrease.setToolTipText("Set value of how much should the connection be decreased on each defection");
		lblGainDecrease.setFont(new Font("SansSerif", Font.BOLD, 14));
		gameSettings.add(lblGainDecrease);

		textFieldGainDecrease = new JTextField();
		sl_gameSettings.putConstraint(SpringLayout.WEST, textFieldGainDecrease, 332, SpringLayout.EAST, checkBoxForce);
		sl_gameSettings.putConstraint(SpringLayout.EAST, textFieldGainDecrease, -436, SpringLayout.EAST, gameSettings);
		textFieldGainDecrease.setToolTipText("Defection");
		sl_gameSettings.putConstraint(SpringLayout.WEST, textFieldGainIncrease, 27, SpringLayout.EAST,
				textFieldGainDecrease);
		sl_gameSettings.putConstraint(SpringLayout.NORTH, textFieldGainDecrease, -4, SpringLayout.NORTH,
				lblStrategyChangeReason);
		textFieldGainDecrease.setText("-2.0");
		textFieldGainDecrease.setColumns(10);
		gameSettings.add(textFieldGainDecrease);

		textFieldRandomChance = new JTextField();
		textFieldRandomChance.setEnabled(false);
		textFieldRandomChance.setToolTipText("Set random strategy change value in [%] for Tit for Tat strategy");
		sl_gameSettings.putConstraint(SpringLayout.NORTH, textFieldRandomChance, 6, SpringLayout.SOUTH,
				checkBoxTitTwoTat);
		sl_gameSettings.putConstraint(SpringLayout.WEST, textFieldRandomChance, 10, SpringLayout.WEST, gameSettings);
		sl_gameSettings.putConstraint(SpringLayout.EAST, textFieldRandomChance, 82, SpringLayout.WEST, gameSettings);
		textFieldRandomChance.setText("0.10");
		gameSettings.add(textFieldRandomChance);
		textFieldRandomChance.setColumns(10);

		JPanel networkStructure = new JPanel();
		panel.add(networkStructure, "networkStructurePanel");
		networkStructure.setLayout(null);

		// view:
		JPanel networkStructureMenu = new JPanel();
		networkStructureMenu.setBounds(0, 0, 256, 892);
		networkStructure.add(networkStructureMenu);
		networkStructureMenu.setLayout(null);

		JLabel lblStructureView = new JLabel("Structure View");
		lblStructureView.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblStructureView.setBounds(6, 11, 182, 16);
		networkStructureMenu.add(lblStructureView);

		JLabel lblZoomOut = new JLabel("Zoom out");
		lblZoomOut.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblZoomOut.setBounds(6, 553, 80, 16);
		networkStructureMenu.add(lblZoomOut);

		JLabel lblNewLabel_1 = new JLabel("Zoom in");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 510, 80, 16);
		networkStructureMenu.add(lblNewLabel_1);

		btnOut = new JButton("Out");
		btnOut.setPreferredSize(new Dimension(77, 23));
		btnOut.setMinimumSize(new Dimension(43, 23));
		btnOut.setMaximumSize(new Dimension(77, 23));
		btnOut.setActionCommand(Action.ZOOM_OUT.name());
		btnOut.addActionListener(controller);
		btnOut.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnOut.setBounds(96, 547, 90, 28);
		networkStructureMenu.add(btnOut);

		btnIn = new JButton("In");
		btnIn.setPreferredSize(new Dimension(77, 23));
		btnIn.setMinimumSize(new Dimension(77, 23));
		btnIn.setMaximumSize(new Dimension(77, 23));
		btnIn.setActionCommand(Action.ZOOM_IN.name());
		btnIn.addActionListener(controller);
		btnIn.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnIn.setBounds(96, 504, 90, 28);
		networkStructureMenu.add(btnIn);

		btnPlay = new JButton("Play");
		btnPlay.setMinimumSize(new Dimension(77, 23));
		btnPlay.setMaximumSize(new Dimension(77, 23));
		btnPlay.setPreferredSize(new Dimension(77, 23));
		btnPlay.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnPlay.setEnabled(false);
		btnPlay.setActionCommand(Action.GAME_START.name());
		btnPlay.addActionListener(controller);
		btnPlay.setBounds(96, 465, 90, 28);
		networkStructureMenu.add(btnPlay);

		JLabel lblPlayGame = new JLabel("Play game");
		lblPlayGame.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblPlayGame.setBounds(6, 469, 80, 21);
		networkStructureMenu.add(lblPlayGame);

		btnResults = new JButton("Results");
		btnResults.setPreferredSize(new Dimension(77, 23));
		btnResults.setMinimumSize(new Dimension(77, 23));
		btnResults.setMaximumSize(new Dimension(77, 23));
		btnResults.setActionCommand(Action.VIEW_RESULT_NETWORK.name());
		btnResults.addActionListener(controller);
		btnResults.setEnabled(false);
		btnResults.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnResults.setBounds(157, 858, 89, 23);
		networkStructureMenu.add(btnResults);

		JLabel lblSeeTheNetwork = new JLabel("See final network");
		lblSeeTheNetwork.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblSeeTheNetwork.setBounds(10, 862, 141, 14);
		networkStructureMenu.add(lblSeeTheNetwork);

		networkStructureView = new JPanel();
		networkStructureView.setLayout(new BorderLayout());
		networkStructureView.setBounds(257, 0, 1001, 892);
		networkStructureView.setPreferredSize(frmPdgApplication.getContentPane().getSize());
		networkStructure.add(networkStructureView);

		JPanel resultsView = new JPanel();
		panel.add(resultsView, "resultsPanel");
		resultsView.setLayout(null);

		JPanel resultsSideMenu = new JPanel();
		resultsSideMenu.setBounds(0, 0, 256, 892);
		resultsView.add(resultsSideMenu);
		SpringLayout sl_resultsSideMenu = new SpringLayout();
		resultsSideMenu.setLayout(sl_resultsSideMenu);

		JLabel label = new JLabel("Zoom out");
		sl_resultsSideMenu.putConstraint(SpringLayout.WEST, label, 10, SpringLayout.WEST, resultsSideMenu);
		sl_resultsSideMenu.putConstraint(SpringLayout.EAST, label, -160, SpringLayout.EAST, resultsSideMenu);
		label.setFont(new Font("SansSerif", Font.PLAIN, 14));
		resultsSideMenu.add(label);

		btnOutResults = new JButton("Out");
		sl_resultsSideMenu.putConstraint(SpringLayout.NORTH, btnOutResults, -2, SpringLayout.NORTH, label);
		sl_resultsSideMenu.putConstraint(SpringLayout.WEST, btnOutResults, 6, SpringLayout.EAST, label);
		sl_resultsSideMenu.putConstraint(SpringLayout.EAST, btnOutResults, -70, SpringLayout.EAST, resultsSideMenu);
		btnOutResults.setPreferredSize(new Dimension(77, 23));
		btnOutResults.setMinimumSize(new Dimension(43, 23));
		btnOutResults.setMaximumSize(new Dimension(77, 23));
		btnOutResults.setEnabled(false);
		btnOutResults.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnOutResults.setActionCommand(Action.RESULTS_ZOOM_OUT.name());
		btnOutResults.addActionListener(controller);
		resultsSideMenu.add(btnOutResults);

		JLabel label_1 = new JLabel("Zoom in");
		sl_resultsSideMenu.putConstraint(SpringLayout.WEST, label_1, 10, SpringLayout.WEST, resultsSideMenu);
		sl_resultsSideMenu.putConstraint(SpringLayout.SOUTH, label_1, -363, SpringLayout.SOUTH, resultsSideMenu);
		sl_resultsSideMenu.putConstraint(SpringLayout.NORTH, label, 24, SpringLayout.SOUTH, label_1);
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		resultsSideMenu.add(label_1);

		btnInResults = new JButton("In");
		sl_resultsSideMenu.putConstraint(SpringLayout.WEST, btnInResults, 102, SpringLayout.WEST, resultsSideMenu);
		sl_resultsSideMenu.putConstraint(SpringLayout.EAST, btnInResults, -70, SpringLayout.EAST, resultsSideMenu);
		sl_resultsSideMenu.putConstraint(SpringLayout.EAST, label_1, -6, SpringLayout.WEST, btnInResults);
		sl_resultsSideMenu.putConstraint(SpringLayout.NORTH, btnInResults, -2, SpringLayout.NORTH, label_1);
		btnInResults.setPreferredSize(new Dimension(77, 23));
		btnInResults.setMaximumSize(new Dimension(77, 23));
		btnInResults.setEnabled(false);
		btnInResults.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnInResults.setActionCommand(Action.RESULTS_ZOOM_IN.name());
		btnInResults.addActionListener(controller);
		resultsSideMenu.add(btnInResults);

		JLabel lblResultsView = new JLabel("Results View");
		sl_resultsSideMenu.putConstraint(SpringLayout.NORTH, lblResultsView, 10, SpringLayout.NORTH, resultsSideMenu);
		sl_resultsSideMenu.putConstraint(SpringLayout.WEST, lblResultsView, 10, SpringLayout.WEST, resultsSideMenu);
		sl_resultsSideMenu.putConstraint(SpringLayout.EAST, lblResultsView, -70, SpringLayout.EAST, resultsSideMenu);
		lblResultsView.setFont(new Font("SansSerif", Font.BOLD, 18));
		resultsSideMenu.add(lblResultsView);

		JLabel lblHierarchicalLayout = new JLabel("Hierarchical");
		sl_resultsSideMenu.putConstraint(SpringLayout.NORTH, lblHierarchicalLayout, 631, SpringLayout.NORTH,
				resultsSideMenu);
		sl_resultsSideMenu.putConstraint(SpringLayout.WEST, lblHierarchicalLayout, 10, SpringLayout.WEST,
				resultsSideMenu);
		lblHierarchicalLayout.setFont(new Font("SansSerif", Font.PLAIN, 14));
		resultsSideMenu.add(lblHierarchicalLayout);

		JLabel lblCircleLayout = new JLabel("Circle");
		sl_resultsSideMenu.putConstraint(SpringLayout.NORTH, lblCircleLayout, 14, SpringLayout.SOUTH, lblHierarchicalLayout);
		sl_resultsSideMenu.putConstraint(SpringLayout.WEST, lblCircleLayout, 10, SpringLayout.WEST, resultsSideMenu);
		lblCircleLayout.setFont(new Font("SansSerif", Font.PLAIN, 14));
		resultsSideMenu.add(lblCircleLayout);

		btnApplyHierarchical = new JButton("Apply");
		sl_resultsSideMenu.putConstraint(SpringLayout.NORTH, btnApplyHierarchical, -2, SpringLayout.NORTH, lblHierarchicalLayout);
		sl_resultsSideMenu.putConstraint(SpringLayout.WEST, btnApplyHierarchical, 0, SpringLayout.WEST, btnOutResults);
		sl_resultsSideMenu.putConstraint(SpringLayout.EAST, btnApplyHierarchical, -70, SpringLayout.EAST, resultsSideMenu);
		btnApplyHierarchical.setMinimumSize(new Dimension(43, 23));
		btnApplyHierarchical.setMaximumSize(new Dimension(77, 23));
		btnApplyHierarchical.setPreferredSize(new Dimension(77, 23));
		btnApplyHierarchical.setEnabled(false);
		btnApplyHierarchical.setActionCommand(Action.HIERARCHICAL.name());
		btnApplyHierarchical.addActionListener(controller);
		btnApplyHierarchical.setFont(new Font("SansSerif", Font.PLAIN, 14));
		resultsSideMenu.add(btnApplyHierarchical);

		btnApplyCircle = new JButton("Apply");
		sl_resultsSideMenu.putConstraint(SpringLayout.NORTH, btnApplyCircle, -2, SpringLayout.NORTH, lblCircleLayout);
		sl_resultsSideMenu.putConstraint(SpringLayout.WEST, btnApplyCircle, 0, SpringLayout.WEST, btnOutResults);
		sl_resultsSideMenu.putConstraint(SpringLayout.EAST, btnApplyCircle, -70, SpringLayout.EAST, resultsSideMenu);
		btnApplyCircle.setMinimumSize(new Dimension(43, 23));
		btnApplyCircle.setMaximumSize(new Dimension(77, 23));
		btnApplyCircle.setPreferredSize(new Dimension(77, 23));
		btnApplyCircle.setEnabled(false);
		btnApplyCircle.setActionCommand(Action.CIRCLE.name());
		btnApplyCircle.addActionListener(controller);
		btnApplyCircle.setFont(new Font("SansSerif", Font.PLAIN, 14));
		resultsSideMenu.add(btnApplyCircle);

		JLabel lblNewLabel_2 = new JLabel("Organic");
		sl_resultsSideMenu.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 10, SpringLayout.SOUTH, lblCircleLayout);
		sl_resultsSideMenu.putConstraint(SpringLayout.WEST, lblNewLabel_2, 10, SpringLayout.WEST, resultsSideMenu);
		lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 14));
		resultsSideMenu.add(lblNewLabel_2);

		btnApplyOrganic = new JButton("Apply");
		sl_resultsSideMenu.putConstraint(SpringLayout.NORTH, btnApplyOrganic, -2, SpringLayout.NORTH, lblNewLabel_2);
		sl_resultsSideMenu.putConstraint(SpringLayout.WEST, btnApplyOrganic, 0, SpringLayout.WEST, btnOutResults);
		sl_resultsSideMenu.putConstraint(SpringLayout.EAST, btnApplyOrganic, -70, SpringLayout.EAST, resultsSideMenu);
		btnApplyOrganic.setPreferredSize(new Dimension(77, 23));
		btnApplyOrganic.setMinimumSize(new Dimension(43, 23));
		btnApplyOrganic.setMaximumSize(new Dimension(77, 23));
		btnApplyOrganic.setEnabled(false);
		btnApplyOrganic.setActionCommand(Action.ORGANIC.name());
		btnApplyOrganic.addActionListener(controller);
		btnApplyOrganic.setFont(new Font("SansSerif", Font.PLAIN, 14));
		resultsSideMenu.add(btnApplyOrganic);

		JLabel lblLayouts = new JLabel("Switch layout type:");
		sl_resultsSideMenu.putConstraint(SpringLayout.WEST, lblLayouts, 10, SpringLayout.WEST, resultsSideMenu);
		sl_resultsSideMenu.putConstraint(SpringLayout.SOUTH, lblLayouts, -6, SpringLayout.NORTH, lblHierarchicalLayout);
		sl_resultsSideMenu.putConstraint(SpringLayout.EAST, lblLayouts, -70, SpringLayout.EAST, resultsSideMenu);
		lblLayouts.setFont(new Font("SansSerif", Font.PLAIN, 14));
		resultsSideMenu.add(lblLayouts);

		btnBackToView = new JButton("Back");
		sl_resultsSideMenu.putConstraint(SpringLayout.NORTH, btnBackToView, -33, SpringLayout.SOUTH, resultsSideMenu);
		sl_resultsSideMenu.putConstraint(SpringLayout.WEST, btnBackToView, -94, SpringLayout.EAST, resultsSideMenu);
		sl_resultsSideMenu.putConstraint(SpringLayout.SOUTH, btnBackToView, -10, SpringLayout.SOUTH, resultsSideMenu);
		sl_resultsSideMenu.putConstraint(SpringLayout.EAST, btnBackToView, -10, SpringLayout.EAST, resultsSideMenu);
		btnBackToView.setMinimumSize(new Dimension(77, 23));
		btnBackToView.setMaximumSize(new Dimension(77, 23));
		btnBackToView.setPreferredSize(new Dimension(77, 23));;
		btnBackToView.setActionCommand(Action.BACK_TO_VIEW.name());
		btnBackToView.addActionListener(controller);
		btnBackToView.setFont(new Font("SansSerif", Font.PLAIN, 14));
		resultsSideMenu.add(btnBackToView);

		JLabel label_2 = new JLabel("                    ");
		sl_resultsSideMenu.putConstraint(SpringLayout.WEST, label_2, 102, SpringLayout.WEST, resultsSideMenu);
		sl_resultsSideMenu.putConstraint(SpringLayout.EAST, label_2, -70, SpringLayout.EAST, resultsSideMenu);
		label_2.setBackground(Color.YELLOW);
		resultsSideMenu.add(label_2);
		
		JTextArea txtrPerformanceWasCalculated = new JTextArea();
		sl_resultsSideMenu.putConstraint(SpringLayout.NORTH, label_2, 14, SpringLayout.SOUTH, txtrPerformanceWasCalculated);
		txtrPerformanceWasCalculated.setFont(new Font("SansSerif", Font.PLAIN, 12));
		txtrPerformanceWasCalculated.setLineWrap(true);
		txtrPerformanceWasCalculated.setWrapStyleWord(true);
		sl_resultsSideMenu.putConstraint(SpringLayout.NORTH, txtrPerformanceWasCalculated, 6, SpringLayout.SOUTH, lblResultsView);
		sl_resultsSideMenu.putConstraint(SpringLayout.WEST, txtrPerformanceWasCalculated, 10, SpringLayout.WEST, resultsSideMenu);
		sl_resultsSideMenu.putConstraint(SpringLayout.EAST, txtrPerformanceWasCalculated, 246, SpringLayout.WEST, resultsSideMenu);
		txtrPerformanceWasCalculated.setText("Performance was calculated using total score of the node divided by times the node has played (up to whole game length)\r\n\r\nNode background colour:\r\n- blue - the node was in a close to ideal neighbourhood, it had a performance ratio 0.75 - 1.25\r\n- green - performance was between 1.25 - 1.5 or 0.25 - 0.75 meaning the neighbourhood was less cooperative\r\n- yellow - performance was between 1.5 - 1.75 or 0.25 - -0.25\r\n- red/purple - performance was on or below extreme cases  <= -0.25 or  >= 1.75\r\n\r\nEdges were coloured from  bright blue, dark blue, bright green, dark green, yellow, orange to red showing drop by 20% of connection initial value (100%) calculated as value between two neighbouring nodes divided by 2");
		resultsSideMenu.add(txtrPerformanceWasCalculated);

		resultsStructureView = new JPanel();
		resultsStructureView.setBounds(257, 0, 1001, 892);
		resultsView.add(resultsStructureView);
		resultsStructureView.setLayout(new BorderLayout(0, 0));

		frmPdgApplication.setVisible(true);
	}

	public void addController(GameController controller) {
		// add controller to all buttons etc. button.addActionListener(controller);
		this.controller = controller;
	}

	public void setGraphModel(mxGraphComponent graph) {
		this.graph = graph;
	}

	public mxGraphComponent getGraph() {
		return graph;
	}

	public void setNetworkView(mxGraphComponent graph) {
		networkStructureView.removeAll();
		this.graph = graph;
		networkStructureView.add(graph);
		networkStructureView.revalidate();
		networkStructureView.repaint();
	}

	public void setResultsView(mxGraphComponent results) {
		resultsStructureView.removeAll();
		this.results = results;
		resultsStructureView.add(results);
		resultsStructureView.revalidate();
		resultsStructureView.repaint();
	}
	
	public void resetResultsView() {
		resultsStructureView.removeAll();
		resultsStructureView.revalidate();
		resultsStructureView.repaint();
	}
}
