https://leetcode.com/problems/valid-sudoku/

public class Solution {
    public boolean isValidSudoku(char[][] board) {
        int len = board.length;
        int[] hset = new int[len];
        int[] vset = new int[len];
        int[] bckt = new int[len];
        
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (board[i][j] != '.') {
                    int idx = 1 << (board[i][j] - '1');
                    if ((hset[i] & idx) != 0 || (vset[j] & idx) != 0 || (bckt[(i / 3) * 3 + j / 3] & idx) != 0) {
                        return false;
                    }
                    hset[i] |= idx;
                    vset[j] |= idx;
                    bckt[(i / 3) * 3 + j / 3] |= idx;
                }
            }
        }
        
        return true;
    }
}
