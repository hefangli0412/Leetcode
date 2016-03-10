// 11. Container With Most Water
// https://leetcode.com/problems/container-with-most-water/

/* O(n)

Greedy or Contradiction?

The idea is : to compute area, we need to take min(height[i],height[j]) as our height. 
Thus if height[i]<height[j], then the expression min(height[i],height[j]) will always 
lead to at maximum height[i] for all other j(i being fixed), hence no point checking them. 
Similarly when height[i]>height[j] then all the other i's can be ignored for that particular j.
*/

public class Solution {
    public int maxArea(int[] height) {
        if (height == null || height.length == 0) return 0;
        
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            max = Math.max(max, (right - left) * Math.min(height[left], height[right]));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        
        return max;
    }
}
