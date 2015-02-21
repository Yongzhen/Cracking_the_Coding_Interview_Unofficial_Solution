package chapter8;

import java.util.*;

public class Chapter8 {

	public static void main(String[] args) {
		testProblem1();
		testProblem2();
		testProblem3();
		testProblem4();
		testProblem5();
		testProblem7();
		testProblem8();
	}
	
	/*********************************************/
	/***************** Problem 1 *****************/
	/*********************************************/
	
	public static int generateFibonacciNumber(int n) {
		if(n == 0 || n == 1) {
			return n;
		}
		return generateFibonacciNumber(n - 1) + generateFibonacciNumber(n - 2);
	}
	
	private static void testProblem1() {
		System.out.println("\n\nProblem1 test:\n");
		
		int[] FibonacciArray = new int[20]; 
		for(int i = 0; i < FibonacciArray.length; i++) {
			FibonacciArray[i] = generateFibonacciNumber(i + 1);
		}
		System.out.println("Fibonacci Array with 20 numbers:\n");
		for(int i = 0; i < FibonacciArray.length; i++) {
			System.out.print(FibonacciArray[i] + ", ");
		}
	}
	
	/*********************************************/
	/***************** Problem 2 *****************/
	/*********************************************/
	
	//I am not quite understand the follow up question
	public static int possiblePathsFromLocation(int x, int y, int N) {
		if(x == N) {
			return 1;
		}
		if(y == N) {
			return 1;
		}
		return possiblePathsFromLocation(x + 1, y, N) + possiblePathsFromLocation(x, y + 1, N);
	}
	
	private static void testProblem2() {
		System.out.println("\n\nProblem2 test:\n");

		System.out.println("Possible paths for N = 2: " + possiblePathsFromLocation(1, 1, 2));
		System.out.println("Possible paths for N = 3: " + possiblePathsFromLocation(1, 1, 3));
		System.out.println("Possible paths for N = 4: " + possiblePathsFromLocation(1, 1, 4));
		System.out.println("Possible paths for N = 5: " + possiblePathsFromLocation(1, 1, 5));
	}
	
	/*********************************************/
	/***************** Problem 3 *****************/
	/*********************************************/
	
	public static Set<HashSet<String>> generateAllSubSet(HashSet<String> set) {
		if(set == null) {
			return null;
		}
		Set<HashSet<String>> subsets = new HashSet<HashSet<String>>();
		if(set.size() == 1) {
			subsets.add(set);
			return subsets;
		}
		Iterator<String> iterator = set.iterator();
		if(iterator.hasNext()) {
			String element = iterator.next();
			set.remove(element);
			Set<HashSet<String>> partSubset = generateAllSubSet(set);
			subsets.addAll(partSubset);
			for(HashSet<String> s : partSubset) {
				HashSet<String> ssub = new HashSet<String>(s);
				ssub.add(element);
				subsets.add(ssub);
			}
			HashSet<String> rest = new HashSet<String>();
			rest.add(element);
			subsets.add(rest);
		}
		return subsets;
	}
	
	private static void testProblem3() {
		System.out.println("\n\nProblem3 test:\n");
		
		HashSet<String> set = new HashSet<String>();
		set.add("a");
		set.add("b");
		set.add("c");
		set.add("d");
		set.add("e");
		
		System.out.println("Original Set: " + set);
		System.out.println("All subsets: " + generateAllSubSet(set));
	}
	
	/*********************************************/
	/***************** Problem 4 *****************/
	/*********************************************/
	
	public static HashSet<String> generateAllPermutationsOfString(String s) {
		HashSet<String> results = new HashSet<String>();
		if(s == null || s.length() == 0) {
			return results;
		}
		if(s.length() == 1) {
			results.add(s);
			return results;
		}
		char firstChar = s.charAt(0);
		HashSet<String> tmpResult = generateAllPermutationsOfString(s.substring(1));
		for(String string : tmpResult) {
			for(int i = 0; i <= string.length(); i++) {
				StringBuilder sb = new StringBuilder(string);
				sb.insert(i, firstChar);
				results.add(sb.toString());
			}
		}
		return results;
	}
	
