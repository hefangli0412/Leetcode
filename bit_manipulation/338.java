// 338. Counting Bits
// 找规律

public class Solution {
    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        result[0] = 0;
        
        int len = 1;
        int slow = 0;
        int fast = 1;
        
        while (fast <= num) {
            if (slow == len) {
                slow = 0;
                len *= 2;
            }
            result[fast++] = 1 + result[slow++];
            
        }
        return result;
    }
}
