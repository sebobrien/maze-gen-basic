package analysis;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import maze.Maze;
import mazeGen.MazeGenFac;

public class Benchmark {

	public static void runOnce(int x, int y, List<String> mazeTypes, List<String> algoTypes) {

		for (String algo : algoTypes) {
			for (String maze : mazeTypes) {
				printTimedRun(maze, algo, x, y, 0, 0);
			}
		}

	}

	public static void printAverageOf(int nrOfRuns, int x, int y, List<String> mazeTypes, List<String> algoTypes) {
		String result = "";
		for (String algo : algoTypes) {
			for (String maze : mazeTypes) {
				Long sum = 0L;
				for (int i = 0; i < nrOfRuns; i++) {
					sum += timeRun(maze, algo, x, y, 0, 0);
				}
				result = result + maze + " + " + algo + ":\n\t Average of " + nrOfRuns +" runs. "
						+ NumberFormat.getNumberInstance(Locale.getDefault()).format(x * y) + " cells: " + (sum/nrOfRuns) + "ms\n";
			}
		}
		System.out.println(result);
	}

	private static void printTimedRun(String mazeType, String algoType, int nrOfRows, int nrOfCol, int start, int end) {

		Long startTime = System.currentTimeMillis();
		MazeGenFac.GetGenInstanceOf(mazeType, algoType, nrOfRows, nrOfCol, start, end).generateMaze();
		Long endTime = System.currentTimeMillis();
		System.out.println(mazeType + " + " + algoType + ":\n\t"
				+ NumberFormat.getNumberInstance(Locale.US).format(nrOfCol * nrOfRows) + " cells: "
				+ (endTime - startTime));

	}

	private static Long timeRun(String mazeType, String algoType, int nrOfRows, int nrOfCol, int start, int end) {
		Long startTime = System.currentTimeMillis();
		MazeGenFac.GetGenInstanceOf(mazeType, algoType, nrOfRows, nrOfCol, start, end).generateMaze();
		Long endTime = System.currentTimeMillis();
		return endTime - startTime;
	}

}
