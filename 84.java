// 84. Largest Rectangle in Histogram

public class Solution {
    // brutal force ?
    // for each element in the array, find the nearest smaller elements on both left and right sides
    // then how to optimize it ? 
    // for one side, we can utilize "next taller person" to find the nearest smaller element and maitain an increasing stack
    // for the other side, we may look at this problem from a different perspective - 
    // the boundaries are always lower than middle elements so we only update global max when we meet a smaller element
    //
    // to the right side of an element in the stack, elements not in the stack but in the original array are greater than 
    // that element according to “next smaller element”
    
    public int largestRectangleArea(int[] heights) {
        int max = 0;
        
        int[] helperHeights = new int[heights.length + 2];
        for (int i = 1; i < helperHeights.length - 1; i++) {
            helperHeights[i] = heights[i - 1];
        }
        helperHeights[0] = 0; // add 0 height element to the leftmost
        helperHeights[helperHeights.length - 1] = 0; // add 0 height element to the rightmost
        
        // increasing stack, record index
        Deque<Integer> stack = new LinkedList<>();
        stack.push(0);
        for (int i = 1; i < helperHeights.length; i++) {
            while (helperHeights[i] < helperHeights[stack.peek()]) {
                int higherHeight = helperHeights[stack.pop()];
                max = Math.max(max, (i - 1 - stack.peek()) * higherHeight);
            }
            
            stack.push(i);
        }
        
        return max;
    }
}
