// 169. Majority Element

public class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;
        int result = 0;
        
        for (int num : nums) {
            if (count == 0) {
                result = num;
                count = 1;
            } else if (num == result) {
                count++;
            } else {
                count--;
            }
        }
        
        return result;
    }
}
