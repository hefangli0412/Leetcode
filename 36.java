https://leetcode.com/problems/valid-sudoku/

public class Solution {
    // R+pos+num
    // C+pos+num
    // P+pos+num
    public boolean isValidSudoku(char[][] board) {
        Set<String> set = new HashSet<>();
        int len = board.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                char ch = board[i][j];
                if (ch == '.') {
                    continue;
                }
                int index = ch - 'a';
                if (!set.add("R-" + i + "-" + ch)) {
                    return false;
                }
                if (!set.add("C-" + j + "-" + ch)) {
                    return false;
                }
                if (!set.add("P-" + String.valueOf(i / 3 * 3 + j / 3 )+ "-" + ch)) {
                    return false;
                }
            }
        }
        return true;
    }
}
