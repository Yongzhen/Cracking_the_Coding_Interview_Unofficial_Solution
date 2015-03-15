package chapter9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Chapter9 {

	public static void main(String[] args) {
		testSort();
		testProblem1();
		testProblem2();
		testProblem5();
		testProblem6();
		testProblem7();
	}

	private static void testSort() {
		System.out.println("Test Sort:");
		testBubbleSort();
		testSelectionSort();
		testInsertionSort();
		testMergeSort();
		testMergeSortInPlace();
		testQuickSort();
		testBucketSort();
	}
	
	private static void testBubbleSort() {
		int[] testArray = {24,2,45,20,56,75,2,56,99,53,12};
		System.out.println("\nTest Array:");
		printArray(testArray);
		
		System.out.println("\nBubble Sort:");
		printArray(Sort.bubbleSort(testArray, true));
		printArray(Sort.bubbleSort(testArray, false));
	}
	
	private static void testSelectionSort() {
		int[] testArray = {24,2,45,20,56,75,2,56,99,53,12};
		System.out.println("\nTest Array:");
		printArray(testArray);
		
		System.out.println("\nSelection Sort:");
		printArray(Sort.selectionSort(testArray, true));
		printArray(Sort.selectionSort(testArray, false));
	}
	
	private static void testInsertionSort() {
		int[] testArray = {24,2,45,20,56,75,2,56,99,53,12};
		System.out.println("\nTest Array:");
		printArray(testArray);
		
		System.out.println("\nInsertion Sort:");
		printArray(Sort.insertionSort(testArray, true));
		printArray(Sort.insertionSort(testArray, false));
	}
	
	private static void testMergeSort() {
		int[] testArray = {24,2,45,20,56,75,2,56,99,53,12};
		System.out.println("\nTest Array:");
		printArray(testArray);
		
		System.out.println("\nMerge Sort:");
		printArray(Sort.mergeSort(testArray, true));
		printArray(Sort.mergeSort(testArray, false));
	}
	
	private static void testMergeSortInPlace() {
		int[] testArray = {24,2,45,20,56,75,2,56,99,53,12};
		System.out.println("\nTest Array:");
		printArray(testArray);
		
		System.out.println("\nMerge Sort in Place:");
		Sort.mergeSortInPlace(testArray);
		printArray(testArray);
	}
	
	private static void testQuickSort() {
		int[] testArray = {24,2,45,20,56,75,2,56,99,53,12};
		System.out.println("\nTest Array:");
		printArray(testArray);
		
		System.out.println("\nQuick Sort:");
		printArray(Sort.quickSort(testArray, true));
		printArray(Sort.quickSort(testArray, false));
	}
	
	private static void testBucketSort() {
		int[] testArray = {24,2,45,20,56,75,2,56,99,53,12};
		System.out.println("\nTest Array:");
		printArray(testArray);
		
		System.out.println("\nBucket Sort:");
		printArray(Sort.bucketSort(testArray, true));
		printArray(Sort.bucketSort(testArray, false));
	}

	private static void printArray(int[] array) {
		System.out.println(Arrays.toString(array));
	}

	/*********************************************/
	/***************** Problem 1 *****************/
	/*********************************************/

	public static void mergeBToA(int[] a, int[] b, int elementsInA, int elementsInB) {
		int resultIndex = elementsInA + elementsInB - 1;
		int indexA = elementsInA - 1;
		int indexB = elementsInB - 1;

		while(true) {
			if(a[indexA] > b[indexB]) {
				a[resultIndex--] = a[indexA--];
			} else {
				a[resultIndex--] = b[indexB--];
			}
			if(indexA < 0 || indexB < 0) {
				break;
			}
		}
		while(indexB >= 0) {
			a[resultIndex--] = b[indexB--];
		}
	}

	private static void testProblem1() {
		System.out.println("\n\nProblem1 test:\n");

		int[] a = new int[20];
		int[] tmp = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20};

		System.arraycopy(tmp, 0, a, 0, 10);

		int[] b = {1, 3 , 5, 7, 100};

		System.out.println("Before merge:");
		printArray(a);
		printArray(b);

		System.out.println("After merge:");
		mergeBToA(a, b, 10, 5);
		printArray(a);		
	}

	/*********************************************/
	/***************** Problem 2 *****************/
	/*********************************************/

	//I didn't figure this out, I am not familiar with comparator
	public static void sortAnagram(String[] stringArray) {
		Arrays.sort(stringArray, new AnagramComparator());
	}

	private static void testProblem2() {
		System.out.println("\n\nProblem2 test:\n");

		String[] testStringArray = {"abc", "ehllo", "sssssas", "assssss", "hello", "cba"};
		System.out.println("Before Sort: " + Arrays.toString(testStringArray));
		sortAnagram(testStringArray);
		System.out.println("After Sort: " + Arrays.toString(testStringArray));
	}

	/*********************************************/
	/***************** Problem 3 *****************/
	/*********************************************/
	// I don't quite understand how it works?


	/*********************************************/
	/***************** Problem 4 *****************/
	/*********************************************/

	//Use bucket sort, because in this case n is very large, and string has limited number of characters so n+m < nlog(n).

	/*********************************************/
	/***************** Problem 5 *****************/
	/*********************************************/

	//At the beginning, I forgot to handle null and ""
	public static int getLocationOfString(String[] stringArray, String s) {
		if(s == null || stringArray == null) {
			return -1;
		}
		if(s == "") {
			for(int i = 0; i < stringArray.length; i++) {
				if(stringArray[i] == "") {
					return i;
				}
			}
			return -1;
		}
		return searchStringArray(stringArray, 0, stringArray.length - 1, s);
	}

	private static int searchStringArray(String[] stringArray, int leftIndex, int rightIndex, String s) {
		if(leftIndex > rightIndex || (leftIndex == rightIndex && stringArray[leftIndex] != s)) {
			return -1;
		}
		int half = (leftIndex + rightIndex) / 2;
		int currentIndex = half;

		while(stringArray[currentIndex] == "") {
			currentIndex--;
			if(currentIndex == leftIndex) {
				break;
			}
		}
		if(stringArray[currentIndex] == s) {
			return currentIndex;
		} else if(stringArray[currentIndex] == "" || stringArray[currentIndex].compareTo(s) < 0) {
			return searchStringArray(stringArray, ++half, rightIndex, s);
		} else {
			return searchStringArray(stringArray, leftIndex, --currentIndex, s);
		}		
	}

	private static void testProblem5() {
		System.out.println("\n\nProblem5 test:\n");

		String[] testStringArray = {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};

		System.out.println("Test String Array: " + Arrays.toString(testStringArray));
		System.out.println("Location of 'ball': " + getLocationOfString(testStringArray, "ball"));
		System.out.println("Location of 'car': " + getLocationOfString(testStringArray, "car"));
		System.out.println("Location of 'ballcar': " + getLocationOfString(testStringArray, "ballcar"));
	}

	/*********************************************/
	/***************** Problem 6 *****************/
	/*********************************************/

	//I tried a lot of time using the diagonal, but never thought about the inverse diagonal
	public static int[] findElementInSortedMatrix(int[][] matrix, int element) {
		int rows = matrix.length;
		int columns = matrix[0].length;

		int currentRow = 0;
		int currentColumn = columns - 1;
		int[] result = null;

		while(currentRow < rows && currentColumn > 0) {
			if(matrix[currentRow][currentColumn] == element) {
				result = new int[2];
				result[0] = currentRow;
				result[1] = currentColumn;
				break;
			}
			if(matrix[currentRow][currentColumn] < element) {
				currentRow++;
			} else {
				currentColumn--;
			}
		}
		return result;
	}	

	private static void testProblem6() {
		System.out.println("\n\nProblem6 test:\n");

		int[][] testMatrix = {
				{1, 2, 3, 4, 5},
				{6, 7, 8, 9, 10},
				{11, 12, 13, 14, 15},
				{16, 17, 18, 19, 20},
				{21, 22, 23, 24, 25}};	

		System.out.println("Test Matrix:");
		for(int i = 0; i < testMatrix.length; i++) {
			for(int j = 0; j < testMatrix[0].length; j++) {
				System.out.print(testMatrix[i][j] + " ");
			}
			System.out.println();
		}
		int[] position1 = findElementInSortedMatrix(testMatrix, 14);
		System.out.println("\nLocation of 14: " + position1[0] +"," + position1[1]);

		int[] position2 = findElementInSortedMatrix(testMatrix, 25);
		System.out.println("Location of 25: " + position2[0] +"," + position2[1]);

		int[] postion3 = findElementInSortedMatrix(testMatrix, 100);
		System.out.println("Location of 100: " + postion3);
	}


	/*********************************************/
	/***************** Problem 7 *****************/
	/*********************************************/
	
	//This solution becomes too complicate, but I think the official solution is not correct. BTW, it seems can also make it a matrix and do a search through it. I will try that later.
	public static ArrayList<CircusPeople> findLongestSequence(ArrayList<CircusPeople> allPeople) {
		ArrayList<CircusPeople>[] solutions = new ArrayList[allPeople.size()];
		
		Collections.sort(allPeople);
		
		findAllLongestInscreasingSubSequence(allPeople, solutions, 0);
		
		ArrayList<CircusPeople> bestSolution = new ArrayList<CircusPeople>();
		
		for(int i = 0; i < solutions.length; i++) {
			bestSolution = longerSequence(bestSolution, solutions[i]);
		}
		return bestSolution;
	}
	
	private static void findAllLongestInscreasingSubSequence(ArrayList<CircusPeople> allPeople, ArrayList<CircusPeople>[] solutions, int currentIndex) {
		if(currentIndex >= allPeople.size()) {
			return;
		}
		CircusPeople currentPeople = allPeople.get(currentIndex);
		ArrayList<CircusPeople> currentLongestSequence = null;
		
		for(int i = 0; i < currentIndex; i++) {
			if(allPeople.get(i).isBefore(currentPeople)){
				currentLongestSequence = longerSequence(currentLongestSequence, solutions[i]);
			}
		}
		ArrayList<CircusPeople> newSolution = new ArrayList<CircusPeople>();
		if(currentLongestSequence != null) {
			newSolution.addAll(currentLongestSequence);
		}
		newSolution.add(currentPeople);
		solutions[currentIndex] = newSolution;
		findAllLongestInscreasingSubSequence(allPeople, solutions, currentIndex + 1);
	}
	
	private static ArrayList<CircusPeople> longerSequence(ArrayList<CircusPeople> s1, ArrayList<CircusPeople> s2) {
		if(s1 == null) {
			return s2;
		} else if(s2 == null) {
			return s1;
		}
		return s1.size() > s2.size() ? s1 : s2;
	}
	
	private static void testProblem7() {
		System.out.println("\n\nProblem7 test:\n");
		ArrayList<CircusPeople> people = new ArrayList<CircusPeople>();
		people.add(new CircusPeople(65, 100));
		people.add(new CircusPeople(70, 30));
		people.add(new CircusPeople(56, 90));
		people.add(new CircusPeople(75, 190));
		people.add(new CircusPeople(60, 95));
		people.add(new CircusPeople(68, 110));

		System.out.println("Before sorting: ");
		System.out.println(people.toString());
		
		System.out.println("Longest sequence");
		System.out.println(findLongestSequence(people).toString());
	}

}
