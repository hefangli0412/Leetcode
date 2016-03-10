// 325. Maximum Size Subarray Sum Equals k
// https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/

/* The original idea need to select two indexes, but since the sum is fixed, we can
utilize hashtable to store and retrieve sums in constant time.

The improved idea is O(n).

  sum - k        sum
------->|--------->|
              k
*/

public class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        
        int max = 0;
        int sum = 0;
        // <sum_so_far, index>
        Map<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum == k) {
                max = i + 1;
            } else if (map.containsKey(sum - k)) {
                max = Math.max(max, i - map.get(sum - k));
            }
            // do not overwrite, since the previous value
            // is the correct candidate !!!
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        
        return max;
    }
}
