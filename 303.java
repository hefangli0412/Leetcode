// 303. Range Sum Query - Immutable
// https://leetcode.com/problems/range-sum-query-immutable/

public class NumArray {
    private int[] sums;
    
    public NumArray(int[] nums) {
        int n = nums.length;
        
        // do not forget about this corner case
        // You are calling int[0] in the next statement
        if (n == 0) {
            sums = new int[0];
            return;
        }
        
        sums = new int[n];
        sums[0] = nums[0];
        for (int i = 1; i < n; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }
    }

    public int sumRange(int i, int j) {
        if (i == 0) {
            return sums[j];
        } else {
            return sums[j] - sums[i - 1];
        }
    }
}


// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.sumRange(1, 2);
