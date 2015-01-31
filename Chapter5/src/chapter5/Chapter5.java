package chapter5;

public class Chapter5 {

	public static void main(String[] args) {
		testProblem1();
		testProblem2();
		testProblem3();
		testProblem5();
		testProblem6();
	}

	/*********************************************/
	/***************** Problem 1 *****************/
	/*********************************************/

	//I didn't figure this out, this is the official solution
	public static int bitsCopy(int copyTo, int copyFrom, int start, int end) {
		int max = ~0;
		int left = max - (1 << end - 1);
		int right = 1 << start - 1;

		int mask = left | right;

		return (copyTo & mask) | copyFrom << start;
	}

	private static void testProblem1() {
		System.out.println("\n\nProblem1 test:\n");

		int copyTo = 0b10000000000;
		int copyFrom = 0b10101;

		System.out.println("Copy from " + Integer.toBinaryString(copyFrom) + " to " + Integer.toBinaryString(copyTo) + " from 1 - 5 \nresult: " + Integer.toBinaryString(bitsCopy(copyTo, copyFrom, 1, 5)));
	}

	/*********************************************/
	/***************** Problem 2 *****************/
	/*********************************************/

	public static void printBinary(String stringNumber) {
		int intPart = Integer.parseInt(stringNumber.substring(0, stringNumber.indexOf('.')));
		double decPart = Double.parseDouble(stringNumber.substring(stringNumber.indexOf('.')));

		String intPartBinary = Integer.toBinaryString(intPart);
		StringBuilder decPartBinary = new StringBuilder();

		while(decPart > 0) {
			if(decPartBinary.length() > 32) {
				System.out.println("Error");
				return;
			}
			if(decPart == 1) {
				decPartBinary.append(1);
				break;
			}
			decPart = decPart * 2;
			if(decPart >= 1) {
				decPartBinary.append(1);
				decPart = decPart - 1;
			} else {
				decPartBinary.append(0);
			}
		}
		System.out.println(intPartBinary + "." + decPartBinary);
	}

	private static void testProblem2() {
		System.out.println("\n\nProblem2 test:\n");

		String stringNumber1 = "100.0";
		String stringNumber2 = "1024.01";
		String stringNumber3 = "22.125";

		System.out.println("Decimal: " + stringNumber1);
		printBinary(stringNumber1);
		System.out.println("Decimal: " + stringNumber2);
		printBinary(stringNumber2);
		System.out.println("Decimal: " + stringNumber3);
		printBinary(stringNumber3);
	}

	/*********************************************/
	/***************** Problem 3 *****************/
	/*********************************************/

	//At first I missed the part of rearranging all the 1s to be as far right as possible, vice versa.
	public static int[] findNearestNumberWithSameOneBits(int integer) {
		int zeroOneBit = -1;
		int oneZeroBit = -1;
		int currentBit = 0;
		int tmpInteger = integer;

		while(tmpInteger > 0) {
			if(tmpInteger % 2 == 1) {
				if(oneZeroBit == -1) {
					if((tmpInteger / 2) % 2 == 0) {
						oneZeroBit = currentBit;
					}
				}
			} else {
				if(zeroOneBit == -1) {
					if((tmpInteger / 2) % 2 == 1) {
						zeroOneBit = currentBit;
					}
				}
			}
			tmpInteger = tmpInteger / 2;
			currentBit++;
		}
		int nextSmallest = 0;
		int nextLargest = 0;

		if(oneZeroBit != -1) {
			nextLargest = integer + (1 << oneZeroBit);
		}
		if(zeroOneBit != -1) {
			nextSmallest = integer - (1 << zeroOneBit);
		}
		int[] result = {switchOnesToLeftOrRight(nextSmallest, zeroOneBit, false), switchOnesToLeftOrRight(nextLargest, oneZeroBit, true)};
		return result;
	}

	private static int switchOnesToLeftOrRight(int integer, int bit, boolean toRight) {
		int tmpInteger = integer;
		int countOfOne = 0;

		for(int i = 0; i < bit; i++) {
			if(tmpInteger % 2 == 1) {
				countOfOne++;
				integer = integer - (1 << i);
			}
			tmpInteger = tmpInteger / 2;
		}
		for(int i = 0; i < countOfOne; i++) {
			if(toRight) {
				integer = integer + (1 << i);
			} else {
				integer = integer + (1 << (bit - 1 - i));
			}
		}
		return integer;
	}


