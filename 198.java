// 198. House Robber
// https://leetcode.com/problems/house-robber/

public class Solution {
    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        
        int[] M = new int[3];
        M[0] = nums[0];
        M[1] = Math.max(nums[0], nums[1]);
        
        for (int i = 2; i < nums.length; i++) {
            M[i % 3] = Math.max(M[(i - 1) % 3], M[(i - 2) % 3] + nums[i]);
        }
        
        return M[(nums.length - 1) % 3];
    }
}
