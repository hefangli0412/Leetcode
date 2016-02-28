// 257. Binary Tree Paths
// https://leetcode.com/problems/binary-tree-paths/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
/* use Depth-First-Search to print all paths of the tree
  The treenode should be printed out when it has no children
  because if reaching null condition the path could be printed twice
  by both of its null children.
  Do not forget to add the last element into the helper list.
*/
public class Solution {

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        if (root == null) return result;
        
        List<Integer> helperList = new ArrayList<>();
        dfs(result, root, helperList);
        
        return result;
    }
    
    private void dfs(List<String> result, TreeNode root, List<Integer> helperList) {
        if (root.left == null && root.right == null) {
            helperList.add(root.val);
            
            StringBuilder sb = new StringBuilder();
            for (Integer num : helperList) {
                sb.append(Integer.toString(num) + "->");
            }
            sb.delete(sb.length() - 2, sb.length());
            
            result.add(sb.toString());
            
            helperList.remove(helperList.size() - 1);
            return;
        }
        
        // case 1 : go left
        if (root.left != null) {
            helperList.add(root.val);
            dfs(result, root.left, helperList);
            helperList.remove(helperList.size() - 1);
        }
        
        // case 1 : go right
        if (root.right != null) {
            helperList.add(root.val);
            dfs(result, root.right, helperList);
            helperList.remove(helperList.size() - 1);
        } 
    }
}
