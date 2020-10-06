package mazeApp;



import disjointSet.CompositeUpTree;
import disjointSet.DisjointSet;
import maze.BitSetMaze;
import maze.Maze;


import mazeGen.MazeGenFac;
import mazeGen.MazeGenerator;

public class MazeApp {

	public static void main(String[] args) {
		
		Long startNano = System.nanoTime();
		Maze maze  = MazeGenFac.GetGenInstanceOf("BitSetMaze", "DisjointSetCompUT", 10000, 1000, 0, 29, 1).generateMaze();
		Long endNano = System.nanoTime();
		System.out.println(endNano - startNano);
		System.out.println((endNano - startNano)/1000000000.0);
	}

}
