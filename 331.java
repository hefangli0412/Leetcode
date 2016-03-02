// 331. Verify Preorder Serialization of a Binary Tree
// https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/

/* 通过观察上图中二叉树我们可以发现，一颗合法的二叉树去掉某个叶子节点后仍是合法的二叉树。
在给出的字符序列中，叶子节点有很明显的特征，即叶子节点之后一定紧跟两个空节点。通过不断的
把number,#,#的子串缩成空节点#(把number,#,#子串替换成#)，如果最后字符序列可以缩短到只有
一个字符#，那它就是我们要找的合法的先序遍历了。
*/

public class Solution {
    public boolean isValidSerialization(String preorder) {
        if (preorder == null || preorder.length() == 0) return true;
        
        String[] strs = preorder.split(","); // "9,#,92,#,#"
        Deque<String> stack = new LinkedList<>();
        
        for (String str : strs) {
            if (stack.size() < 2 || !str.equals("#") || !stack.peek().equals("#")) {
                stack.push(str);
            } else {
                while (stack.size() >= 2 && stack.peek().equals("#")) {
                    stack.pop();
                    if (stack.peek().equals("#")) return false; // three continuous '#'
                    stack.pop();
                }
                stack.push("#");
            }
        }
        
        return stack.size() == 1 && stack.peek().equals("#");
    }
}
