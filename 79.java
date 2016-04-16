https://leetcode.com/problems/word-search/

public class Solution {
    private static final int[][] DIRS = {{0,1}, {0,-1}, {1,0}, {-1,0}};
    
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean dfs(char[][] board, String word, int x, int y, int index) {
        if (board[x][y] != word.charAt(index)) {
            return false;
        }
        
        if (index == word.length() - 1) {
            return true;
        }
        
        char ch = word.charAt(index);
        board[x][y] = '*';
        
        for (int i = 0; i < 4; i++) {
            int newX = x + DIRS[i][0];
            int newY = y + DIRS[i][1];
            
            if (newX >=0 && newX < board.length && newY >= 0 && newY < board[0].length) {
                if (dfs(board, word, newX, newY, index + 1)) {
                    return true;
                }
            }
        }
        
        board[x][y] = ch;
        
        return false;
    }
}
