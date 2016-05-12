// https://leetcode.com/problems/minimum-path-sum/

public class Solution {
    public int minPathSum(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        
        int len1 = grid.length;
        int len2 = grid[0].length;
        int[][] M = new int[2][len2];
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (i == 0 && j == 0) {
                    M[i % 2][j] = grid[i][j];
                } else if (i == 0) {
                    M[i % 2][j] = grid[i][j] + M[i % 2][j - 1];
                } else if (j == 0) {
                    M[i % 2][j] = grid[i][j] + M[(i - 1) % 2][j];
                } else {
                    M[i % 2][j] = grid[i][j] + Math.min(M[i % 2][j - 1], M[(i - 1) % 2][j]);
                }
            }
        }
        return M[(len1 - 1) % 2][len2 - 1];
    }
}
