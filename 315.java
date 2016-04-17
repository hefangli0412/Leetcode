// 315. Count of Smaller Numbers After Self
// https://leetcode.com/problems/count-of-smaller-numbers-after-self/

/* System.arraycopy(Object src, int srcPos, Object dest, int destPos, int length) */

// Solution 1 : merge sort
public static List<Integer> countSmaller(int[] nums) {
    List<Integer> result = new ArrayList<Integer>();
    if (nums == null || nums.length == 0) return result;
    
    int len = nums.length;
    
    int[] index = new int[len];
    for (int i = 0; i < len; i++) {
        index[i] = i;
    }
    
    int[] count = new int[len];
    
    int[] helper = new int[len];
    int[] countHelper = new int[len];
    int[] indexHelper = new int[len];
    
    mergesort(nums, count, index, 0, len - 1, helper, countHelper, indexHelper);
    
    // map of <index, count>
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    for (int i = 0; i < len; i++) {
        map.put(index[i], count[i]);
    }
    
    for (int i = 0; i < len; i++) {
        result.add(map.get(i));
    }
    
    return result;
}

private static void mergesort(int[] nums, int[] count, int[] index, int left, int right, int[] helper, int[] countHelper, int[] indexHelper) {
    if (left >= right) return;
    
    int mid = left + (right - left) / 2;
    mergesort(nums, count, index, left, mid, helper, countHelper, indexHelper);
    mergesort(nums, count, index, mid + 1, right, helper, countHelper, indexHelper);
    
    merge(nums, count, index, left, mid, right, helper, countHelper, indexHelper);
}

private static void merge(int[] nums, int[] count, int[] index, int left, int mid, int right, int[] helper, int[] countHelper, int[] indexHelper) {
    System.arraycopy(nums, left, numsHelper, left, right - left + 1);
    System.arraycopy(count, left, countHelper, left, right - left + 1);
    System.arraycopy(index, left, indexHelper, left, right - left + 1);
    
    int l = left;
    int r = mid + 1;
    int cur = left;
    while (l <= mid && r <= right) {
        if (helper[l] <= helper[r]) {
            nums[cur] = helper[l];
            count[cur] = countHelper[l] + r - mid - 1; // here count the inversions
            index[cur] = indexHelper[l];
            cur++; l++;
        } else {
            nums[cur] = helper[r];
            count[cur] = countHelper[r];
            index[cur] = indexHelper[r];
            cur++; r++;
        }
    }
    
    while (l <= mid) {
        nums[cur] = helper[l];
        count[cur] = countHelper[l] + r - mid - 1;
        index[cur] = indexHelper[l];
        cur++; l++;
    }
}

// solution 2: augmented BST

public class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();
        
        if (nums == null || nums.length < 1) {
            return res;
        }
        
        // step 1: construct
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);
        TreeNode root = constructBST(sortedNums, 0, nums.length - 1);
        
        // step 2: traverse from right to left
        for (int i = nums.length - 1; i >= 0; i--) {
            res.add(insert(root, nums[i]));
        }
        
        Collections.reverse(res);
        
        return res;
    }
    
    // constructed balanced augmented binary seatch tree
    private TreeNode constructBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        } else if (left == right) {
            return new TreeNode(nums[left]);
        }
        
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = constructBST(nums, left, mid - 1);
        root.right = constructBST(nums, mid + 1, right);
        
        return root;
    }
    
    // insert and find numSmaller
    private int insert(TreeNode root, int target) {
    	int numSmaller = 0;
    	while (root.val != target) {
    		if (root.val < target) {
    			numSmaller += root.numLeft + root.count;
    			root = root.right;
    		} else {
    			root.numLeft++;
    			root = root.left;
    		}
    	}
    	root.count++;
    	return numSmaller + root.numLeft;
    }
    
    // Augmented BST
    class TreeNode {
        int val;
        int numLeft;
        int count;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
            numLeft = 0;
            count = 0;
            left = null;
            right = null;
        }
    }
}
