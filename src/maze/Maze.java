package maze;

public interface Maze {
	
	String toString();
	boolean getCellRightWall(int i, int j);
	void setCellRightWall(int i, int j, boolean value);
	boolean getCellLowerWall(int i, int j);
	void setCellLowerWall(int i, int j, boolean value);
	void clearWall(int idx);
	int getFirstWall(int i);
	int getStart();
	int getEnd();
	int getWidth();
	int getSize();
}
