package chapter4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Chapter4 {

	public static void main(String[] args) {
		testCheckBinarySearchTree();
		testProblem1();
		testProblem3();
		testProblem4();
		testProblem5();
		testProblem6();
		testProblem7();
		testProblem8();
	}
	
	private static void testCheckBinarySearchTree() {
		System.out.println("\n\nTest Check Binary Search Tree:\n");

		Tree root = generateTestTree();;

		printTree(root);
		System.out.println("Check BST: " + Tree.checkBST(root));
		
		root.left.right.addRight(9);
		
		printTree(root);
		System.out.println("Check BST: " + Tree.checkBST(root));
	}
	
	private static void testProblem1() {
		System.out.println("\n\nProblem1 test:\n");

		Tree root = generateTestTree();;
		
		printTree(root);
		System.out.println("Check balanced: " + root.checkBalance());
		
		root.right.right.right.addRight(22);
		
		printTree(root);
		System.out.println("Check balanced: " + root.checkBalance());
	}
	
	private static void testProblem3() {
		System.out.println("\n\nProblem3 test:\n");

		int[] sortedArray = {1, 2, 3, 4, 5, 6};
		int[] sortedArray2 = {1, 2, 3, 4, 5, 6, 7};

		printTree(Tree.BSTreeFromSortedArray(sortedArray));
		printTree(Tree.BSTreeFromSortedArray(sortedArray2));
	}
	
	private static void testProblem4() {
		System.out.println("\n\nProblem4 test:\n");
		
		int[] sortedArray = {1, 2, 3, 4, 5, 6, 7};
		Tree root = Tree.BSTreeFromSortedArray(sortedArray);
		
		printTree(root);
		
		ArrayList<Node> linkedLists = Tree.linkedListsPerDepth(root);
		
		for(Node node : linkedLists) {
			printNodeLinkedList(node);
		}
	}
	
	private static void testProblem5() {
		System.out.println("\n\nProblem5 test:\n");

		Tree root = generateTestTree();;
	
		printTree(root);
		
		System.out.println("Find Successer of " + root.data + " : " + Tree.findSuccessor(root).data);
		System.out.println("Find Successer of " + root.right.data + " : " + Tree.findSuccessor(root.right).data);
		System.out.println("Find Successer of " + root.left.right.data + " : " + Tree.findSuccessor(root.left.right).data);
	}
	
	private static void testProblem6() {
		System.out.println("\n\nProblem6 test:\n");
		
		Tree root = generateTestTree();;
		
		printTree(root);
		
		System.out.println("First Commen Ancestor of 0 and 3 : " + Tree.findFirstCommenAncestor(root.left.left, root.left.right.left).data);
		System.out.println("First Commen Ancestor of 7 and 20 : " + Tree.findFirstCommenAncestor(root.right.left, root.right.right.right).data);
		System.out.println("First Commen Ancestor of 5 and 20 : " + Tree.findFirstCommenAncestor(root, root.right.right.right).data);		
	}
	
	private static void testProblem7() {
		System.out.println("\n\nProblem7 test:\n");

		Tree root = generateTestTree();;

		System.out.println("Main Tree:");
		printTree(root);

		Tree testSubTree1 = new Tree(5);
		testSubTree1.addRight(10);
		testSubTree1.right.addLeft(7);
		
		System.out.println("\nTest subTree1:");
		printTree(testSubTree1);
		System.out.println("Result: " + Tree.checkContaining(root, testSubTree1));

		testSubTree1.addLeft(2);
		testSubTree1.left.addLeft(1);
		
		System.out.println("\nTest subTree2:");
		printTree(testSubTree1);
		System.out.println("Result: " + Tree.checkContaining(root, testSubTree1));
		
		Tree testSubTree2 = new Tree(15);
		testSubTree2.addRight(20);

		System.out.println("\nTest subTree3:");
		printTree(testSubTree2);
		System.out.println("Result: " + Tree.checkContaining(root, testSubTree2));
	}
	
	private static void testProblem8() {
		System.out.println("\n\nProblem7 test:\n");
		
		Tree root = new Tree(1);
		
		root.addLeft(-1);
		root.addRight(0);
		root.left.addLeft(1);
		root.left.addRight(2);
		root.left.right.addRight(-1);
		root.right.addLeft(0);
		root.right.addRight(10);
		root.right.right.addRight(-1);
		
		System.out.println("Test Tree:");
		printTree(root);
		
		System.out.println("Find all paths sum to 1");
		Tree.printPathsEqualToSum(root, 1);
	}
	
	private static Tree generateTestTree() {
		Tree root = new Tree(5);
		root.addLeft(2);
		root.addRight(10);
		
		Tree left = root.left;
		Tree right = root.right;
		
		left.addLeft(0);
		left.addRight(4);
		left.right.addLeft(3);
		
		right.addLeft(7);
		right.addRight(15);
		right.right.addRight(20);
		
		return root;
	}
	
	public static void printTree(Tree root) {
		printTreeInternal(Collections.singletonList(root), 1, root.getHeight() + 1);
	}
	
	//Reference: http://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
	private static void printTreeInternal(List<Tree> nodes, int level, int maxLevel) {
		if(nodes.isEmpty() || isAllElementsNull(nodes)) {
			return;
		}
        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0))); 
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;
        
        printWhitespaces(firstSpaces);
        
        List<Tree> newNodes = new ArrayList<Tree>();

		for(Tree node : nodes) {
			if(node != null) {
				System.out.print(node.data);
				newNodes.add(node.left);
				newNodes.add(node.right);
			} else {
                System.out.print(" ");
                newNodes.add(null);
                newNodes.add(null);
			}
	        printWhitespaces(betweenSpaces);
		}
        System.out.println();
        
        for(int i = 1; i <= endgeLines; i++) {
        	for(int j = 0; j < nodes.size(); j++) {
    	        printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }
                if (nodes.get(j).left != null) {
                    System.out.print("/");
                } else {
                    printWhitespaces(1);
                }
                printWhitespaces(i + i - 1);
                if (nodes.get(j).right != null) {
                    System.out.print("\\");
                } else {
                    printWhitespaces(1);
                }
                printWhitespaces(endgeLines + endgeLines - i);
        	}
        	System.out.println();
        }
        printTreeInternal(newNodes, level + 1, maxLevel);
	}
	
    private static boolean isAllElementsNull(List<Tree> list) {
        for (Tree node : list) {
            if (node != null)
                return false;
        }
        return true;
    }
    
    public static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }
    
	public static void printNodeLinkedList(Node head) {
		if(head != null) {
			System.out.print(head.data);
			Node currentNode = head;
			while(currentNode.next != null) {
				currentNode = currentNode.next;
				System.out.print("--->" + currentNode.data);
			}
			System.out.println();
		}
	}
}
