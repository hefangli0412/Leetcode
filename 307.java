// https://leetcode.com/problems/range-sum-query-mutable/

public class NumArray {
    int[] nums; // used to get diff when update
    TreeNode root;
    
    public NumArray(int[] nums) {
        this.nums = nums.clone();
        root = constructBST(0, nums.length - 1);
        populateLeftSum();
    }

    void update(int i, int val) {
        int diff = val - nums[i];
        nums[i] = val;
        updateBST(i, diff);
    }
    
    private void updateBST(int index, int diff) {
        TreeNode cur = root;
        while (cur.index != index) {
            if (cur.index > index) {
                cur.sumLeft += diff;
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        cur.val += diff;
    }

    public int sumRange(int i, int j) {
        int sum_i = sumFromLeft(i);
        int sum_j = sumFromLeft(j);
        return sum_j + nums[j] - sum_i;
    }
     
    private int sumFromLeft(int index) {
        int sum = 0;
        TreeNode cur = root;
        while (cur.index != index) {
            if (cur.index < index) {
                sum += cur.sumLeft + cur.val;
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        return sum + cur.sumLeft;
    }
    
    // 从下往上
    private TreeNode constructBST(int left, int right) {
        if (left > right) {
            return null;
        } else if (left == right) {
            return new TreeNode(nums[left], left);
        }
        
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid], mid);
        root.left = constructBST(left, mid - 1);
        root.right = constructBST(mid + 1, right);
       
        return root;
    }
    
    // 从上往下
    private void populateLeftSum() {
    	for (int i = 0; i < nums.length; i++) {
    		TreeNode cur = root;
    		while (cur.index != i) {
    			if (cur.index > i) {
    				cur.sumLeft += nums[i];
    				cur = cur.left;
    			} else {
    				cur = cur.right;
    			}
    		}
    	}
    }
    
    class TreeNode {
        int val; 
        int sumLeft;
        int index;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val, int index) {
            this.val = val;
            sumLeft = 0;
            this.index = index;
        }
    }
}


// Your NumArray object will be instantiated and called as such:
// NumArray numArray = new NumArray(nums);
// numArray.sumRange(0, 1);
// numArray.update(1, 10);
// numArray.sumRange(1, 2);
