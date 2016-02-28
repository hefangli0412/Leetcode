// 114. Flatten Binary Tree to Linked List
// https://leetcode.com/problems/flatten-binary-tree-to-linked-list/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 /* Iterative */
public class Solution {
    /* variation of preorder traversal */
    public void flatten(TreeNode root) {
        if (root == null) return;
        
        TreeNode prev = null;
        
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode curNode = stack.pop();
            
            // expand its children
            if (curNode.right != null) {
                stack.push(curNode.right);
                curNode.right = null;
            }
            
            if (curNode.left != null) {
                stack.push(curNode.left);
                curNode.left = null;
            }
            
            // process the current node
            if (prev != null) {
                prev.right = curNode;
                // curNode.left = prev; // Note that the result is a singly linked list
            }
            
            prev = curNode;
        }
    }
}

/* Recursive */
public class Solution {
    /* variation of preorder traversal */
    TreeNode prev = null;
    
    public void flatten(TreeNode root) {
        if (root == null) return;
        
        prev = null;
        
        preorderTra(root);
    }
    
    private void preorderTra(TreeNode root) {
        if (root == null) return;
        
        TreeNode left = root.left;
        TreeNode right = root.right;
        
        root.left = null;
        root.right = null;
        
        if (prev != null) {
            prev.right = root;
        }
        
        prev = root;
        
        preorderTra(left);
        preorderTra(right);
    }
}