	private static void testProblem3() {
		System.out.println("\n\nProblem3 test:\n");

		int testNumber1 = 44;
		int[] testResult1 = findNearestNumberWithSameOneBits(testNumber1);

		System.out.println("Test 1 number: " + testNumber1 + ", binary: " + Integer.toBinaryString(testNumber1));
		System.out.println("Smallest: " + testResult1[0] + ", binary: " + Integer.toBinaryString(testResult1[0]));
		System.out.println("Largest: " + testResult1[1] + ", binary: " + Integer.toBinaryString(testResult1[1]));

		int testNumber2 = 19;
		int[] testResult2 = findNearestNumberWithSameOneBits(testNumber2);

		System.out.println("\nTest 2 number: " + testNumber2 + ", binary: " + Integer.toBinaryString(testNumber2));
		System.out.println("Smallest: " + testResult2[0] + ", binary: " + Integer.toBinaryString(testResult2[0]));
		System.out.println("Largest: " + testResult2[1] + ", binary: " + Integer.toBinaryString(testResult2[1]));

		int testNumber3 = 31;
		int[] testResult3 = findNearestNumberWithSameOneBits(testNumber3);

		System.out.println("\nTest 3 number: " + testNumber3 + ", binary: " + Integer.toBinaryString(testNumber3));
		System.out.println("Smallest: " + testResult3[0] + ", binary: " + Integer.toBinaryString(testResult3[0]));
		System.out.println("Largest: " + testResult3[1] + ", binary: " + Integer.toBinaryString(testResult3[1]));
	}



	/*********************************************/
	/***************** Problem 4 *****************/
	/*********************************************/

	// ((n & (n-1)) == 0) checks whether the integer is an power of 2
	// Oh I forgot 0.

	/*********************************************/
	/***************** Problem 5 *****************/
	/*********************************************/

	//I don't quite understand the official solution
	public static int numberOfBitsNeededToConvert(int fromNumber, int toNumber) {
		int countOfConvertion = 0;
		while(fromNumber > 0 || toNumber > 0) {
			if((fromNumber % 2) != (toNumber % 2)) {
				countOfConvertion++;
			}
			fromNumber /= 2;
			toNumber /= 2;
		}
		return countOfConvertion;
	}

	private static void testProblem5() {
		System.out.println("\n\nProblem5 test:\n");

		int convertFrom1 = 31;
		int convertTo1 = 14;
		
		System.out.println("Test 1 convert " + convertFrom1 + " to " + convertTo1 + " Bits needed to change: " + numberOfBitsNeededToConvert(convertFrom1, convertTo1));
	}

	/*********************************************/
	/***************** Problem 6 *****************/
	/*********************************************/
	
	//This is much worse than the official solution
	public static int switchOddAndEven(int integer) {
		int oneBits = Integer.bitCount(integer);
		int tmpInteger = integer;
		int currentBit = 0;
		
		while(oneBits > 0) {
			if((tmpInteger & 1) == 1) {
				oneBits--;
			}
			if(((tmpInteger >> 1) & 1) == 1) {
				oneBits--;
			}
			if((tmpInteger & 1) != ((tmpInteger >> 1) & 1)) {
				integer = integer - ((tmpInteger & 1) << currentBit) - (((tmpInteger >> 1) & 1) << (currentBit + 1));
				integer = integer + ((tmpInteger & 1) << (currentBit + 1)) + (((tmpInteger >> 1) & 1) << currentBit);
			}
			currentBit += 2;
			tmpInteger >>= 2;
		}
		return integer;
	}
	
	private static void testProblem6() {
		System.out.println("\n\nProblem6 test:\n");

		int testInteger1 = 0b101010101010;
		int testInteger2 = 0b111010000;

		System.out.println("Switch odd and even for " + Integer.toBinaryString(testInteger1) + "\nresult: " + Integer.toBinaryString(switchOddAndEven(testInteger1)));
		System.out.println("Switch odd and even for " + Integer.toBinaryString(testInteger2) + "\nresult: " + Integer.toBinaryString(switchOddAndEven(testInteger2)));
	}
	
	/*********************************************/
	/***************** Problem 7 *****************/
	/*********************************************/
	
	//Pending
	
	

}
