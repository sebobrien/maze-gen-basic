package disjointSet;

public class CompositeUpTree implements DisjointSet {

	private UpTreeNode[] forest;

	public CompositeUpTree(int size) {

		this.forest = new UpTreeNode[size];
		for (int i = 0; i < size; i++) {
			forest[i] = new UpTreeNode(i);
		}
	}

	public UpTreeNode findAndCompressRecursive(int id) {
		UpTreeNode current = forest[id];
		if (current.getParent() == current) {
			return current;
		} else {
			current.setParent(findAndCompressRecursive(current.getParent().getId()));
		}
		return current.getParent();
	}

	public int find(int id) {
		return findAndCompressRecursive(id).getId();
	}

	public void union(int id1, int id2) {
		UpTreeNode set1 = forest[find(id1)];
		UpTreeNode set2 = forest[find(id2)];
		if (set1 != set2) {
			if (set1.getRank() > set2.getRank()) {
				set2.setParent(set1);

			} else if (set1.getRank() < set2.getRank()) {
				set1.setParent(set2);

			} else {
				set2.setParent(set1);
				set1.setRank(set1.getRank() + 1);
			}
		}

	}

	public String toString() {
		String result = "{";
		for (int i = 0; i < forest.length; i++) {
			result = i < forest.length - 1
					? result + "(" + forest[i].getId() + ":" + forest[i].getParent().getId() + ") , "
					: result + "(" + forest[i].getId() + ":" + forest[i].getParent().getId() + ")";
		}
		result = result + "}";
		return result;
	}

}
