package output;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.plaf.ColorUIResource;

import maze.Maze;

public class MazeDrawer {

	static BufferedImage image;
	static Graphics graphics;
	static Color mazeCol;
	static Color backCol;
	
	
	

	public static void drawMaze(Maze maze, String format, String path, String fileName, int cellSizeFactor,
			int bufferSize, Color mazeColour, Color BackGroundColour) {
		int imageH = maze.getHeight() * cellSizeFactor + 2 * bufferSize;
		int imageW = maze.getWidth() * cellSizeFactor + 2 * bufferSize;
		image = new BufferedImage(imageW, imageH, BufferedImage.TYPE_INT_ARGB);
		graphics = image.createGraphics();
		mazeCol = mazeColour;
		backCol = BackGroundColour;
		int bX = bufferSize;
		int bY = bufferSize;
		graphics.setColor(backCol);
		graphics.fillRect(0, 0, imageW, imageH);
		drawBorder(maze, cellSizeFactor, bX, bY);
		drawAllCells(maze, cellSizeFactor, bX, bY);
		File outputfile = new File(path+fileName);
		try {
			ImageIO.write(image, "png", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void drawBorder(Maze maze, int cellSize, int bX, int bY) {

		int mazeH = maze.getHeight() * cellSize + cellSize;
		int mazeW = maze.getWidth() * cellSize + cellSize;

		int entranceS = (maze.getStart() % maze.getWidth()) * cellSize;
		int entranceE = entranceS + cellSize;
		int exitS = (maze.getEnd() % maze.getWidth());
		int exitE = exitS + cellSize;

		graphics.setColor(mazeCol);
		// Top + bottom border
		graphics.drawLine(bX, bY, mazeW, bY);
		//graphics.drawLine(bX, mazeH, mazeW, mazeW);
		// left border
		graphics.drawLine(bX, bY, bX, bY + entranceS);
		graphics.drawLine(bX, bY + entranceE, bX, mazeH);
		// Right border
		//graphics.drawLine(mazeW, bY, mazeW, bY + exitS);
		//graphics.drawLine(mazeW, bY + exitE, mazeW, mazeH);
	}	
	
	private static void drawAllCells(Maze maze, int cellSize, int bX, int bY) {
		for (int i = 0; i < maze.getHeight(); i++) {
			for (int j = 0; j < maze.getWidth(); j++)
			drawCell(maze, i,j, cellSize, bX, bY);
		}
	}
	
	private static void drawCell(Maze maze, int i,int j, int cellsize, int bX, int bY) {
		graphics.setColor(mazeCol);
		int x = bX + cellsize * (j); 
		int y = bY + cellsize * (i); 
		if (maze.getCellLowerWall(i,j)) {
			graphics.drawLine(x, y+cellsize, x+cellsize, y+cellsize);
		}
		;
		if (maze.getCellRightWall(i,j)) {
			graphics.drawLine(x+cellsize, y, x+cellsize, y+cellsize);
		}
		;
	}

}
