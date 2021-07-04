package mazeGenAlgo;

import java.awt.Color;

import gifWriter.Frame;
import maze.Maze;
import output.MazeDrawer;

public class FrameableAlgo {
	
	boolean draw = false;
	boolean frame = false;
	//FrameSequence frames;
	int step = 0;
	
	 
	public FrameableAlgo() {
		
	}

	protected void drawStep(Maze maze) {if (draw) {
		MazeDrawer.drawMaze(maze, "png", "mazes/steps/dis/", step +".png", 10 ,10, Color.WHITE, Color.BLACK);
		step++;}}
	
	protected void frameStep(Maze maze) {if (frame) {
		
		}}

}
