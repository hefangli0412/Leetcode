// 116. Populating Next Right Pointers in Each Node
// https://leetcode.com/problems/populating-next-right-pointers-in-each-node/

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    /* level order traversal - 
       since only constant extra space could be used,
       queue cannot be used here
    */
    public void connect(TreeLinkNode root) {

        /* node represents the node being processed;
           root indicates the root of the current node, 
           it moves to its right on the same level;
           leftMost represents the left most node in the current level,
           we assign it to root at the start of the next level
           */
        for (TreeLinkNode leftMost = root; leftMost != null; ) {
            TreeLinkNode newLeftMost = null; 
            TreeLinkNode tail = null;
            
            for (TreeLinkNode parent = leftMost; parent != null; parent = parent.next) {
                if (parent.left != null) {
                    if (newLeftMost == null) {
                        newLeftMost = parent.left;
                        tail = newLeftMost;
                    } else {
                        tail.next = parent.left;
                        tail = tail.next;
                    }
                }
                
                if (parent.right != null) {
                    if (newLeftMost == null) {
                        newLeftMost = parent.right;
                        tail = newLeftMost;
                    } else {
                        tail.next = parent.right;
                        tail = tail.next;
                    }
                }
            }
            
            leftMost = newLeftMost;
        }
    }
}

/* previous solution */

public class Solution {
    public void connect(TreeLinkNode root) {
        // level order traversal
        if (root == null) return;
        
        Queue<TreeLinkNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeLinkNode prev = null;
            for (int i = 0; i < size; i++) {
                TreeLinkNode cur = queue.poll();
                
                // process cur
                if (prev == null) {
                    prev = cur;
                } else {
                    prev.next = cur;
                    prev = cur;
                }
                
                // process cur's children
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            prev.next = null;
        }
    }
}
