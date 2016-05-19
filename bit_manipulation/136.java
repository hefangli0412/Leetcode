// Given an array of integers, every element appears twice except for one. Find that single one.

/* Solution 1 - xor */

public class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }
}

/* Solution 2 - count bits */

public class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;
		for (int i = 0; i < 32; i++) {
			int cnt = 0;
			for (int num : nums) {
				if ((num & (1 << i)) != 0) {
					cnt++;
				}
			}
			if (cnt % 2 == 1) {
				result |= (1 << i);
			}
		}
		return result;
    }
}
