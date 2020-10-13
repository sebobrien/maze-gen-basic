package mazeApp;



import java.util.Arrays;



import analysis.Benchmark;


public class MazeApp {

	public static void main(String[] args) {
		
		//"BitSetMaze" "CompositeMaze"
		//"DisjointSetArrayUT", "DisjointSetCompUT"
		java.util.List<String> mazeTypes = Arrays.asList("BitSetMaze", "CompositeMaze");
		java.util.List<String> algoTypes = Arrays.asList("DisjointSetArrayUT", "DisjointSetCompUT");
		Benchmark.printAverageOf(30, 1000, 1000, mazeTypes, algoTypes);
		}

}
