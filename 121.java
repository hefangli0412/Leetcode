// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/

public class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        
        // M[i] denotes the largest sum of a subarray of 1,2,..,i ending at i.
        int[] M = new int[2];
        int global_max = 0;
        
        M[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            M[i % 2] = Math.max(0, M[(i - 1) % 2] + prices[i] - prices[i - 1]);
            global_max = Math.max(global_max, M[i % 2]);
        }
        
        return global_max;
    }
}
