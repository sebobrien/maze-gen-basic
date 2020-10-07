package disjointSet;

import java.util.Arrays;

public class ArrayUpTree implements DisjointSet {
 
	private int[] forest;
	private int[] ranks;
	
	public ArrayUpTree(int size) {
		this.forest = new int[size];
		this.ranks = new int[size];
		Arrays.setAll(forest, i -> i);
		Arrays.fill(ranks, 0);
	}
	
	private int findRecursiveAndCompress(int i) {
		int current = forest[i];
		if (current == i) { return current;}
		else { forest[i] = findRecursiveAndCompress(current) ;
		return forest[i];}
	}


	public int find(int id) {
		return findRecursiveAndCompress(id);
	}

	
	public void union(int setId1, int setId2) {
		int set1 = forest[find(setId1)];
		int set2 = forest[find(setId2)];
		if (set1 != set2) {
			if (getRank(set1) > getRank(set2)) {
				forest[set2] = set1;

			} else if (getRank(set1) < getRank(set2)) {
				forest[set1] = set2;

			} else {
				forest[set2] = set1;
				ranks[set1] = ++ranks[set1];
			}
		}
		
	}

	
	public int getRank(int id) {
		return ranks[id];
	}


	
	public String toString() {
		return Arrays.toString(forest) + "\n" + Arrays.toString(ranks);
	}
	
	

}
