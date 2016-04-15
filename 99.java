https://leetcode.com/problems/recover-binary-search-tree/

// LeetCode 上有遍历一遍的方法
public class Solution {
    private TreeNode firstNode = null;
    private TreeNode secondNode = null;
    private TreeNode prevNode = null;
    
    public void recoverTree(TreeNode root) {
        prevNode = new TreeNode(Integer.MIN_VALUE);
        inorderTrav(root);
    
       // swap
        int temp = firstNode.val;
        firstNode.val = secondNode.val;
        secondNode.val = temp;
    }
    
    public void inorderTrav(TreeNode cur) {
        if (cur == null) {
        	return;
        }
        
        inorderTrav(cur.left);
        
        // find the first inversion pair
        if (firstNode == null && cur.val < prevNode.val) {
        	firstNode = prevNode;
        }

        // find the last inversion pair
        if (cur.val < prevNode.val) {
        	secondNode = cur;
        }
        prevNode = cur;
        
        inorderTrav(cur.right);
    }
}
