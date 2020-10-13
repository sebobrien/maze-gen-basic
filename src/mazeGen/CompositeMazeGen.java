package mazeGen;

import maze.BitSetMaze;
import maze.CompositeMaze;
import maze.Maze;
import mazeGenAlgo.MazeGenAlgo;

public class CompositeMazeGen extends MazeGenAbstract {
	
	CompositeMaze maze;

	public CompositeMazeGen(int height, int width, int start, int end, MazeGenAlgo algo) {
		this.maze = new CompositeMaze(height, width, start, end);
		this.size = height * width;
		this.genAlgo = algo;
	}

	@Override
	public Maze generateMaze() {
		return this.genAlgo.Generate(maze);
	}

}
