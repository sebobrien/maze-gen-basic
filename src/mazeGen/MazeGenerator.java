package mazeGen;

import maze.Maze;

public interface MazeGenerator {
	
	public Maze generateMaze();
	 
	public Byte[] GenerateFrames(); 
	public void GenerateAndDraw();
}
