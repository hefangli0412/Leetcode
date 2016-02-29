// 130. Surrounded Regions My Submissions Question
// https://leetcode.com/problems/surrounded-regions/

/* This solution is time out. I have no idea about the reason. Everything seems fine to me. */

public class Solution {
    /* Use DFS.This problem is similar to Number of Islands. 
    In this problem, only the cells on the boarders can not be surrounded. 
    So we can first merge those O's on the boarders like in Number of Islands 
    and replace O's with 'B', and then scan the board and replace all O's left (if any).
    */
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;
        
        int m = board.length;
        int n = board[0].length;
        
        // step 1: mark border
        for (int i = 0; i < n; i++) {
            if (board[0][i] == 'O') {
                dfs(board, m, n, 0, i, 'O', 'B');
            }
            if (board[m - 1][i] == 'O') {
                dfs(board, m, n, m - 1, i, 'O', 'B');
            }
        }
        for (int i = 0; i < m; i++) { // right
            if (board[i][0] == 'O') {
                dfs(board, m, n, i, 0, 'O', 'B');
            }
            if (board[i][n - 1] == 'O') {
                dfs(board, m, n, i, n - 1, 'O', 'B');
            }
        }
        
        // step 2: mark all 'O' and convert all 'B' back to 'O'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'B') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }
    
    private void dfs(char[][] board, int m, int n, int i, int j, char from, char to) {
        board[i][j] = to;
        
        if (i > 0 && board[i - 1][j] == from) 
            dfs(board, m, n, i - 1, j, from, to);
            
        if (i < m - 1 && board[i + 1][j] == from) 
            dfs(board, m, n, i + 1, j, from, to);
        
        if (j > 0 && board[i][j - 1] == from) 
            dfs(board, m, n, i, j - 1, from, to);
            
        if (j < n - 1 && board[i][j + 1] == from) 
            dfs(board, m, n, i, j + 1, from, to);
    }
}

/* The following is the strange working version */

public class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) return;
        
        int len1 = board.length;
        int len2 = board[0].length;
        
        // first column and last column
        for (int i = 0; i < len1; i++) {
            if (board[i][0] == 'O') {
                boundaryDFS(board, i, 0);
            }
            if (board[i][len2 - 1] == 'O') {
                boundaryDFS(board, i, len2 - 1);
            }
        }
        // first row and last row
        for (int j = 0; j < len2; j++) {
            if (board[0][j] == 'O') {
                boundaryDFS(board, 0, j);
            }
            if (board[len1 - 1][j] == 'O') {
                boundaryDFS(board, len1 - 1, j);
            }
        }
        
        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                if (board[i][j] == '*') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }
    
    private void boundaryDFS(char[][] board, int i, int j) {
        int len1 = board.length;
        int len2 = board[0].length;
        
        if (i < 0 || j < 0 || i >= len1 || j >= len2) return;
        if (board[i][j] == 'O') {
            board[i][j] = '*';
        }
        if (i-1 >= 1 && board[i-1][j] == 'O') 
            boundaryDFS(board, i-1, j);
        if (j-1 >= 1 && board[i][j-1] == 'O') 
            boundaryDFS(board, i, j-1);
        if (i+1 < len1-1 && board[i+1][j] == 'O') 
            boundaryDFS(board, i+1, j);
        if (j+1 < len2-1 && board[i][j+1] == 'O') 
            boundaryDFS(board, i, j+1);
    }
}
