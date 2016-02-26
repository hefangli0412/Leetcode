// 61. Rotate List
// https://leetcode.com/problems/rotate-list/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;
        
        k = k % getLength(head);
        
        if (k == 0) return head; // guarantee that newHead is not null
        
        ListNode slow = head;
        ListNode fast = slow;
        for (int i = 0; i < k; i++) {
            fast = fast.next;
        }
        
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        
        ListNode newHead = slow.next;
        slow.next = null;
        fast.next = head;
        
        return newHead;
    }
    
    private int getLength(ListNode head) {
        int count = 0;
        
        while (head != null) {
            count++;
            head = head.next;
        }
        
        return count;
    }
}
