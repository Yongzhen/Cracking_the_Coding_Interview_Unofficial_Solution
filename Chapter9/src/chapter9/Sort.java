package chapter9;

public class Sort {

	private static void switchOrder(int[] array, int leftIndex, int rightIndex) {
		int tmp = array[leftIndex];
		array[leftIndex] = array[rightIndex];
		array[rightIndex] = tmp;
	}
	
	  /*********************************************/
	 /**************** Bubble Sort ****************/
	/*********************************************/
	
	public static int[] bubbleSort(int[] unsorted, boolean ascending) {
		for(int i = 0; i < unsorted.length - 1; i++) {
			for(int j = 0; j < unsorted.length - i - 1; j++) {
				if(ascending) {
					if(unsorted[j] > unsorted[j + 1]) {
						switchOrder(unsorted, j, j + 1);
					}
				} else {
					if(unsorted[j] < unsorted[j + 1]) {
						switchOrder(unsorted, j, j + 1);
					}
				}
			}
		}
		return unsorted;
	}
	
	  /*********************************************/
	 /************** Selection Sort ***************/
	/*********************************************/
	
	public static int[] selectionSort(int[] unsorted, boolean ascending) {
		for(int i = 0; i < unsorted.length; i++) {
			int minMax = ascending ? Integer.MAX_VALUE : Integer.MIN_VALUE;
			int index = 0;
			for(int j = i; j < unsorted.length; j++) {
				if(ascending) {
					if(unsorted[j] < minMax) {
						minMax = unsorted[j];
						index = j;
					}
				} else {
					if(unsorted[j] > minMax) {
						minMax = unsorted[j];
						index = j;
					}
				}
			}
			switchOrder(unsorted, i, index);
		}
		return unsorted;
	}
	
	  /*********************************************/
	 /************** Insertion Sort ***************/
	/*********************************************/
	
	public static int[] insertionSort(int[] unsorted, boolean ascending) {
		for(int i = 1; i < unsorted.length; i++) {
			if(ascending) {
				for(int j = i - 1; j >= 0 && unsorted[j] > unsorted[j + 1]; j--) {
					switchOrder(unsorted, j, j + 1);
				}
			} else {
				for(int j = i - 1; j >= 0 && unsorted[j] < unsorted[j + 1]; j--) {
					switchOrder(unsorted, j, j + 1);
				}
			}
		}
		return unsorted;
	}
	
	  /*********************************************/
	 /**************** Merge Sort *****************/
	/*********************************************/
	
	public static int[] mergeSort(int[] unsorted, boolean ascending) {
		if(unsorted.length == 1) {
			return unsorted;
		}
		int half = unsorted.length / 2;
		int[] array1 = new int[half];
		int[] array2 = new int[unsorted.length - half];
		
		System.arraycopy(unsorted, 0, array1, 0, array1.length);
		System.arraycopy(unsorted, half, array2, 0, array2.length);
		
		array1 = mergeSort(array1, ascending);
		array2 = mergeSort(array2, ascending);
		
		return mergeSortSub(array1,array2, ascending);
	}
	
	private static int[] mergeSortSub(int[] array1, int[] array2, boolean ascending) {
		int index1 = 0;
		int index2 = 0;
		int resultIndex = 0;
		
		int[] result = new int[array1.length + array2.length];
		
		while(true) {
			if(array1[index1] < array2[index2]) {
				result[resultIndex++] = ascending ? array1[index1++] : array2[index2++];
			} else {
				result[resultIndex++] = ascending ? array2[index2++] : array1[index1++];
			}
			if(index1 >= array1.length || index2 >= array2.length) {
				break;
			}
		}
		while(index1 < array1.length) {
			result[resultIndex++] = array1[index1++];
		}
		while(index2 < array2.length) {
			result[resultIndex++] = array2[index2++];
		}
		return result;
	}
	
	  /*********************************************/
	 /**************** Quick Sort *****************/
	/*********************************************/
	
	public static int[] quickSort(int[] unsorted, boolean ascending) {
		quickSortSub(unsorted, 0, unsorted.length - 1, ascending);
		return unsorted;
	}
	
	private static void quickSortSub(int[] unsorted, int leftIndex, int rightIndex, boolean ascending) {
		int i = leftIndex;
		int j = rightIndex;
		int pivot = unsorted[(leftIndex + rightIndex) / 2];
		
		while(i <= j) {
			if(ascending) {
				while(unsorted[i] < pivot) {
					i++;
				}
				while(unsorted[j] > pivot) {
					j--;
				}
			} else {
				while(unsorted[i] > pivot) {
					i++;
				}
				while(unsorted[j] < pivot) {
					j--;
				}
			}
			if(i <= j) {
				switchOrder(unsorted, i, j);
				i++;
				j--;
			}
		}
		if(leftIndex < j) {
			quickSortSub(unsorted, leftIndex, j, ascending);
		}
		if(i < rightIndex) {
			quickSortSub(unsorted, i, rightIndex, ascending);
		}
	}
	
	  /*********************************************/
	 /**************** Bucket Sort ****************/
	/*********************************************/
	
	//By now this version can only handle integer >= 0
	public static int[] bucketSort(int[] unsorted, boolean ascending) {
		int maxValue = getMax(unsorted);
		return bucketSortSub(unsorted, maxValue, ascending);
	}
	
	private static int[] bucketSortSub(int[] unsorted, int maxValue, boolean ascending) {
		int[] bucket = new int[maxValue + 1];
		int[] result = new int[unsorted.length];
		
		for(int i = 0; i < unsorted.length; i++) {
			bucket[unsorted[i]]++;
		}
		int resultIndex = 0;
		if(ascending) {
			for(int i = 0; i < bucket.length; i++) {
				for(int j = 0; j < bucket[i]; j++) {
					result[resultIndex++] = i; 
				}
			}
		} else {
			for(int i = bucket.length - 1; i >= 0; i--) {
				for(int j = 0; j < bucket[i]; j++) {
					result[resultIndex++] = i; 
				}
			}
		}
		return result;
	}
	
	private static int getMax(int[] array) {
		int max = 0;
		for(int i = 0; i < array.length; i++) {
			if(max < array[i]) {
				max = array[i];
			}
		}
		return max;
	}
	
}
