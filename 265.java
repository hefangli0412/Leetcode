// 265. Paint House II
// https://leetcode.com/problems/paint-house-ii/

/* Follow up:
  Could you solve it in O(nk) runtime?
*/

public class Solution {
    public int minCostII(int[][] costs) {
        int n = costs.length;
        if (n == 0) return 0;
        int k = costs[0].length;
        
        int min1 = 0; // min1 is the index of the 1st-smallest cost till previous house
        int min2 = 0; // min2 is the index of the 2nd-smallest cost till previous house
        int idMin1 = -1;

        for (int i = 0; i < n; i++) {
            int m1 = Integer.MAX_VALUE;
            int m2 = Integer.MAX_VALUE;
            int id = -1;
            
            for (int j = 0; j < k; j++) {
                // If same color as j - 1, we can only extend from 2nd min of j - 1
                int cost = costs[i][j] + (j == idMin1 ? min2 : min1);
                
                // Update m1 m2 if cost is smaller than any of them
                if (cost < m1) {
                    m2 = m1;
                    m1 = cost;
                    id = j;
                } else if (cost < m2) {
                    m2 = cost;
                }
            }
            min1 = m1; min2 = m2; idMin1 = id;
        }
        
        return min1;
    }
}
