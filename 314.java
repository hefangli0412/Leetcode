// 314. Binary Tree Vertical Order Traversal
// https://leetcode.com/problems/binary-tree-vertical-order-traversal/

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
    /* variation of level order traversal, the nodeQueue stores tree nodes
    and indexQueue stores the corresponding index
    the map use index as key, correstponding elements from top to bottom as value.
    */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        int min = 0;
        int max = 0;
        
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> indexQueue = new LinkedList<>();
        nodeQueue.offer(root);
        indexQueue.offer(0);
        
        while (!nodeQueue.isEmpty()) {
            TreeNode curNode = nodeQueue.poll();
            int curIndex = indexQueue.poll();
            
            List<Integer> curList = map.get(curIndex);
            if (curList == null) {
                curList = new ArrayList<Integer>();
                map.put(curIndex, curList);
                
                min = Math.min(min, curIndex);
                max = Math.max(max, curIndex);
            }
            
            curList.add(curNode.val);
            
            if (curNode.left != null) {
                nodeQueue.offer(curNode.left);
                indexQueue.offer(curIndex - 1);
            }
            
            if (curNode.right != null) {
                nodeQueue.offer(curNode.right);
                indexQueue.offer(curIndex + 1);
            }
        }
        
        List<List<Integer>> result = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            result.add(map.get(i));
        }
        
        return result;
    }
}
