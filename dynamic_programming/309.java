// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/

public class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int[] M = new int[prices.length];
        for (int i = 1; i < prices.length; i++) {
            // case 1: day i not included
            M[i] = M[i - 1];
            
            // case 2: buy at day 0, sell at day i
            M[i] = Math.max(M[i], prices[i] - prices[0]);
            
            // case 3: buy at day 1, sell at day i
            M[i] = Math.max(M[i], prices[i] - prices[1]);
            
            // case 4: other cases
            for (int j = 2; j < i; j++) {
                M[i] = Math.max(M[i], M[j - 2] + prices[i] - prices[j]);
            }
        }
        return M[prices.length - 1];
    }
}
