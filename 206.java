// 206. Reverse Linked List
// https://leetcode.com/problems/reverse-linked-list/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    // 1. iterative
    // public ListNode reverseList(ListNode head) {
    //     ListNode prev = null;
    //     ListNode cur = head;
    //     while (cur != null) {
    //         ListNode next = cur.next;
            
    //         cur.next = prev;
            
    //         prev = cur;
    //         cur = next;
    //     }
        
    //     return prev;
    // }
    
    // 2. recursive
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        
        return newHead;
    }
}
