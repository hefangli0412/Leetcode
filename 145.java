// 145. Binary Tree Postorder Traversal
// https://leetcode.com/problems/binary-tree-postorder-traversal/

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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        
        if (root == null) return result;
        
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        
        TreeNode parent = null;
        while (!stack.isEmpty()) {
            TreeNode cur = stack.peek();
            if (parent == null || parent.left == cur || parent.right == cur) {
                if (cur.left != null) {
                    stack.push(cur.left);
                } else if (cur.right != null) {
                    stack.push(cur.right);
                } else {
                    result.add(cur.val);
                    stack.pop();
                }
            } else if (cur.left == parent) {
                if (cur.right != null) {
                    stack.push(cur.right);
                } else {
                    result.add(cur.val);
                    stack.pop();
                }
            } else {
                result.add(cur.val);
                stack.pop();
            }
            
            parent = cur;
        }
        
        return result;
    }
}
