package mazeApp;



import java.util.Arrays;



import analysis.Benchmark;

public class MazeApp {

	public static void main(String[] args) {
		
		java.util.List<String> mazeTypes = Arrays.asList("BitSetMaze");
		java.util.List<String> algoTypes = Arrays.asList("DisjointSetArrayUT", "DisjointSetCompUT");
		Benchmark.printAverageOf(10, 1000, 1000, mazeTypes, algoTypes);
		
	}

}
