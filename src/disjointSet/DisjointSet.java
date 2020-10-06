package disjointSet;

public interface DisjointSet {
	
	int find(int id);
	void union(int setId1,int setId2);
	String toString();
	int getRank(int id);
}
