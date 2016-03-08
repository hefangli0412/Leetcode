// 152. Maximum Product Subarray
// https://leetcode.com/problems/maximum-product-subarray/

/* https://leetcode.com/discuss/14235/possibly-simplest-solution-with-o-n-time-complexity */

public class Solution {
    // Use DP wisely!!
    public int maxProduct(int[] nums) {
        int res = nums[0];
        int max = res;
        int min = res;
        // max/min stores the max/min product of
        // subarray that ends with the current number A[i]
    
        for (int i = 1; i < nums.length; i++) {
            // multiplied by a negative makes big number smaller, small number bigger
            // so we redefine the extremums by swapping them
            if (nums[i] < 0) {
                int tmp = max;
                max = min;
                min = tmp;
            }
            
            // max/min product for the current number is either the current number itself
            // or the max/min by the previous number times the current one
            max = Math.max(nums[i], nums[i] * max);
            min = Math.min(nums[i], nums[i] * min);
            
            // the newly computed max value is a candidate for our global result
            res = Math.max(res, max);
        }
        
        return res;
    }
}
