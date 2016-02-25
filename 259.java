// 259. 3Sum Smaller
// https://leetcode.com/problems/3sum-smaller/

public class Solution {

    /* Similar to 3-sum problem, we sort the array first. 
    Again, similar to 3-sum problem, we use two pointers (lo and hi) 
    to check if the sum satisfies the condition. The only trick here 
    is that if we found out
    
    nums[i] + nums[lo] + nums[hi] < target
    then for all hi in (lo, hi] satisfy the condition. That's why we have
    
    count += hi-lo;
    */

    public int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length < 3) return 0;
        
        Arrays.sort(nums);
        
        int count = 0;
        
        for (int i = 0; i < nums.length; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            
            while (j < k) {
                int tmp = nums[j] + nums[k] + nums[i];
                if (tmp < target) {
                    count += k - j;
                    j++;
                } else {
                    k--;
                }
            }
        }
        
        return count;
    }
}
