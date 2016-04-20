https://leetcode.com/problems/sudoku-solver/

public class Solution {
    public void solveSudoku(char[][] board) {
        solveSudokuHelper(board);
    }
    
    private boolean solveSudokuHelper(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        // 到现在的状态这个位置能填这个数，但后面可能没法解
                        if (isValidSudoku(board, i, j, c)) {
                            board[i][j] = c;
                            if (solveSudokuHelper(board)) {
                                return true; // return earlier
                            } else {
                                board[i][j] = '.';
                            }
                        }
                    }
                    return false; // return earlier
                }
            }
        }
        return true;
    }
    
    private boolean isValidSudoku(char[][] board, int i, int j, char c) {
        for (int row = 0; row < 9; row++) {
            if (board[row][j] == c) {
                return false;
            }
        }
        
        for (int col = 0; col < 9; col++) {
            if (board[i][col] == c) {
                return false;
            }
        }
        
        for (int row = (i / 3) * 3; row < (i / 3) * 3 + 3; row++) {
            for (int col = (j / 3) * 3; col < (j / 3) * 3 + 3; col++) {
                if (board[row][col] == c) {
                    return false;
                }
            }
        }
        
        return true;
    }
}
