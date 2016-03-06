// 53. Maximum Subarray
// https://leetcode.com/problems/maximum-subarray/

/* O(n) */

/* DP */

public class Solution {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        
        int sum_so_far = 0;
        for (int num : nums) {
            sum_so_far += num;
            max = Math.max(max, sum_so_far);
            sum_so_far = Math.max(0, sum_so_far);
        }
        
        return max;
    }
}

/* Divide and Conquer */

public class Solution {
    public int maxSubArray(int[] nums) {
        return dac(nums, 0, nums.length - 1).m;
    }
    
    private Result dac(int[] nums, int left, int right) {
        if (left == right) {
            return new Result(nums[left], nums[left], nums[left], nums[left]);
        }
        
        int mid = left + (right - left) / 2;
        Result res1 = dac(nums, left, mid);
        Result res2 = dac(nums, mid + 1, right);
        
        int l, m, r, s;
        l = Math.max(res1.l, res1.s + res2.l);
        m = Math.max(Math.max(res1.m, res2.m), res1.r + res2.l);
        r = Math.max(res2.r, res1.r + res2.s);
        s = res1.s + res2.s;
        
        return new Result(l, m, r, s);
    }
}

/*
l: the sum of the sub array with largest sum starting from the first element
m: the sum of the sub array with largest sum
r: the sum of the sub array with largest sum ending at the last element
s: the sum of the whole array
*/
class Result {
    int l, m, r, s;
    
    public Result(int l, int m, int r, int s) {
        this.l = l;
        this.m = m;
        this.r = r;
        this.s = s;
    }
};
