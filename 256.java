// 256. Paint House
// https://leetcode.com/problems/paint-house/

public class Solution {
    public int minCost(int[][] costs) {
        int n = costs.length;
        if (n == 0) return 0;
        
        int[][] M = new int[n][3];
        
        M[0][0] = costs[0][0];
        M[0][1] = costs[0][1];
        M[0][2] = costs[0][2];
        
        for (int i = 1; i < n; i++) {
            M[i][0] = Math.min(M[i - 1][1], M[i - 1][2]) + costs[i][0];
            M[i][1] = Math.min(M[i - 1][2], M[i - 1][0]) + costs[i][1];
            M[i][2] = Math.min(M[i - 1][0], M[i - 1][1]) + costs[i][2];
        }
        
        return Math.min(Math.min(M[n - 1][0], M[n - 1][1]), M[n - 1][2]);
    }
}
