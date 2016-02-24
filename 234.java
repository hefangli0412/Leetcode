
// 234. Palindrome Linked List
// https://leetcode.com/problems/palindrome-linked-list/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isPalindrome(ListNode head) {
        // reverse the second half of the linkedlist
        // compare two halves 
        // and reverse the second half back
        
        // base case
        if (head == null || head.next == null) return true;
        
        ListNode firstTail = findPreMid(head);
        ListNode secondHead = reverse(firstTail.next);
        
        boolean result = compare(head, secondHead);
        
        secondHead = reverse(secondHead);
        firstTail.next = secondHead;
        
        return result;
    }
    
    private ListNode findPreMid(ListNode head) {
        // Assumption that the head list has at least two elements
        // 1 -> 2 -> 3 -> 4       return 2
        // 1 -> 2 -> 3 -> 4 -> 5  return 2
        ListNode slow = head;
        ListNode fast = head.next;
        
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
    
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        
        return newHead;
    }
    
    private boolean compare(ListNode first, ListNode second) {
        while (first != null && second != null) {
            if (first.val != second.val) {
                return false;
            }
            
            first = first.next;
            second = second.next;
        }
        
        return true;
    }
}
