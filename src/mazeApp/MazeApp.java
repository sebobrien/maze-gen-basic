package mazeApp;



import java.util.Arrays;



import analysis.Benchmark;
import maze.BitSetMaze;
import maze.CompositeMaze;
import maze.Maze;
import mazeGen.MazeGenFac;


public class MazeApp {

	public static void main(String[] args) {
		
		//"BitSetMaze" "CompositeMaze"
		//"DisjointSetArrayUT", "DisjointSetCompUT"
		java.util.List<String> mazeTypes = Arrays.asList("BitSetMaze", "CompositeMaze");
		java.util.List<String> algoTypes = Arrays.asList("DisjointSetArrayUT", "DisjointSetCompUT","RecursiveDivAlgo");
		Benchmark.printAverageOf(30, 1000, 1000, mazeTypes, algoTypes);
//		Maze maze = MazeGenFac.GetGenInstanceOf(mazeTypes.get(0), algoTypes.get(2), 15,60, 0, 4).generateMaze();
//		Maze maze = new CompositeMaze(10, 10, 0, 9);
//		maze.wallsUpWithGap(0, 0, 1, 2, 9, false);
//		System.out.println(maze);
//		maze.wallsUpWithGap(6, 1, 8, 9, 9, true);
//		System.out.println(maze);
		//System.out.println(MazeGenFac.GetGenInstanceOf(mazeTypes.get(1), algoTypes.get(1), 10, 50, 2, 0).generateMaze());;

		}
	

}
