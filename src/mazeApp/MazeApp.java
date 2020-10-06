package mazeApp;



import disjointSet.CompositeUpTree;
import disjointSet.DisjointSet;
import maze.BitSetMaze;
import maze.Maze;


import mazeGen.MazeGenFac;
import mazeGen.MazeGenerator;

public class MazeApp {

	public static void main(String[] args) {
		 
		Maze maze  = MazeGenFac.GetGenInstanceOf("BitSetMaze", "DisjointSetCompUT", 10, 10, 5, 6, 1).generateMaze();		
		System.out.println(maze.toString());
	}

}
