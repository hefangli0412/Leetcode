// https://leetcode.com/problems/single-number-iii/

/* Given an array of numbers nums, in which exactly two elements appear only once and 
all the other elements appear exactly twice. Find the two elements that appear only once.
*/

public class Solution {
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        int bit = xor & (-xor);
        
        int[] result = new int[2];
        for (int num : nums) {
            if ((num & bit) != 0) {
                result[0] ^= num;
            } else {
                result[1] ^= num;
            }
        }
        return result;
    }
}
