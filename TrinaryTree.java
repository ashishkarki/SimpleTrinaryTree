package main;

public class TrinaryTree<T extends Comparable<T>> {

	// Node class /////////////////////
	private class Node {
		public T data;
		public Node left;
		public Node middle;
		public Node right;

		public Node(T data) {
			this.data = data;
			this.left = null;
			this.middle = null;
			this.right = null;
		}

		public String toString() {
			return this.data.toString();
		}
	}

	// //////////// EO NOde class

	/**
	 * Represents root of this tree
	 */
	private Node root;

	/**
	 * Constructor
	 */
	public TrinaryTree() {
		root = null;
	}

	/**
	 * @param x
	 * @param y
	 * @return -1 if x < y, 0 if x = y and 1 if x > y
	 */
	private int compare(T x, T y) {
		return x.compareTo(y);
	}

	// INSERTION //////////////////////
	/**
	 * Insert new Node
	 * 
	 * @param data
	 *            : data of new node (to be inserted)
	 */
	public void insert(T data) {
		root = insert(root, data);
	}

	private Node insert(Node p, T toInsert) {
		// create a new Node
		if (null == p)
			return new Node(toInsert);

		if (compare(toInsert, p.data) == 0)
			p.middle = insert(p.middle, toInsert);
		else if (compare(toInsert, p.data) < 0)
			p.left = insert(p.left, toInsert);
		else
			p.right = insert(p.right, toInsert);

		return p;
	}

	// EO Insertion /////////////////////

	// DELETION ///////////////////////
	/**
	 * Delete a Node
	 * 
	 * @param data
	 *            : data of node (to be deleted)
	 */
	public void delete(T data) {
		root = delete(root, data);
	}

	private Node delete(Node p, T toDel) {
		if (null == p) {
			System.out.println("Empty tree! cannot delete");
		}

		if (compare(toDel, p.data) < 0) {
			p.left = delete(p.left, toDel);
		} else if (compare(toDel, p.data) > 0) {
			p.right = delete(p.right, toDel);
		} else {
			/*
			 * If middle child is present, promote it to be the new parent
			 */
			if (p.middle != null) {
				p.middle.left = p.left;
				p.middle.right = p.right;

				return p.middle;
			} else {
				if (p.left == null) {
					// if only left child
					return p.right;
				} else if (p.right == null) {
					// if only right child
					return p.left;
				} else {
					// if both children

					// Get the rightmost node of left subtree
					// This becomes replaces the deleted node
					p.data = findMax(p.left);
					// delete rightmost node of left subtree
					p.left = delete(p.left, p.data);
				}
			}
		}

		return p;
	}

	/**
	 * @param p
	 *            : root of the sub-tree where we are trying to find our max
	 * @return the largest value in a BST would be the rightmost node
	 */
	private T findMax(Node p) {
		while (p.right != null) {
			p = p.right;
		}

		return p.data;
	}

	// Print the tree ///////////////////
	public void printTree() {
		if (root == null) {
			System.out.println("Tree is empty");
		} else {
			printTree(root);
			System.out.println("\n******************");
		}
	}

	private void printTree(Node p) {
		// Node
		System.out.print(p.data + " ");
		// Left
		if (null != p.left)
			printTree(p.left);
		// middle
		if (null != p.middle)
			printTree(p.middle);
		// Right
		if (null != p.right)
			printTree(p.right);
	}

	// Finished printing BST //////////////////

	// to test the code
	public static void main(String[] args) {
		TrinaryTree<Integer> trinaryTree = new TrinaryTree<Integer>();

		trinaryTree.insert(5);
		trinaryTree.insert(4);
		trinaryTree.insert(9);
		trinaryTree.insert(5);
		trinaryTree.insert(7);
		trinaryTree.insert(2);
		trinaryTree.insert(2);

		trinaryTree.printTree();

		// Delete 4
		trinaryTree.delete(4);
		trinaryTree.printTree();

		// Delete 2
		trinaryTree.delete(2);
		trinaryTree.printTree();

		// Delete 5 (current root)
		trinaryTree.delete(5);
		trinaryTree.printTree();
	}
}
