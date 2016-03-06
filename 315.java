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
