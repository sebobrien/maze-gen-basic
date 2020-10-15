package analysis;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;

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
		Long startTime  = System.currentTimeMillis();
		TreeSet<Triplet> set = new TreeSet<Triplet>();
		String result = "";
		for (String algo : algoTypes) {
			for (String maze : mazeTypes) {
				Long sum = 0L;
				for (int i = 0; i < nrOfRuns; i++) {
					sum += timeRun(maze, algo, x, y, 0, 0);					
				}
				set.add(new Triplet(maze, algo, sum));	
			}
		}
		Long total = 0L;
		for (Triplet t : set) {			
			total += t.getKey();
			result = result + t.getS1() + " + " + t.getS2() + ":\n\t Average of " + nrOfRuns + " runs. "
					+ NumberFormat.getNumberInstance(Locale.getDefault()).format(x * y) + " cells: "
					+ (t.getKey() / nrOfRuns) + "ms\n";
		}

		System.out.println(result);
		long totalTime = System.currentTimeMillis() - startTime;
		System.out.println("Total avg: "+ total + "ms\nTotal time: " + totalTime + "ms\nDiff: " + (totalTime-total));
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

	static class Triplet implements Comparable<Triplet> {

		private String s1;
		private String s2;
		private Long key;

		public Triplet(String s1, String s2, Long key) {
			super();
			this.s1 = s1;
			this.s2 = s2;
			this.key = key;
		}

		public String getS1() {
			return s1;
		}

		public void setS1(String s1) {
			this.s1 = s1;
		}

		public String getS2() {
			return s2;
		}

		public void setS2(String s2) {
			this.s2 = s2;
		}

		public Long getKey() {
			return key;
		}

		public void setKey(Long key) {
			this.key = key;
		}

		@Override
		public int compareTo(Triplet other) {
			return (int) (this.getKey() - other.getKey());
		}

	}
}
