// 154. Find Minimum in Rotated Sorted Array II
// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/

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
                int min1 = findMinHelper(nums, left, mid);
                int min2 = findMinHelper(nums, mid + 1, right);
                return Math.min(min1, min2);
            }
        }
    }
}

public class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid;
            } else if (nums[mid] < nums[right]){
                right = mid;
            } else {
                right--;
            }
        }
        return Math.min(nums[left], nums[right]);
    }
}
