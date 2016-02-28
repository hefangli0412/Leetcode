// 112. Path Sum
// https://leetcode.com/problems/path-sum/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 /* No need to record path or maintain a current list or variable
 pass the current sum as the input parameter is enough.
 */
 
public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        
        return dfs(root, 0, sum);
    }
    
    private boolean dfs(TreeNode root, int curSum, int target) {
        if (root == null) return false;
        
        if (root.left == null && root.right == null && curSum + root.val == target) return true; 
        
        return dfs(root.left, curSum + root.val, target) || dfs(root.right, curSum + root.val, target);
    }
}
