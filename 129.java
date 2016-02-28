// 129. Sum Root to Leaf Numbers
// https://leetcode.com/problems/sum-root-to-leaf-numbers/

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
    private int sum;
    
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;

        sum = 0;
        List<Integer> prefixList = new ArrayList<>();
        dfs(root, prefixList);
        
        return sum;
    }
    
    private void dfs(TreeNode root, List<Integer> prefixList) {
        if (root.left == null && root.right == null) {
            prefixList.add(root.val);
            sum += getValue(prefixList);
            prefixList.remove(prefixList.size() - 1);
            return;
        }
        
        // case 1 : go left
        if (root.left != null) {
            prefixList.add(root.val);
            dfs(root.left, prefixList);
            prefixList.remove(prefixList.size() - 1);
        }
        
        // case 1 : go right
        if (root.right != null) {
            prefixList.add(root.val);
            dfs(root.right, prefixList);
            prefixList.remove(prefixList.size() - 1);
        }
    }
    
    private int getValue(List<Integer> prefixList) {
        int value = 0;
        for (Integer num : prefixList) {
            value = value * 10 + num;
        }
        return value;
    }
}
