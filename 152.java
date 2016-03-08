// 152. Maximum Product Subarray
// https://leetcode.com/problems/maximum-product-subarray/

/* The idea is simply. The product basically is calculated using 
the numbers before the current number and the numbers after the current number. 
Thus, we can scan the array twice. First, we calcuate the running product of 
the part before the current number. Second, we calculate the running product of 
the part after the current number through scanning from the end of the array.
*/

public class Solution {
    // Two pass
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        
        int n = nums.length;
        int[] res = new int[n];
        
        // from left to right
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }
        
        // from right to left
        int tmp = 1;
        for (int i = n - 2; i >= 0; i--) {
            tmp *= nums[i + 1];
            res[i] *= tmp;
        }
        
        return res;
    }
}
