// 297. Serialize and Deserialize Binary Tree
// https://leetcode.com/problems/serialize-and-deserialize-binary-tree/

/* Solution 1 - preorder traversal */

public class Codec {
    private static final String spliter = ",";
    private static final String NN = "X";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NN).append(spliter);
        } else {
            sb.append(node.val).append(spliter);
            buildString(node.left, sb);
            buildString(node.right,sb);
        }
    }
    
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(spliter)));
        return buildTree(nodes);
    }

    private TreeNode buildTree(Deque<String> nodes) {
        String val = nodes.remove();
        if (val.equals(NN)) return null;
        else {
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = buildTree(nodes); // tail recursion
            node.right = buildTree(nodes); // tail recursion
            return node;
        }
    }
}


/* Solution 2 - level order traversal */

/* convert String to int - int num = Integer.parseInt("10");
  convert int to String - String str = String.valueOf(10);
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
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "#";
        
        StringBuilder sb = new StringBuilder();
        
        // iterative level-order traversal
        Queue<TreeNode> q = new LinkedList<>();
        sb.append(String.valueOf(root.val) + ',');
        q.offer(root);
        
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur.left != null) {
                sb.append(String.valueOf(cur.left.val) + ',');
                q.offer(cur.left);
            } else {
                sb.append("#,");
            }
            if (cur.right != null) {
                sb.append(String.valueOf(cur.right.val) + ',');
                q.offer(cur.right);
            } else {
                sb.append("#,");
            }
        }
        
        sb.deleteCharAt(sb.length() - 1);
        
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0 || data.equals("#")) return null;
        
        String[] strs = data.split(",");
        
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(strs[0]));
        q.offer(root);
        int index = 1;
        
        while (!q.isEmpty()) {
            TreeNode parent = q.poll();
            String l = strs[index++];
            String r = strs[index++];
            if (!l.equals("#")) {
                TreeNode left = new TreeNode(Integer.parseInt(l));
                parent.left = left;
                q.offer(left);
            }
            if (!r.equals("#")) {
                TreeNode right = new TreeNode(Integer.parseInt(r));
                parent.right = right;
                q.offer(right);
            }
        }
        
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
