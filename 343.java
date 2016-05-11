// https://leetcode.com/problems/integer-break/

public class Solution {
    public int integerBreak(int n) {
        if (n < 2) {
            return 0;
        }
        int[] M = new int[n + 1];
        M[2] = 1;
        for (int i = 3; i <= n; i++) {
            // j denotes the first number, i - j denots the second number
            // the second number is not to be divided further
            // the first number may be divided later
            for (int j = 1; j < i; j++) {
                M[i] = Math.max(M[i], j * (i - j));
                M[i] = Math.max(M[i], M[j] * (i - j));
            }
        }
        return M[n];
    }
}
