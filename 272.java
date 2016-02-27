// 272. Closest Binary Search Tree Value II
// https://leetcode.com/problems/closest-binary-search-tree-value-ii/

/* Following the hint, I use a predecessor stack and successor stack. 
I do a logn traversal to initialize them until I reach the null node. 
Then I use the getPredecessor and getSuccessor method to pop k closest 
nodes and update the stacks.

Time complexity is O(klogn), since k BST traversals are needed and each 
is bounded by O(logn) time. Note that it is not O(logn + k) which is 
the time complexity for k closest numbers in a linear array.

Space complexity is O(klogn), since each traversal brings O(logn) new 
nodes to the stack.
*/


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
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> result = new ArrayList<>();
        if (k == 0) return result;
        
        // step 1 : populate predecessor and successor stacks
        Deque<TreeNode> precStack = new LinkedList<>();
        Deque<TreeNode> succStack = new LinkedList<>();
        
        TreeNode cur = root;
        while (cur != null) {
            if (target == cur.val) {
                result.add(cur.val);
                k--;
                precStack.push(cur);
                getPredecessor(precStack);
                succStack.push(cur);
                getSuccessor(succStack);
                break;
            } else if (target < cur.val) {
                succStack.push(cur);
                cur = cur.left;
            } else {
                precStack.push(cur);
                cur = cur.right;
            }
        }
        
        while (k > 0) {
            if (succStack.isEmpty() && precStack.isEmpty()) {
                break;
            } else if (succStack.isEmpty()) {
                result.add(getPredecessor(precStack).val);
            } else if (precStack.isEmpty()) {
                result.add(getSuccessor(succStack).val);
            } else if (Math.abs(precStack.peek().val - target) < Math.abs(succStack.peek().val - target)) {
                result.add(getPredecessor(precStack).val);
            } else {
                result.add(getSuccessor(succStack).val);
            }
            
            k--;
        }
        
        return result;
    }
    
    private TreeNode getPredecessor(Deque<TreeNode> precStack) {
        TreeNode top = precStack.pop();
        TreeNode cur = top.left;
        while (cur != null) {
            precStack.push(cur);
            cur = cur.right;
        }
        
        return top;
    }
    
    private TreeNode getSuccessor(Deque<TreeNode> succStack) {
        TreeNode top = succStack.pop();
        TreeNode cur = top.right;
        while (cur != null) {
            succStack.push(cur);
            cur = cur.left;
        }
        
        return top;
    }
}
