package mazeApp;



import disjointSet.CompositeUpTree;
import disjointSet.DisjointSet;
import maze.BitSetMaze;
import maze.Maze;

import mazeGen.DisjointSetMazeGen;
import mazeGen.MazeGenerator;

public class MazeApp {

	public static void main(String[] args) {
		//Maze maze = (new BitSetMaze(10, 50, 0, 9));
		//System.out.println(maze.toString());

		 
	
		MazeGenerator mazeGen = new DisjointSetMazeGen(40, 40, 0, 2); 
		Maze maze = mazeGen.generateMaze();
		System.out.println(maze.toString());
	}

}
