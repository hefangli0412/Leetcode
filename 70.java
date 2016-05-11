// https://leetcode.com/problems/climbing-stairs/

public class Solution {
    public int climbStairs(int n) {
        if (n < 2) {
            return 1;
        }
        
        // M[i] denotes the number of different ways to reach i-th stair
        int[] M = new int[3];
        M[0] = 1;
        M[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            M[i % 3] = M[(i - 1) % 3] + M[(i - 2) % 3];
        }
        
        return M[n % 3];
    }
}
