package chapter1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Chapter1 {

	public static void main(String[] args) {
		testProblem1();
		testProblem3();
		testProblem4();
		testProblem5();
		testProblem6();
		testProblem7();
		testProblem8();
	}
	
	  /*********************************************/
	 /***************** Problem 1 *****************/
	/*********************************************/

	private static void testProblem1() {
		System.out.println("\n\nProblem1 test:\n");
		String[] words = {"abcde", "hello", "apple", "kite", "padle", "!@$%", "$$"};
		for (String word : words) {
			System.out.println(word + ": " + checkStringUnique(word) + " " + checkStringUniqueWithoutAdditional(word));
		}
	}
	
	//This method used HashMap, which increases the space complexity, the time complexity is OK
	public static boolean checkStringUnique(String s) {
		HashMap<Character, Integer> characterMap = new HashMap<Character, Integer>();
		
		for(int i = 0; i < s.length(); i++) {
			if(characterMap.containsKey(s.charAt(i))) {
				return false;
			} else {
				characterMap.put(s.charAt(i), 1);
			}
		}
		return true;
	}

	//Even this method works, the time complexity is not good because of 2 loop. Better way should make use of the char index of ASCII
	public static boolean checkStringUniqueWithoutAdditional(String s) {
		char[] charSet = new char[256];
		for(int i = 0; i < s.length(); i++) {
			Boolean found = false;
			for (char character : charSet) {
				//If we don't use the Java Array, even thought there's no thing in the charSet, it still will iterate 256 times
				if(character == s.charAt(i)) {
					found = true;
					break;
				}
			}
			if(found) {
				return false;
			} else {
				charSet[i] = s.charAt(i);
			}
		}
		return true;
	}
	
	  /*********************************************/
	 /***************** Problem 3 *****************/
	/*********************************************/
	
	private static void testProblem3() {
		System.out.println("\n\nProblem3 test:\n");
		String[] words = {"aaabbb", "hello", "apple", "kite", "padle", "!@$$$$%", "$$", "", null};
		for (String word : words) {
			System.out.println(word + " ------> " + removeDuplication(word));
		}
	}
	
	//The solution of the book used C level operations. This solution used Java wrapped Set, but it doesn't respect the order. 
	//Using LinkedHashSet and iterator will respect the order, but decrease the performance. 
	public static String removeDuplication(String s) {
		if(s == null) {
			return null;
		}
		Set<Character> charSet = new HashSet<Character>();
		for(int i = 0; i < s.length(); i++) {
			charSet.add(s.charAt(i));
		}
		char[] characters = new char[charSet.size()];
		int index = 0;
		
		for(Character c : charSet) {
			characters[index] = c;
			index++;
		}				
		return new String(characters);
	}
	
	  /*********************************************/
	 /***************** Problem 4 *****************/
	/*********************************************/
	
	private static void testProblem4() {
		System.out.println("\n\nProblem4 test:\n");
		System.out.println("abc <---> bca   :" + checkAnagrams("abc","cba"));
		System.out.println("Myname <---> Mynamee   :" + checkAnagrams("Myname","Mynamee"));
		System.out.println("ABCDEF <---> FEDCBA   :" + checkAnagrams("ABCDEF","FEDCBA"));
		System.out.println("aaaa <---> bb   :" + checkAnagrams("aaaa","bb"));
		System.out.println("null <---> one   :" + checkAnagrams(null,"one"));
	}

	public static boolean checkAnagrams(String s1, String s2) {
		if(s1 == null || s2 == null) {
			return false;
		}
		char[] characters1 = s1.toCharArray();
		char[] characters2 = s2.toCharArray();
		Arrays.sort(characters1);
		Arrays.sort(characters2);
		return Arrays.equals(characters1, characters2);
	}
	
	  /*********************************************/
	 /***************** Problem 5 *****************/
	/*********************************************/
	
	private static void testProblem5() {
		System.out.println("\n\nProblem5 test:\n");
		String testString1 = "My name is ?";
		String testString2 = "How are\n you man     !";
		
		System.out.println("Before: " + testString1);
		System.out.println("After: " + replaceSpaces(testString1));

		System.out.println("Before: " + testString2);
		System.out.println("After: " + replaceSpaces(testString2));	
	}
	
	//The problem may be meant for operation of charr[], however in Java String can't be passed by reference. 
	//We need to understand that the last element of char[] is always '\0', and char[] always need to know the length, not dynamically.
	public static String replaceSpaces(String s) {
		if(s == null) {
			return null;
		}
		StringBuilder newString = new StringBuilder();
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) != ' ') {
				newString.append(s.charAt(i));
			} else {
				newString.append("%20");
			}
		}
		return newString.toString();
	}
	
	  /*********************************************/
	 /***************** Problem 6 *****************/
	/*********************************************/
	
	private static void testProblem6() {
		System.out.println("\n\nProblem6 test:\n");
		int[][] matrix1 = {{1, 1, 1}, {2, 2, 2}, {3, 3, 3}};
		int[][] matrix2 = {{1, 2, 3, 4}, {1, 2, 3, 4}, {1, 2, 3, 4}};
		
		System.out.println("Matrix 1 before retating:");
		printMatrix(matrix1);
		System.out.println("Matrix 1 after clockwise retating:");
		printMatrix(rotateMatrix(matrix1, true));
		System.out.println("Matrix 1 after anticlockwise retating:");
		printMatrix(rotateMatrix(matrix1, false));

		System.out.println("Matrix 2 before retating:");
		printMatrix(matrix2);
		System.out.println("Matrix 2 after retating:");
		printMatrix(rotateMatrix(matrix2, true));
		System.out.println("Matrix 2 after anticlockwise retating:");
		printMatrix(rotateMatrix(matrix2, false));
	}
	
	public static void printMatrix(int[][] matrix) {
		int height = matrix.length;
		int length = matrix[0].length;
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < length; j++) {
				System.out.print(matrix[i][j] + "   ");
			}
			System.out.println();
		}
	}
	
	//The reason why I used return int[][] not void, is return can handle m*n matrix, void can only handle n*n
	//Compared with the solution in the book, that solution is more space efficient, because it only alloc one int value, here we alloc a whole int[][] matrix;
	public static int[][] rotateMatrix(int[][] matrix, boolean isClockWise) {
		int height = matrix.length;
		int length = matrix[0].length;
		int[][] rotatedMatrix = new int[length][height];
		if(isClockWise) {
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < length; j++) {
					rotatedMatrix[j][height - 1 - i] = matrix[i][j];
				}
			}
		} else {
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < length; j++) {
					rotatedMatrix[length - 1 - j][i] = matrix[i][j];
				}
			}
		}
		return rotatedMatrix;
	}
	
	  /*********************************************/
	 /***************** Problem 7 *****************/
	/*********************************************/
	
	private static void testProblem7() {
		System.out.println("\n\nProblem7 test:\n");
		int[][] matrix1 = {{1, 1, 1, 0}, {2, 2, 2, 2}, {3, 3, 3, 3}};
		int[][] matrix2 = {{1, 0, 3}, {1, 0, 3}, {1, 2, 3}, {1, 2, 3}};
		
		System.out.println("Matrix 1 before expanding zero:");
		printMatrix(matrix1);
		expandMatrixZero(matrix1);
		System.out.println("Matrix 1 after expanding zero:");
		printMatrix(matrix1);

		System.out.println("Matrix 2 before expanding zero:");
		printMatrix(matrix2);
		expandMatrixZero(matrix2);
		System.out.println("Matrix 2 after expanding zero:");
		printMatrix(matrix2);
	}
	
	public static void expandMatrixZero(int[][] matrix) {
		Set<Integer> rows = new HashSet<Integer>();
		Set<Integer> columns = new HashSet<Integer>();
		int height = matrix.length;
		int length = matrix[0].length;
		
		for(int i = 0; i < height; i++) {
			for(int j = 0; j < length; j++) {
				if(matrix[i][j] == 0) {
					rows.add(i);
					columns.add(j);
				}
			}
		}
		for(int i : rows) {
			for(int j = 0; j < length; j++) {
				matrix[i][j] = 0;
			}
		}
		for(int i : columns) {
			for(int j = 0; j < height; j++) {
				matrix[j][i] = 0;
			}
		}
	}
	
	  /*********************************************/
	 /***************** Problem 8 *****************/
	/*********************************************/
	
	private static void testProblem8() {
		System.out.println("\n\nProblem8 test:\n");
		System.out.println("waterbottle <-----> erbottlewat : " + checkStringRotate("waterbottle", "erbottlewat"));
		System.out.println("waterbottle <-----> bottlewatre : " + checkStringRotate("waterbottle", "bottlewatre"));
	}
	
	//The trick is concatenate a rotated string will get the whole string in its substring
	public static boolean checkStringRotate(String s1, String s2) {
		if(s1 == null || s2 == null) {
			return false;
		}
		if(s1.length() != s2.length()) {
			return false;
		}
		return s1.concat(s1).contains(s2);//isSubString == contains
	}
	
}
