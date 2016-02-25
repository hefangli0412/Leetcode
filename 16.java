// 16. 3Sum Closest
// https://leetcode.com/problems/3sum-closest/

public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        // Assume that nums has at least three elements
        int result = nums[0] + nums[1] + nums[2];
        
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            
            while (j < k) {
                int tmp = nums[i] + nums[j] + nums[k];
                
                if (tmp == target) {
                    return target;
                } else if (tmp < target) {
                    j++;
                } else {
                    k--;
                }
                
                if (Math.abs(tmp - target) < Math.abs(result - target)) {
                    result = tmp;
                }
            }
        }
        
        return result;
    }
}
