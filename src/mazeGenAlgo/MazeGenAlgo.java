package mazeGenAlgo;

import java.io.OutputStream;

import maze.Maze;

public interface MazeGenAlgo {
	
	public Maze Generate(Maze maze); 
	public Byte[] GenerateFrames(Maze maze); 
	public void GenerateAndDraw(Maze maze);
}
