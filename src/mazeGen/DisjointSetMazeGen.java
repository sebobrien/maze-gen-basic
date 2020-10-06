package mazeGen;

import java.util.Random;

import disjointSet.CompositeUpTree;
import disjointSet.DisjointSet;
import maze.BitSetMaze;
import maze.Maze;

public class DisjointSetMazeGen extends BitSetMazeGen {
	
	protected DisjointSet disjointSet;
	
	public DisjointSetMazeGen(int height, int width, int start, int end) {
		super(height, width, start, end);
		this.disjointSet = new CompositeUpTree(height * width);
	}

	public Maze generateMaze() {
		int wall;
		int cell;
		int cellNext;
		Random random = new Random();
		while (disjointSet.find(maze.getStart()) != disjointSet.find(maze.getEnd())) {
			wall = random.nextInt((size * 2) - 1);
			if ((wall % 2 == 0)) {
				cell = wall / 2;
				cellNext = cell + 1;
				if (cellNext % maze.getWidth() != 0) {
					maze.clearWall(wall);
					disjointSet.union(cell, cellNext);
				}
			} else {
				cell = (wall - 1) / 2;
				cellNext = cell + maze.getWidth();
				if (cellNext < size) {
					maze.clearWall(wall);
					disjointSet.union(cell, cellNext);
				}
			}
		}
		return maze;
	}

}
