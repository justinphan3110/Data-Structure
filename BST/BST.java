
public class BST {

	public static void main(String[] args) {
		BST bst = new BST();
//		bst.add(9);
//		bst.add(3);
//		bst.add(2);
//		bst.add(1);
//		bst.add(6);
//		bst.add(5);
//		bst.add(4);
//		bst.add(7);
//		bst.add(8);
		bst.add(6);
		bst.add(1);
		bst.add(3);
		bst.add(2);
		bst.add(9);
		bst.add(7);
		bst.add(5);
		bst.add(4);
		bst.add(8);
		bst.remove(4);
		bst.remove(1);
		bst.remove(6);
		bst.traverseInOrder();
		System.out.println();
		bst.traversePreOrder();
		System.out.println();
		bst.traversePostOrder();
		System.out.println();
		System.out.println("Current size is: " + bst.currentSize);

	}

	private Node root = null;
	private int currentSize = 0;

	class Node {
		int data;
		Node left;
		Node right;
		Node parent;

		public Node(int data) {
			this.data = data;
			left = null;
			right = null;
		}
	}

	public void add(int data) {
		if (root == null)
			root = new Node(data);

		else
			add(data, root);
		currentSize++;
	}

	private void add(int data, Node node) {
		// Check right
		if (data > node.data) {
			// If right is available
			if (node.right == null) {
				node.right = new Node(data);
				node.right.parent = node;
				System.out.println("Adding " + data + " right to " + node.data);
				
				return;
			}
			// if right not available keep traverse right
			add(data, node.right);
		} else {
			// check left if right failed
			if (node.left == null) {
				// If left is available
				node.left = new Node(data);
				node.left.parent = node;
				System.out.println("Adding " + data + " left to " + node.data);
				return;
			}
			// If left is not available keep traverse left
			add(data, node.left);
		}
	}

	public Node remove(int data) {
		return remove(data, root);

	}

	private Node remove(int data, Node node) {
		Node removedNode = node;
		// Check if this is an empty tree
		if (root == null)
			return null;

		if (node.data == data) {
			// this node is a leaf
			if (node.left == null && node.right == null) {
				if (node.data > node.parent.data)
					node.parent.right = null;
				else
					node.parent.left = null;
			} else {
				Node parent = node.parent;
				Node child;
				// this node has one child
				if (node.left == null) {
					child = node.right;
					if (child.data > parent.data) {
						child.parent = parent;
						parent.right = child;
						node.parent = null;
					} else {
						child.parent = parent;
						parent.left = child;
						node.parent = null;
					}
				} else {
					// this node still has one child
					if (node.right == null) {
						child = node.left;
						if (child.data > parent.data) {
							child.parent = parent;
							parent.right = child;
							node.parent = null;
						} else {
							child.parent = parent;
							parent.left = child;
							node.parent = null;
						}
						
					}
					// this node has 2 children
					else {
						Node nextSmallest = InOrderPredessor(node.right);
						int tempData = node.data;
						System.out.println("In Order Predessor is " + nextSmallest.data);
						//Switching value the removed and the InOrderPredessor
						node.data = nextSmallest.data;
						nextSmallest.data = tempData;
						// It return the removing of the InOrderPredessor place now which 
						// has the value of the RemovedNode
						return remove(tempData, nextSmallest);
					}

				}
			}	
			currentSize--;
			System.out.println("Removed : " + removedNode.data);
			return removedNode;
		} 
		
		
		// Traverse
		// go right
		if (data > node.data) {
			return remove(data, node.right);
		}
		// go left
		return remove(data, node.left);
	}

	public void traverseInOrder() {
		System.out.print("Traverse InOrder Data: ");
		traverseInOrder(root);

	}

	private void traverseInOrder(Node node) {
		// Go Left
		if (node.left != null) {
			traverseInOrder(node.left);
		}

		System.out.print(node.data + ", ");

		// Go Right
		if (node.right != null) {
			traverseInOrder(node.right);
		}

	}

	public void traversePreOrder() {
		System.out.print("Traverse PreOrder Data: ");
		traversePreOrder(root);
	}

	private void traversePreOrder(Node node) {
		System.out.print(node.data + ", ");
		// Go Left
		if (node.left != null) {
			traversePreOrder(node.left);
		}

		// Go Right
		if (node.right != null) {
			traversePreOrder(node.right);
		}

	}

	public void traversePostOrder() {
		System.out.print("Traverse PostOrder Data: ");
		traversePostOrder(root);
	}

	private void traversePostOrder(Node node) {
		if (node.left != null) {
			traversePostOrder(node.left);
		}

		// Go Right
		if (node.right != null) {
			traversePostOrder(node.right);
		}

		System.out.print(node.data + ", ");
	}

	private Node InOrderPredessor(Node node) {
		Node temp = node;
		if (node.left != null) {
			temp = node.left;
			return temp;
		}
		if (node.right == null) {
			return temp;
		}
		return InOrderPredessor(node.right);
	}

}
