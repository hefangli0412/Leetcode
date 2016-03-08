// 239. Sliding Window Maximum
// https://leetcode.com/problems/sliding-window-maximum/

/* 
Could you solve it in linear time?

Hint:

How about using a data structure such as deque (double-ended queue)?
The queue size need not be the same as the window’s size.
Remove redundant elements and the queue should store only elements that need to be considered.
*/

public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return new int[0];
        }
        
        int len = nums.length;
        int n = len - k + 1;
        
        int[] res = new int[n];
        // remove at the head and append at the end
        // remove first and append later
        
        // construct deque
        Deque<Integer> dq = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            // remove first
            if (i >= k) {
                if (nums[i - k] == dq.peekFirst()) {
                    dq.pollFirst();
                }
            }
            
            // remove last
            while (!dq.isEmpty() && dq.peekLast() < nums[i]) {
                dq.pollLast();
            }
            dq.offerLast(nums[i]);
            
            // record
            if (i >= k - 1) {
                res[i - k + 1] = dq.peekFirst();
            }
        }
        
        return res;
    }
}
