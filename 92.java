// 92. Reverse Linked List II
// https://leetcode.com/problems/reverse-linked-list-ii/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // Assumption that 1 ≤ m ≤ n ≤ length of list.
        // iterative way
        if (m == n) return head;
        
        int count = 1;
        ListNode cur = head;
        ListNode prev = null;
        
        while (count < m) {
            prev = cur;
            cur = cur.next;
            
            count++;
        }
        
        ListNode frontTail = prev;
        ListNode midTail = cur;
        
        while (count <= n) {
            ListNode next = cur.next;
            
            cur.next = prev;
            
            prev = cur;
            cur = next;
            count++;
        }
        
        ListNode midHead = prev;
        ListNode endHead = cur;
        
        // dummy node can also be used here
        if (m == 1) {
            midTail.next = endHead;
            return midHead;
        } else {
            frontTail.next = midHead;
            midTail.next = endHead;
            
            return head;
        }
    }
}
