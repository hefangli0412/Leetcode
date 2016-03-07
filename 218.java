// 218. The Skyline Problem
// https://leetcode.com/problems/the-skyline-problem/

/* for position in sorted(all start points and all end points)
 if this position is a start point
        add its height
 else if this position is a end point
        delete its height
 compare current max height with previous max height, if different, 
 add current position together with this new max height to our result, 
 at the same time, update previous max height to current max height;
 */
       
public class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> result = new ArrayList<>();
    	List<int[]> heights = new ArrayList<>();
    
    	for (int[] b : buildings) {
    		heights.add(new int[]{b[0], -b[2]}); // start point has negative height value
    		heights.add(new int[]{b[1], b[2]}); // end point has normal height value
    	}
    
    	// Sort heights array, based on the first value, 
    	// if necessary, use the second to break ties
    	// (consider four consitions, the sequence is a must)
    	Collections.sort(heights, new Comparator<int[]>() {
    		public int compare(int[] a, int[] b) {
    			if (a[0] < b[0]) {
    				return -1;
    			} else if (a[0] > b[0]) {
    				return 1;
    			} else if (a[1] < b[1]) {
    				return -1;
    			} else if (a[1] > b[1]) {
    				return 1;
    			} else {
    			    return 0;
    			}
    		}
    	});
    
    	// Use a maxHeap to store possible heightss
    	Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
    
      // Provide a initial value to make pq not empty during processing
    	pq.offer(0);
    
    	int prev = 0;
    
    	for (int[] h : heights) {
    		if (h[1] < 0) { // start point, add heights
    			pq.offer(-h[1]);
    		} else { // end point, remove height
    			pq.remove(h[1]);
    		}
    
    		int cur = pq.peek(); // current max height;
    
        // compare current max height with previous max height, 
        // update result and previous max height if necessary
    		if (prev != cur) {
    			result.add(new int[]{h[0], cur});
    			prev = cur;
    		}
    	}
    
    	return result;
    }
}
