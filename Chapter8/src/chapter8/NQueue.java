package chapter8;

import java.util.*;

/*********************************************/
/***************** Problem 8 *****************/
/*********************************************/

public class NQueue {
    private int[] columnInRow;
    private boolean[] columnsAvailable;
    private boolean[] leftDiag;
    private boolean[] rightDiag;
    private int N;
    private List<String[]> results;
    
    public NQueue(int n) {
        N = n;
        results = new ArrayList<String[]>();
        columnInRow = new int[n];
        columnsAvailable = new boolean[n];
        leftDiag = new boolean[n * 2 - 1];
        rightDiag = new boolean[n * 2 -1];
        
        for(int i = 0; i < n; i++) {
            columnInRow[i] = -1;
            columnsAvailable[i] = true;
        }
        for(int i = 0; i < 2 * n - 1; i++) {
            leftDiag[i] = true;
            rightDiag[i] = true;
        }
    }
    
    public List<String[]> generateAllPosibilities() {
    	putQueue(0);
    	return results;
    }
    
    public void printResult() {
    	System.out.println("Possible Solution for " + N + " Queue:");
    	for(String[] solution : results) {
    		System.out.println();
    		for(String s : solution) {
    			System.out.println(s);
    		}
    		System.out.println();
    	}
    }
    
    private void putQueue(int row) {
        for(int col = 0; col < N; col++) {
            if(row >= N) {
                break;
            }
            if(columnsAvailable[col] && leftDiag[col + row] && rightDiag[N - 1 - col + row]) {
                columnInRow[row] = col;
                columnsAvailable[col] = false;
                leftDiag[col + row] =false;
                rightDiag[N - 1 - col + row] = false;
                if(row < N - 1) {
                    putQueue(row + 1);
                } else {
                    addResult();
                }
                columnsAvailable[col] = true;
                leftDiag[col + row] =true;
                rightDiag[N - 1 - col + row] = true;
            }
        }
    }
    
    private void addResult() {
        String[] solution = new String[N];
        for(int i = 0; i < N; i++) {
            solution[i] = "";
            for(int j = 0; j < N; j++) {
                if(columnInRow[i] != j) {
                    solution[i] = solution[i] + ". ";
                } else {
                    solution[i] = solution[i] + "Q";
                }
            }
        }
        results.add(solution);
    }
    
}
