// 1. Two Sum
// https://leetcode.com/problems/two-sum/

public class Solution {
    // 1. HashMap - O(n)
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) {
            return new int[0];
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {i, map.get(target - nums[i])};
            }
            
            map.put(nums[i], i);
        }
        
        return new int[0];
    }
    
    // 2. Sorted array with two pointers - O(n log n)
    // It is not a wise solution because the index - value relationship
    // has to be saved before sorting the array
}
