package chapter3;

public class MyQueue {
	
	  /*********************************************/
	 /***************** Problem 5 *****************/
	/*********************************************/
	
	private Stack inStack;
	private Stack outStack;
	
	public MyQueue() {
		inStack = new Stack();
		outStack = new Stack();
	}
	
	public void enqueue(int d) {
		inStack.push(d);
	}
	
	public Node dequeue() {
		if(outStack.top == null && inStack.top != null) {
			pourInStackToOutStack();
		}
		return outStack.pop();
	}
	
	private void pourInStackToOutStack() {
		while(inStack.top != null) {
			outStack.push(inStack.pop().data);
		}
	}
	
}
