package maze;

public interface Maze {
	
	String toString();
	boolean getCellRightWall(int i, int j);
	boolean getCellRightWall(int idx);
	void setCellRightWall(int i, int j, boolean value);
	void setCellRightWall(int idx, boolean value);
	boolean getCellLowerWall(int i, int j);
	boolean getCellLowerWall(int idx);
	void setCellLowerWall(int i, int j, boolean value);
	void setCellLowerWall(int idx, boolean value);
	void clearWall(int idx);
	//int getNextRightWall(int idx);
	//int getNextLowerWall(int idx);
	int getFirstWall(int i);
	int getStart();
	int getEnd();
	int getWidth();
	int getSize();
	void allWallsUp();
	void edgesUp();
	void horizontalUp(int i);
	void verticalUp(int j);
	void wallsUpWithGap(int rowColNr, int from, int gapFrom,int gapTo, int to, boolean isHorizontal );
	int getHeight();
}
