// 255. Verify Preorder Sequence in Binary Search Tree
// https://leetcode.com/problems/verify-preorder-sequence-in-binary-search-tree/

public class Solution {
    public boolean verifyPreorder(int[] preorder) {
        return verifyPreorder(preorder, 0, preorder.length - 1);
    }
    
    private boolean verifyPreorder(int[] preorder, int left, int right) {
        if (left >= right) return true; // base case
        
        int pivot = preorder[left];
        
        int i = left + 1;
        while (i <= right && preorder[i] < pivot) {
            i++;
        }
        
        int tmp = i;
        while (i <= right) {
            if (preorder[i] <= pivot) return false;
            i++;
        }
        
        return verifyPreorder(preorder, left + 1, tmp - 1) && verifyPreorder(preorder, tmp, right);
    }
}
