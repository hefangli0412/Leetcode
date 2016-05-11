// 303. Range Sum Query - Immutable
// https://leetcode.com/problems/range-sum-query-immutable/

public class NumArray {
    
    // prefixSum[i] denotes the sum of numbers 1, 2, ..., i - 1
    int[] prefixSum;
    
    public NumArray(int[] nums) {
        prefixSum = new int[nums.length + 1];
        for (int i = 1; i <= nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        return prefixSum[j + 1] - prefixSum[i];
    }
}


// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.sumRange(1, 2);
