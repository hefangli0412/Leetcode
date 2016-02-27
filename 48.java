// 48. Rotate Image
// https://leetcode.com/problems/rotate-image/

public class Solution {
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        verticalFlip(matrix, rows, cols);
        
        diagnalFlip(matrix, rows, cols);
    }
    
    private void verticalFlip(int[][] matrix, int rows, int cols) {
        for (int i = 0; i < rows / 2; i++) {
            for (int j = 0; j < cols; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[rows - 1 - i][j];
                matrix[rows - 1 - i][j] = tmp;
            }
        }
    }
    
    private void diagnalFlip(int[][] matrix, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = cols - 1; j > i; j--) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }
}
