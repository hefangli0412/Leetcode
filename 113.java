// 113. Path Sum II
// https://leetcode.com/problems/path-sum-ii/

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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        List<Integer> prefixList = new ArrayList<>();
        dfs(result, root, prefixList, 0, sum);
        
        return result;
    }
    
    private void dfs(List<List<Integer>> result, TreeNode root, 
        List<Integer> prefixList, int curSum, int target) {
        if (root.left == null && root.right == null) {
            prefixList.add(root.val);
            if (curSum + root.val == target) {
                result.add(new ArrayList(prefixList));
            }
            prefixList.remove(prefixList.size() - 1);
            return;
        }
        
        // case 1 : go left
        if (root.left != null) {
            prefixList.add(root.val);
            dfs(result, root.left, prefixList, curSum + root.val, target);
            prefixList.remove(prefixList.size() - 1);
        }
        
        // case 1 : go right
        if (root.right != null) {
            prefixList.add(root.val);
            dfs(result, root.right, prefixList, curSum + root.val, target);
            prefixList.remove(prefixList.size() - 1);
        }
    }
}
