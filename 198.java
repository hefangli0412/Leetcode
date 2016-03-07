// 198. House Robber
// https://leetcode.com/problems/house-robber/

public class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        // For the first house
        int last = nums[0]; // we rob the current house
        int nonLast = 0; // we don't
        
        // For the second house and on
        for (int i = 1; i < nums.length; i++) {
            int max = Math.max(last, nonLast);
            last = nonLast + nums[i];
            nonLast = max;
        }
        
        return Math.max(last, nonLast);
    }
}
