// 110. Balanced Binary Tree
// https://leetcode.com/problems/balanced-binary-tree/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 /* Time complexity is the same as getHeight and is O(n) */
 
public class Solution {
    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }
    
    private int getHeight(TreeNode root) {
        if (root == null) return 0;
        
        int leftHeight = getHeight(root.left);
        if (leftHeight == -1) return -1;
        
        int rightHeight = getHeight(root.right);
        if (rightHeight == -1) return -1;
        
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            return 1 + Math.max(leftHeight, rightHeight);
        }
    }
}
