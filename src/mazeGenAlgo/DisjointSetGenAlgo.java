package mazeGenAlgo;

import java.util.Random;

import disjointSet.DisjointSet;
import maze.Maze;

public class DisjointSetGenAlgo implements MazeGenAlgo {
	
	protected DisjointSet disjointSet;	
	
	public DisjointSetGenAlgo(DisjointSet disjointSet) {		
		this.disjointSet = disjointSet;
	}

	public Maze Generate(Maze maze) {
		int wall;
		int cell;
		int cellNext;
		Random random = new Random();
		while (disjointSet.find(maze.getStart()) != disjointSet.find(maze.getEnd())) {
			wall = random.nextInt((maze.getSize() * 2) - 1);
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
				if (cellNext < maze.getSize()) {
					maze.clearWall(wall);
					disjointSet.union(cell, cellNext);
				}
			}
		}
		return maze;
		
	}
	
	

}
