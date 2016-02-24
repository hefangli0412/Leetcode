// 156. Binary Tree Upside Down
// https://leetcode.com/problems/binary-tree-upside-down/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        // base case
        if (root == null || root.left == null) return root;
        
        TreeNode newRoot = upsideDownBinaryTree(root.left);
        
        root.left.left = root.right;
        root.left.right = root;
        root.left = null; // don't forget
        root.right = null; // don't forget
        
        return newRoot;
    }
}
