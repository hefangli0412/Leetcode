// 213. House Robber II
// https://leetcode.com/problems/house-robber-ii/

/* This problem is a little tricky at first glance. However, if you have finished the House Robber problem, 
this problem can simply be decomposed into two House Robber problems. Suppose there are n houses, 
since house 0 and n - 1 are now neighbors, we cannot rob them together and thus the solution is now the maximum of

Rob houses 0 to n - 2;
Rob houses 1 to n - 1.
*/

public class Solution {
    // Twice pass:
    // not rob nums[n-1]
    // not rob nums[0]
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];
        
        return Math.max(helper(nums, 0, n - 2), helper(nums, 1, n - 1));
    }
    
    // start and end are inclusive
    private int helper(int[] nums, int start, int end) {
        // For the first house
        int last = nums[start];
        int nonLast = 0;
        
        // For the second house and on
        for (int i = start + 1; i <= end; i++) {
            int max = Math.max(last, nonLast);
            last = nonLast + nums[i];
            nonLast = max;
        }
        
        return Math.max(last, nonLast);
    }
}
