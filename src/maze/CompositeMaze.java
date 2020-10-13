package maze;

import java.util.Arrays;

import mazeGenAlgo.MazeGenAlgo;

public class CompositeMaze implements Maze {
	
	boolean[][] booleans;
	int start;
	int end;
	private int height;
	private int width;
	private int startI;
	private int startJ;
	private int endI;
	private int endJ;
	
	public CompositeMaze(int height, int width, int start, int end) {
		this.booleans = new boolean[height][2*width];
		this.start = start;
		this.end = end;
		this.height = height;
		this.width = width;
		this.startI = start;
		this.startJ = 0;
		this.endI = end;
		this.endJ = width-1;
		for (int i  = 0; i < height; i++) {
			for (int j = 0; j < width*2; j++) {
				booleans[i][j] = true;
			}
		}
		setCellRightWall(endI, endJ, false);
		
	}

	public String toString() {
		
		String result = "  ";
		for (int j = 0; j < width; j++) {
			result = result + "_ ";
		}
		for (int i = 0; i < height; i++) {
			result = i == startI ? result + "\n" +" ," : result + "\n" +" |";
			//System.out.println(Arrays.toString(booleans[i]));
			for (int j = 0; j < width; j++) {
				
				if(getCellRightWall(i,j) && getCellLowerWall(i, j)) {
					result = result + "_|";
				}
				else if(getCellRightWall(i,j) && !getCellLowerWall(i, j)) {
					result = result + " |";
				}
				else if(!getCellRightWall(i,j) && getCellLowerWall(i, j)) {
					result = result + "_,";
				}
				else {
					result = result + " ,";}
			}
		}
		return result;
	}

	@Override
	public boolean getCellRightWall(int i, int j) {
		return booleans[i][j*2];
	}

	@Override
	public void setCellRightWall(int i, int j, boolean value) {
		booleans[i][j*2] = value;
		
	}

	@Override
	public boolean getCellLowerWall(int i, int j) {
		return booleans[i][(j*2)+1];
	}

	@Override
	public void setCellLowerWall(int i, int j, boolean value) {
		booleans[i][(j*2)+1] = value;
		
	}

	@Override
	public void clearWall(int idx) {
		int i = idx == 0 ? 0 : Math.floorDiv(idx, width*2);
		int j = idx % (width*2);
		booleans[i][j] = false;
		
	}

	@Override
	public int getStart() {
		return startJ*width;
	}

	@Override
	public int getEnd() {
		return (width * (endI + 1) ) - 1;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getSize() {
		return height * width;
	}

	@Override
	public int getFirstWall(int idx) {
	
		int i  = idx == 0 ? 0 : Math.floorDiv(idx, width*2);
		int j = idx % (width*2);		
		while(i < height && booleans[i][j] == false) {
			idx+=1;
			i  = Math.floorDiv((idx - 1), width*2);
			j = idx % (width*2);	
			
		}
		return idx;
	}

	
	
}
