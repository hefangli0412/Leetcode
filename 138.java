// 138. Copy List with Random Pointer
// https://leetcode.com/problems/copy-list-with-random-pointer/

/* Use iterative way first if possible, it will use less space. - StackOverFlow

  There are many way to solve it.
  
  1. The one teacher Sun told us as I wrote. 
  This is a general solution for copying all graphs.
  
  2. Create new nodes along the next path, and then chain along the next path.
  This uses the condition, it is a singly linked list.
  
  Both solutions need a helper hashmap to find the corresponding copied node
  */

/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;
        
        Map<Integer, RandomListNode> map = new HashMap<>();
        RandomListNode copiedHead = new RandomListNode(head.label);
        RandomListNode newHead = copiedHead;
        map.put(head.label, copiedHead);
        
        while (head != null) {
            if (head.random != null) {
                RandomListNode copiedRandom = map.get(head.random.label);
                if (copiedRandom == null) {
                    copiedRandom = new RandomListNode(head.random.label);
                    map.put(head.random.label, copiedRandom);
                }
                copiedHead.random = copiedRandom;
            }
            if (head.next != null) {
                RandomListNode copiedNext = map.get(head.next.label);
                if (copiedNext == null) {
                    copiedNext = new RandomListNode(head.next.label);
                    map.put(head.next.label, copiedNext);
                }
                copiedHead.next = copiedNext;
            }
            
            head = head.next;
            copiedHead = copiedHead.next;
        }
        
        return newHead;
    }
}

/* Solution 2 */

public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }

        final Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();

        RandomListNode cur = head;
        while(cur != null) {
            map.put(cur, new RandomListNode(cur.label));
            cur = cur.next;
        }

        for (Map.Entry<RandomListNode, RandomListNode> entry : map.entrySet()) {
            final RandomListNode newNode = entry.getValue();
            newNode.next = map.get(entry.getKey().next);
            newNode.random = map.get(entry.getKey().random);
        }

        return map.get(head);
    }
}
