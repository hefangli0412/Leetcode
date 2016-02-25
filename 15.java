// 15. 3Sum
// https://leetcode.com/problems/3sum/

public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        
        if (nums == null || nums.length < 3) return result;
        
        // sort the array first
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && (nums[i] == nums[i-1])) continue; // avoid duplicates
            
            int j = i + 1;
            int k = nums.length - 1;
            
            while (j < k) {
                // while (j < k - 1 && nums[j + 1] == nums[j]) j++;
                // while (j < k - 1 && nums[k - 1] == nums[k]) k--;
                // cannot remove elements here
                // counter examples are 
                // Input : [-4,-2,1,-5,-4,-4,4,-2,0,4,0,-2,3,1,-5,0]
                // Output : [[-5,1,4],[-4,0,4],[-4,1,3],[-2,-2,4]]
                // Expected : [[-5,1,4],[-4,0,4],[-4,1,3],[-2,-2,4],[-2,1,1],[0,0,0]]
                
                if (nums[i] + nums[j] + nums[k] == 0) {
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++; k--;
                    while ((j < k) && (nums[j] == nums[j-1])) j++;// avoid duplicates
                    while ((j < k) && (nums[k] == nums[k+1])) k--;// avoid duplicates
                } else if (nums[i] + nums[j] + nums[k] < 0) {
                    j++;
                    while ((j < k) && (nums[j] == nums[j-1])) j++;// avoid duplicates
                } else {
                    k--;
                    while ((j < k) && (nums[k] == nums[k+1])) k--;// avoid duplicates
                }
            }
        }
        
        return result;
    }
}
