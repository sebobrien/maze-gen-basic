package mazeGen;

import disjointSet.ArrayUpTree;
import disjointSet.CompositeUpTree;
import maze.BitSetMaze;
import maze.CompositeMaze;
import maze.Maze;
import mazeGenAlgo.DisjointSetGenAlgo;
import mazeGenAlgo.MazeGenAlgo;

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

		}

		switch (mazeType) {

		case "BitSetMaze":

			result = new BitSetMazeGen(height, width, start, end, algo);
			break;

		case "CompositeMaze":

			result = new CompositeMazeGen(height, width, start, end, algo);
			break;

		}
		

		
		return result;
		
	}

}
