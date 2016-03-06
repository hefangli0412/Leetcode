// 4. Median of Two Sorted Arrays
// https://leetcode.com/problems/median-of-two-sorted-arrays/

/* Which element to find in findMedianSortedArrays? 1-based

3 -> 2th          3/2 = 1

4 -> 2th, 3th     4/2 = 2

Which index to compare in helper? 0-based

3 -> 0            3/2 = 1

4 -> 1            4/2 = 2

What if one of the array is shorter than n?

Simply delete k/2 elements in the longer array.

*/

public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        
        if ((m + n) % 2 != 0) {
            return (double)helper(nums1, m, 0, nums2, n, 0, (m + n) / 2 + 1);
        } else {
            return 0.5 * (helper(nums1, m, 0, nums2, n, 0, (m + n) / 2) 
                + helper(nums1, m, 0, nums2, n, 0, (m + n) / 2 + 1));
        }
    }
    
    // find the nth (1-based)
    private int helper(int[] nums1, int m, int start1, int[] nums2, int n, int start2, int k) {
        if (start1 >= m) return nums2[start2 + k - 1];
        if (start2 >= n) return nums1[start1 + k - 1];
        if (k == 1) return Math.min(nums1[start1], nums2[start2]);
        
        int mid1 = (start1 + k / 2 - 1 < m) ? nums1[start1 + k / 2 - 1] : Integer.MAX_VALUE;
        int mid2 = (start2 + k / 2 - 1 < n) ? nums2[start2 + k / 2 - 1] : Integer.MAX_VALUE;
        if (mid1 < mid2) {
            return helper(nums1, m, start1 + k / 2, nums2, n, start2, k - k / 2);
        } else {
            return helper(nums1, m, start1, nums2, n, start2 + k / 2, k - k / 2);
        }
    }
}
