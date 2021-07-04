package mazeGenAlgo;

import java.awt.Color;
import java.util.Random;

import gifWriter.Frame;
import maze.Maze;
import output.MazeDrawer;

public class RecursiveDivAlgo implements MazeGenAlgo {

	Random random;
	boolean draw = false;
	int step = 0;

	@Override
	public Maze Generate(Maze maze) {
		random = new Random();
		int factor = 2 * (maze.getHeight() > maze.getWidth() ? Math.floorDiv(maze.getHeight(), maze.getWidth())
				: -Math.floorDiv(maze.getWidth(), maze.getHeight()));
		boolean isHorizontal = factor > 0 ? true : false;
		int upperBound = isHorizontal ? maze.getHeight() - 1 : maze.getWidth() - 1;
		int to = isHorizontal ? maze.getWidth() - 1 : maze.getHeight() - 1;
		if (draw) {
			MazeDrawer.drawMaze(maze, "png", "mazes/steps/rec/", step +".png", 10 ,10, Color.WHITE, Color.BLACK);
			step++;}
		recursiveDiv(0, upperBound, 0, to, Math.abs(factor), maze, isHorizontal);
		return maze;

	}

	private void recursiveDiv(int lowerBound, int upperBound, int from, int to, int factor, Maze maze,
			boolean isHorizontal) {
		int height = upperBound - lowerBound + 1;
		int width = to - from + 1;
		if (factor == 1) {
			recursiveDivAlternating(from, to, lowerBound, upperBound, !isHorizontal, maze);
		} else if (height < 2 || width < 2) {
		} else {
			int spread = (upperBound - lowerBound)/4;
			
			int wall = random.nextInt(upperBound - lowerBound - spread) + lowerBound + spread;
			int gap = random.nextInt(to - from) + from;
			//System.out.println(
			//		"LB: " + lowerBound + " UB: " + upperBound + " f: " + from + " to: " + to + " h?: " + isHorizontal);
			maze.wallsUpWithGap(wall, from, gap, gap + 1, to, isHorizontal);
			//System.out.println(maze);
			if (draw) {
				MazeDrawer.drawMaze(maze, "png", "mazes/steps/rec/", step +".png", 10 ,10, Color.WHITE, Color.BLACK);
			step++;}
			recursiveDiv(lowerBound, wall, from, to, factor / 2, maze, isHorizontal);
			recursiveDiv(wall + 1, upperBound, from, to, factor / 2, maze, isHorizontal);
		}
	}

	private void recursiveDivAlternating(int lowerBound, int upperBound, int from, int to, boolean isHorizontal,
			Maze maze) {
		int height = upperBound - lowerBound + 1;
		int width = to - from + 1;

		if (height < 2 || width < 2) {
		} else {
			int wall = random.nextInt(upperBound - lowerBound) + lowerBound;
			int gap = random.nextInt(to - from) + from;			
			maze.wallsUpWithGap(wall, from, gap, gap + 1, to, isHorizontal);
			if (draw) {
				MazeDrawer.drawMaze(maze, "png", "mazes/steps/rec/", step +".png", 10 ,10, Color.WHITE, Color.BLACK);
				step++;}
			recursiveDivAlternating(from, to, lowerBound, wall, !isHorizontal, maze);
			recursiveDivAlternating(from, to, wall + 1, upperBound, !isHorizontal, maze);
		}

	}

	@Override
	public Frame[] GenerateFrames(Maze maze) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void GenerateAndDraw(Maze maze) {
		draw = true;
		Generate(maze);
		draw = false;
		step = 0;
		
	}
	
	

}
