// 215. Kth Largest Element in an Array
// https://leetcode.com/problems/kth-largest-element-in-an-array/

/* Rule of partition 
i j to be processed
i: the left side of i are all smaller than pivot(exclusive)
j: the right side of j are all greater than or equal to pivot(exclusive)

since i and j can only be one step apart, i lies in the right side, 
swap i and the rightmost element.

Be careful about the boundary [left, right]. Do not mess up with [0, array.length - 1]

Be consistent and let kth represents kth element in the original array.
*/

public class Solution {
    
    public int findKthLargest(int[] nums, int k)
    {
        return findKthLargest(nums, 0, nums.length - 1, k);
    }

    // k is 1-based
    // kth element in the original array
    private int findKthLargest(int[] array, int left, int right, int k)
    {
        if (left >= right) 
        {
            return array[left];
        }
        
        int pivot = partition(array, left, right);
        if (pivot == k - 1)
        {
            return array[pivot];
        }
        else if (pivot < k - 1)
        {
            return findKthLargest(array, pivot + 1, right, k);
        }
        else
        {
            return findKthLargest(array, left, pivot - 1, k);
        }
    }
    
    // right must be greater than left
    private int partition(int[] array, int left, int right)
    {
        int pivotIndex = left + (int) ((right - left + 1) * Math.random());
        int pivot = array[pivotIndex];
        // swap the pivot to the rightmost position
        swap(array, pivotIndex, right);

        int i = left; // the left side of i are all smaller than pivot(exclusive)
        int j = right - 1; // the right side of j are all greater than or equal to pivot(exclusive)
        while (i <= j)
        {
            if (array[i] > pivot)
            {
                i++;
            }
            else if (array[j] <= pivot)
            {
                j--;
            }
            else
            {
                swap(array, i++, j--);
            }
        }
        swap(array, i, right); // i is in the rightside part (>=)
        return i;
    }
    
    private void swap(int[] array, int x, int y)
    {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }
}