	private static void testProblem4() {
		System.out.println("\n\nProblem5 test:\n");
		
		System.out.println("All permutations of String abc: \n" + generateAllPermutationsOfString("abc"));
		System.out.println("All permutations of String acc: \n" + generateAllPermutationsOfString("acc"));
		System.out.println("All permutations of String abcde: \n" + generateAllPermutationsOfString("abcde"));
	}
	
	
	/*********************************************/
	/***************** Problem 5 *****************/
	/*********************************************/
	
	public static void printAllPossibleParentheses(int pairs) {
		HashSet<String> results = getAllPossibleParentheses(pairs);
		
		for(String s : results) {
			System.out.print(s + " ,");
		}
		System.out.println();
	}
	
	private static HashSet<String> getAllPossibleParentheses(int pairs) {
		HashSet<String> result = new HashSet<String>();
		if(pairs == 0) {
			return result;
		}
		if(pairs == 1) {
			result.add("()");
			return result;
		}
		HashSet<String> tmpResult = getAllPossibleParentheses(pairs - 1);
		for(String s : tmpResult) {
			result.add(s + "()");
			result.add("()" + s);
			result.add("(" + s + ")");
		}
		return result;
	}
	
	private static void testProblem5() {
		System.out.println("\n\nProblem5 test:\n");

		System.out.println("Possible parentheses with 1 paris: ");
		printAllPossibleParentheses(1);
		
		System.out.println("Possible parentheses with 2 paris: ");
		printAllPossibleParentheses(2);		
		
		System.out.println("Possible parentheses with 3 paris: ");
		printAllPossibleParentheses(3);
		
		System.out.println("Possible parentheses with 4 paris: ");
		printAllPossibleParentheses(4);	
	}
	
	/*********************************************/
	/***************** Problem 6 *****************/
	/*********************************************/
	
	//I am not quite understand what question 6 mean.
	
	/*********************************************/
	/***************** Problem 7 *****************/
	/*********************************************/
	
	//I didn't figure this out
	public static int makeChange(int amount) {
		if(amount <= 0) {
			return 0;
		}
		return makeChangeSub(amount, 25);
	}
	
	private static int makeChangeSub(int amount, int denom) {
		int ways = 0;
		int nextDenom = 0;
		
		switch(denom) {
		case 25:
			nextDenom = 10;
			break;
		case 10:
			nextDenom = 5;
			break;
		case 5:
			nextDenom = 1;
			break;
		case 1:
			return 1;
		}
		
		for(int i = 0; i * denom <= amount; i++) {
			ways += makeChangeSub(amount - i * denom, nextDenom);
		}
		return ways;
	}
	
	private static void testProblem7() {
		System.out.println("\n\nProblem7 test:\n");
		
		System.out.println("Make change of 5: " + makeChange(5));
		System.out.println("Make change of 10: " + makeChange(10));
		System.out.println("Make change of 15: " + makeChange(15));
		System.out.println("Make change of 25: " + makeChange(25));
		System.out.println("Make change of 50: " + makeChange(50));
		System.out.println("Make change of 100: " + makeChange(100));
	}
	
	/*********************************************/
	/***************** Problem 8 *****************/
	/*********************************************/
	
	// I didn't figure this out without reference
	
	private static void testProblem8() {
		System.out.println("\n\nProblem7 test:\n");

		NQueue queue4 = new NQueue(4);
		queue4.generateAllPosibilities();
		System.out.println("Result of 4 Queue:\n");
		queue4.printResult();
		
		NQueue queue6 = new NQueue(6);
		queue6.generateAllPosibilities();
		System.out.println("Result of 6 Queue:\n");
		queue6.printResult();
		
		NQueue queue8 = new NQueue(8);
		queue8.generateAllPosibilities();
		System.out.println("Result of 8 Queue:\n");
		queue8.printResult();
	}
	
	
	
	
	
}
