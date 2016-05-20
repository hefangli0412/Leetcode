// https://leetcode.com/problems/subsets/

public class Solution {

	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();

		Arrays.sort(nums);

		int n = nums.length;
		// get 2's power
		int max = (1 << n) - 1;

		for (int i = 0; i <= max; i++) {
			List<Integer> subset = new ArrayList<>();
			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) != 0) {
					subset.add(nums[j]);
				}
			}
			result.add(subset);
		}
		return result;
	}
	
}
