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
//		DisjointSet test = new CompositeUpTree(10);
//		System.out.println(test.toString());
//		test.union(test.find(0),test.find(8));
//		System.out.println(test.toString());
//		test.union(test.find(9),test.find(1));
//		System.out.println(test.toString());
//		test.union(test.find(8),test.find(6));
//		System.out.println(test.toString());
//		test.union(test.find(9),test.find(0));
//		System.out.println(test.toString());
//		test.union(test.find(2),test.find(3));
//		System.out.println(test.toString());
//		test.union(test.find(5),test.find(4));
//		System.out.println(test.toString());
//		test.union(test.find(2),test.find(5));
//		System.out.println(test.toString());
//		test.union(test.find(6),test.find(4));
//		System.out.println(test.toString());
//		test.union(test.find(7),test.find(1));
//		System.out.println(test.toString());
//		System.out.println(test.find(4));
//		System.out.println(test.toString());
		 
	
		MazeGenerator mazeGen = new DisjointSetMazeGen(40, 40, 0, 2); 
		Maze maze = mazeGen.generateMaze();
		System.out.println(maze.toString());
	}

}
