// 153. Find Minimum in Rotated Sorted Array My Submissions Question
// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/

public class Solution {
    public int findMin(int[] nums) {
        // Assume that nums has at least one element
        return findMinHelper(nums, 0, nums.length - 1);
    }
    
    private int findMinHelper(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        } else if (left + 1 == right) {
            return Math.min(nums[left], nums[right]);
        } else {
            int mid = left + (right - left) / 2;
            if (nums[left] > nums[mid]) {
                return findMinHelper(nums, left + 1, mid);
            } else if (nums[mid] > nums[right]) {
                return findMinHelper(nums, mid + 1, right);
            } else {
                return nums[left];
            }
        }
    }
}
