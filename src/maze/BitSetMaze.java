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
	
	public BitSetMaze(int height, int width, int start, int end ) {
		
		this.bits = new BitSet((height * width * 2));		
		this.bits.set(0,(height * width * 2));
		this.height = height;
		this.width = width;
		this.startI = start;
		this.startJ = 0;
		this.endI = end;
		this.endJ = --width;
		setCellRightWall(endI, endJ, false);
		
	}
	
	public boolean getCellRightWall(int i, int j) {
		return bits.get((i*width+j)*2);
	}
	
	public boolean getCellLowerWall(int i, int j) {
		return bits.get(((i*width+j)*2) + 1);
	}
	
	public void setCellRightWall(int i, int j, boolean value) {
		bits.set((i*width+j)*2, value);}
	
	public void setCellLowerWall(int i, int j, boolean value) {
		bits.set(((i*width+j)*2) + 1, value);}
	
	public void clearWall(int b) {
		bits.clear(b);
	}
	
	
	public String toString() {
		String result = "  ";
		for (int j = 0; j < width; j++) {
			result = result + "_ ";
		}
		for (int i = 0; i < height; i++) {
			result = i == startI ? result + "\n" +" ," : result + "\n" +" |";
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

	
	public int getStart() {
		return (width * startJ);
	}

	
	public int getEnd() {
		return (width * (endI + 1) ) - 1;
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

	

}
