package mazeGen;

import disjointSet.DisjointSet;
import maze.BitSetMaze;
import maze.Maze;
import mazeGenAlgo.MazeGenAlgo;

public class BitSetMazeGen extends MazeGenAbstract {
	
	protected BitSetMaze maze;
	
	
	public BitSetMazeGen(int height, int width, int start, int end, MazeGenAlgo algo, int difficulty) {
		this.maze = new BitSetMaze(height, width, start, end);
		this.size = height * width;
		this.genAlgo = algo;
		this.difficulty = difficulty;
		
	}

	
	public Maze generateMaze() {		
		return genAlgo.Generate(maze);
	}
	
	
	
	

}
