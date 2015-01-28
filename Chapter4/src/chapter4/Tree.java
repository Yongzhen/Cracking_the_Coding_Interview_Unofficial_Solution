package chapter4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tree {
	int data;
	Tree left;
	Tree right;
	Tree parent;

	public Tree(int d) {
		data = d;
		left = null;
		right = null;
		parent = null;
	}

	public void addLeft(int d) {
		if(left == null) {
			left = new Tree(d);
			left.parent = this;
		} else {
			assert false;
		}
	}

	public void removeLeft() {
		left = null;
		left.parent = null;
	}

	public void addRight(int d) {
		if(right == null) {
			right = new Tree(d);
			right.parent = this;
		} else {
			assert false;
		}
	}

	public void removeRight() {
		right = null;
		right.parent = null;
	}

	/*********************************************/
	/***************** Problem 1 *****************/
	/*********************************************/

	//There's a easy way to do that, which is maxDepth(root) - minDepth(root) <= 1
	public boolean checkBalance() {
		int leftHeight = 0;
		int rightHeight = 0;
		boolean leftBalanced = true;
		boolean rightBalanced = true;

		if(this.left != null) {
			leftHeight = this.left.getHeight();
			leftBalanced = this.left.checkBalance();
		}
		if(this.right != null) {
			rightHeight = this.right.getHeight();
			rightBalanced = this.right.checkBalance();
		}
		return leftBalanced && rightBalanced && Math.abs(leftHeight - rightHeight) < 2;
	}

	public int getHeight() {
		if(this.left == null && this.right == null) {
			return 0;
		}
		int leftHeight = 0;
		int rightHeight = 0;
		if(this.left != null) {
			leftHeight = this.left.getHeight();
		}
		if(this.right != null) {
			rightHeight = this.right.getHeight();
		}
		return Math.max(leftHeight, rightHeight) + 1;
	}
	
	public int getDepth() {
		if(this.parent == null) {
			return 0;
		}
		return this.parent.getDepth() + 1;
	}
	
	/*********************************************/
	/***************** Problem 2 *****************/
	/*********************************************/
	
	// Problem 2 is a graph question, so I skip it here for the time being.

	/*********************************************/
	/***************** Problem 3 *****************/
	/*********************************************/

	public static Tree BSTreeFromSortedArray(int[] sortedArray) {
		int length = sortedArray.length;
		if(length == 0) {
			return null;
		}
		int[] leftArray;
		int[] rightArray;
		if(length % 2 == 0) {
			leftArray = new int[length / 2];
			rightArray = new int[length / 2 - 1];
		} else {
			leftArray = new int[length / 2];
			rightArray = new int[length / 2];
		}
		int middle = sortedArray[length / 2];
		System.arraycopy(sortedArray, 0, leftArray, 0, leftArray.length);
		System.arraycopy(sortedArray, leftArray.length + 1, rightArray, 0, rightArray.length);

		Tree node = new Tree(middle);
		node.left = BSTreeFromSortedArray(leftArray);
		if(node.left != null) {
			node.left.parent = node;
		}
		node.right = BSTreeFromSortedArray(rightArray);
		if(node.right != null) {
			node.right.parent = node;
		}
		return node;
	}

	/*********************************************/
	/***************** Problem 4 *****************/
	/*********************************************/

	public static ArrayList<Node> linkedListsPerDepth(Tree root) {
		ArrayList<Node> linkedLists = new ArrayList<Node>();
		createLinkedLists(linkedLists, Collections.singletonList(root));

		return linkedLists;
	}

	private static void createLinkedLists(ArrayList<Node> linkedLists, List<Tree> trees) {
		if(trees.size() == 0) {
			return;
		}
		List<Tree> treesNextLevel = new ArrayList<Tree>();
		Node node = new Node(trees.get(0).data);

		if(trees.get(0).left != null) {
			treesNextLevel.add(trees.get(0).left);
		}
		if(trees.get(0).right != null) {
			treesNextLevel.add(trees.get(0).right);
		}

		Node tmpNode = node;

		for(int i = 1; i < trees.size(); i++) {
			Tree tree = trees.get(i);
			if(tree.left != null) {
				treesNextLevel.add(tree.left);
			}
			if(tree.right != null) {
				treesNextLevel.add(tree.right);
			}
			tmpNode.next = new Node(tree.data);
			tmpNode = tmpNode.next;
		}
		linkedLists.add(node);

		createLinkedLists(linkedLists, treesNextLevel);
	}


	/*********************************************/
	/***************** Problem 5 *****************/
	/*********************************************/

	public static Tree findSuccessor(Tree node) {
		if(node.right != null) {
			Tree tmpNode = node.right;
			while(tmpNode.left != null) {
				tmpNode = tmpNode.left;
			}
			return tmpNode;
		}
		Tree tmpNode = node;
		if(node.parent != null) {
			while(true) {
				tmpNode = tmpNode.parent;
				if(tmpNode == null) {
					break;
				}
				if(tmpNode.parent.left == tmpNode) {
					tmpNode = tmpNode.parent;
					break;
				}

			}
		}
		return tmpNode;
	}
	
	/*********************************************/
	/***************** Problem 6 *****************/
	/*********************************************/

	//There's other way of doing it, when tree don't have parent pointer. 
	//It can from the root check whether the node1 and node2 are in the same side of the current node. 
	//When they are on different side of current node or current node reach one of node1 or node2, current node is the ancestor.
	public static Tree findFirstCommenAncestor(Tree node1, Tree node2) {
		Tree firstCommenAncestor;
		if(node1 == node2) {
			return node1;
		}
		
		if(node1.getDepth() > node2.getDepth()) {
			firstCommenAncestor = findFirstCommenAncestor(node1.parent, node2);
		} else if(node1.getDepth() < node2.getDepth()) {
			firstCommenAncestor = findFirstCommenAncestor(node1, node2.parent);
		} else {
			firstCommenAncestor = findFirstCommenAncestor(node1.parent, node2.parent);
		}
		return firstCommenAncestor;
	}
	
	/*********************************************/
	/***************** Problem 7 *****************/
	/*********************************************/
	
	//I think the official solution has some problem, in the matchTree method, the first check should't be if (r2 == null && r1 == null), because the r1 may be larger than r2
	public static boolean checkContaining(Tree mainTree, Tree subTree) {
		if(mainTree == null) {
			return false;
		}		
		if(mainTree.data == subTree.data) {
			if(checkSubTree(mainTree, subTree)) {
				return true;
			}
		}
		return checkContaining(mainTree.left, subTree) || checkContaining(mainTree.right, subTree);
	}
	
	private static boolean checkSubTree(Tree mainTree, Tree subTree) {
		if(subTree == null) {
			return true;
		}
		if(mainTree == null) {
			return false;
		}
		if(mainTree.data == subTree.data) {
			return checkSubTree(mainTree.left, subTree.left) && checkSubTree(mainTree.right, subTree.right);
		}
		return false;
	}
	
	/*********************************************/
	/***************** Problem 8 *****************/
	/*********************************************/
	
	//It seems the official solution has better space complexity.
	public static void printPathsEqualToSum(Tree root, int sum) {
		List<Boolean> path = new ArrayList<Boolean>();
		checkPathSum(root, root, path, sum, 0);
	}
	
	private static void checkPathSum(Tree startNode, Tree currentNode, List<Boolean> path, int sum, int currentSum) {
		if(currentNode == null) {
			return;
		}
		currentSum = currentSum + currentNode.data;
		if(currentSum == sum) {
			printPath(startNode, path);
		}
		List<Boolean> leftPath = new ArrayList<Boolean>(path);
		leftPath.add(true);
		checkPathSum(startNode, currentNode.left, leftPath, sum, currentSum);
		
		List<Boolean> rightPath = new ArrayList<Boolean>(path);
		rightPath.add(false);
		checkPathSum(startNode, currentNode.right, rightPath, sum, currentSum);
		
		checkPathSum(currentNode.left, currentNode.left, new ArrayList<Boolean>(), sum, 0);
		checkPathSum(currentNode.right, currentNode.right, new ArrayList<Boolean>(), sum, 0);
	}
	
	private static void printPath(Tree startNode, List<Boolean> path) {
		int spaces = path.size() * 2;
		
		Chapter4.printWhitespaces(spaces);
		System.out.println(startNode.data);
		
		Tree currentNode = startNode;
		
		for(Boolean isToLeft : path) {
			if(isToLeft) {
				currentNode = currentNode.left;
				Chapter4.printWhitespaces(--spaces);
				System.out.println("/");
				Chapter4.printWhitespaces(--spaces);
			} else {
				currentNode = currentNode.right;
				Chapter4.printWhitespaces(++spaces);
				System.out.println("\\");
				Chapter4.printWhitespaces(++spaces);
			}
			System.out.println(currentNode.data);
		}
		System.out.println();
	}
	
}
