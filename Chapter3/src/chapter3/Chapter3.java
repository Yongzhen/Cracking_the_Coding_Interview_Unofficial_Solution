package chapter3;


public class Chapter3 {

	public static void main(String[] args) {
		testProblem2();
		testProblem3();
		testProblem4();
		testProblem5();
		testProblem6();
	}
	
	private static void testProblem2() {
		System.out.println("\n\nProblem2 test:\n");

		Stack stack = new Stack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		
		System.out.println("Stack:");
		printStack(stack);
		System.out.println("Minimum: " + stack.min.data);
		
		stack.push(0);
		System.out.println("\nStack (After pushing a new min):");
		printStack(stack);
		System.out.println("Minimum: " + stack.min.data);
		
		stack.pop();
		System.out.println("\nStack (After pop that min):");
		printStack(stack);
		System.out.println("Minimum: " + stack.min.data);
		
	}

	private static void testProblem3() {
		System.out.println("\n\nProblem3 test:\n");

		Stack stack = new Stack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		stack.push(6);
		stack.push(7);
		stack.push(8);
		
		System.out.println("Stack:");
		printStack(stack);

		SetOfStack stackSet = new SetOfStack(stack);
		System.out.println("\nSet of Stack:");
		printSetOfStack(stackSet);

		stackSet.push(9);
		stackSet.push(10);
		stackSet.push(11);
		System.out.println("\nSet of Stack After Pushing:");
		printSetOfStack(stackSet);
		
		System.out.println("\nSet of Stack After Poping:" + stackSet.pop().data);
		printSetOfStack(stackSet);
		
		System.out.println("\nSet of Stack After Poping At Index 1:" + stackSet.popAt(1).data);
		printSetOfStack(stackSet);
	}
	
	private static void testProblem4() {
		System.out.println("\n\nProblem4 test:\n");

		Stack firstRod = new Stack();
		firstRod.push(5);
		firstRod.push(4);
		firstRod.push(3);
		firstRod.push(2);
		firstRod.push(1);

		Stack middleRod = new Stack();
		Stack lastRod = new Stack();
		
		System.out.println("Before hanoi:");
		System.out.print("First Rod:");
		printStack(firstRod);
		System.out.print("Middle Rod:");
		printStack(middleRod);
		System.out.print("Last Rod:");
		printStack(lastRod);

		Stack.hanoiTowers(firstRod, middleRod, lastRod);
		System.out.println("\nAfter hanoi:");
		System.out.print("First Rod:");
		printStack(firstRod);
		System.out.print("Middle Rod:");
		printStack(middleRod);
		System.out.print("Last Rod:");
		printStack(lastRod);
	}
	
	private static void testProblem5() {
		System.out.println("\n\nProblem5 test:\n");

		MyQueue mq = new MyQueue();
		mq.enqueue(1);
		mq.enqueue(2);
		mq.enqueue(3);
		
		System.out.println("Dequeue:" + mq.dequeue().data);
		System.out.println("Dequeue:" + mq.dequeue().data);

		mq.enqueue(4);
		mq.enqueue(5);
		
		System.out.println("Dequeue:" + mq.dequeue().data);
		System.out.println("Dequeue:" + mq.dequeue().data);
		System.out.println("Dequeue:" + mq.dequeue().data);
		System.out.println("Dequeue when there's no element:" + mq.dequeue());
	}
	
	private static void testProblem6() {
		System.out.println("\n\nProblem5 test:\n");

		Stack stack = new Stack();
		stack.push(4);
		stack.push(1);
		stack.push(3);
		stack.push(2);
		stack.push(5);
		
		System.out.println("Stack before sorting:");
		printStack(stack);
		
		stack.sort(true);
		System.out.println("Stack after sorting ascendingly:");
		printStack(stack);
		
		stack.sort(false);
		System.out.println("Stack after sorting descendingly:");
		printStack(stack);
	}
	
	public static void printStack(Stack stack) {
		if(stack.top != null) {
			Node currentNode = stack.top;
			System.out.print(currentNode.data);
			
			while(currentNode.next != null) {
				currentNode = currentNode.next;
				System.out.print("--->" + currentNode.data);
			}
		}
		System.out.println();
	}
	
	public static void printSetOfStack(SetOfStack ss) {
		for(int i = ss.stacks.size() - 1; i >= 0; i--) {
			printStack(ss.stacks.get(i));
		}
	}
	
}

