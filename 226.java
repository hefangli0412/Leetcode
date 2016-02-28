// 226. Invert Binary Tree
// https://leetcode.com/problems/invert-binary-tree/

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
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        
        TreeNode invertedLeftSubtree = invertTree(root.left);
        TreeNode invertedRightSubtree = invertTree(root.right);
        
        root.left = invertedRightSubtree;
        root.right = invertedLeftSubtree;
        
        return root;
    }
}
