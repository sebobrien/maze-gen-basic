package mazeGenAlgo;

import java.io.OutputStream;

import gifWriter.Frame;
import maze.Maze;

public interface MazeGenAlgo {
	
	public Maze Generate(Maze maze); 
	public Frame[] GenerateFrames(Maze maze);

	void GenerateAndDraw(Maze maze);
}
