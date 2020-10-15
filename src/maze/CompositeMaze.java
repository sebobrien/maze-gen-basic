package maze;

import java.util.Arrays;
import java.util.function.Function;

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
		this.booleans = new boolean[height][2 * width];
		this.start = start;
		this.end = end;
		this.height = height;
		this.width = width;
		this.startI = start;
		this.startJ = 0;
		this.endI = end;
		this.endJ = width - 1;
		edgesUp();
		setCellRightWall(endI, endJ, false);

	}

	public void edgesUp() {
		for (int i = 0; i < height; i++) {
			{
				booleans[i][(width * 2) - 2] = true;
			}

		}
		for (int j = 1; j < width * 2; j += 2) {
			booleans[height - 1][j] = true;
		}
	}

	public void wallsUpWithGap(int rowColnr, int from, int gapFrom, int gapTo, int to, boolean isHorizontal) {
		Function<Integer, Integer> proc = null;
		to++;
		if (isHorizontal) {
			from += rowColnr * width;
			gapFrom += rowColnr * width;
			gapTo += rowColnr * width;
			to += rowColnr * width;
			proc = idx -> {
				
				setCellLowerWall(idx, true);
				return idx + 1;
			};
		} else {
			from = rowColnr + from * width;
			gapFrom =rowColnr + gapFrom * width;
			gapTo = rowColnr + gapTo * width;
			to = rowColnr + to *width;
			proc = idx -> {
				
				setCellRightWall(idx, true);
				return idx + width;
			};
		}
		while (from < gapFrom) {
			from = proc.apply(from);
		}
		while (gapTo < to) {
			gapTo = proc.apply(gapTo);
		}

	}

	public void allWallsUp() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width * 2; j++) {
				booleans[i][j] = true;
			}
		}
		setCellRightWall(endI, endJ, false);
	}

	public void horizontalUp(int i) {
		for (int j = 1; j < width * 2; j += 2) {
			booleans[i][j] = true;
		}

	};

	public void verticalUp(int j) {
		for (int i = 0; i < height; i++) {
			{
				booleans[i][j * 2] = true;
			}
		}
	};

	public String toString() {

		String result = "  ";
		for (int j = 0; j < width; j++) {
			result = result + "_ ";
		}
		for (int i = 0; i < height; i++) {
			result = i == startI ? result + "\n" + " ," : result + "\n" + " |";
			// System.out.println(Arrays.toString(booleans[i]));
			for (int j = 0; j < width; j++) {

				if (getCellRightWall(i, j) && getCellLowerWall(i, j)) {
					result = result + "_|";
				} else if (getCellRightWall(i, j) && !getCellLowerWall(i, j)) {
					result = result + " |";
				} else if (!getCellRightWall(i, j) && getCellLowerWall(i, j)) {
					result = result + "_,";
				} else {
					result = result + " ,";
				}
			}
		}
		return result;
	}

	public boolean getCellRightWall(int i, int j) {
		return booleans[i][j * 2];
	}

	public void setCellRightWall(int i, int j, boolean value) {
		booleans[i][j * 2] = value;

	}

	public void setCellRightWall(int idx, boolean value) {
		int i = Math.floorDiv(idx, width);
		int j = ((idx % width) * 2) ;
		booleans[i][j] = true;
	};

	public boolean getCellLowerWall(int i, int j) {
		return booleans[i][(j * 2) + 1];
	}

	public void setCellLowerWall(int i, int j, boolean value) {
		booleans[i][(j * 2) + 1] = value;

	}

	public void setCellLowerWall(int idx, boolean value) {	
		int i =  Math.floorDiv(idx, width );
		int j = ((idx % width) * 2) + 1;
		booleans[i][j] = true;
	};

	public void clearWall(int idx) {
		int i = Math.floorDiv(idx, width * 2);
		int j = idx % (width * 2);
		booleans[i][j] = false;

	}

	public int getStart() {
		return startJ * width;
	}

	public int getEnd() {
		return (width * (endI + 1)) - 1;
	}

	public int getWidth() {
		return width;
	}

	public int getSize() {
		return height * width;
	}

	public int getFirstWall(int idx) {

		int i = Math.floorDiv(idx, width * 2);
		int j = idx % (width * 2);
		while (i < height && booleans[i][j] == false) {
			idx += 1;
			i = Math.floorDiv((idx - 1), width * 2);
			j = idx % (width * 2);

		}
		return idx;
	}

	@Override
	public int getHeight() {
		return height;
	}

}
