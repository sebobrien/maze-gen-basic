package mazeGen;

import disjointSet.ArrayUpTree;
import disjointSet.CompositeUpTree;
import maze.BitSetMaze;
import maze.CompositeMaze;
import maze.Maze;
import mazeGenAlgo.DisjointSetGenAlgo;
import mazeGenAlgo.MazeGenAlgo;
import mazeGenAlgo.RecursiveDivAlgo;

public class MazeGenFac {

	public static MazeGenerator GetGenInstanceOf(String mazeType, String algoType, int height, int width,
			int start, int end) {

		
		MazeGenAlgo algo = null;
		MazeGenerator result = null;
		
		switch (algoType) {

		case "DisjointSetCompUT":

			algo = new DisjointSetGenAlgo(new CompositeUpTree(height * width));
			break;

		case "DisjointSetArrayUT":

			algo = new DisjointSetGenAlgo(new ArrayUpTree(height * width));
			break;
			
		case "RecursiveDivAlgo":
			
			algo = new RecursiveDivAlgo();
		}

		switch (mazeType) {

		case "BitSetMaze":

			result = new MazeGen(height, width, start, end, new BitSetMaze(height, width,start, end), algo);
			break;

		case "CompositeMaze":

			result = new MazeGen(height, width, start, end,new CompositeMaze(height, width,start, end), algo);
			break;

		}
		

		
		return result;
		
	}

}
