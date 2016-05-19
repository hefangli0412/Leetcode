// Given an array of integers, every element appears three times except for one. Find that single one.

/* Solution 1 - count bits */

public class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (int num : nums) {
                if ((num & (1 << i)) != 0) {
                    count++;
                }
            }
            if (count % 3 == 1) {
                result |= 1 << i;
            }
        }
        return result;
    }
}

/* Solution 2.1 - reset 11 -> 00 */

public class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            int lowerBit = 0, higherBit = 0;
            for (int num : nums) {
                int old_lowerBit = lowerBit;
                lowerBit ^= (num & (1 << i));
                higherBit ^= (old_lowerBit & (num & (1 << i)));
                int flip = lowerBit & higherBit;
				lowerBit ^= flip;
				higherBit ^= flip;
            }
            result |= lowerBit;
        }
        return result;
    }
}

/* Solution 2.2 - reset 11 -> 00 */

public class Solution {
    public int singleNumber(int[] nums) {
        int lowerBit = 0, higherBit = 0;
        for (int num : nums) {
            int old_lowerBit = lowerBit;
            lowerBit ^= num;
            higherBit ^= old_lowerBit & num;
            int flip = lowerBit & higherBit;
			lowerBit ^= flip;
			higherBit ^= flip;
        }
        return lowerBit;
    }
}

/* Solution 3.1 - transition state observation 
change higherBit when two bits are different
change lowerBit higherBit is zero
*/

public class Solution {
    public int singleNumber(int[] nums) {
        int lowerBit = 0, higherBit = 0;
        for (int num : nums) {
            int old_lowerBit = lowerBit;
            lowerBit ^= (~higherBit) & num;
            higherBit ^= (old_lowerBit ^ higherBit) & num;
        }
        return lowerBit;
    }
}
