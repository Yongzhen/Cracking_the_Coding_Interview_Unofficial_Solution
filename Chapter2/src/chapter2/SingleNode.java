package chapter2;

import java.util.HashSet;
import java.util.Set;

public class SingleNode {

	public SingleNode next;
	public int data;

	public SingleNode(int d) {
		data = d; 
	}

	public void appendToTail(int d) {
		SingleNode end = new SingleNode(d);
		SingleNode currentNode = this;
		while(currentNode.next != null) {
			currentNode = currentNode.next;
		}
		currentNode.next = end;
	}
	
	public static SingleNode reverse(SingleNode head) {
		if(head == null) {
			return null;
		}
		SingleNode currentNode = head.next;
		SingleNode previousNode = head;
		head.next = null;
		
		while(currentNode != null) {
			SingleNode tmp = currentNode.next;
			currentNode.next = previousNode;
			previousNode = currentNode;
			currentNode = tmp;
		}
		return previousNode;
	}
	
	  /*********************************************/
	 /***************** Problem 1 *****************/
	/*********************************************/
	
	public static void removeDuplicate(SingleNode head) {
		Set<Integer> dataSet = new HashSet<Integer>();
		SingleNode currentNode = head;
		SingleNode previousNode = null;
		
		while(currentNode != null) {
			if(!dataSet.contains(currentNode.data)) {
				dataSet.add(currentNode.data);
				previousNode = currentNode;
				currentNode = currentNode.next;
			} else {
				previousNode.next = currentNode.next;
				currentNode = currentNode.next;
			}
		}
	}
	
	public static void removeDuplicate2(SingleNode head) {
		SingleNode previousNode = null;
		SingleNode currentNode = head;
		while(currentNode != null) {
			SingleNode iterator = head;
			while(iterator != currentNode) {
				if(iterator.data == currentNode.data) {
					previousNode.next = currentNode.next;
					currentNode = currentNode.next;
					break;
				}
				iterator = iterator.next;
			}
			if(iterator == currentNode) {
				previousNode = currentNode;
				currentNode = currentNode.next;
			}
		}
	}
	
	  /*********************************************/
	 /***************** Problem 2 *****************/
	/*********************************************/
	
	public static SingleNode findNToTheLast(SingleNode head, int n) {
		SingleNode currentNode = head;
		SingleNode NToCurrent = null;
		
		for(int i = 0; i < n; i++) {
			currentNode = currentNode.next;
		}
		if(currentNode == null) {
			return null; //Number of nodes is less than n
		}
		NToCurrent = head;
		while(currentNode.next != null) { //While current node is not the last one
			NToCurrent = NToCurrent.next;
			currentNode = currentNode.next;
		}
		return NToCurrent;
	}
	
	  /*********************************************/
	 /***************** Problem 3 *****************/
	/*********************************************/
	
	//My solution here is not good, it only need to delete that node, but not rotate all after it.
	public static void rotateForwardOne(SingleNode node) {
		SingleNode previousNode = null;
		while(node.next != null) {
			node.data = node.next.data;
			previousNode = node;
			node = node.next;
		}
		previousNode.next = null;
	}
	
	//Official solution
	public static boolean deleteNode(SingleNode node) {
		if(node == null || node.next == null) {
			return false;
		}
		node.data = node.next.data;
		node.next = node.next.next;
		return true;
	}
	
	  /*********************************************/
	 /***************** Problem 4 *****************/
	/*********************************************/
	
	public static SingleNode sumNumber(SingleNode first, SingleNode second) {
		SingleNode sumNode = null;
		int carry = 0;
		
		while(first != null || second != null) {
			int firstFactor = 0;
			int secondFactor = 0;
			
			if(first != null) {
				firstFactor = first.data;
				first = first.next;
			}
			if(second != null) {
				secondFactor = second.data;
				second = second.next;
			}			
			int sum = firstFactor + secondFactor + carry;
			carry = sum / 10;
			
			if(sumNode == null) {
				sumNode = new SingleNode(sum % 10);
			} else {
				sumNode.appendToTail(sum % 10);
			}
		}
		if(carry > 0) {
			sumNode.appendToTail(carry);
		}
		return sumNode;
	}
	
	  /*********************************************/
	 /***************** Problem 5 *****************/
	/*********************************************/
	
	//This solution used HashSet to simplify the problem, instead if we can't use any buffer, it will need to do the trick like the official solution, keep two pointers moving at different speed.
	public static SingleNode detectLoop(SingleNode head) {
		HashSet<SingleNode> nodeSet = new HashSet<SingleNode>();
		SingleNode currentNode = head;
		
		while(currentNode != null) {
			if(!nodeSet.contains(currentNode)) {
				nodeSet.add(currentNode);
				currentNode = currentNode.next;
			} else {
				break;
			}
		}
		return currentNode;
	}
	
}
