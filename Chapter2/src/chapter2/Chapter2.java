package chapter2;

public class Chapter2 {

	public static void main(String[] args) {
		testReverse();
		testProblem1();
		testProblem2();
		testProblem3();
		testProblem4();
		testProblem5();
	}
	
	private static void testReverse() {
		System.out.println("\n\nTest Reverse:\n");
		
		SingleNode head = new SingleNode(1);
		head.appendToTail(2);
		head.appendToTail(3);
		head.appendToTail(3);
		head.appendToTail(2);
		head.appendToTail(4);
		
		System.out.println("Before reverse:");
		printSingleNodeList(head);
		
		head = SingleNode.reverse(head);
		
		System.out.println("After reverse:");
		printSingleNodeList(head);
	}
	
	private static void testProblem1() {
		System.out.println("\n\nProblem1 test:\n");
		
		SingleNode head = new SingleNode(1);
		head.appendToTail(2);
		head.appendToTail(3);
		head.appendToTail(3);
		head.appendToTail(2);
		head.appendToTail(4);
		
		System.out.println("Before removing duplication");
		printSingleNodeList(head);
		
		SingleNode.removeDuplicate(head);
		System.out.println("After removing duplication");
		printSingleNodeList(head);
		
		head.appendToTail(4);
		head.appendToTail(5);
		head.appendToTail(3);
		
		System.out.println("Before removing duplication");
		printSingleNodeList(head);
		
		SingleNode.removeDuplicate2(head);
		System.out.println("After removing duplication");
		printSingleNodeList(head);
	}
	
	private static void testProblem2() {
		System.out.println("\n\nProblem2 test:\n");
		
		SingleNode head = new SingleNode(5);
		head.appendToTail(4);
		head.appendToTail(3);
		head.appendToTail(2);
		head.appendToTail(1);
		head.appendToTail(0);

		System.out.println("Get 0 to the end: " + SingleNode.findNToTheLast(head, 0).data);
		System.out.println("Get 3 to the end: " + SingleNode.findNToTheLast(head, 3).data);
		System.out.println("Get 5 to the end: " + SingleNode.findNToTheLast(head, 5).data);
	}
	
	private static void testProblem3() {
		System.out.println("\n\nProblem3 test:\n");
		
		SingleNode head = new SingleNode(1);
		head.appendToTail(2);
		head.appendToTail(3);
		head.appendToTail(4);
		head.appendToTail(5);
		SingleNode middle = head.next.next;
		
		System.out.println("Before removing middle");
		printSingleNodeList(head);
		
		SingleNode.deleteNode(middle);
		System.out.println("After removing middle");
		printSingleNodeList(head);
	}
	
	private static void testProblem4() {
		System.out.println("\n\nProblem4 test:\n");
		
		SingleNode first = new SingleNode(3);
		first.appendToTail(9);
		first.appendToTail(9);
		
		SingleNode second = new SingleNode(1);
		second.appendToTail(2);
		
		System.out.print("First Node:");
		printSingleNodeList(first);
		System.out.println("PLUS");
		System.out.print("Second Node:");
		printSingleNodeList(second);
		
		System.out.println("EQUAL:");
		printSingleNodeList(SingleNode.sumNumber(first, second));
	}
	
	private static void testProblem5() {
		System.out.println("\n\nProblem4 test:\n");
		
		SingleNode head = new SingleNode(1);
		head.appendToTail(2);
		head.appendToTail(3);
		head.appendToTail(4);
		head.appendToTail(5);

		SingleNode end = head;
		while(end.next != null) {
			end = end.next;
		}
		end.next = head.next.next;
		
		SingleNode loopStart = SingleNode.detectLoop(head);
		System.out.println("Loop start node's data is:" + loopStart.data);
	}
	
	public static void printSingleNodeList(SingleNode head) {
		if(head != null) {
			System.out.print(head.data);
			SingleNode currentNode = head;
			while(currentNode.next != null) {
				currentNode = currentNode.next;
				System.out.print("--->" + currentNode.data);
			}
			System.out.println();
		}
	}
	
	
}
