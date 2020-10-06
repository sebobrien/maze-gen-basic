package mazeGen;

import disjointSet.DisjointSet;
import maze.BitSetMaze;
import maze.Maze;

public abstract class BitSetMazeGen implements MazeGenerator {
	
	protected BitSetMaze maze;
	
	protected int size;
	
	public BitSetMazeGen(int height, int width, int start, int end) {
		this.maze = new BitSetMaze(height, width, start, end);
		this.size = height * width;
		
	}
	
	
	
	

}
