// 274. H-Index
// https://leetcode.com/problems/h-index/

public class Solution {
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        
        return quickSelect(citations, 0, citations.length - 1);
    }
    
    private int quickSelect(int[] array, int left, int right) {
        if (left > right) {
            return array.length - left;
        }
        
        int pivotIndex = partition(array, left, right);
        
        int num = array.length - pivotIndex;
        if (array[pivotIndex] == num) {
            return array.length - pivotIndex;
        } else if (array[pivotIndex] > num) {
            return quickSelect(array, left, pivotIndex - 1);
        } else {
            return quickSelect(array, pivotIndex + 1, right);
        }
    }
    
    // right must be greater than left
    private int partition(int[] array, int left, int right)
    {
        if (left == right) {
            return left;
        }
        
        int pivotIndex = left + (int) ((right - left + 1) * Math.random());
        int pivot = array[pivotIndex];
        // swap the pivot to the rightmost position
        swap(array, pivotIndex, right);

        int i = left; // the left side of i are all smaller than pivot(exclusive)
        int j = right - 1; // the right side of j are all greater than or equal to pivot(exclusive)
        while (i <= j)
        {
            if (array[i] < pivot)
            {
                i++;
            }
            else if (array[j] >= pivot)
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
