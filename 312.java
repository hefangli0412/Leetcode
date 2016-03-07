// 312. Burst Balloons
// https://leetcode.com/problems/burst-balloons/

/* we can find that for any balloons left the maxCoins does not depends on the balloons already bursted. 
  This indicate that we can use memorization (top down) or dynamic programming (bottom up) for all the cases 
  from small numbers of balloon until n balloons. 
  
  中心开花吗？

  O(n ^ 3)
*/

public class Solution {
    
    public int maxCoins(int[] nums) {
        int n = nums.length + 2;
        
        int[] arr = new int[n];
        for (int i = 1; i < n - 1; i++) {
            arr[i] = nums[i - 1];
        }
        arr[0] = arr[n - 1] = 1;
        
        /* M[i][j] represents the cost of bursting balloons between i and j exclusively */ 
        int[][] M = new int[n][n];
        for (int k = 2; k < n; k++) {
            for (int left = 0; left < n - k; left++) {
                int right = left + k;
                for (int i = left + 1; i < right; i++) {
                    M[left][right] = Math.max(M[left][right], arr[left] * arr[i] * arr[right] + M[left][i] + M[i][right]);
                }
            }
        }
        
        return M[0][n - 1];
    }
}
