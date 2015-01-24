package chapter3;

public class Stack {
	public Node top;
	public int count;

	/*********************************************/
	/***************** Problem 2 *****************/
	/*********************************************/

	//My solution is to let the stack maintain a reference to the min, it has O(1) access, and have space efficiency. 
	//But the drawback is when doing pop or push, it need to do extra steps to handle min changing.
	public Node min;

	public Stack() {
		top = null;
		min = null;
		count = 0;
	}

	public void push(int d) {
		Node node = new Node(d);
		node.next = top;
		top = node;
		count++;

		if(min == null || d < min.data) {
			min = node;
		}
	}

	public Node pop() {
		Node nodeToPop = null;
		if(top != null) {
			nodeToPop = top;
			top = top.next;
			count--;

			if(nodeToPop == min) {
				findMinimum();
			}
		}
		return nodeToPop;
	}

	public Integer peek() {
		return top == null ? null : top.data;
	}

	public boolean isEmpty() {
		return top == null;
	}

	private void findMinimum() {
		Node currentNode = top;
		min = null;

		while(currentNode != null) {
			if(min == null) {
				min = currentNode;
			} else {
				if(currentNode.data <= min.data) {
					min = currentNode;
				}
			}
			currentNode = currentNode.next;
		}
	}

	/*********************************************/
	/***************** Problem 4 *****************/
	/*********************************************/

	public static void hanoiTowers(Stack firstRod, Stack middleRod, Stack lastRod) {
		moveDisks(firstRod.count, firstRod, middleRod, lastRod);
	}

	//I didn't figure out this recursive algorithm until I saw the solution
	private static void moveDisks(int n, Stack source, Stack buffer, Stack destination) {
		if(n > 0) {
			moveDisks(n - 1, source, destination, buffer);
			destination.push(source.pop().data);
			moveDisks(n - 1, buffer, source, destination);
		}
	}

	/*********************************************/
	/***************** Problem 6 *****************/
	/*********************************************/

	//The official solution is much simple, I used a recursive algorithm. In all, their performance are both O(n^2).
	public void sort(boolean isAscending) {
		Stack tmpStack = new Stack();
		while(!this.isEmpty()) {
			tmpStack.push(this.pop().data);
		}
		while(!tmpStack.isEmpty()) {
			insertNodeWithOrder(isAscending, tmpStack.pop(), this);
		}
	}

	private static void  insertNodeWithOrder(boolean isAscending, Node node, Stack stack) {
		if(stack.isEmpty()) {
			stack.push(node.data);
		} else {
			if(isAscending) {
				if(node.data <= stack.peek()) {
					stack.push(node.data);
				} else {
					Node nodeOut = stack.pop();
					insertNodeWithOrder(isAscending, node, stack);
					stack.push(nodeOut.data);
				}
			} else {
				if(node.data >= stack.peek()) {
					stack.push(node.data);
				} else {
					Node nodeOut = stack.pop();
					insertNodeWithOrder(isAscending, node, stack);
					stack.push(nodeOut.data);
				}
			}
		}
	}
	
}
