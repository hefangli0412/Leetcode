// 230. Kth Smallest Element in a BST
// https://leetcode.com/problems/kth-smallest-element-in-a-bst/

Follow up:
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

Hint:

Try to utilize the property of a BST.
What if you could modify the BST node's structure?
The optimal runtime complexity is O(height of BST).

Solution:

Maintain the parent pointer. Maintain the kth smallest element as a global variable.

/**********************************************************************/
// O(n) - Iterative
public class Solution {
    public int kthSmallest(TreeNode root, int k) {
        int count = 1;
        
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                TreeNode top = stack.pop(); // the left side is already processed
                
                if (count == k) {
                    return top.val;
                }
                count++;
                
                cur = top.right;
            }
        }
        
        return Integer.MIN_VALUE;
    }
}

/**********************************************************************/
// O(n) - Recursive
public class Solution {
    int tmpVal = 0;
    int count = 0;
    
    public int kthSmallest(TreeNode root, int k) {
        count = 1;
        inorderTra(root, k);
        return tmpVal;
    }
    
    private void inorderTra(TreeNode root, int k) {
        if (root == null) return;
        
        inorderTra(root.left, k);
        
        if (count == k) {
            tmpVal = root.val;
        }
        count++;
        
        inorderTra(root.right, k);
    }
}

/**********************************************************************/
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
    // O(n log n)
    public int kthSmallest(TreeNode root, int k) {
        int leftNum = getNumber(root.left);
        
        if (k == leftNum + 1) {
            return root.val;
        } else if (k < leftNum + 1) {
            return kthSmallest(root.left, k);
        } else {
            return kthSmallest(root.right, k - leftNum - 1);
        }
    }
    
    // O(n)
    private int getNumber(TreeNode root) {
        if (root == null) return 0;
        
        return 1 + getNumber(root.left) + getNumber(root.right);
    }
}
