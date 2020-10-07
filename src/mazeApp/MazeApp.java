package mazeApp;



import disjointSet.ArrayUpTree;
import disjointSet.CompositeUpTree;
import disjointSet.DisjointSet;
import maze.BitSetMaze;
import maze.Maze;


import mazeGen.MazeGenFac;
import mazeGen.MazeGenerator;

public class MazeApp {

	public static void main(String[] args) {
		
		int x = 999999;
		int y = 100;
		Long startNano;
		Long endNano;
		Maze maze ;
		
//		startNano = System.nanoTime();
//		maze  = MazeGenFac.GetGenInstanceOf("BitSetMaze", "DisjointSetCompUT", x, y, 0, 0, 1).generateMaze();		
//		endNano = System.nanoTime();		
//		System.out.println("BitSetMaze + DisjointSetCompUT: \n\t" + x*y + " cells: " + (endNano - startNano)/1000000000.0);
		//System.out.println(maze);
		startNano = System.nanoTime();
		maze  = MazeGenFac.GetGenInstanceOf("BitSetMaze", "DisjointSetArrayUT", x, y, 0, 0, 1).generateMaze();	
		endNano = System.nanoTime();
		System.out.println("BitSetMaze + DisjointSetArrayUT:  \n\t" + x*y + " cells: " + (endNano - startNano)/1000000000.0);
	}

}
