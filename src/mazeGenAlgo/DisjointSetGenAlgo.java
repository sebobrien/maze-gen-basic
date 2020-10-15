package mazeGenAlgo;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import disjointSet.DisjointSet;
import maze.Maze;

public class DisjointSetGenAlgo implements MazeGenAlgo {
	
	private DisjointSet disjointSet;
	private RandomIntegerQueue unvisited; 
	
	public DisjointSetGenAlgo(DisjointSet disjointSet) {		
		this.disjointSet = disjointSet;
		
	}

	public Maze Generate(Maze maze) {
		
		int wall;
		int cell;
		int cellNext;
		//this.unvisited = new RandomIntegerQueue(maze.getSize()*2);
		Random random = new Random();
		maze.allWallsUp();
		
		while (disjointSet.find(maze.getStart()) != disjointSet.find(maze.getEnd())) {
			
			wall = maze.getFirstWall(random.nextInt((maze.getSize() * 2) - 1));
			//wall = unvisited.Deque();		
			
			if ((wall % 2 == 0)) {
				cell = wall / 2;
				cellNext = cell + 1;
				if (cellNext % maze.getWidth() != 0 && disjointSet.find(cell) != disjointSet.find(cellNext)) {
					maze.clearWall(wall);
					disjointSet.union(cell, cellNext);
				}
			} else {
				cell = (wall - 1) / 2;
				cellNext = cell + maze.getWidth();
				if (cellNext < maze.getSize()&& disjointSet.find(cell) != disjointSet.find(cellNext)) {
					maze.clearWall(wall);
					disjointSet.union(cell, cellNext);
				}
			}
		}
		
		return maze;
		
	}
	
	

}
