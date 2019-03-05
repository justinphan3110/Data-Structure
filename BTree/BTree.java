

public class BTree {
	private final int MINIMUM = 1;
	private final int MAXIMUM = 2 * MINIMUM;
	int dataCount;
	int[] data = new int[MAXIMUM + 1];
	int childCount;
	BTree[] subTree = new BTree[MAXIMUM + 2];

	// Constructor for leaf node (no children)
	public BTree(int[] setData) throws IllegalAccessException {
		if (setData.length < MINIMUM || setData.length > MAXIMUM) {
			throw new IllegalAccessException();
		}

		// DataCount saved the # of elements in this node
		// data is the array saved elements
		// childCount == 0 means no subTree --> leaf
		this.dataCount = setData.length;
		this.data = setData;
		this.childCount = 0;
	}

	// Constructor for non-leaf node (has children)
	public BTree(int[] setData, BTree[] setSubTree) throws IllegalAccessException {

		// check if not violate the property of MINIMUM/MAXIMUM and every node should
		// have length + 1 branches
		if (setData.length < MINIMUM || setData.length > MAXIMUM || (setSubTree.length != (setData.length + 1))) {
			throw new IllegalAccessException();
		}

		// DataCount saved the # of elements in this node
		// data is the array saved elements
		// #childCount is number of subtree it has
		this.data = setData;
		this.dataCount = setData.length;
		this.subTree = setSubTree;
		this.childCount = setSubTree.length;
	}

	public static void main(String[] args) throws IllegalAccessException {
		// Leaf
		BTree node10 = new BTree(new int[] { 10 });
		BTree node7_8 = new BTree(new int[] { 7, 8 });
		BTree node5 = new BTree(new int[] { 5 });
		BTree node3 = new BTree(new int[] { 3 });
		BTree node1 = new BTree(new int[] { 1 });

		// Height 2
		BTree node9 = new BTree(new int[] { 9 }, new BTree[] { node7_8, node10 });
		BTree node2_4 = new BTree(new int[] { 2, 4 },
				new BTree[] { node1, node3, node5 });

		// Root
		BTree root = new BTree(new int[] { 6 }, new BTree[] { node2_4, node9 });
		// Print for part c
		root.print(0);

		// d) Check isValid
		System.out.println("---------------------Test isValid()-----------------");

		// When subtree are valid
		System.out.println("When subtree are valid");
		BTree test1 = new BTree(new int[] { 6 }, new BTree[] { node2_4, node9 });
		test1.print(0);
		test1.isValid();

		System.out.println("---");

		BTree test2 = new BTree(new int[] { 6 }, new BTree[] { node9, node2_4 });
		test2.print(0);
		test2.isValid();

		System.out.println("---");

		// When subtree are not valid
		System.out.println("When SubTree are not valid --> Root not valid");
		BTree node2_4Test = new BTree(new int[] { 2, 4 },
				new BTree[] { node1, node5, node3 });
		BTree test3 = new BTree(new int[] { 6 }, new BTree[] { node2_4Test, node9 });
		test3.print(0);
		test3.isValid();

	}

	public boolean isValid() {
		// Check if this is a leaf, if leaf it is true
		if (this.childCount == 0) {
			System.out.println("Valid");
			return true;
		}
		for (int i = 0; i < this.data.length; i++) {
			// Access the IntBalancedSet2 subtree
			for (int j = 0; j < this.dataCount; j++) {
				// Access Value/Elements in each subtree
				BTree tempNode = subTree[j];
				for (int z = 0; z < tempNode.data.length; z++) {
					if (j <= i) {
						if (data[i] < tempNode.data[z]) {
							System.out.println("Not Valid");
							return false;
						}
					}
					if (j > i) {
						if (data[i] > tempNode.data[z]) {
							System.out.println("Not Valid");
							return false;
						}
					}
				}
			}
		}

		// Check the SubTree(Children) if valid
		for (BTree child : this.subTree)
			return child.isValid();

		return true;

	}

	// Print a representation of this set's B-tree, useful during debugging.
	public void print(int indent) {
		final int EXTRA_INDENTATION = 4;
		int i;
		int space;

		// Print the indentation and the data from this node
		for (space = 0; space < indent; space++)
			System.out.print(" ");
		for (i = 0; i < dataCount; i++)
			System.out.print(data[i] + " ");
		System.out.println();

		// Print the subtrees
		for (i = 0; i < childCount; i++)
			subTree[i].print(indent + EXTRA_INDENTATION);
	}
}
