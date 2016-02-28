// 124. Binary Tree Maximum Path Sum
// https://leetcode.com/problems/binary-tree-maximum-path-sum/

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
    private int globalMax;
    
    public int maxPathSum(TreeNode root) {
        globalMax = Integer.MIN_VALUE;
        if (root == null) return globalMax;
        
        dfs(root);
        
        return globalMax;
    }
    
    private int dfs(TreeNode root) {
        // base case
        if (root == null) return 0;
        
        // process the current node
        // get the max path crossing at the current node
        // update global value if necessary
        int leftSum = dfs(root.left);
        int rightSum = dfs(root.right);
        int curMax = root.val + leftSum + rightSum;
        globalMax = Math.max(globalMax, curMax);
        
        // return max path from this node to any node its subtree
        // if the path sum is smaller than 0, return 0
        return Math.max(0, root.val + Math.max(leftSum, rightSum));
    }
}
