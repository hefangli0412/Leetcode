// 42. Trapping Rain Water
// https://leetcode.com/problems/trapping-rain-water/

public class Solution {
    public int trap(int[] height) {
        if (height == null || height.length <= 1) return 0;
        
        int res = 0;
        int left = 0;
        int right = height.length - 1;
        int left_max = 0;
        int right_max = 0;
        
        while (left <= right) {
            // calculate 
            // update : move the shorter
            if (height[left] < height[right]) {
                if (height[left] < left_max) {
                    res += left_max - height[left];
                } else {
                    left_max = height[left];
                }
                left++;
            } else {
                if (height[right] < right_max) {
                    res += right_max - height[right];
                } else {
                    right_max = height[right];
                }
                right--;
            }
        }
        
        return res;
    }
}
