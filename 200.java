// 200. Number of Islands
// https://leetcode.com/problems/number-of-islands/

/* Solution 1 : Union Find */

public class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        
        int m = grid.length;
        int n = grid[0].length;
        UnionFind uf = new UnionFind(m, n, grid);
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') continue;
                
                int p = i * n + j;
                int q;
                
                if (i > 0 && grid[i - 1][j] == '1') {
                    q = p - n;
                    uf.union(p, q);
                }
                if (i < m - 1 && grid[i + 1][j] == '1') {
                    q = p + n;
                    uf.union(p, q);
                }
                if (j > 0 && grid[i][j - 1] == '1') {
                    q = p - 1;
                    uf.union(p, q);
                }
                if (j < n - 1 && grid[i][j + 1] == '1') {
                    q = p + 1;
                    uf.union(p, q);
                }
            }
        }
        
        return uf.count();
    }
}

class UnionFind {
    private int[] id;
    private int count;
    
    public UnionFind(int m, int n, char[][] grid) {
        id = new int[m * n];
        for (int i = 0; i < m * n; i++) {
            id[i] = i;
        }
        
        count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    count++;
                }
            }
        }
    }
    
    public int find(int i) {
        while (id[i] != i) {
            id[i] = id[id[i]];
            i = id[i];
        }
        
        return i;
    }
    
    public boolean union(int i, int j) {
        int p_i = find(i);
        int p_j = find(j);
        
        if (p_i == p_j) return false;
        
        id[p_i] = p_j;
        count--;
        return true;
    }
    
    public int count() {
        return count;
    }
}

/* Solution 2 : DFS */

public class Solution {
    private int count;
    
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        
        count = 0;
        
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    count++;
                    dfs(grid, visited, m, n, i, j);
                }
            }
        }
        
        return count;
    }
    
    private void dfs(char[][] grid, boolean[][] visited, int m, int n, int x, int y) {
        if (grid[x][y] == '0') return;
        
        visited[x][y] = true;
        
        if (x > 0 && !visited[x - 1][y]) {
            dfs(grid, visited, m, n, x - 1, y);
        }
        if (x < m - 1 && !visited[x + 1][y]) {
            dfs(grid, visited, m, n, x + 1, y);
        }
        if (y > 0 && !visited[x][y - 1]) {
            dfs(grid, visited, m, n, x, y - 1);
        }
        if (y < n - 1 && !visited[x][y + 1]) {
            dfs(grid, visited, m, n, x, y + 1);
        }
    }
}
