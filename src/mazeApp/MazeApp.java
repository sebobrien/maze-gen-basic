package mazeApp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import analysis.Benchmark;
import maze.BitSetMaze;
import maze.CompositeMaze;
import maze.Maze;
import mazeGen.MazeGenFac;
import output.MazeDrawer;

public class MazeApp {

	// "BitSetMaze" "CompositeMaze"
	// "DisjointSetArrayUT", "DisjointSetCompUT" "RecursiveDivAlgo"
	static java.util.List<String> mazeTypes = Arrays.asList("BitSetMaze", "CompositeMaze");
	static java.util.List<String> algoTypes = Arrays.asList("DisjointSetArrayUT", "DisjointSetCompUT",
			"RecursiveDivAlgo");

	public static void main(String[] args) {
		Maze maze1 = MazeGenFac.GetGenInstanceOf(mazeTypes.get(0), algoTypes.get(2), 10, 50, 0, 9).generateMaze();
		Maze maze2 = MazeGenFac.GetGenInstanceOf(mazeTypes.get(0), algoTypes.get(0), 50, 10, 0, 49).generateMaze();
		 MazeGenFac.GetGenInstanceOf(mazeTypes.get(0), algoTypes.get(2), 20, 60, 0, 9).GenerateAndDraw();
//		try {
//			Draw(maze1,20,algoTypes.get(2), Color.WHITE,Color.BLACK);
//			Draw(maze2,20,algoTypes.get(0), Color.WHITE,Color.BLACK);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	
	public static void Draw(Maze maze, int sizeFactor, String name,Color mazeColour, Color BackGroundColour, boolean steps) throws IOException {
		
		MazeDrawer.drawMaze(maze, "png", "mazes/", name +".png", 10 ,10, mazeColour, BackGroundColour);}
	

	public static void Bench() {
		Benchmark.printAverageOf(30, 1000, 1000, mazeTypes, algoTypes);
	}

}
