// 173. Binary Search Tree Iterator
// https://leetcode.com/problems/binary-search-tree-iterator/

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// Note: next() and hasNext() should run in average O(1) time 
// and uses O(h) memory, where h is the height of the tree.

public class BSTIterator {
    Deque<TreeNode> stack = new LinkedList<>();
    
    public BSTIterator(TreeNode root) {
        pushAllNodes(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode popped = stack.pop();
        pushAllNodes(popped.right);
        return popped.val;
    }
    
    private void pushAllNodes(TreeNode p) {
        while (p != null) {
            stack.push(p);
            p = p.left;
        }
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
