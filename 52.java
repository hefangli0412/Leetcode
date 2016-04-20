https://leetcode.com/problems/n-queens-ii/

public class Solution {
    public int totalNQueens(int n) {
		int[] row = new int[n];
		return totalNQueensHelper(row, n, 0);
	}
	
	private int totalNQueensHelper(int[] row, int n, int index) {
		if (index == n) {
			return 1;
		}
		
		int count = 0;
		for (int i = 0; i < n; i++) {
			row[index] = i;
			if (isValidQueen(row, index)) {
				count += totalNQueensHelper(row, n, index + 1);
			}
		}
		
		return count;
	}
	
	private boolean isValidQueen(int[] row, int index) {
		for (int i = 0; i < index; i++) {
			if (row[i] == row[index] || index - i == Math.abs(row[index] - row[i])) {
				return false;
			}
		}
		return true;
	}
}
