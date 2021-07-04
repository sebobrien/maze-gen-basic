package mazeGen;

import disjointSet.DisjointSet;
import maze.BitSetMaze;
import maze.Maze;
import mazeGenAlgo.MazeGenAlgo;

public class MazeGen implements MazeGenerator {
	
	protected Maze maze;
	protected MazeGenAlgo genAlgo;
	protected int size;
	
	public MazeGen(int height, int width, int start, int end,Maze maze, MazeGenAlgo algo) {
		this.maze = maze;
		this.size = height * width;
		this.genAlgo = algo;
		
		
	}

	
	public Maze generateMaze() {		
		return genAlgo.Generate(maze);
	}




	
	public Byte[] GenerateFrames() {
		// TODO Auto-generated method stub
		return null;
	}


	
	public void GenerateAndDraw() {
		genAlgo.GenerateAndDraw(maze);
		
	}
	
	
	
	

}
