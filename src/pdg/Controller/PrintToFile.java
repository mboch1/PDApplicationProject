package pdg.Controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import pdg.Model.GameModel;

public class PrintToFile extends GameController {

	GameModel gm;

	public PrintToFile(GameModel model) {
		gm = model;
	}

	public void printFile() {
		Charset utf8 = StandardCharsets.UTF_8;

		try {
			ArrayList<String> matrixData = new ArrayList<>();
			ArrayList<String> countNeighboursData = new ArrayList<>();
			ArrayList<String> resultData = new ArrayList<>();
			ArrayList<String> coopData = new ArrayList<>();
			ArrayList<String> defData = new ArrayList<>();
			ArrayList<String> strategyData = new ArrayList<>();
			ArrayList<String> weightsData = new ArrayList<>();
			ArrayList<String> interactionHistoryData = new ArrayList<>();
			ArrayList<String> coopFactorData = new ArrayList<>();
			ArrayList<String> nodeData = new ArrayList<>();
			ArrayList<String> timesPlayed = new ArrayList<>();
			ArrayList<String> payoffs = new ArrayList<>();

			Random rd = new Random();
			int stamp = rd.nextInt();

			int[][] matrixToWrite = gm.getMatrix();
			String neighbourhoodData = "Starting Neighbours\tRemaining Neighbours";
			// Convert variables
			// convert matrix data to printable version:
			for (int m = 0; m < matrixToWrite.length; m++) {
				matrixData.add(Arrays.toString(matrixToWrite[m]));
				interactionHistoryData.add(Arrays.toString(gm.getNode(m).getTotalInteractionHistory()));
			}
			// count starting and remaining neighbours
			for (int o = 0; o < gm.getNodeNumber(); o++) {
				int countNeighbours = 0;
				int countIsolated = 0;
				int timesInteracted = 0;
				double averageCoop = 0;

				String pf = Integer.toString(gm.getNode(o).getR()) + "\t" + Integer.toString(gm.getNode(o).getT())
						+ "\t" + Integer.toString(gm.getNode(o).getS()) + "\t" + Integer.toString(gm.getNode(o).getP())
						+ "\t";
				payoffs.add(pf);

				for (int k = 0; k < gm.getNodeNumber(); k++) {
					if (matrixToWrite[o][k] == 1) {
						countNeighbours++;

						if (gm.getNode(k).getStrategy() == "original") {
							averageCoop += gm.getNode(k).getvCoop();
						} else {
							double c = gm.getNode(k).getTimesCoop();
							double d = gm.getNode(k).getTimesDef();
							averageCoop += (c / (c + d));
						}

						timesInteracted += gm.getNode(o).getTimesInteracted(k);
					}
					if (matrixToWrite[o][k] == 1 && gm.getNode(o).getNeighbourWeight(k) <= 0.0) {
						countIsolated++;
					}
				}
				Double avgDoubleTruncated = 0.0;

				if (countNeighbours > 0) {
					avgDoubleTruncated = BigDecimal.valueOf((averageCoop / countNeighbours))
							.setScale(4, RoundingMode.HALF_UP).doubleValue();
				}

				timesPlayed.add(Integer.toString(timesInteracted));
				neighbourhoodData = avgDoubleTruncated + "\t" + countNeighbours + "\t"
						+ (countNeighbours - countIsolated);
				countNeighboursData.add(neighbourhoodData);
			}

			for (int n = 0; n < gm.getNodeNumber(); n++) {
				String weights = "";

				double avgCoop = 0.0;

				if (gm.getNode(n).getStrategy() == "original") {
					avgCoop = gm.getNode(n).getvCoop();
				} else {
					double c = gm.getNode(n).getTimesCoop();
					double d = gm.getNode(n).getTimesDef();
					avgCoop = (c / (c + d));
				}

				String coopFactor = Double.toString(avgCoop);
				coopFactorData.add(coopFactor);

				for (int p = 0; p < gm.getNodeNumber(); p++) {
					double weight = gm.getNode(n).getNeighbourhoodWeight(p);
					// build string of weights:
					Double truncatedDouble = BigDecimal.valueOf(weight).setScale(4, RoundingMode.HALF_UP).doubleValue();
					weights += Double.toString(truncatedDouble) + " ";
				}

				weightsData.add(weights);
			}

			for (int l = 0; l < gm.getNodeNumber(); l++) {
				resultData.add(Integer.toString(gm.getNode(l).getScore()));
				coopData.add(Integer.toString(gm.getNode(l).getTimesCoop()));
				defData.add(Integer.toString(gm.getNode(l).getTimesDef()));
				strategyData.add(gm.getNode(l).getStrategy());
			}

			// create the file structure and write each line as a string on array list
			String header = "ID" + "\t" + "Node Score" + "\t" + "Vcoop" + "\t" + "VAvgneigh" + "\t" + "Neighbours"
					+ "\t" + "Remaining" + "\t" + "Cooperated" + "\t" + "Defected" + "\t" + "Times Interacted" + "\t"
					+ "Strategy" + "\t" + "Reward\tTemptation\tSucker\tPunishment\t";
			nodeData.add(header);

			for (int r = 0; r < gm.getNodeNumber(); r++) {
				String data = "";
				data = r + "\t" + resultData.get(r) + "\t" + coopFactorData.get(r) + "\t" + countNeighboursData.get(r)
						+ "\t" + coopData.get(r) + "\t" + defData.get(r) + "\t" + timesPlayed.get(r) + "\t"
						+ strategyData.get(r) + "\t" + payoffs.get(r) + "\t";
				nodeData.add(data);
			}

			Files.write(Paths.get(stamp + " nodeData.txt"), nodeData, utf8);
			Files.write(Paths.get(stamp + " nodeMatrixData.txt"), matrixData, utf8);
			Files.write(Paths.get(stamp + " nodeWeightsData.txt"), weightsData, utf8);
			Files.write(Paths.get(stamp + " nodeInteractionData.txt"), interactionHistoryData, utf8);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
