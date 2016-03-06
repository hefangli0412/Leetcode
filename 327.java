// 327. Count of Range Sum
// https://leetcode.com/problems/count-of-range-sum/

/* The preprocessed sum array should be of type Long.

  Given the sum array, the order of elements do not matter any more.
  
  refer to https://leetcode.com/discuss/79907/summary-divide-conquer-based-binary-indexed-based-solutions
*/

/* O(n^2) - native solution */

public class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] sums = new long[n + 1];
        for (int i = 0; i < n; ++i)
            sums[i + 1] = sums[i] + nums[i];
            
        int count = 0;
        for (int i = 0; i < n; ++i)
            for (int j = i + 1; j <= n; ++j)
                if (sums[j] - sums[i] >= lower && sums[j] - sums[i] <= upper)
                    count++;
        return count;
    }
}

/* O(n log n) - merge sort */

public class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        int len = nums.length;
        
        long[] sums = new long[len + 1];
        for (int i = 1; i <= len; ++i) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        long[] helper = new long[len + 1];
        
        return mergesort(sums, 0, len, lower, upper, helper);
    }
    
    private int mergesort(long[] sums, int left, int right, int lower, int upper, long[] helper) {
        if (left >= right) return 0; // base case
        
        int mid = (left + right) / 2;
        int count = mergesort(sums, left, mid, lower, upper, helper) 
                  + mergesort(sums, mid + 1, right, lower, upper, helper);
        
        System.arraycopy(sums, left, helper, left, right - left + 1);
        
        int l = left;
        int r = mid + 1;
        int cur = left;
        int lowerBound = mid + 1; // exclusive
        int upperBound = mid + 1; // inclusive
    
        // O(n)
        while (l <= mid) {
            while (lowerBound <= right && helper[lowerBound] - helper[l] < lower) lowerBound++;
            while (upperBound <= right && helper[upperBound] - helper[l] <= upper) upperBound++;
            count += upperBound - lowerBound;
    
            while (r <= right && helper[r] < helper[l]) sums[cur++] = helper[r++];
            sums[cur++] = helper[l++];
        }
    
        while (r <= right) sums[cur++] = helper[r++]; 
        
        return count;
    }
}
