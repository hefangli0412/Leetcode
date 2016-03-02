// 329. Longest Increasing Path in a Matrix
// https://leetcode.com/problems/longest-increasing-path-in-a-matrix/

/* To get max length of increasing sequences:

1. Do DFS from every cell
2. Compare every 4 direction and skip cells that are out of boundary or smaller
3. Get matrix max from every cell's max
4. Use matrix[x][y] <= matrix[i][j] so we don't need a visited[m][n] array
5. The key is to cache the distance because it's highly possible to revisit a cell
*/

public class Solution {
    private static final int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
    
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        int max = 0;
        
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] cache = new int[m][n]; // cache
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, dfs(matrix, m, n, i, j, cache));
            }
        }
        
        return max;
    }
    
    private int dfs(int[][] matrix, int m, int n, int i, int j, int[][] cache) {
        if (cache[i][j] != 0) return cache[i][j]; // cache
        
        int max = 1;
        
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            
            if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] < matrix[i][j]) {
                max = Math.max(max, 1 + dfs(matrix, m, n, x, y, cache));
            }
        }
        
        cache[i][j] = max;
        return max;
    }
}
