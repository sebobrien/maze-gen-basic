package disjointSet;

public class UpTreeNode {

	private UpTreeNode parent;
	private int id;
	private int rank;

	public UpTreeNode(int id) {
		this.parent = this;
		this.rank = 0;
		this.id = id;
	}
    
	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public UpTreeNode getParent() {
		return parent;
	}

	public void setParent(UpTreeNode parent) {
		this.parent = parent;
	}

	public int getId() {
		return id;
	}

	
	
}
