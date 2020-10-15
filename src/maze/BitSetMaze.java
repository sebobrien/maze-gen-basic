package maze;

import java.util.BitSet;

public class BitSetMaze implements Maze {

	private BitSet bits;
	private int height;
	private int width;
	private int startI;
	private int startJ;
	private int endI;
	private int endJ;

	public BitSetMaze(int height, int width, int start, int end) {

		this.bits = new BitSet((height * width * 2));

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
		int lastRightwall = (height * width * 2) - 1;
		for (int i = (width * 2) - 2; i < lastRightwall; i += (width * 2)) {
			bits.set(i);
		}
		for (int i = lastRightwall - (width * 2) + 2; i < lastRightwall + 2; i += 2) {
			bits.set(i);
		}
	};

	public void horizontalUp(int i) {
		for (int j = 1; j < width * 2; j += 2) {
			bits.set(j);
		}

	};

	public void verticalUp(int j) {
		for (int i = 0; i < height; i++) {
			{
				bits.set(i);
			}
		}
	};

	public void allWallsUp() {
		this.bits.set(0, (height * width * 2));
		setCellRightWall(endI, endJ, false);
	}

	public boolean getCellRightWall(int i, int j) {
		return bits.get((i * width + j) * 2);
	}

	public boolean getCellLowerWall(int i, int j) {
		return bits.get(((i * width + j) * 2) + 1);
	}

	public void setCellRightWall(int i, int j, boolean value) {
		bits.set((i * width + j) * 2, value);
	}

	public void setCellLowerWall(int i, int j, boolean value) {
		bits.set(((i * width + j) * 2) + 1, value);
	}

	public void clearWall(int b) {
		bits.clear(b);
	}

	public String toString() {
		String result = "  ";
		for (int j = 0; j < width; j++) {
			result = result + "_ ";
		}
		for (int i = 0; i < height; i++) {
			result = i == startI ? result + "\n" + " ," : result + "\n" + " |";
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

	public int getStart() {
		return (width * startJ);
	}

	public int getEnd() {
		return (width * (endI + 1)) - 1;
	}

	public int getWidth() {
		return width;
	}

	public int getSize() {
		return width * height;
	}

	public int getFirstWall(int i) {
		return bits.nextSetBit(i);
	}

	@Override
	public void setCellRightWall(int idx, boolean value) {
		bits.set(idx * 2, value);

	}

	@Override
	public void setCellLowerWall(int idx, boolean value) {
		bits.set((idx * 2) + 1, value);

	}

	@Override
	public void wallsUpWithGap(int rowColNr, int from, int gapFrom, int gapTo, int to, boolean isHorizontal) {
		if (!isHorizontal) {			
			from = rowColNr + from * width;
			gapFrom = rowColNr + gapFrom * width;
			gapTo = rowColNr + gapTo * width;
			to = rowColNr + to * width;
			while (from < gapFrom) {
				setCellRightWall(from, true);
				from += width;
			}
			while (gapTo < to+1) {
				setCellRightWall(gapTo, true);
				gapTo += width;
			
			}
		} else {
			from += rowColNr * width;
			gapFrom += rowColNr * width;
			gapTo += rowColNr * width;
			to += rowColNr * width;
			while (from < gapFrom) {
				setCellLowerWall(from, true);
				from++;
			}
			while (gapTo < to +1) {
				setCellLowerWall(gapTo, true);
				gapTo++;
			}
		}

	}

	@Override
	public int getHeight() {
		return height;
	}

}
