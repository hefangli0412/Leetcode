// 98. Validate Binary Search Tree
// https://leetcode.com/problems/validate-binary-search-tree/

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
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }
    
    private boolean isValidBST(TreeNode root, Integer min, Integer max) {
        if (root == null) return true; // base case
        
        if (max != null && root.val >= max || min != null && root.val <= min) return false;
        
        return isValidBST(root.left, min, root.val) &&  isValidBST(root.right, root.val, max);
    }
}
