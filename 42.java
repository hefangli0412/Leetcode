// 42. Trapping Rain Water
// https://leetcode.com/problems/trapping-rain-water/

// Trapping rain water 1-d array

/* 我们要找的是左边的最大值和右边的最大值中的最小值；
two pointer相向而行，记录左边最大和右边最大；
trick是每次移动某边最大值较小的，因为这个位置的结果已经决定了 */

public int maxTrapped(int[] array) {
  if (array == null || array.length < 2) return 0;
  
  int result = 0;
  
  int leftMax = array[0];
  int rightMax = array[array.length - 1];
  int left = 0; // to be processed
  int right = array.length - 1; // to be processed
  while (left <= right) {
    if (leftMax <= rightMax) {
      if (array[left] > leftMax) {
        leftMax = array[left];
      } else {
        result += leftMax - array[left];
      }
      left++;
    } else {
      if (array[right] > rightMax) {
        rightMax = array[right];
      } else {
        result += rightMax - array[right];
      }
      right--;
    }
    
  }
  
  return result;
}
