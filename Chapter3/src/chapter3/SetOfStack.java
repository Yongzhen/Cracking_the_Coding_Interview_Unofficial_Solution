package chapter3;

import java.util.ArrayList;

public class SetOfStack {
	
	  /*********************************************/
	 /***************** Problem 3 *****************/
	/*********************************************/

	public Stack topStack;
	public ArrayList<Stack> stacks;//I put this public only to hack to print them to show the correctness, in fact it should be private
	private final int STACK_CAPACITY = 5;
	
	public SetOfStack() {
		topStack = new Stack();
		stacks = new ArrayList<Stack>();
		stacks.add(topStack);
	}
	
	//This constructor is not efficient because in order to maintain the sequence, we need to pop all of them out then push one by one
	public SetOfStack(Stack s) {
		stacks = new ArrayList<Stack>();
		
		if(s.count <= STACK_CAPACITY) {
			topStack = s;
			stacks.add(s);
		} else {
			//If the initial stack's count larger than the capacity
			Stack reverseStack = new Stack();
			topStack = new Stack();
			
			while(s.top != null) {
				reverseStack.push(s.pop().data);
			}
			while(reverseStack.count > STACK_CAPACITY) {
				Stack splittedStack = new Stack();
				for(int i = 0; i < STACK_CAPACITY; i++) {
					splittedStack.push(reverseStack.pop().data);
				}
				stacks.add(splittedStack);
			}
			while(reverseStack.top != null) {
				topStack.push(reverseStack.pop().data);
			}
			stacks.add(topStack);
		}
	}
	
	public void push(int d) {
		if(topStack.count < STACK_CAPACITY) {
			topStack.push(d);
		} else {
			topStack = new Stack();
			topStack.push(d);
			stacks.add(topStack);
		}
	}
	
	public Node pop() {
		Node nodeToPop = topStack.pop();
		if(topStack.top == null) {
			stacks.remove(stacks.size() - 1);
			topStack = stacks.get(stacks.size() - 1);
		}
		return nodeToPop;
	}
	
	//This is not efficient, if we can access the bottom node of a stack, it will be better, but that is not the concept of stack
	public Node popAt(int index) {
		if(index > stacks.size()) {
			return null;
		}
		Node nodeToPop = stacks.get(index - 1).top;
		Stack tmpStack = new Stack();
		
		while(true) {
			Node tmpNode = this.pop();
			if(nodeToPop == tmpNode) {
				break;
			}
			tmpStack.push(tmpNode.data);
		}
		while(tmpStack.top != null) {
			this.push(tmpStack.pop().data);
		}
		return nodeToPop;
	}
	
}
