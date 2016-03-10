// 209. Minimum Size Subarray Sum
// https://leetcode.com/problems/minimum-size-subarray-sum/

/* Sliding Window
Be careful, update the length before modify the sum.
*/

public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int len = Integer.MAX_VALUE;
        
        int left = 0; // inclusive
        int right = 0; // inclusive
        int sum = nums[0];
        
        while (right < nums.length && left <= right) {
            if (sum >= s) {
                // after checking, first update length, then move and modify sum
                if (left == right) return 1;
                len = Math.min(len, right - left + 1);
                sum -= nums[left++];
            } else {
                if (right == nums.length - 1) break;
                sum += nums[++right];
            }
        }
        
        return len == Integer.MAX_VALUE ? 0 : len;
    }
}
