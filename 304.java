// 304. Range Sum Query 2D - Immutable
// https://leetcode.com/problems/range-sum-query-2d-immutable/

public class NumMatrix {
    private int[][] sum;
    
    public NumMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        
        int m = matrix.length;
        int n = matrix[0].length;
        sum = new int[m][n];
        for (int i = 0; i < m; i++) {
            int tmp = 0;
            for (int j = 0; j < n; j++) {
                tmp += matrix[i][j];
                if (i == 0) {
                    sum[i][j] = tmp;
                } else {
                    sum[i][j] = tmp + sum[i - 1][j];
                }
            }
        }
    }

    // Be careful about the indexes !!
    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (row1 == 0 && col1 == 0) {
            return sum[row2][col2];
        } else if (row1 == 0) {
            return sum[row2][col2] - sum[row2][col1 - 1];
        } else if (col1 == 0) {
            return sum[row2][col2] - sum[row1 - 1][col2];
        } else {
            return sum[row2][col2] - sum[row2][col1 - 1] - sum[row1 - 1][col2] + sum[row1 - 1][col1 - 1];
        }
    }
}


// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.sumRegion(1, 2, 3, 4);
