// 298. Binary Tree Longest Consecutive Sequence
// https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/

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
    private int max;
    
    public int longestConsecutive(TreeNode root) {
        max = 0;
        
        dfs(root, 0, null);
        
        return max;
    }
    
    private void dfs(TreeNode root, int curMax, Integer parentVal) {
        if (root == null) return;
        
        if (parentVal == null || root.val == parentVal + 1) {
            curMax++;
            max = Math.max(max, curMax);
        } else {
            curMax = 1;
        }
        
        dfs(root.left, curMax, root.val);
        dfs(root.right, curMax, root.val);
    }
}
